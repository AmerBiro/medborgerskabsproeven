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
import com.amer.medborgerskabsprven.databinding.RegistrationCreateAccountBinding;
import com.amer.medborgerskabsprven.logic.FieldChecker;
import com.amer.medborgerskabsprven.logic.User;

public class CreateAccount extends Fragment implements View.OnClickListener {

    private @NonNull RegistrationCreateAccountBinding binding;
    private NavController controller;
    private FieldChecker checker;
    private EditText[] fields;
    private String[] errorMessage;
    private User user;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = RegistrationCreateAccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        controller = Navigation.findNavController(view);
        checker = new FieldChecker();
        fields = new EditText[2];
        errorMessage = new String[2];
        fields[0] = binding.edittextCreateAccountUsername;
        fields[1] = binding.edittextCreateAccountPassword;
        errorMessage[0] = "Enter a username";
        errorMessage[1] = "Enter a password";
        user = new User(getActivity(), view);
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.buttonCreateAccountCreateAccount.setOnClickListener(this);
    }

    private void createAccount() {
        if (!checker.isEmpty(fields, errorMessage)) {
            user.createUser(fields[0], fields[1], R.id.action_createAccount_to_questionsList);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_create_account_create_account:
                createAccount();
                break;
            default:
        }
    }
}