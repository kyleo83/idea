package com.example.idea.Controllers;

import com.example.idea.Types.User;
import com.google.firebase.firestore.FirebaseFirestore;

public class DataWriter {

    private static final String TAG = "DataWriter";

    public DataWriter() {}

    public void basicUserReadWrite(User user, FirebaseFirestore dbRef) {
//
//        // Write to the database
//        DatabaseReference dbUserRef = FirebaseFirestore.getInstance().getReference();
//
//        // Creating new user node, which returns the unique key value
//        // new user node would be /users/$userid/
//        String userId = dbUserRef.push().getKey();
//        user.setUid(userId);
//
//        // pushing user to 'users' node using the userId
//        dbUserRef.child(userId).setValue(user);
//
//        // Read from the database
//        dbUserRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                User user = dataSnapshot.getValue(User.class);
//                Log.d(TAG, "User is: " + user);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//    }
    }
}