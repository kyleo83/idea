package com.example.idea.Types;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

public class RoomTags {

    private static final String TAG = "RoomTagDescriptor";

    private final String roomTag;

    // Describes when annotation is to be discarded
    @Retention(RetentionPolicy.SOURCE)

    // Interface for validating String types
    public @interface RoomTagDef{}

    // Marks the arg as restricted to the enumerated types
    public RoomTags(@RoomTagDef String roomTag) {
        this.roomTag = roomTag;
    }

    public static class Tags {
        private HashMap<String, String> tagList;

        public Tags() {
            this.tagList = new HashMap<>();
        }

        void getTagsFromStore(FirebaseFirestore firestore) {
            firestore.collection("tags").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getString("id") + document.getId());
                            tagList.put(document.getId(), document.getString("id"));
                        }
                        Log.d(TAG, tagList.toString());
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }
            });
        }

        public HashMap<String, String> getTags(FirebaseFirestore db) {
            getTagsFromStore(db);
            return tagList;
        }
    }
}
