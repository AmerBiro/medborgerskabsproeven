package com.amer.medborgerskabsprven.mvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amer.medborgerskabsprven.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class QuestionsListAdapter extends RecyclerView.Adapter <QuestionsListAdapter.QuestionsListViewHolder> {

    private List<QuestionsListModel> questionsListModel;

    public void setQuestionsListsModels(List<QuestionsListModel> questionsListModel) {
        this.questionsListModel = questionsListModel;
    }

    @NonNull
    @Override
    public QuestionsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.questions_list_single_item, parent, false);
        return new QuestionsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsListViewHolder holder, int position) {
        holder.title.setText(questionsListModel.get(position).getTitle());
        holder.year.setText(questionsListModel.get(position).getYear());
        holder.note.setText(questionsListModel.get(position).getNote());

        Glide
                .with(holder.itemView.getContext())
                .load(questionsListModel.get(position).getImage_url())
                .centerCrop()
                .placeholder(R.drawable.placeholder_image)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        if(questionsListModel == null){
            return 0;
        } else {
            return questionsListModel.size();
        }
    }


    public class QuestionsListViewHolder extends RecyclerView.ViewHolder {
        TextView title, year, note;
        ImageView image;

        public QuestionsListViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.questions_list_single_item_title);
            year = itemView.findViewById(R.id.questions_list_single_item_year);
            note = itemView.findViewById(R.id.questions_list_single_item_note);
            image = itemView.findViewById(R.id.questions_list_single_item_placeholder);

        }
    }
}
