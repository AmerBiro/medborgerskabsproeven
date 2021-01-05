package com.amer.medborgerskabsprven.questions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amer.medborgerskabsprven.R;
import com.amer.medborgerskabsprven.databinding.QuestionQuestionsListBinding;
import com.amer.medborgerskabsprven.databinding.RegistrationAccountBinding;

public class QuestionsList extends Fragment {


    private @NonNull QuestionQuestionsListBinding binding;
    private NavController controller;

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
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_questionsList_to_Account);
            }
        });

    }

}