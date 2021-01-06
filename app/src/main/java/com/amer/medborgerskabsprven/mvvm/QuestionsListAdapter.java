package com.amer.medborgerskabsprven.mvvm;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amer.medborgerskabsprven.R;
import com.bumptech.glide.Glide;

import java.util.List;

import static android.content.ContentValues.TAG;

public class QuestionsListAdapter extends RecyclerView.Adapter <QuestionsListAdapter.QuestionsListViewHolder> {

    private List<QuestionsListModel> questionsListModel;
    private OnQuestionsListItemClicked onQuestionsListItemClicked;

    public QuestionsListAdapter(OnQuestionsListItemClicked onQuestionsListItemClicked) {
        this.onQuestionsListItemClicked = onQuestionsListItemClicked;
    }

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
        holder.title.setText("Title\n"+questionsListModel.get(position).getTitle());
        holder.year.setText("Year: " + questionsListModel.get(position).getYear());

        String note = questionsListModel.get(position).getNote();
        if (note.length() > 25){
            note = note.substring(0, 25);
            note = note + " ...";
        }

        holder.note.setText("Important note\n" + questionsListModel.get(position).getNote());

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


    public class QuestionsListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, year, note;
        ImageView image;
        LinearLayout button;

        public QuestionsListViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.questions_list_single_item_title);
            year = itemView.findViewById(R.id.questions_list_single_item_year);
            note = itemView.findViewById(R.id.questions_list_single_item_note);
            image = itemView.findViewById(R.id.questions_list_single_item_placeholder);
            button = itemView.findViewById(R.id.questions_list_single_item_button);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onQuestionsListItemClicked.onItemClicked(getAdapterPosition());
        }
    }

    public interface OnQuestionsListItemClicked{
        public void onItemClicked(int position);
    }
}
