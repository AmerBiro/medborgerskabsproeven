package com.amer.medborgerskabsprven.logic;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.amer.medborgerskabsprven.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class User implements IUser {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    private NavController controller;
    private View view;
    private Activity activity;


    public User(Activity activity, View view) {
        this.view = view;
        this.activity = activity;
        this.controller = controller;
        this.controller = Navigation.findNavController(view);
    }

    @Override
    public void createUser(EditText username, EditText password, int i) {
        firebaseAuth.createUserWithEmailAndPassword(
                username.getText().toString().trim(),
                password.getText().toString().trim())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(activity, "Account created successfully...", 0).show();
                        controller.navigate(i);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Logged in error\n" + e.getMessage(), 1).show();
                return;
            }
        });
    }


    @Override
    public void signInAnonymously() {

    }

    @Override
    public void signIn(EditText username, EditText password, int i) {
        firebaseAuth.signInWithEmailAndPassword(
                username.getText().toString().trim(),
                password.getText().toString().trim())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(activity, "Logged in successfully...", 0).show();
                        controller.navigate(i);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Logged in error\n" + e.getMessage(), 1).show();
                return;
            }
        });
    }


    @Override
    public Boolean signOut(int i) {
        if (firebaseUser != null) {
            firebaseAuth.signOut();
            controller.navigate(i);
            Toast.makeText(activity, "Signed out successfully", 0).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    activity.finish();
                }
            },1000);
            return true;
        } else return false;
    }

    @Override
    public void verifyUser() {

    }

    @Override
    public void updateEmail() {

    }

    @Override
    public void resetPassword() {
        final EditText resetPassword = new EditText(activity);
        resetPassword.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        final AlertDialog.Builder resetPasswordDialog = new AlertDialog.Builder(activity);
        resetPasswordDialog.setTitle("Reset Password");
        resetPasswordDialog.setMessage("You can type your email below to get a reset link");
        resetPasswordDialog.setView(resetPassword);
        resetPasswordDialog.setPositiveButton("SEND LINK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (resetPassword.getText().toString().trim().isEmpty()) {
                    resetPassword.setError("Enter your email");
                    return;
                } else {
                    firebaseAuth.sendPasswordResetEmail(resetPassword.getText().toString()
                    ).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(activity, "A reset link is sent to " + resetPassword.getText().toString()
                                    , 0).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(activity, "An error has occured!\n" + e.getMessage(), 1).show();
                        }
                    });
                }
            }
        });
        resetPasswordDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        resetPasswordDialog.create().show();
    }

    @Override
    public void updatePassword() {

    }

    @Override
    public void deleteUser() {

    }

}
