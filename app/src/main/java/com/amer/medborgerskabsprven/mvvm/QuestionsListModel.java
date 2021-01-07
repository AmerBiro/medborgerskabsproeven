package com.amer.medborgerskabsprven.mvvm;


import com.google.firebase.firestore.DocumentId;

public class QuestionsListModel {

    @DocumentId
    private String questionsList_Id;
    private String image, image_url, title, year, description;
    private long question;

    public QuestionsListModel(String questionsList_Id, String image, String image_url, String title, String year, String description, long question) {
        this.questionsList_Id = questionsList_Id;
        this.image = image;
        this.image_url = image_url;
        this.title = title;
        this.year = year;
        this.description = description;
        this.question = question;
    }

    public QuestionsListModel() {
    }

    public String getQuestionsList_Id() {
        return questionsList_Id;
    }

    public void setQuestionsList_Id(String questionsList_Id) {
        this.questionsList_Id = questionsList_Id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getQuestion() {
        return question;
    }

    public void setQuestion(long question) {
        this.question = question;
    }
}
