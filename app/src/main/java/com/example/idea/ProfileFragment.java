package com.example.idea;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;
import java.util.Objects;

public class ProfileFragment extends Fragment implements View.OnClickListener{


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




//        create reference to imageView
//        imageView = imageView.findViewById(R.id.imageView);


        view = inflater.inflate(R.layout.fragment_profile, container, false);


        SharedPreferences pref = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);

        if(pref.getString("name", null) != null || !pref.getString("name", null).equals("")){
            ViewSwitcher switcher = view.findViewById(R.id.my_switcher);
            TextView myTV =  switcher.findViewById(R.id.nameView);
            myTV.setText(pref.getString("name", null));
        }

        SharedPreferences pref2 = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);

        if(pref2.getString("description", null) != null || !pref.getString("description", null).equals("")){
            ViewSwitcher switcher2 = view.findViewById(R.id.my_switcher2);
            TextView myTV2 =  switcher2.findViewById(R.id.descriptionView);
            myTV2.setText(pref2.getString("description", null));
        }




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
        SharedPreferences pref = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        ViewSwitcher switcher = view.findViewById(R.id.my_switcher);
        switcher.showNext(); //or switcher.showPrevious();
        TextView myTV =  switcher.findViewById(R.id.nameView);
        myTV.setText(pref.getString("name", null));
        EditText editText = switcher.findViewById(R.id.nameViewEdit);
        editText.setHint(pref.getString("name", null));

        SharedPreferences sharedPref = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name", editText.getText().toString());
        editor.commit();





        SharedPreferences pref2 = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        ViewSwitcher switcher2 = view.findViewById(R.id.my_switcher2);
        switcher2.showNext(); //or switcher2.showPrevious();
        TextView myTV2 = switcher2.findViewById(R.id.descriptionView);
        myTV2.setText(pref2.getString("description", null));
        EditText editText2 = switcher2.findViewById(R.id.descriptionViewEdit);
//        editText2.setHint(pref2.getString("description", null));


        SharedPreferences sharedPref2 = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPref2.edit();
        editor2.putString("description", editText2.getText().toString());
        editor2.commit();

//        Fragment frg = .getSupportFragmentManager().findFragmentByTag("Your_Fragment_TAG");
//        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.detach(frg);
//        ft.attach(frg);
//        ft.commit();
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