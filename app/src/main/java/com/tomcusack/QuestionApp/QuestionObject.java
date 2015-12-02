// Copyright 2015 Tom Cusack

package com.tomcusack.QuestionApp;
public class QuestionObject
{

    // Declarations
    private String question;
    private Boolean answer;
    private String imageURL;

    // Constructors
    public QuestionObject(String question, Boolean answer, String imageURL)
    {
        this.question = question;
        this.answer = answer;
        this.imageURL = imageURL;
    }

    // START: GETTERS //
    public String getQuestion()
    {
        return this.question;
    }
    public Boolean getAnswer()
    {
        return this.answer;
    }
    public String getImageURL()
    {
        return this.imageURL;
    }
    // END: GETTERS //
}