package com.example.idea.Types;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

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

//    public static class Tags {
//        private ArrayList<String> tagList;
//
//        public Tags() {
//            this.tagList = new ArrayList<>();
//        }
//
//        void getTagsFromStore(FirebaseFirestore firestore) {
//            firestore.collection("tags").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                    if (task.isSuccessful()) {
//                        for (QueryDocumentSnapshot document : task.getResult()) {
//                            Log.d(TAG, document.getString("id") + document.getId());
//                            tagList.add(document.getId());
////                                    , document.getString("id"));
//                        }
//                        Log.d(TAG, tagList.toString());
//                    } else {
//                        Log.d(TAG, "Error getting documents: ", task.getException());
//                    }
//                }
//            });
//        }
//
//        public ArrayList<String> getTags(FirebaseFirestore db) {
//            getTagsFromStore(db);
//            return tagList;
//        }
//    }
}
