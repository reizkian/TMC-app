package org.tmcindonesia.application;

public class UserAnswer {
    // variable for user answer container
    private int numberOfCorrectAnswer;
    private String userAnswerMJWJ1;
    private String userAnswerMJWJ2;
    private String userAnswerMJWJ3;
    private String userAnswerMJWJ4;

    // constructor
    public UserAnswer(int numberOfCorrectAnswer, String userAnswerMJWJ1, String userAnswerMJWJ2, String userAnswerMJWJ3, String userAnswerMJWJ4) {
        this.numberOfCorrectAnswer = numberOfCorrectAnswer;
        this.userAnswerMJWJ1 = userAnswerMJWJ1;
        this.userAnswerMJWJ2 = userAnswerMJWJ2;
        this.userAnswerMJWJ3 = userAnswerMJWJ3;
        this.userAnswerMJWJ4 = userAnswerMJWJ4;
    }

    // to string method: put all variable into a single string


    @Override
    public String toString() {
        return "numberOfCorrectAnswer=" + numberOfCorrectAnswer +
                ", userAnswerMJWJ1='" + userAnswerMJWJ1 + '\'' +
                ", userAnswerMJWJ2='" + userAnswerMJWJ2 + '\'' +
                ", userAnswerMJWJ3='" + userAnswerMJWJ3 + '\'' +
                ", userAnswerMJWJ4='" + userAnswerMJWJ4 + '\'' +
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

    public String getUserAnswerMJWJ3() {
        return userAnswerMJWJ3;
    }

    public String getUserAnswerMJWJ4() {
        return userAnswerMJWJ4;
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

    public void setUserAnswerMJWJ3(String userAnswerMJWJ3) {
        this.userAnswerMJWJ3 = userAnswerMJWJ3;
    }

    public void setUserAnswerMJWJ4(String userAnswerMJWJ4) {
        this.userAnswerMJWJ4 = userAnswerMJWJ4;
    }
}
