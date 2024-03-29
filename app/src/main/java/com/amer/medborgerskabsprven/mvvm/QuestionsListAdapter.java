package com.amer.medborgerskabsprven.mvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amer.medborgerskabsprven.R;
import com.bumptech.glide.Glide;

import java.util.List;

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
        holder.title.setText(questionsListModel.get(position).getTitle());
        holder.year.setText( questionsListModel.get(position).getYear());

        String description = questionsListModel.get(position).getDescription();
        if (description.length() > 50){
            description = description.substring(0, 50);
            description = description + " ...";
        }

        holder.description.setText(description);

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
        TextView title, year, description;
        ImageView image;
        LinearLayout button;

        public QuestionsListViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.questions_list_single_item_title);
            year = itemView.findViewById(R.id.questions_list_single_item_year);
            description = itemView.findViewById(R.id.questions_list_single_item_description);
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
