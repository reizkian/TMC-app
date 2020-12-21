package org.tmcindonesia.application;

public class TestimonyAnswer {
    // variable for user answer container
    private int numberOfCorrectAnswer;
    private String userAnswerMJWJ1;
    private String userAnswerMJWJ2;

    // constructor
    public TestimonyAnswer(int numberOfCorrectAnswer, String userAnswerMJWJ1, String userAnswerMJWJ2) {
        this.numberOfCorrectAnswer = numberOfCorrectAnswer;
        this.userAnswerMJWJ1 = userAnswerMJWJ1;
        this.userAnswerMJWJ2 = userAnswerMJWJ2;
    }

    // to string method: put all variable into a single string


    @Override
    public String toString() {
        return "numberOfCorrectAnswer=" + numberOfCorrectAnswer +
                ", userAnswerMJWJ1='" + userAnswerMJWJ1 + '\'' +
                ", userAnswerMJWJ2='" + userAnswerMJWJ2 + '\'' +
                '}';
    }

    // getter
    public int getNumberOfCorrectAnswer() {
        return numberOfCorrectAnswer;
    }

    public String getUserAnswerMJWJ1() {
        return userAnswerMJWJ1;
    }

    public String getUserAnswerMJWJ2() {
        return userAnswerMJWJ2;
    }



    // setter

    public void setNumberOfCorrectAnswer(int numberOfCorrectAnswer) {
        this.numberOfCorrectAnswer = numberOfCorrectAnswer;
    }

    public void setUserAnswerMJWJ1(String userAnswerMJWJ1) {
        this.userAnswerMJWJ1 = userAnswerMJWJ1;
    }

    public void setUserAnswerMJWJ2(String userAnswerMJWJ2) {
        this.userAnswerMJWJ2 = userAnswerMJWJ2;
    }

}
