package org.tmcindonesia.content.tmc_Explorer1.questions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import org.tmcindonesia.R;
import org.tmcindonesia.application.DataBaseHandler;
import org.tmcindonesia.application.UserInput.UserData;
import org.tmcindonesia.content.tmc_Explorer1.HomeExplorer1;
import org.tmcindonesia.application.UserInput.UserAnswer;

import java.util.HashMap;
import java.util.Map;

public class LESSON10 extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    String userName;
    public static final String TAG = "TAG";
    // variable QUESTIONS PAGE
    private int correctAnswerQuestionsPage[] = {1, 1, 0, 0, 0};
    private String questions_ayojawab[];
    private String answers_ayojawab[];
    private TextView textView_quesion1, textView_quesion2, textView_quesion3, textView_quesion4, textView_quesion5;
    private RadioGroup rgqp_question1, rgqp_question2, rgqp_question3, rgqp_question4, rgqp_question5;
    private RadioButton rb_question1, rb_question2, rb_question3, rb_question4, rb_question5;
    private Button getCheckAnswerQuestionsPage;
    private int numberOfCorrectAnswer = 0;
    private static final String key_rb_question1 = "key_rb_question1";
    private static final String key_rb_question2 = "key_rb_question2";
    private static final String key_rb_question3 = "key_rb_question3";
    private static final String key_rb_question4 = "key_rb_question4";
    private static final String key_rb_question5 = "key_rb_question5";
    // variable MY JOURNEY WITH JESUS
    private EditText mjwj_answer1, mjwj_answer2, mjwj_answer3, mjwj_answer4;
    private String AnswersMJWJ[] = {};
    private Button getAnswerMJWJ;
    private String userAnswers;
    private static final String key_mjwj_answer1 = "key_mjwj_answer1";
    private static final String key_mjwj_answer2 = "key_mjwj_answer2";
    private static final String key_mjwj_answer3 = "key_mjwj_answer3";
    private static final String key_mjwj_answer4 = "key_mjwj_answer4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorer1_question10);

        // *******************************QUESTIONS PAGE*********************************
        // get layout ID radio group
        rgqp_question1 = (RadioGroup) findViewById(R.id.radioGroup_QuestionPage10_MultipleChoiceQuestion1);
        rgqp_question2 = (RadioGroup) findViewById(R.id.radioGroup_QuestionPage10_MultipleChoiceQuestion2);
        rgqp_question3 = (RadioGroup) findViewById(R.id.radioGroup_QuestionPage10_MultipleChoiceQuestion3);
        rgqp_question4 = (RadioGroup) findViewById(R.id.radioGroup_QuestionPage10_MultipleChoiceQuestion4);
        rgqp_question5 = (RadioGroup) findViewById(R.id.radioGroup_QuestionPage10_MultipleChoiceQuestion5);
        // get layout ID text view question
        textView_quesion1 = (TextView) findViewById(R.id.textView_QuestionPage10_MultipleChoiceQuestion1);
        textView_quesion2 = (TextView) findViewById(R.id.textView_QuestionPage10_MultipleChoiceQuestion2);
        textView_quesion3 = (TextView) findViewById(R.id.textView_QuestionPage10_MultipleChoiceQuestion3);
        textView_quesion4 = (TextView) findViewById(R.id.textView_QuestionPage10_MultipleChoiceQuestion4);
        textView_quesion5 = (TextView) findViewById(R.id.textView_QuestionPage10_MultipleChoiceQuestion5);
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
                // get string from questions text view layout
                try{
                    questions_ayojawab = new String[]{
                            textView_quesion1.getText().toString().trim(),
                            textView_quesion2.getText().toString().trim(),
                            textView_quesion3.getText().toString().trim(),
                            textView_quesion4.getText().toString().trim(),
                            textView_quesion5.getText().toString().trim()
                    };
                    answers_ayojawab= new String[]{
                            rb_question1.getText().toString().trim(),
                            rb_question2.getText().toString().trim(),
                            rb_question3.getText().toString().trim(),
                            rb_question4.getText().toString().trim(),
                            rb_question5.getText().toString().trim(),
                    };
                }catch(Exception e){
                    return;
                }
                checkAnswerQuestionsPage(correctAnswerQuestionsPage, rb_index_array);
            }

            // getNumberOfCorrectAnswer
            public void checkAnswerQuestionsPage(int[] listOfCorrectAnswer, int[] listOfUserAnswer) {
                // reset number
                numberOfCorrectAnswer = 0;
                for (int index = 0; index < listOfCorrectAnswer.length; index++) {
                    if (listOfCorrectAnswer[index] == listOfUserAnswer[index]) {
                        numberOfCorrectAnswer++;
                    }
                }
                Toast.makeText(LESSON10.this,
                        String.valueOf(numberOfCorrectAnswer) + " soal kamu jawab dengan benar",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // ***************************MY JOURNEY WITH JESUS*******************************
        // get layout ID
        mjwj_answer1 = (EditText) findViewById(R.id.editText_QuestionPage10_MJWJAnswer1);
        mjwj_answer2 = (EditText) findViewById(R.id.editText_QuestionPage10_MJWJAnswer2);
        mjwj_answer3 = (EditText) findViewById(R.id.editText_QuestionPage10_MJWJAnswer3);
        mjwj_answer4 = (EditText) findViewById(R.id.editText_QuestionPage10_MJWJAnswer4);
        getAnswerMJWJ = (Button) findViewById(R.id.button_CheckAnswer_MJWJ);
        // Ok button clicked MY JOURNEY WITH JESUS
        getAnswerMJWJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create instance
                try{
                    UserAnswer userAnswers = new UserAnswer(
                            numberOfCorrectAnswer,
                            mjwj_answer1.getText().toString().trim(),
                            mjwj_answer2.getText().toString().trim(),
                            mjwj_answer3.getText().toString().trim(),
                            mjwj_answer4.getText().toString().trim(),
                            answers_ayojawab[0],
                            answers_ayojawab[1],
                            answers_ayojawab[2],
                            answers_ayojawab[3],
                            answers_ayojawab[4]);
                    // write data base method
                    writeUserAnswerToDataBase(userAnswers);
                }catch (Exception e){
                    return;
                }
                // save preferences
                SavePreferences();
                // toast
                Toast.makeText(LESSON10.this,
                        "Terimakasih, ayo lanjutkan pelajaran mu",
                        Toast.LENGTH_SHORT).show();
                // move to home page
                startActivity(new Intent(getApplicationContext(), HomeExplorer1.class));
            }
        });
        LoadPreferences();
    }


    // WRITE DATA TO FIRE STORE DATA BASE
    public void writeUserAnswerToDataBase(UserAnswer userAnswers) {
        // get the content
        String className = this.getClass().getSimpleName().toString();
        // question page answers
        Map<String, Object> answers_qp = new HashMap<>();
        answers_qp.put("Correct answer", userAnswers.getNumberOfCorrectAnswer());
        answers_qp.put(questions_ayojawab[0].replace(".",""),userAnswers.getUserAnswerAyoJawab1());
        answers_qp.put(questions_ayojawab[1].replace(".",""),userAnswers.getUserAnswerAyoJawab2());
        answers_qp.put(questions_ayojawab[2].replace(".",""),userAnswers.getUserAnswerAyoJawab3());
        answers_qp.put(questions_ayojawab[3].replace(".",""),userAnswers.getUserAnswerAyoJawab4());
        answers_qp.put(questions_ayojawab[4].replace(".",""),userAnswers.getUserAnswerAyoJawab5());
        Log.d("HashMap", answers_qp.toString());
        // my journey with Jesus answers
        Map<String, Object> answers_mjwj = new HashMap<>();
        answers_mjwj.put(getResources().getString(R.string.MJWJ1_question1).replace(".",""), userAnswers.getUserAnswerMJWJ1());
        answers_mjwj.put(getResources().getString(R.string.MJWJ1_question2).replace(".",""), userAnswers.getUserAnswerMJWJ2());
        answers_mjwj.put(getResources().getString(R.string.MJWJ1_question3).replace(".",""), userAnswers.getUserAnswerMJWJ3());
        answers_mjwj.put(getResources().getString(R.string.MJWJ1_question4).replace(".",""), userAnswers.getUserAnswerMJWJ4());
        Log.d("HashMap", answers_mjwj.toString());
        // create fire base instance
        firebaseFirestore = FirebaseFirestore.getInstance();
        userName = getUserNameFromDataBase(this);
        firebaseAuth = firebaseAuth.getInstance();
        String userID = firebaseAuth.getCurrentUser().getUid();
        String userEmail = firebaseAuth.getCurrentUser().getEmail();
        // write on Fire Base
        firebaseFirestore.collection("TMC EXPLORER ONE USER").document(userID).collection(className).document("Questions Page")
                .set(answers_qp)
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
        firebaseFirestore.collection("TMC EXPLORER ONE USER").document(userID).collection(className).document("My Journey With Jesus")
                .set(answers_mjwj)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
        // write to realtime database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference realtimeDatabase = firebaseDatabase.getReference();
        realtimeDatabase.child("__collections__")
                .child("TMC EXPLORER ONE USER")
                .child(userID)
                .child("__collections__")
                .child(className)
                .child("My Journey With Jesus")
                .setValue(answers_qp).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Error writing document", e);
            }
        });
        realtimeDatabase.child("__collections__")
                .child("TMC EXPLORER ONE USER")
                .child(userID)
                .child("__collections__")
                .child(className)
                .child("Questions Page")
                .setValue(answers_mjwj);
    }

    // SAVE PREFERENCE WHEN BACK BACK PRESSED and ACTIVITY GET DESTROYED
    private void SavePreferences() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key_mjwj_answer1, mjwj_answer1.getText().toString());
        editor.putString(key_mjwj_answer2, mjwj_answer2.getText().toString());
        editor.putString(key_mjwj_answer3, mjwj_answer3.getText().toString());
        editor.putString(key_mjwj_answer4, mjwj_answer4.getText().toString());
        editor.commit();
    }

    private void LoadPreferences() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        // reset number
        numberOfCorrectAnswer = 0;
        // set text just like when the user leave it (back pressed)
        // load user answer MULTIPLE CHOICE
        rgqp_question1.check(sharedPreferences.getInt(key_rb_question1,rgqp_question1.getCheckedRadioButtonId()));
        rgqp_question2.check(sharedPreferences.getInt(key_rb_question2,rgqp_question2.getCheckedRadioButtonId()));
        rgqp_question3.check(sharedPreferences.getInt(key_rb_question3,rgqp_question3.getCheckedRadioButtonId()));
        rgqp_question4.check(sharedPreferences.getInt(key_rb_question4,rgqp_question4.getCheckedRadioButtonId()));
        rgqp_question5.check(sharedPreferences.getInt(key_rb_question5,rgqp_question5.getCheckedRadioButtonId()));
        // set text just like when the user leave it (back pressed)
        mjwj_answer1.setText(sharedPreferences.getString(key_mjwj_answer1, mjwj_answer1.getText().toString()));
        mjwj_answer2.setText(sharedPreferences.getString(key_mjwj_answer2, mjwj_answer2.getText().toString()));
        mjwj_answer3.setText(sharedPreferences.getString(key_mjwj_answer3, mjwj_answer3.getText().toString()));
        mjwj_answer4.setText(sharedPreferences.getString(key_mjwj_answer4, mjwj_answer4.getText().toString()));
    }

    @Override
    public void onBackPressed() {
        SavePreferences();
        super.onBackPressed();
    }

    public String getUserNameFromDataBase(Context c){
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
}