package org.tmcindonesia.content.tmc_SeeAndDo1.questions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.tmcindonesia.R;
import org.tmcindonesia.content.tmc_Explorer2.questions.LESSON1;

import java.util.Arrays;

public class SD1Question1 extends AppCompatActivity {
    private int correctAnswerQuestionsPage[] = {1, 0, 0, 0};
    private int numberOfCorrectAnswer;
    private TextView question1, question2, question3, question4;
    private RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4;
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snd1_question1);

        // radioGroup
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup_question1);
        radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup_question2);
        radioGroup3 = (RadioGroup) findViewById(R.id.radioGroup_question3);
        radioGroup4 = (RadioGroup) findViewById(R.id.radioGroup_question4);

        // questions TextViews
        question1 = (TextView) findViewById(R.id.textView_question1);
        question2 = (TextView) findViewById(R.id.textView_question2);
        question3 = (TextView) findViewById(R.id.textView_question3);
        question4 = (TextView) findViewById(R.id.textView_question4);

        // okButton
        okButton = (Button) findViewById(R.id.button_CheckAnswer_QuestionsPage);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // get the ID of checked button for a radio group
                int radioButtonChecked1 = radioGroup1.getCheckedRadioButtonId();
                int radioButtonChecked2 = radioGroup2.getCheckedRadioButtonId();
                int radioButtonChecked3 = radioGroup3.getCheckedRadioButtonId();
                int radioButtonChecked4 = radioGroup4.getCheckedRadioButtonId();
                int radioButtonIDs[] = {
                        radioButtonChecked1,
                        radioButtonChecked2,
                        radioButtonChecked3,
                        radioButtonChecked4
                };
                // get the Index of checked button for a radio group
                int indexButtonChecked1 = radioGroup1.indexOfChild((RadioButton)findViewById(radioButtonChecked1));
                int indexButtonChecked2 = radioGroup2.indexOfChild((RadioButton)findViewById(radioButtonChecked2));
                int indexButtonChecked3 = radioGroup3.indexOfChild((RadioButton)findViewById(radioButtonChecked3));
                int indexButtonChecked4 = radioGroup4.indexOfChild((RadioButton)findViewById(radioButtonChecked4));
                int radioButtonIndexes [] = {
                        indexButtonChecked1,
                        indexButtonChecked2,
                        indexButtonChecked3,
                        indexButtonChecked4
                };
                //  get the Answer Texts of checked button for a radio group
                String answerTextQuestion1 = ((RadioButton) findViewById(radioButtonChecked1)).getText().toString().trim();
                String answerTextQuestion2 = ((RadioButton) findViewById(radioButtonChecked2)).getText().toString().trim();
                String answerTextQuestion3 = ((RadioButton) findViewById(radioButtonChecked3)).getText().toString().trim();
                String answerTextQuestion4 = ((RadioButton) findViewById(radioButtonChecked4)).getText().toString().trim();
                String answerTexts [] = {
                        answerTextQuestion1,
                        answerTextQuestion2,
                        answerTextQuestion3,
                        answerTextQuestion4,
                };
                // get the Question Texts of each questions
                String questionText1 = question1.getText().toString().trim();
                String questionText2 = question2.getText().toString().trim();
                String questionText3 = question3.getText().toString().trim();
                String questionText4 = question4.getText().toString().trim();
                String questionTexts [] = {
                        questionText1,
                        questionText2,
                        questionText3,
                        questionText4,
                };
                checkAnswerQuestionsPage(correctAnswerQuestionsPage, radioButtonIndexes);
            }
        });
    }

    // getNumberOfCorrectAnswer
    private void checkAnswerQuestionsPage(int[] listOfCorrectAnswer, int[] listOfUserAnswer) {
        // reset number
        numberOfCorrectAnswer = 0;
        for (int index = 0; index < listOfCorrectAnswer.length; index++) {
            if (listOfCorrectAnswer[index] == listOfUserAnswer[index]) {
                numberOfCorrectAnswer++;
            }
        }
        Toast.makeText(getApplicationContext(),
                String.valueOf(numberOfCorrectAnswer) + " soal kamu jawab dengan benar",
                Toast.LENGTH_SHORT).show();
    }
}