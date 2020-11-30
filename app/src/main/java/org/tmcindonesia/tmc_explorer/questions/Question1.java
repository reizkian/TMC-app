package org.tmcindonesia.tmc_explorer.questions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.tmcindonesia.tmc_explorer.R;

import java.util.Arrays;

public class Question1 extends AppCompatActivity {
    // variable TREASURE HUNT
    private EditText UserAnswerTreasureHunt;
    private Button checkAnswerTreasureHunt;
    // variable QUESTIONS PAGE
    private int correctAnswerQuestionsPage[] = {1,0,1,0,0};
    private RadioGroup rgqp_question1, rgqp_question2, rgqp_question3, rgqp_question4, rgqp_question5;
    private RadioButton rb_question1, rb_question2, rb_question3, rb_question4, rb_question5;
    private Button getCheckAnswerQuestionsPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        // *******************************TREASURE HUNT**********************************
        // get layout id
        UserAnswerTreasureHunt = (EditText)findViewById(R.id.editText_AnswerTreasureHunt);
        checkAnswerTreasureHunt = (Button)findViewById(R.id.button_CheckAnswer_TreasureHunt);
        // True Answer for Treasure Hunt Question
        String trueAnswerTreasureHunt = "love god with all your heart";
        // OK button clicked TREASURE HUNT
        checkAnswerTreasureHunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // take user input as answer
                String userInputAnswer = UserAnswerTreasureHunt.getText().toString().trim().toLowerCase();

                // compare to true answer
                if(userInputAnswer.equals(trueAnswerTreasureHunt)){
                    // change button color to green
                    checkAnswerTreasureHunt.setBackgroundColor(ContextCompat.getColor(org.tmcindonesia.tmc_explorer.questions.Question1.this, R.color.green_true_answer));
                    // notify correct answer
                    Toast.makeText(org.tmcindonesia.tmc_explorer.questions.Question1.this, "Jawaban Kamu Benar!",Toast.LENGTH_SHORT).show();
                }
                if(!userInputAnswer.equals(trueAnswerTreasureHunt)){
                    // change button color to red
                    checkAnswerTreasureHunt.setBackgroundColor(ContextCompat.getColor(org.tmcindonesia.tmc_explorer.questions.Question1.this, R.color.red_false_answer));
                    // notify wrong answer
                    Toast.makeText(org.tmcindonesia.tmc_explorer.questions.Question1.this, "Jawaban Kamu Salah, ayo coba lagi",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // *******************************QUESTIONS PAGE*********************************
        // get layout ID radio group
        rgqp_question1 = (RadioGroup) findViewById(R.id.radioGroup_QuestionPage1_MultipleChoiceQuestion1);
        rgqp_question2 = (RadioGroup) findViewById(R.id.radioGroup_QuestionPage1_MultipleChoiceQuestion2);
        rgqp_question3 = (RadioGroup) findViewById(R.id.radioGroup_QuestionPage1_MultipleChoiceQuestion3);
        rgqp_question4 = (RadioGroup) findViewById(R.id.radioGroup_QuestionPage1_MultipleChoiceQuestion4);
        rgqp_question5 = (RadioGroup) findViewById(R.id.radioGroup_QuestionPage1_MultipleChoiceQuestion5);
        // OK button clicked QUESTION PAGE
        getCheckAnswerQuestionsPage = findViewById(R.id.button_CheckAnswer_QuestionsPage);
        getCheckAnswerQuestionsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get which radio button is choose
                int rb_id_question1 = rgqp_question1.getCheckedRadioButtonId();
                int rb_id_question2 = rgqp_question2.getCheckedRadioButtonId();
                int rb_id_question3 = rgqp_question3.getCheckedRadioButtonId();
                int rb_id_question4 = rgqp_question4.getCheckedRadioButtonId();
                int rb_id_question5 = rgqp_question5.getCheckedRadioButtonId();
                int rb_id_array[] = {rb_id_question1, rb_id_question2, rb_id_question3, rb_id_question4, rb_id_question5};
                // get layout ID radio button
                rb_question1 = (RadioButton) findViewById(rb_id_question1);
                rb_question2 = (RadioButton) findViewById(rb_id_question2);
                rb_question3 = (RadioButton) findViewById(rb_id_question3);
                rb_question4 = (RadioButton) findViewById(rb_id_question4);
                rb_question5 = (RadioButton) findViewById(rb_id_question5);
                //get radio button index
                int rb_index_question1 = rgqp_question1.indexOfChild(rb_question1);
                int rb_index_question2 = rgqp_question2.indexOfChild(rb_question2);
                int rb_index_question3 = rgqp_question3.indexOfChild(rb_question3);
                int rb_index_question4 = rgqp_question4.indexOfChild(rb_question4);
                int rb_index_question5 = rgqp_question5.indexOfChild(rb_question5);
                int rb_index_array[] = {rb_index_question1, rb_index_question2, rb_index_question3, rb_index_question4, rb_index_question5};
                //toast
                checkAnswerQuestionsPage(correctAnswerQuestionsPage, rb_index_array);
            }
            // getNumberOfCorrectAnswer
            private void checkAnswerQuestionsPage(int[] listOfCorrectAnswer, int[] listOfUserAnswer) {
                int numberOfCorrectAnswer = 0;
                for (int index=0; index<listOfCorrectAnswer.length; index++){
                    if(listOfCorrectAnswer[index]==listOfUserAnswer[index]){
                        numberOfCorrectAnswer++;
                    }
                }
                Toast.makeText(org.tmcindonesia.tmc_explorer.questions.Question1.this,
                        String.valueOf(numberOfCorrectAnswer)+" soal kamu jawab dengan benar",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}