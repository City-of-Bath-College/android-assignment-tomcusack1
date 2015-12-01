package com.tomcusack.QuestionApp;

/**
 * Created by Mark on 03/10/2015.
 */
public class QuestionObject {

    /* Member variables */
    private String question;
    private Boolean answer;
    private String imageURL;

    /* Constructor */
    public QuestionObject(String question, Boolean answer, String imageURL){
        /* Assign vars */
        this.question = question;
        this.answer = answer;
        this.imageURL = imageURL;
    }

    /* Public methods */
    public String getQuestion(){
        return this.question;
    }

    public Boolean getAnswer(){
        return this.answer;
    }

    public String getImageURL(){
        return this.imageURL;
    }
}
