package org.tmcindonesia.tmc_explorer.questions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.tmcindonesia.tmc_explorer.R;

public class Question1 extends AppCompatActivity {
    private EditText UserAnswerTreasureHunt;
    private Button checkAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        UserAnswerTreasureHunt = (EditText)findViewById(R.id.editText_AnswerTreasureHunt);
        checkAnswer = (Button)findViewById(R.id.button_CheckAnswer);

        // True Answer for Treasure Hunt Question
        String trueAnswerTreasureHunt = "love god with all your heart";

        // User input OK button
        checkAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // take user input as answer
                String userInputAnswer = UserAnswerTreasureHunt.getText().toString().trim().toLowerCase();

                // compare to true answer
                if(userInputAnswer.equals(trueAnswerTreasureHunt)){
                    // change button color to green
                    checkAnswer.setBackgroundColor(ContextCompat.getColor(org.tmcindonesia.tmc_explorer.questions.Question1.this, R.color.green_true_answer));
                    // notify correct answer
                    Toast.makeText(org.tmcindonesia.tmc_explorer.questions.Question1.this, "Jawaban Kamu Benar!",Toast.LENGTH_SHORT).show();
                }
                if(!userInputAnswer.equals(trueAnswerTreasureHunt)){
                    // change button color to red
                    checkAnswer.setBackgroundColor(ContextCompat.getColor(org.tmcindonesia.tmc_explorer.questions.Question1.this, R.color.red_false_answer));
                    // notify wrong answer
                    Toast.makeText(org.tmcindonesia.tmc_explorer.questions.Question1.this, "Jawaban Kamu Salah, ayo coba lagi",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}