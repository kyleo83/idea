package com.example.idea;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class ProfileFragment extends Fragment implements View.OnClickListener{


    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private Map<String, Object> userData;
    private static final String TAG = "ProfileFragment";

    ImageView imageView;


    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        db = FirebaseFirestore.getInstance();
        // maps info to userData var for reference
        getUserInfo(db);

        


//        create reference to imageView
//        imageView = imageView.findViewById(R.id.imageView);


        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return view;

    }

    public interface OnNextClickListener {
        void OnProfileFragmentNextClick(ProfileFragment fragment);
    }

    OnNextClickListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnNextClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement OnHomeFragmentNextClick");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void onClick(View view) {
        if (listener != null) {
            listener.OnProfileFragmentNextClick(this);
        }
    }

    /**
     * Gets current logged in user's data from Firestore
     * so that we may use the data to display in their ProfileFragment.
     * @param db FirebaseFirestore
     */
    public void getUserInfo(FirebaseFirestore db) {
        String authUserUid = auth.getCurrentUser().getUid();
        DocumentReference docRef = db.collection("users").document(authUserUid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        userData = document.getData();
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}