package com.example.idea.Controllers;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class DesignCardAdapter extends FirestoreAdapter<DesignCardAdapter.ViewHolder> {

    private static final String TAG = "DesignCardAdapter";
    private FirebaseFirestore db;
    private DesignCardAdapter.ClickListener mClickListener;
    Query query;

    public DesignCardAdapter(Query query) {
        super(query);
        this.query = query;
    }

    @Override
    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
        super.onEvent(documentSnapshots, e);
    }

    @Override
    public void startListening() {
        super.startListening();
    }

    @Override
    public void stopListening() {
        super.stopListening();
    }

    @Override
    public void setQuery(Query query) {
        super.setQuery(query);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    protected DocumentSnapshot getSnapshot(int index) {
        return super.getSnapshot(index);
    }

    @Override
    protected void onDocumentAdded(DocumentChange change) {
        super.onDocumentAdded(change);
    }

    @Override
    protected void onDocumentModified(DocumentChange change) {
        super.onDocumentModified(change);
    }

    @Override
    protected void onDocumentRemoved(DocumentChange change) {
        super.onDocumentRemoved(change);
    }

    @Override
    protected void onError(FirebaseFirestoreException e) {
        super.onError(e);
    }

    @Override
    protected void onDataChanged() {
        super.onDataChanged();

    }


    @Override
    public DesignCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        db = FirebaseFirestore.getInstance();
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DesignCardAdapter.ViewHolder holder, int position) {
//        LayoutInflater inflater = LayoutInflater.from(getContext());
//        View view = inflater.inflate(R.layout.record_logs_card, parent, false);
//
//        holder.setOnClickListener(new DesignCardAdapter.ClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                cache
//                Toast.makeText(getActivity(), "Item clicked at " + position, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//                Toast.makeText(getActivity(), "Item long clicked at " + position, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        return new recordViewHolder(view, mClickListener);
    }

    public interface ClickListener {
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
