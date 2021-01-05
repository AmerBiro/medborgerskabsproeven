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
import com.amer.medborgerskabsprven.databinding.RegistrationAccountBinding;
import com.amer.medborgerskabsprven.logic.FieldChecker;
import com.amer.medborgerskabsprven.logic.User;

public class Account extends Fragment {

    private @NonNull RegistrationAccountBinding binding;
    private NavController controller;
    private User user;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = RegistrationAccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        user = new User(getActivity(), view);
    }

    @Override
    public void onStart() {
        super.onStart();

        binding.logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.signOut(R.id.action_Account_to_registration);
            }
        });

    }
}