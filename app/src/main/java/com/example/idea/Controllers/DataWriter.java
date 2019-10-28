package com.example.idea.Controllers;

import android.util.Log;

import com.example.idea.Types.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataWriter {

    private static final String TAG = "DataWriter";

    public DataWriter() {}

    public void basicUserReadWrite(User user) {

        // Write to the database
        DatabaseReference dbUserRef = FirebaseDatabase.getInstance().getReference("users");

        // Creating new user node, which returns the unique key value
        // new user node would be /users/$userid/
        String userId = dbUserRef.push().getKey();
        user.setId(userId);

        // pushing user to 'users' node using the userId
        dbUserRef.child(userId).setValue(user);

        // Read from the database
        dbUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User user = dataSnapshot.getValue(User.class);
                Log.d(TAG, "User is: " + user);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

}
