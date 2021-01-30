package org.tmcindonesia.application;

public class AcceptJesusAnswer {
    // variable for user answer container
    private int numberOfCorrectAnswer;
    private String userAnswerMJWJ1;
    private String userAnswerMJWJ2;
    private String userAnswerMJWJ3;

    // constructor
    public AcceptJesusAnswer(int numberOfCorrectAnswer, String userAnswerMJWJ1, String userAnswerMJWJ2, String userAnswerMJWJ3) {
        this.numberOfCorrectAnswer = numberOfCorrectAnswer;
        this.userAnswerMJWJ1 = userAnswerMJWJ1;
        this.userAnswerMJWJ2 = userAnswerMJWJ2;
        this.userAnswerMJWJ3 = userAnswerMJWJ3;
    }

    // to string method: put all variable into a single string


    @Override
    public String toString() {
        return "numberOfCorrectAnswer=" + numberOfCorrectAnswer +
                ", userAnswerMJWJ1='" + userAnswerMJWJ1 + '\'' +
                ", userAnswerMJWJ2='" + userAnswerMJWJ2 + '\'' +
                ", userAnswerMJWJ3='" + userAnswerMJWJ3 + '\'' +
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

}
