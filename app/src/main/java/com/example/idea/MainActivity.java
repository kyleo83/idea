package com.example.idea;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.idea.Controllers.CacheManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements HomeFragment.OnNextClickListener,
        ProfileFragment.OnNextClickListener, ChangePasswordFragment.OnNextClickListener,
        OwnerFragment.OnNextClickListener, AboutFragment.OnNextClickListener,
        NavigationView.OnNavigationItemSelectedListener{

    public static final String ADMIN_EMAIL = "bentunigold@gmail.com";
    public static final String ADMIN = "admin@idea.com";
    public CacheManager cacheManager;
    private SwipePlaceHolderView mSwipeView;
    private Context mContext;
    
    private String email;
    private TextView emailView;
    private Button signOut;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    //declares variables for Tags, Navigation, DrawerLayout, and Toolbar
    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    Fragment newFragment;

    boolean logout = false;

    //declares spinner dropdown
    MaterialSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);




        setCacheManager(new CacheManager(getApplicationContext()));

        //gets spinner reference
        spinner = findViewById(R.id.spinner);

        //adds tags to spinner array
        final ArrayList<String> tagList = new ArrayList<>();
        tagList.add("<SELECT DESIGNS>");
        tagList.add("ALL");
        tagList.add("kitchen");
        tagList.add("bathroom");
        tagList.add("living room");
        tagList.add("bedroom");
        tagList.add("interior");
        tagList.add("landscape");
        tagList.add("architecture");
        tagList.add("design");
        spinner.setItems(tagList);


        //adds listener for selected tag
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Toast.makeText(MainActivity.this, "Tag : " + tagList.get(position), Toast.LENGTH_SHORT).show();

                HomeFragment fragment = new HomeFragment();
                Bundle bundle = new Bundle();

                //setup fragment manager
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                //switches between spinner selections
                String tag = tagList.get(position);
                if (!tag.equalsIgnoreCase("ALL")) {
                    bundle.putString("filter_tag", tag);
                    fragment.setArguments(bundle);
                    Log.i(TAG, "Filter by " + tag);
                }

                fragmentTransaction.add(R.id.fragment_host, fragment);
                fragmentTransaction.commit();

            }
        });

        //start of fragment, navigation, and toolbar references

        //references widget for toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //add idea app icon inside the Toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.circleidea);
        //reference drawer layout
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, drawer, toolbar,
                        R.string.nav_open_drawer, R.string.nav_close_drawer
                );

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //end of fragment, navigation, and toolbar references
        
        auth = FirebaseAuth.getInstance();
        //get current user and email

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Toast.makeText(MainActivity.this, "auth state changed", Toast.LENGTH_LONG).show();
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                    //get current user and email
                } else {
                    email = user.getEmail();
                    Menu nav_Menu = navigationView.getMenu();
                    nav_Menu.findItem(R.id.nav_owner).setVisible(email.equals(ADMIN_EMAIL) || email.equals(ADMIN));
                }
            }
        };
    }

    //sign out method
    public void signOut() {
        auth.signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));

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
            /**
            case R.id.action_settings:
                Log.i(TAG, "Settings clicked");
                toast = Toast.makeText(this,
                        "Settings clicked", Toast.LENGTH_SHORT);
                toast.show();
                return true;
            **/
            case R.id.action_share:
                Log.i(TAG, "Share clicked");
                toast = Toast.makeText(this,
                        "Share clicked", Toast.LENGTH_SHORT);
                toast.show();
                //adds sharing options
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Your body here";
                String shareSub = "Your subject here";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share Image Using:"));
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    //switch cases for choosing fragments
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Toast toast;

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Log.i(TAG, "home clicked");
                toast = Toast.makeText(this,
                        "Home Fragment", Toast.LENGTH_SHORT);
                toast.show();
                newFragment = new HomeFragment();

                spinner.setVisibility(View.VISIBLE);
                break;

            case R.id.nav_about:
                Log.i(TAG, "about clicked");
                toast = Toast.makeText(this,
                        "About Fragment", Toast.LENGTH_SHORT);
                toast.show();
                newFragment = new AboutFragment();
                //removes spinner from about fragment
                spinner.setVisibility(View.GONE);
                break;

            case R.id.profile:
                Log.i(TAG, "profile");
                toast = Toast.makeText(this,
                        "profile Fragment", Toast.LENGTH_SHORT);
                toast.show();

                newFragment = new ProfileFragment();

                //removes spinner from profile fragment
                spinner.setVisibility(View.GONE);
                break;

            case R.id.change_password:
                Log.i(TAG, "change_password");
                toast = Toast.makeText(this,
                        "Change Password Fragment", Toast.LENGTH_SHORT);
                toast.show();

                newFragment = new ChangePasswordFragment();
                //remove spinner from owner fragment
                spinner.setVisibility(View.GONE);
                break;

            case R.id.nav_owner:
                Log.i(TAG, "owner");
                toast = Toast.makeText(this,
                        "Owner Fragment", Toast.LENGTH_SHORT);
                toast.show();
                
                newFragment = new OwnerFragment();
                //remove spinner from owner fragment
                spinner.setVisibility(View.GONE);
                break;

            case R.id.Signout:
                logout = true;
                Log.i(TAG, "sign out was clicked");

                toast = Toast.makeText(this,
                        "sign out", Toast.LENGTH_SHORT);
                toast.show();
                //removes spinner from logout fragment
                spinner.setVisibility(View.GONE);

//                auth.getCurrentUser().unlink(auth.getCurrentUser().getProviderId());
//                signOut();

                signOut();
                break;

            default:
                newFragment = new HomeFragment();
                //adds spinner back to home fragment
                spinner.setVisibility(View.VISIBLE);
                break;
        }
        //closes drawer
        drawer.closeDrawer(GravityCompat.START);


        if(!logout){
            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();
            Log.i(TAG, "onNavigationItemSelected: ");
            transaction.replace(R.id.fragment_host, newFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }

        return false;
    }

    @Override
    public void OnAboutFragmentNextClick(AboutFragment fragment) {

    }

    @Override
    public void OnProfileFragmentNextClick(ProfileFragment fragment) {

    }

    @Override
    public void OnOwnerFragmentNextClick(OwnerFragment fragment) {

    }

    //end of fragment selector, toolbar menu, and navigation menu selection functions




    /**
     * Gets the uid from current user through FirebaseAuth.
     * Catches NullPointerException.
     * @return String uid
     */
    public static FirebaseUser getCurrentFirebaseUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }


}

