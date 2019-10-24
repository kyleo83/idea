package com.example.idea;

import android.content.Context;
import android.graphics.Point;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;


public class MainActivity extends AppCompatActivity implements HomeFragment.OnNextClickListener, NavigationView.OnNavigationItemSelectedListener{


    private SwipePlaceHolderView mSwipeView;
    private Context mContext;

    private TextView email;
    private Button signOut;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;






    //declares variables for Tags, Navigation, DrawerLayout, and Toolbar
    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        //start of fragment, navigation, and toolbar references

        //references widget for toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setup fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment fragment = new HomeFragment();
        fragmentTransaction.add(R.id.fragment_host, fragment);
        fragmentTransaction.commit();

        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, drawer, toolbar,
                        R.string.nav_open_drawer, R.string.nav_close_drawer
                );

        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //end of fragment, navigation, and toolbar references

        mSwipeView = (SwipePlaceHolderView)findViewById(R.id.swipeView);
        mContext = getApplicationContext();
        int bottomMargin = Utils.dpToPx(160);
        Point windowSize = Utils.getDisplaySize(getWindowManager());

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setViewWidth(windowSize.x)
                        .setViewHeight(windowSize.y - bottomMargin)
                        .setViewGravity(Gravity.TOP)
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.swipe_right_view)
                        .setSwipeOutMsgLayoutId(R.layout.swipe_left_view));


        for(Design design : Utils.loadDesigns(this.getApplicationContext())){
            mSwipeView.addView(new DesignCard(mContext, design, mSwipeView));
        }

        findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });




        auth = FirebaseAuth.getInstance();
        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
        signOut = (Button) findViewById(R.id.sign_out);
        email = (TextView) findViewById(R.id.email);
//        email.setText(user.getEmail());
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }
    //sign out method
    public void signOut() {
        auth.signOut();
    }
    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }









    //start of fragment selector, toolbar menu, and navigation menu selection functions

    //sets fragment selected
    public void swapFragments(Fragment fragment) {
        Fragment newFragment = null;

        if (fragment instanceof HomeFragment) {

            newFragment = new AboutFragment();

        } else {
            newFragment = new HomeFragment();
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_host, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    public void OnHomeFragmentNextClick(HomeFragment fragment) {
        swapFragments(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast toast;
        switch (item.getItemId()) {

            case R.id.action_settings:
                Log.i(TAG, "Settings clicked");
                toast = Toast.makeText(this,
                        "Settings clicked", Toast.LENGTH_SHORT);
                toast.show();
                return true;

            case R.id.action_share:
                Log.i(TAG, "Share clicked");
                toast = Toast.makeText(this,
                        "Share clicked", Toast.LENGTH_SHORT);
                toast.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    //switch cases for choosing fragments
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Toast toast;
        Fragment newFragment;

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Log.i(TAG, "home clicked");
                toast = Toast.makeText(this,
                        "Home Fragment", Toast.LENGTH_SHORT);
                toast.show();
                newFragment = new HomeFragment();
                break;

            case R.id.nav_about:
                Log.i(TAG, "about clicked");
                toast = Toast.makeText(this,
                        "About Fragment", Toast.LENGTH_SHORT);
                toast.show();
                newFragment = new AboutFragment();
                break;

            case R.id.nav_kitchen:
                Log.i(TAG, "kitchen clicked");
                toast = Toast.makeText(this,
                        "Kitchen Fragment", Toast.LENGTH_SHORT);
                toast.show();
                newFragment = new KitchenFragment();
                break;
            case R.id.nav_bathroom:
                Log.i(TAG, "bathroom clicked");
                toast = Toast.makeText(this,
                        "Bathroom Fragment", Toast.LENGTH_SHORT);
                toast.show();
                newFragment = new BathroomFragment();
                break;
            case R.id.nav_livingroom:
                Log.i(TAG, "livingroom clicked");
                toast = Toast.makeText(this,
                        "Livingroom Fragment", Toast.LENGTH_SHORT);
                toast.show();
                newFragment = new LivingroomFragment();
                break;
            case R.id.nav_bedroom:
                Log.i(TAG, "bedroom clicked");
                toast = Toast.makeText(this,
                        "Bedroom Fragment", Toast.LENGTH_SHORT);
                toast.show();
                newFragment = new BedroomFragment();
                break;
            case R.id.nav_interior:
                Log.i(TAG, "interior clicked");
                toast = Toast.makeText(this,
                        "Interior Fragment", Toast.LENGTH_SHORT);
                toast.show();
                newFragment = new InteriorFragment();
                break;
            case R.id.nav_landscape:
                Log.i(TAG, "landscape clicked");
                toast = Toast.makeText(this,
                        "Landscape Fragment", Toast.LENGTH_SHORT);
                toast.show();
                newFragment = new LandscapeFragment();
                break;
            case R.id.nav_architecture:
                Log.i(TAG, "architecture clicked");
                toast = Toast.makeText(this,
                        "Architecture Fragment", Toast.LENGTH_SHORT);
                toast.show();
                newFragment = new ArchitectureFragment();
                break;
            case R.id.nav_design:
                Log.i(TAG, "design clicked");
                toast = Toast.makeText(this,
                        "Design Fragment", Toast.LENGTH_SHORT);
                toast.show();
                newFragment = new DesignFragment();
                break;

            default:
                newFragment = new HomeFragment();
                break;
        }
        //closes drawer
        drawer.closeDrawer(GravityCompat.START);

        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_host, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();

        return false;
    }

    //end of fragment selector, toolbar menu, and navigation menu selection functions



}
