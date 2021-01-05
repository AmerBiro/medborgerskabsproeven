package com.amer.medborgerskabsprven.questions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amer.medborgerskabsprven.R;
import com.amer.medborgerskabsprven.databinding.QuestionQuestionsListBinding;
import com.amer.medborgerskabsprven.databinding.RegistrationAccountBinding;
import com.amer.medborgerskabsprven.logic.AlertDialogViewer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class QuestionsList extends Fragment {


    private @NonNull QuestionQuestionsListBinding binding;
    private NavController controller;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private AlertDialogViewer dialogViewer;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = QuestionQuestionsListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        dialogViewer = new AlertDialogViewer(getActivity(), view);
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogViewer.logInOrCreateAccount();
            }
        });

    }

}