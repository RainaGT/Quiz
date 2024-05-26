package com.example.ayurveda;

public class Question {
    private int imageResId;
    private String questionText;
    private String[] answers;
    private int correctAnswerIndex;

    public Question(int imageResId, String questionText, String[] answers, int correctAnswerIndex) {
        this.imageResId = imageResId;
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
