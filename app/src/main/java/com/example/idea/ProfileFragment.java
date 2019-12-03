package com.example.idea;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.idea.Controllers.DesignCardAdapter;
import com.example.idea.Types.Design;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.SwipeViewBinder;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProfileFragment extends Fragment implements View.OnClickListener{

    private DesignCardAdapter designCardAdapter;
    private SwipePlaceHolderView mSwipeView;
    Context context = getContext();

    private View view;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private Map<String, Object> userData;
    private static final String TAG = "ProfileFragment";

    ImageView imageView;


    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        // maps info to userData var for reference
        getUserInfo(db);
        getImages(db);


//        create reference to imageView
//        imageView = imageView.findViewById(R.id.imageView);


        view = inflater.inflate(R.layout.fragment_profile, container, false);


        mSwipeView = view.findViewById(R.id.swipeView2);
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        int bottomMargin = Utils.dpToPx(160);
        WindowManager windowManager = getActivity().getWindowManager();
        Point windowSize = Utils.getDisplaySize(windowManager);
        // Load from Firestore and add to mSwipeView
        final List<QueryDocumentSnapshot> results = new ArrayList<>();
        final List<Design> designs = new ArrayList<>();
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setViewWidth(windowSize.x)
                        .setViewHeight(windowSize.y - bottomMargin)
                        .setViewGravity(Gravity.TOP)
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.swipe_right_view)
                        .setSwipeOutMsgLayoutId(R.layout.swipe_left_view));
//        mSwipeView.addItemRemoveListener(new ItemRemovedListener() {
//            @Override
//            public void onItemRemoved(int count) {
////                if (count == 0) mSwipeView.removeAllViews();

//                mSwipeView.remove
//            }
//        });
//        mSwipeView.addItemRemoveListener(new ItemRemovedListener() {
//            @Override
//            public void onItemRemoved(int count) {
//                mSwipeView.
//                Log.i(TAG, "onItemRemoved: " + count);
//            }
//        });


        fetchAllDesignSnapshots(db, results, designs);
        Log.i(TAG, "FETCH COMPLETE");
//
//        view.findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mSwipeView.doSwipe(false);
//            }
//        });
//
//        view.findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mSwipeView.doSwipe(true);
//            }
//        });
//
//        view.findViewById(R.id.uploadBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), UploadActivity.class)) ;
//            }
//        });









        SharedPreferences pref = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
//
//        if(pref.getString("name", null) != null ){
//            ViewSwitcher switcher = view.findViewById(R.id.my_switcher);
//            TextView myTV =  switcher.findViewById(R.id.nameView);
//            myTV.setText(pref.getString("name", null));
//        }

        SharedPreferences pref2 = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);

        if(pref2.getString("description", null) != null){
            ViewSwitcher switcher2 = view.findViewById(R.id.my_switcher2);
            TextView myTV2 =  switcher2.findViewById(R.id.descriptionView);
            myTV2.setText(pref2.getString("description", null));
        }

//        TextView nameTV = view.findViewById(R.id.nameView);
//        nameTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editButtonViewClicked();
//            }
//        });
//
//        TextView descriptionTV = view.findViewById(R.id.descriptionView);
//        descriptionTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editButton2ViewClicked();
//            }
//        });






        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editButtonViewClicked();
            }

        });

        return view;
    }


    public void editButtonViewClicked() {
//        SharedPreferences pref = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
//        ViewSwitcher switcher = view.findViewById(R.id.my_switcher);
//        switcher.showNext(); //or switcher.showPrevious();
//        EditText editText = switcher.findViewById(R.id.nameViewEdit);
//        editText.setHint(pref.getString("name", null));


//        SharedPreferences sharedPref = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString("name", editText.getText().toString());
//        editor.commit();
//        TextView myTV =  switcher.findViewById(R.id.nameView);
//        myTV.setText(pref.getString("name", "def"));




        SharedPreferences pref2 = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        ViewSwitcher switcher2 = view.findViewById(R.id.my_switcher2);
        switcher2.showNext(); //or switcher2.showPrevious();
        EditText editText2 = switcher2.findViewById(R.id.descriptionViewEdit);
        editText2.setHint(pref2.getString("description", null));


        SharedPreferences sharedPref2 = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPref2.edit();
        editor2.putString("description", editText2.getText().toString());
        editor2.commit();
        TextView myTV2 = switcher2.findViewById(R.id.descriptionView);
        myTV2.setText(pref2.getString("description", "def"));

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

    public void getImages(FirebaseFirestore db) {
        String authUserUid = auth.getCurrentUser().getUid();
        DocumentReference docRef = db.collection("pictures").document(authUserUid);
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

    private void fetchAllDesignSnapshots(FirebaseFirestore db,
                                         final List<QueryDocumentSnapshot> results,
                                         final List<Design> designs) {

        Query query = db.collection("saved").whereEqualTo("savedUser", auth.getUid());
        designCardAdapter = new DesignCardAdapter(query);

        Log.i(TAG, "fetchAllDesignSnapshots: ");
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Log.i(TAG, "onComplete: success");
                                results.add(document);
                            }
                            toDesigns(results, designs);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
    public void toDesigns(List<QueryDocumentSnapshot> documentSnapshotList, List<Design> designs) {
        for (QueryDocumentSnapshot doc : documentSnapshotList) {
            String designId = doc.getString("savedID");
            String tag = doc.getString("savedTAG");
            String picUrl = doc.getString("savedURL");
            String textDescription = "";
            Design newDesign = new Design(designId, tag, picUrl, textDescription);
            designs.add(newDesign);
            mSwipeView.addView(new DesignCard(getActivity(), newDesign, mSwipeView));
//            Log.i("How many designs now: ", String.valueOf(designs.size()));
        }
    }
}