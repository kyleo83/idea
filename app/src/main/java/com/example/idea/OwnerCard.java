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
import com.example.idea.Types.Saved;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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

import java.util.HashMap;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

@Layout(R.layout.card_view)
public class OwnerCard {
    @View(R.id.profileImageView)
    private ImageView profileImageView;


    FirebaseFirestore db = FirebaseFirestore.getInstance();


    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private String uid = firebaseAuth.getUid();

    String user;

    @View(R.id.nameAgeTxt)

    private TextView nameAgeTxt;

    @View(R.id.locationNameTxt)
    private TextView locationNameTxt;

    private Design mdesign;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;
    private SharedPreferences sharedPreferences;
    private String documentId;

    public OwnerCard(Context context, Design design, SwipePlaceHolderView swipeView) {
        mContext = context;
        mdesign = design;
        mSwipeView = swipeView;

        if (context != null)
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

        documentId = mdesign.getId();

        db.collection("uploads").document(documentId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
        Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn(){
        Log.d("EVENT", "onSwipedIn");


        Log.i(TAG, "onSwipeIn: 1");

//                String designId = mdesign.getId();
//                String tag = mdesign.getTag();
//                Log.i(TAG, "onSwipeIn: 2");
//
//                String pictureUrl = mdesign.getPictureUrl();
//                String textDescription= mdesign.getTextDescription();

        Map<String, Object> picture = new HashMap<>();
        picture.put("picture_url", mdesign.getPictureUrl());
        picture.put("tag_id", mdesign.getTag());

//                Log.i(TAG, "onSwipeIn: 3" +  picture("picture_url") + " / " + picture_url);

        Log.i(TAG, "onSwipeIn: 4 " + firebaseAuth.getUid());


        db.collection("pictures")
                .add(picture)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "onFailure: ");
                    }
                }).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.i(TAG, "onSuccess: ");
                // Delete from upload collection
                documentId = mdesign.getId();
                db.collection("uploads").document(documentId)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully deleted!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error deleting document", e);
                            }
                        });
            }
        });




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
