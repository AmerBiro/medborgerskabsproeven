package com.amer.medborgerskabsprven.questions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amer.medborgerskabsprven.databinding.QuestionCurrentQuestionBinding;
import com.amer.medborgerskabsprven.mvvm.QuestionModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class CurrentQuestion extends Fragment {

    private @NonNull QuestionCurrentQuestionBinding
     binding;
    private NavController controller;
    private FirebaseFirestore firebaseFirestore;
    private String questionListId;
    private List<QuestionModel> allQuestionList = new ArrayList<>();
    private List<QuestionModel> questionToAnswer = new ArrayList<>();
    private long totalQuestionsToAnswer = 2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = QuestionCurrentQuestionBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        firebaseFirestore = FirebaseFirestore.getInstance();
        questionListId = CurrentQuestionArgs.fromBundle(getArguments()).getQuestionId();
        Log.d(TAG, "currentQuestion: "  + " , " + questionListId);
        totalQuestionsToAnswer = CurrentQuestionArgs.fromBundle(getArguments()).getTotalQuestions();

        firebaseFirestore.collection("QuestionsList").document(questionListId)
                .collection("Question").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            allQuestionList = task.getResult().toObjects(QuestionModel.class);
                            Log.d(TAG, "onComplete: " + allQuestionList.get(1).getQuestion());
                            pickUpQuestions();
                        }else {
                            binding.title.setText("Error Loading Data");
                        }
                    }
                });
    }

    private void pickUpQuestions() {
        for (int i = 0; i < totalQuestionsToAnswer; i++){
            int randomNumber = getRandomInteger(allQuestionList.size(), 0);
            questionToAnswer.add(allQuestionList.get(randomNumber));
            allQuestionList.remove(randomNumber);
            Log.d(TAG, "pickUpQuestions: " + "question " + i + ":" +  questionToAnswer.get(i).getQuestion());
        }
    }

    public static int getRandomInteger(int max, int min){
        return ((int) (Math.random() * (max - min) ) ) + min;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}