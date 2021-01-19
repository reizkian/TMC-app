package org.tmcindonesia.application.UserInput;

public class TestimonyAnswer {
    // variable for user answer container
    private int numberOfCorrectAnswer;
    private String userAnswerMJWJ1;
    private String userAnswerMJWJ2;
    private String userAnswerAyoJawab1;
    private String userAnswerAyoJawab2;
    private String userAnswerAyoJawab3;
    private String userAnswerAyoJawab4;
    private String userAnswerAyoJawab5;

    // constructor

    public TestimonyAnswer(int numberOfCorrectAnswer, String userAnswerMJWJ1, String userAnswerMJWJ2, String userAnswerAyoJawab1, String userAnswerAyoJawab2, String userAnswerAyoJawab3, String userAnswerAyoJawab4, String userAnswerAyoJawab5) {
        this.numberOfCorrectAnswer = numberOfCorrectAnswer;
        this.userAnswerMJWJ1 = userAnswerMJWJ1;
        this.userAnswerMJWJ2 = userAnswerMJWJ2;
        this.userAnswerAyoJawab1 = userAnswerAyoJawab1;
        this.userAnswerAyoJawab2 = userAnswerAyoJawab2;
        this.userAnswerAyoJawab3 = userAnswerAyoJawab3;
        this.userAnswerAyoJawab4 = userAnswerAyoJawab4;
        this.userAnswerAyoJawab5 = userAnswerAyoJawab5;
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

    public String getUserAnswerAyoJawab1() {
        return userAnswerAyoJawab1;
    }

    public String getUserAnswerAyoJawab2() {
        return userAnswerAyoJawab2;
    }

    public String getUserAnswerAyoJawab3() {
        return userAnswerAyoJawab3;
    }

    public String getUserAnswerAyoJawab4() {
        return userAnswerAyoJawab4;
    }

    public String getUserAnswerAyoJawab5() {
        return userAnswerAyoJawab5;
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
