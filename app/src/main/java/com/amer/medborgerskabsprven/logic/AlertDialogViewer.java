package com.amer.medborgerskabsprven.logic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.amer.medborgerskabsprven.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AlertDialogViewer {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    private NavController controller;
    private View view;
    private Activity activity;


    public AlertDialogViewer(Activity activity, View view) {
        this.view = view;
        this.activity = activity;
        this.controller = controller;
        this.controller = Navigation.findNavController(view);
    }


    public void logInOrCreateAccount(){
        if (firebaseUser == null){
            AlertDialog.Builder account = new AlertDialog.Builder(activity);
            account.setTitle("Account needed");
            account.setMessage("You can log in / create a new account")
                    .setPositiveButton("Create account", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            controller.navigate(R.id.action_questionsList_to_createAccount);
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Log in", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            controller.navigate(R.id.action_questionsList_to_registration);
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = account.create();
            alert.show();
        }else{
            controller.navigate(R.id.action_questionsList_to_Account);
        }
    }

}
