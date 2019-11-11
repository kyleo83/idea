package com.example.idea.Controllers;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class DesignCardAdapter extends FirestoreAdapter<DesignCardAdapter.ViewHolder> {

    private static final String TAG = "DesignCardAdapter";
    private FirebaseFirestore db;

    public DesignCardAdapter(Query query) {
        super(query);
    }


    @Override
    public DesignCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        db = FirebaseFirestore.getInstance();
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DesignCardAdapter.ViewHolder holder, int position) {

    }


    static class ViewHolder extends RecyclerView.ViewHolder {



        public ViewHolder(View itemView) {
            super(itemView);

        }
    }



    private void paginate(final DocumentSnapshot last, final int limit) {
        final Query subset;
        if (last == null) {
            subset = db.collection("pictures")
                    .limit(limit);
        } else {
            subset = db.collection("pictures")
                    .startAfter(last)
                    .limit(limit);
        }
        getListChunkQuery(subset);
    }

    private void getListChunkQuery(Query query) {
        CollectionReference picturesRef = db.collection("pictures");
        Query firstQuery = picturesRef.orderBy("tags", Query.Direction.ASCENDING).limit(15);
    }
}
