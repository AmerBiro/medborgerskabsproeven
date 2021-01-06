package com.amer.medborgerskabsprven.questions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amer.medborgerskabsprven.R;
import com.amer.medborgerskabsprven.databinding.QuestionQuestionsDetailsBinding;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class QuestionsDetails extends Fragment {

    private @NonNull QuestionQuestionsDetailsBinding binding;


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = QuestionQuestionsDetailsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = QuestionsDetailsArgs.fromBundle(getArguments()).getPosition();
        Log.d(TAG, "onViewCreated: " + position);
    }

    @Override
    public void onStart() {
        super.onStart();
    }


}