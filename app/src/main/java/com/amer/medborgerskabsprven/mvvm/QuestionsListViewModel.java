package com.amer.medborgerskabsprven.mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class QuestionsListViewModel extends ViewModel implements FirebaseRepository.OnFirestoreTaskComplete {

    private MutableLiveData<List<QuestionsListModel>> questionsListModelData = new MutableLiveData<>();

    public LiveData<List<QuestionsListModel>> getQuestionsListModelData() {
        return questionsListModelData;
    }

    private FirebaseRepository firebaseRepository = new FirebaseRepository(this);

    public QuestionsListViewModel() {
        firebaseRepository.getQuestionsListData();
    }


    @Override
    public void questionsListDataAdded(List<QuestionsListModel> questionsListModels) {
        questionsListModelData.setValue(questionsListModels);
    }

    @Override
    public void onError(Exception e) {

    }
}
