package com.example.idea;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.idea.Controllers.CacheManager;
import com.example.idea.Types.Design;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;


@Layout(R.layout.card_view)
public class DesignCard {
        @View(R.id.profileImageView)
        private ImageView profileImageView;

        @View(R.id.nameAgeTxt)
        private TextView nameAgeTxt;

        @View(R.id.locationNameTxt)
        private TextView locationNameTxt;

        private Design mdesign;
        private Context mContext;
        private SwipePlaceHolderView mSwipeView;
        private SharedPreferences sharedPreferences;

        public DesignCard(Context context, Design design, SwipePlaceHolderView swipeView) {
            mContext = context;
            mdesign = design;
            mSwipeView = swipeView;
            if (mContext == null)
                sharedPreferences = context.getSharedPreferences(CacheManager.PREF_NAME, CacheManager.PRIVATE_MODE);
        }

        @Resolve
        private void onResolved(){
            Glide.with(mContext).load(mdesign.getUrl()).into(profileImageView);
            nameAgeTxt.setText("#" + mdesign.getTag());
            locationNameTxt.setText(mdesign.getTextDescription());
        }

        @SwipeOut
        private void onSwipedOut(){
            Log.d("EVENT", "onSwipedOut");
            mSwipeView.addView(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(CacheManager.KEY_DISLIKED, mdesign.getId());
            editor.apply();
        }

        @SwipeCancelState
        private void onSwipeCancelState(){
            Log.d("EVENT", "onSwipeCancelState");
        }

        @SwipeIn
        private void onSwipeIn(){
            Log.d("EVENT", "onSwipedIn");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(CacheManager.KEY_LIKED, mdesign.getId());
            editor.apply();
        }

        @SwipeInState
        private void onSwipeInState(){
            Log.d("EVENT", "onSwipeInState");
        }

        @SwipeOutState
        private void onSwipeOutState(){
            Log.d("EVENT", "onSwipeOutState");
        }

    }

