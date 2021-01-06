package com.amer.medborgerskabsprven.questions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amer.medborgerskabsprven.R;
import com.amer.medborgerskabsprven.databinding.QuestionQuestionsListBinding;
import com.amer.medborgerskabsprven.databinding.RegistrationAccountBinding;
import com.amer.medborgerskabsprven.logic.AlertDialogViewer;
import com.amer.medborgerskabsprven.mvvm.QuestionsListAdapter;
import com.amer.medborgerskabsprven.mvvm.QuestionsListModel;
import com.amer.medborgerskabsprven.mvvm.QuestionsListViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class QuestionsList extends Fragment implements QuestionsListAdapter.OnQuestionsListItemClicked {


    private @NonNull QuestionQuestionsListBinding binding;
    private NavController controller;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private AlertDialogViewer dialogViewer;

    private RecyclerView recyclerView;
    private QuestionsListViewModel questionsListViewModel;
    private QuestionsListAdapter adapter;

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
        recyclerView = binding.recyclerview;
        adapter = new QuestionsListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        questionsListViewModel = new ViewModelProvider(getActivity()).get(QuestionsListViewModel.class);
        questionsListViewModel.getQuestionsListModelData().observe(getViewLifecycleOwner(), new Observer<List<QuestionsListModel>>() {
            @Override
            public void onChanged(List<QuestionsListModel> questionsListModels) {
                adapter.setQuestionsListsModels(questionsListModels);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClicked(int position) {
        QuestionsListDirections.ActionQuestionsListToQuestionsDetails action =
                QuestionsListDirections.actionQuestionsListToQuestionsDetails();
        action.setPosition(position);
        controller.navigate(action);
    }
}