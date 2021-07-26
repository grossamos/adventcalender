package com.bundespolizei.adventcalender.model;

public class QuestionModel {
    private String question;
    private String[] answers;
    private int correctAnswerIndex;

    // Needed for Jackson
    public QuestionModel() {
    }

    public QuestionModel(String question, String[] answers, int correctAnswerIndex) {
        if (correctAnswerIndex < 0 || answers.length <= correctAnswerIndex) {
            throw new IllegalStateException("The correct answer index needs to be in range of the answers");
        }
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }
}
