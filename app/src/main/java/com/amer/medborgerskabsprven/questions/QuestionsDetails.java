package com.amer.medborgerskabsprven.questions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amer.medborgerskabsprven.R;
import com.amer.medborgerskabsprven.databinding.QuestionQuestionsDetailsBinding;
import com.amer.medborgerskabsprven.mvvm.QuestionsListModel;
import com.amer.medborgerskabsprven.mvvm.QuestionsListViewModel;
import com.bumptech.glide.Glide;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class QuestionsDetails extends Fragment {

    private @NonNull QuestionQuestionsDetailsBinding binding;
    private QuestionsListViewModel questionsListViewModel;
    private int position;


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = QuestionQuestionsDetailsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        position = QuestionsDetailsArgs.fromBundle(getArguments()).getPosition();
        Log.d(TAG, "onViewCreated: " + position);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        questionsListViewModel = new ViewModelProvider(getActivity()).get(QuestionsListViewModel.class);
        questionsListViewModel.getQuestionsListModelData().observe(getViewLifecycleOwner(), new Observer<List<QuestionsListModel>>() {
            @Override
            public void onChanged(List<QuestionsListModel> questionsListModels) {
                binding.year.setText(questionsListModels.get(position).getYear());
                binding.title.setText(questionsListModels.get(position).getTitle());
                binding.note.setText(questionsListModels.get(position).getNote());
                Glide
                        .with(getActivity())
                        .load(questionsListModels.get(position).getImage_url())
                        .centerCrop()
                        .placeholder(R.drawable.placeholder_image)
                        .into(binding.image);
            }
        });
    }


}