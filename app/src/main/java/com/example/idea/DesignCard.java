package com.example.idea;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.idea.Controllers.CacheManager;
import com.example.idea.Types.Design;
import com.example.idea.Types.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

import static android.support.constraint.Constraints.TAG;


@Layout(R.layout.card_view)
public class DesignCard {
        @View(R.id.profileImageView)
        private ImageView profileImageView;

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        private String uid = firebaseAuth.getUid();
//        User user = new User();
        String user;

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




            db.collection("users")
                    .whereEqualTo("uid", uid)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    user = document.getReference().getId();
                                    Log.i(TAG, "onComplete: "+ user);
                                }
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });

            String designId = mdesign.getId();
            String tag = mdesign.getTag();
            String pictureUrl = mdesign.getPictureUrl();
            String textDescription=mdesign.getTextDescription();


            Design design = new Design(designId,  tag,  pictureUrl,  textDescription);

//
//            // Add a new document with a generated ID
//            CollectionReference collectionReference = db.collection("users")
//                    .document(user)
//                    .collection("saved")
//                    .document("");


            db.
                    collection("users")
                    .document("kZNdZDmfJWhOQ1If1EDs")
                    .collection("saved")
                    .add(design);
            Log.i(TAG, "onSwipeIn: " + design);






//                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference) {
//                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.w(TAG, "Error adding document", e);
//                        }
//                    });
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

