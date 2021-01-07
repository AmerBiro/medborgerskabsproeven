package com.amer.medborgerskabsprven.mvvm;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amer.medborgerskabsprven.questions.QuestionsList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Type;
import java.util.List;

import static android.content.ContentValues.TAG;


public class FirebaseRepository {


    private OnFirestoreTaskComplete onFirestoreTaskComplete;

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = firebaseAuth.getCurrentUser();
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private Query questionsListRef = firebaseFirestore
            .collection("QuestionsList").orderBy("year");

    public FirebaseRepository(OnFirestoreTaskComplete onFirestoreTaskComplete) {
        this.onFirestoreTaskComplete = onFirestoreTaskComplete;
    }

    public void getQuestionsListData() {
/*        questionsListRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    onFirestoreTaskComplete.questionsListDataAdded(task.getResult().toObjects(QuestionsListModel.class));
                } else {
                    onFirestoreTaskComplete.onError(task.getException());
                }
            }
        });*/


        questionsListRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                Log.d(TAG, "onEvent: " + value.getDocumentChanges().toString());
                List<QuestionsListModel> questionsListModels = value.toObjects(QuestionsListModel.class);
                onFirestoreTaskComplete.questionsListDataAdded(questionsListModels);
            }
        });
    }


    public interface OnFirestoreTaskComplete {
        void questionsListDataAdded(List<QuestionsListModel> questionsListModels);

        void onError(Exception e);
    }

}