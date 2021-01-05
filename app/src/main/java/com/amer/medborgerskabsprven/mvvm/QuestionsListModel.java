package com.amer.medborgerskabsprven.mvvm;


import com.google.firebase.firestore.DocumentId;

public class QuestionsListModel {


    private String questionsList_id, image, image_url, title, year, note;

    public QuestionsListModel(String questionsList_id, String image, String image_url, String title, String year, String note) {
        this.questionsList_id = questionsList_id;
        this.image = image;
        this.image_url = image_url;
        this.title = title;
        this.year = year;
        this.note = note;
    }

    public QuestionsListModel() {
    }

    public String getQuestionsList_id() {
        return questionsList_id;
    }

    public void setId(String questionsList_id) {
        this.questionsList_id = questionsList_id;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
