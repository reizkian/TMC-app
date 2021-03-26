package org.tmcindonesia.content.tmc_SeeAndDo1.questions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import org.tmcindonesia.content.tmc_SeeAndDo1.HomeSeeAndDo1;

import java.util.HashMap;
import java.util.Map;

public class LESSON7 extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    private int correctAnswerQuestionsPage[] = {1,1,0,1,0,0,0,0,0};
    private int numberOfCorrectAnswer;
    private TextView question1, question2;
    private EditText accJesusDate;
    private RadioGroup radioGroup2;
    private Button okButton;
    private CheckBox checkBoxAlkitab, checkBoxJesus, checkBoxTomb, checkBoxAngels;
    private CheckBox checkBoxMeds, checkBoxSun, checkBoxMoon, checkBoxDosa;
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snd1_question7);

        // questions TextViews
        question1 = (TextView) findViewById(R.id.textView_question1);
        question2 = (TextView) findViewById(R.id.textView_question2);

        // question 1 checkBox
        checkBoxAlkitab = (CheckBox) findViewById(R.id.checkbox_alkitab);
        checkBoxJesus = (CheckBox) findViewById(R.id.checkbox_jesus);
        checkBoxTomb = (CheckBox) findViewById(R.id.checkbox_tomb);
        checkBoxAngels = (CheckBox) findViewById(R.id.checkbox_angels);
        checkBoxMeds = (CheckBox) findViewById(R.id.checkbox_meds);
        checkBoxSun = (CheckBox) findViewById(R.id.checkbox_sun);
        checkBoxMoon = (CheckBox) findViewById(R.id.checkbox_moon);
        checkBoxDosa = (CheckBox) findViewById(R.id.checkbox_dosa);

        // radioGroup
        radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup_question2);

        // editText accept Jesus date
        accJesusDate = (EditText) findViewById(R.id.editText_DateAcceptedJesus);

        //okButton
        okButton = (Button) findViewById(R.id.button_CheckAnswer_QuestionsPage);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the Question Texts of each questions
                String questionText1 = question1.getText().toString().trim();
                String questionText2 = question2.getText().toString().trim();
                String questionTexts[] = {
                        questionText1,
                        questionText2,
                        "Accept Jesus Date: "
                };
                // get the ID of checked button for a radio group
                int radioButtonChecked2 = radioGroup2.getCheckedRadioButtonId();
                // get the Index of checked button for a radio group
                int indexButtonChecked2 = radioGroup2.indexOfChild((RadioButton)findViewById(radioButtonChecked2));
                //  get the Answer Texts of checked button for a radio group
                String answerTextQuestion2 = ((RadioButton) findViewById(radioButtonChecked2)).getText().toString().trim();
                // answers
                int indexAnswers[] = {
                        checkBoxAlkitab.isChecked()? 1:0,
                        checkBoxJesus.isChecked()? 1:0,
                        checkBoxTomb.isChecked()? 1:0,
                        checkBoxAngels.isChecked()? 1:0,
                        checkBoxMeds.isChecked()? 1:0,
                        checkBoxSun.isChecked()? 1:0,
                        checkBoxMoon.isChecked()? 1:0,
                        checkBoxDosa.isChecked()? 1:0,
                        indexButtonChecked2
                };
                String textAnswers[] = {
                        checkBoxAlkitab.isChecked()? "Buku kehidupan":"-",
                        checkBoxJesus.isChecked()? "Tuhan Yesus":"-",
                        checkBoxTomb.isChecked()? "Makam":"-",
                        checkBoxAngels.isChecked()? "Malaikat-malaikat":"-",
                        checkBoxMeds.isChecked()? "Obat-obatan":"-",
                        checkBoxSun.isChecked()? "Matahari":"-",
                        checkBoxMoon.isChecked()? "Bulan":"-",
                        checkBoxDosa.isChecked()? "Dosa":"-",
                        answerTextQuestion2,
                        accJesusDate.getText().toString().trim()
                };
                // check answer
                checkAnswerQuestionsPage(correctAnswerQuestionsPage, indexAnswers);
                writeUserAnswerToDataBase(questionTexts, textAnswers, numberOfCorrectAnswer);
                startActivity(new Intent(getApplicationContext(), HomeSeeAndDo1.class));
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
            "key_box1",
            "key_box2",
            "key_box3",
            "key_box4",
            "key_box5",
            "key_box6",
            "key_box7",
            "key_box8",
            "key_question2",
            "key_accJesus"
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
        editor.putBoolean(keys[0], checkBoxAlkitab.isChecked());
        editor.putBoolean(keys[1], checkBoxJesus.isChecked());
        editor.putBoolean(keys[2], checkBoxTomb.isChecked());
        editor.putBoolean(keys[3], checkBoxAngels.isChecked());
        editor.putBoolean(keys[4], checkBoxMeds.isChecked());
        editor.putBoolean(keys[5], checkBoxSun.isChecked());
        editor.putBoolean(keys[6], checkBoxMoon.isChecked());
        editor.putBoolean(keys[7], checkBoxDosa.isChecked());
        editor.putInt(keys[8], radioGroup2.getCheckedRadioButtonId());
        editor.putString(keys[9], accJesusDate.getText().toString().trim());
        editor.commit();
    }

    // load references
    private void LoadPreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        // reset correct number counter
        numberOfCorrectAnswer = 0;
        // load user answers multiple choices
        checkBoxAlkitab.setChecked(sharedPreferences.getBoolean(keys[0],checkBoxAlkitab.isChecked()));
        checkBoxJesus.setChecked(sharedPreferences.getBoolean(keys[1],checkBoxJesus.isChecked()));
        checkBoxTomb.setChecked(sharedPreferences.getBoolean(keys[2],checkBoxTomb.isChecked()));
        checkBoxAngels.setChecked(sharedPreferences.getBoolean(keys[3],checkBoxAngels.isChecked()));
        checkBoxMeds.setChecked(sharedPreferences.getBoolean(keys[4],checkBoxMeds.isChecked()));
        checkBoxSun.setChecked(sharedPreferences.getBoolean(keys[5],checkBoxSun.isChecked()));
        checkBoxMoon.setChecked(sharedPreferences.getBoolean(keys[6],checkBoxMoon.isChecked()));
        checkBoxDosa.setChecked(sharedPreferences.getBoolean(keys[7],checkBoxDosa.isChecked()));
        radioGroup2.check(sharedPreferences.getInt(keys[8],radioGroup2.getCheckedRadioButtonId()));
        accJesusDate.setText(sharedPreferences.getString(keys[9], accJesusDate.getText().toString().trim()));
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
        String compoundAnswerQuestion1 = textAnswers[0]+
                textAnswers[1]+", "+
                textAnswers[2]+", "+
                textAnswers[3]+", "+
                textAnswers[4]+", "+
                textAnswers[5]+", "+
                textAnswers[6]+", "+
                textAnswers[7];
        answers.put(textQuestions[0], compoundAnswerQuestion1);
        answers.put(textQuestions[1], textAnswers[8]);
        answers.put(textQuestions[2], textAnswers[9]);
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