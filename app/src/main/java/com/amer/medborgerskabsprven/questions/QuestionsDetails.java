package com.amer.medborgerskabsprven.questions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amer.medborgerskabsprven.R;
import com.amer.medborgerskabsprven.databinding.QuestionQuestionsDetailsBinding;
import com.amer.medborgerskabsprven.mvvm.QuestionsListModel;
import com.amer.medborgerskabsprven.mvvm.QuestionsListViewModel;
import com.bumptech.glide.Glide;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class QuestionsDetails extends Fragment implements View.OnClickListener {

    private @NonNull
    QuestionQuestionsDetailsBinding binding;
    private QuestionsListViewModel questionsListViewModel;
    private int position;
    private NavController controller;
    private String questionListId;
    private long totalQuestions = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = QuestionQuestionsDetailsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        position = QuestionsDetailsArgs.fromBundle(getArguments()).getPosition();
        Log.d(TAG, "onViewCreated: " + position);
        controller = Navigation.findNavController(view);
    }

    @Override
    public void onStart() {
        super.onStart();

        binding.startQuizButton.setOnClickListener(this);
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
                binding.note.setText(questionsListModels.get(position).getDescription());
                questionListId = questionsListModels.get(position).getQuestionsList_Id();
                totalQuestions = questionsListModels.get(position).getQuestion();
                Glide
                        .with(getActivity())
                        .load(questionsListModels.get(position).getImage_url())
                        .centerCrop()
                        .placeholder(R.drawable.placeholder_image)
                        .into(binding.image);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_quiz_button:
                QuestionsDetailsDirections.ActionQuestionsDetailsToCurrentQuestion action =
                        QuestionsDetailsDirections.actionQuestionsDetailsToCurrentQuestion();
                action.setTotalQuestions(totalQuestions);
                action.setQuestionId(questionListId);
                controller.navigate(action);
                break;
            default:
        }
    }
}