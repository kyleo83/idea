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
            subset = db.collection("ideas")
                    .limit(limit);
        } else {
            subset = db.collection("ideas")
                    .startAfter(last)
                    .limit(limit);
        }
        getListChunkQuery(subset);
    }

    public void getListChunkQuery(Query query) {
        CollectionReference ideasRef = db.collection("ideas");
        Query firstQuery = ideasRef.orderBy("tags", Query.Direction.ASCENDING).limit(15);
    }
}
