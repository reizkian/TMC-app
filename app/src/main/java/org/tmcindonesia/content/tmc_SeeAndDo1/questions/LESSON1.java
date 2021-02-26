package org.tmcindonesia.content.tmc_SeeAndDo1.questions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.tmcindonesia.R;
import org.tmcindonesia.application.DataBaseHandler;
import org.tmcindonesia.application.UserInput.UserData;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LESSON1 extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    private int correctAnswerQuestionsPage[] = {1, 0, 0, 0};
    private int numberOfCorrectAnswer;
    private TextView question1, question2, question3, question4;
    private RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4;
    private Button okButton;
    public static final String TAG = "TAG";

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
                writeUserAnswerToDataBase(questionTexts, answerTexts, numberOfCorrectAnswer);
            }
        });
        LoadPreferences();
    }



    // get Number Of Correct Answer
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

    /** SAVE REFERENCES **/
    /**the methods bellow are for saving user input and load it again
    when this page is loaded on screen **/

    private static final String keys[] = {
            "key_question1",
            "key_question2",
            "key_question3",
            "key_question4"
    };
    // back press override save reference
    @Override
    public void onBackPressed() {
        SavePreferences();
        super.onBackPressed();
    }
    // save references
    private void SavePreferences() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // save user answers multiple choices
        editor.putInt(keys[0], radioGroup1.getCheckedRadioButtonId());
        editor.putInt(keys[1], radioGroup2.getCheckedRadioButtonId());
        editor.putInt(keys[2], radioGroup3.getCheckedRadioButtonId());
        editor.putInt(keys[3], radioGroup4.getCheckedRadioButtonId());
        editor.commit();
    }

    // load references
    private void LoadPreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        // reset correct number counter
        numberOfCorrectAnswer = 0;
        // load user answers multiple choices
        radioGroup1.check(sharedPreferences.getInt(keys[0],radioGroup1.getCheckedRadioButtonId()));
        radioGroup2.check(sharedPreferences.getInt(keys[1],radioGroup2.getCheckedRadioButtonId()));
        radioGroup3.check(sharedPreferences.getInt(keys[2],radioGroup3.getCheckedRadioButtonId()));
        radioGroup4.check(sharedPreferences.getInt(keys[3],radioGroup4.getCheckedRadioButtonId()));
    }

    /** WRITE TO DATABASE - FIREBASE **/
    /** the methods bellow are for saving user input to
     remote and local data base **/
    // get User Name
    private String getUserNameFromDataBase(Context c){
        DataBaseHandler dataBaseHandler = new DataBaseHandler(c);
        SQLiteDatabase database = dataBaseHandler.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ UserData.UserDetails.TABLE_NAME,null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            String username = cursor.getString(1).toString().trim();
            return username;
        }
        else {
            return null;
        }
    }
    private void writeUserAnswerToDataBase(String[] textQuestions, String[] textAnswers, int correctAnswer){
        // get class name
        String className = this.getClass().getSimpleName().toString();
        // put question and answer to HashMap
        Map<String, Object> answers = new HashMap<>();
        answers.put("Correct answers", correctAnswer);
        answers.put(textQuestions[0], textAnswers[0]);
        answers.put(textQuestions[1], textAnswers[1]);
        answers.put(textQuestions[2], textAnswers[2]);
        answers.put(textQuestions[3], textAnswers[3]);
        // instantiate firebase object
        firebaseFirestore = firebaseFirestore.getInstance();
        String userName = getUserNameFromDataBase(this);
        // write data to fireStore
        firebaseFirestore
                .collection("TMC SEE and DO ONE USER")
                .document(userName)
                .collection(className)
                .document("Questions Page")
                .set(answers)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "successfully written!");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error writing document", e);
            }
        });
    }
}