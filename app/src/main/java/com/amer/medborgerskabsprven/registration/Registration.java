package com.amer.medborgerskabsprven.registration;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.amer.medborgerskabsprven.R;
import com.amer.medborgerskabsprven.databinding.RegistrationRegistrationBinding;
import com.amer.medborgerskabsprven.logic.FieldChecker;
import com.amer.medborgerskabsprven.logic.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends Fragment {

    private @NonNull
    RegistrationRegistrationBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private NavController controller;
    private FieldChecker checker;
    private EditText[] fields;
    private String[] errorMessage;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = RegistrationRegistrationBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        checker = new FieldChecker();
        fields = new EditText[2];
        errorMessage = new String[2];
        fields[0] = binding.edittextRegistrationUsername;
        fields[1] = binding.edittextRegistrationPassword;
        errorMessage[0] = "Enter a username";
        errorMessage[1] = "Enter a password";
        user = new User(getActivity(), view);
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.buttonRegistrationLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        binding.buttonRegistrationGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueAsAGuest();
            }
        });

        binding.buttonRegistrationCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });

        binding.buttonRegistrationResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }


    private void signIn() {
        if (!checker.isEmpty(fields, errorMessage)) {
            user.signIn(fields[0], fields[1], R.id.action_registration_to_questionsList);
        }
    }

    private void continueAsAGuest() {
        controller.navigate(R.id.action_registration_to_questionsList);

    }

    private void createAccount() {
        controller.navigate(R.id.action_registration_to_createAccount);
    }

    private void resetPassword() {
        user.resetPassword();
    }


}