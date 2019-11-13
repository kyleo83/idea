package com.example.idea;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordFragment extends Fragment implements View.OnClickListener {

    private EditText oldPassword, newPassword;
    private FirebaseAuth auth;
    private Button btnChangePassword;
    private View v;
    private String email;
    Fragment newFragment;

    private static final String TAG = "ChangePasswordFragment";

    public ChangePasswordFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.activity_change_password, container, false);
        v = inflater.inflate(R.layout.fragment_change_password, container, false);

        oldPassword = (EditText) v.findViewById(R.id.old_password);
        newPassword = (EditText) v.findViewById(R.id.new_password);
        btnChangePassword = (Button) v.findViewById(R.id.btn_change_password);
        Log.i(TAG, "Change password clicked");

        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
        email = user.getEmail();
        btnChangePassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i(TAG, "Change password button clicked");

                auth.signInWithEmailAndPassword(email, oldPassword.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                String password = oldPassword.getText().toString().trim();
                                if (!task.isSuccessful()) {
                                    Log.i(TAG, "Current password wrong");
                                    // there was an error
                                    Toast.makeText(getActivity(), getString(R.string.wrong_password), Toast.LENGTH_LONG).show();
                                } else {
                                    Log.i(TAG, "Current password correct");
                                    passwordUpdate(user);
                                }
                            }
                        });

            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {

    }

    public interface OnNextClickListener {
    }

    private void passwordUpdate(FirebaseUser user) {
        user.updatePassword(newPassword.getText().toString().trim())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Password is updated!", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "Change password successful");
                            getActivity().onBackPressed();
                        } else {
                            Toast.makeText(getActivity(), "Failed to update password!", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "Change password failed");
                        }
                    }
                });
    }
}
