package com.amer.medborgerskabsprven.logic;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;

import androidx.navigation.NavController;

public interface IUser {

    public void createUser(EditText username, EditText password, int i);

    public void signInAnonymously();


    public void signIn(EditText username, EditText password, int i);


    public Boolean signOut(int i);


    public void verifyUser();


    public void updateEmail();


    public void resetPassword();

    public void updatePassword();


    public void deleteUser();
}






