package com.example.kappa.assignment;

/**
 * Created by Kappa on 3/3/2017.
 */

public class Question {

    private int ID;
    private String Question;
    private String Answer;
    private String Option1;
    private String Option2;
    private String Image;


    //Question constructor
    public Question(){
        ID=0;
        Question="";
        Answer="";
        Option1="";
        Option2="";
        Image="";

    }

    public Question(int id, String question, String answer, String option1, String option2, String image) {
        ID = id;
        Question = question;
        Answer = answer;
        Option1 = option1;
        Option2 = option2;
        Image=image;
    }

    //Question getter and setter

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getOption1() {
        return Option1;
    }

    public void setOption1(String option1) {
        Option1 = option1;
    }

    public String getOption2() {
        return Option2;
    }

    public void setOption2(String option2) {
        Option2 = option2;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

}
