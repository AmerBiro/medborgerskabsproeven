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
import android.widget.Toast;

import com.amer.medborgerskabsprven.R;
import com.amer.medborgerskabsprven.databinding.RegistrationRegistrationBinding;
import com.amer.medborgerskabsprven.logic.FieldChecker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends Fragment implements View.OnClickListener {

    private @NonNull
    RegistrationRegistrationBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private NavController controller;
    private FieldChecker checker;
    private EditText[] fields;
    private String[] errorMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = RegistrationRegistrationBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        controller = Navigation.findNavController(view);
        checker = new FieldChecker();
        fields = new EditText[2];
        errorMessage = new String[2];
        fields[0] = binding.edittextRegistrationUsername;
        fields[1] = binding.edittextRegistrationPassword;
        errorMessage[0] = "Enter a username";
        errorMessage[1] = "Enter a password";
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
                guest();
            }
        });

        binding.buttonRegistrationCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }



    private void signIn() {
        if (!checker.isEmpty(fields, errorMessage)) {
            firebaseAuth.signInWithEmailAndPassword(
                    fields[0].getText().toString(),
                    fields[1].getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(getActivity(), "Logged in successfully...", 0).show();
                            controller.navigate(R.id.action_registration_to_questionsList);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Logged in error\n" + e.getMessage(), 1).show();
                    return;
                }
            });
        }
    }

    private void guest(){
        controller.navigate(R.id.action_registration_to_questionsList);
    }

    private void createAccount(){
        controller.navigate(R.id.action_registration_to_createAccount);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_registration_log_in:
                signIn();
                break;
            case R.id.button_registration_guest:
                guest();
                break;
            case R.id.createAccount:
                createAccount();
                break;
            default:
        }
    }

}