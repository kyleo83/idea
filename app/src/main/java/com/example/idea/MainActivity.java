package com.example.idea;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.idea.Controllers.CacheManager;
import com.example.idea.Types.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

public class MainActivity extends AppCompatActivity {

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;
    private User user;
    private CacheManager cachePrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Firebase auth
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        this.user = loggedInOrGuest(mFirebaseAuth);

        mSwipeView = (SwipePlaceHolderView)findViewById(R.id.swipeView);
        mContext = getApplicationContext();

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
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
    }

    private User loggedInOrGuest(FirebaseAuth mFirebaseAuth) {
        if (cachePrefs.isLoggedIn()) {
            // call login activity
            FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
            if (mFirebaseUser != null) {
                // if User logged in, create User based on info from logged
                int userUid = Integer.parseInt(mFirebaseUser.getUid());
                String userDisplayName = mFirebaseUser.getDisplayName();
                User userCreated = new User(userUid, userDisplayName);
                cachePrefs.createLoginSession(userUid, userDisplayName);
                return userCreated;
            }
        }
        // Guest user created
        User guestCreated = new User(user.GUEST_NAME);
        cachePrefs.createLoginSession(user.GUEST_ID, user.GUEST_NAME);
        return guestCreated;
    }
}
