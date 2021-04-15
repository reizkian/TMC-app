package org.tmcindonesia.content.tmc_Explorer1.questions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
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
import org.tmcindonesia.application.HomeApp;
import org.tmcindonesia.application.DataBaseHandler;
import org.tmcindonesia.application.UserInput.TestimonyAnswer;
import org.tmcindonesia.application.UserInput.UserData;

import java.util.HashMap;
import java.util.Map;

public class LESSON12 extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    String userName;
    public static final String TAG = "TAG";
    // variable QUESTIONS PAGE
    private int correctAnswerQuestionsPage[] = {0, 1, 0, 0, 1};
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
    private EditText mjwj_answer1, mjwj_answer2;
    private String AnswersMJWJ[] = {};
    private Button getAnswerMJWJ;
    private static final String key_mjwj_answer1 = "key_mjwj_answer1";
    private static final String key_mjwj_answer2 = "key_mjwj_answer2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorer1_question12);

        // *******************************QUESTIONS PAGE*********************************
        // get layout ID radio group
        rgqp_question1 = (RadioGroup) findViewById(R.id.radioGroup_QuestionPage12_MultipleChoiceQuestion1);
        rgqp_question2 = (RadioGroup) findViewById(R.id.radioGroup_QuestionPage12_MultipleChoiceQuestion2);
        rgqp_question3 = (RadioGroup) findViewById(R.id.radioGroup_QuestionPage12_MultipleChoiceQuestion3);
        rgqp_question4 = (RadioGroup) findViewById(R.id.radioGroup_QuestionPage12_MultipleChoiceQuestion4);
        rgqp_question5 = (RadioGroup) findViewById(R.id.radioGroup_QuestionPage12_MultipleChoiceQuestion5);
        // get layout ID text view question
        textView_quesion1 = (TextView) findViewById(R.id.textView_QuestionPage12_MultipleChoiceQuestion1);
        textView_quesion2 = (TextView) findViewById(R.id.textView_QuestionPage12_MultipleChoiceQuestion2);
        textView_quesion3 = (TextView) findViewById(R.id.textView_QuestionPage12_MultipleChoiceQuestion3);
        textView_quesion4 = (TextView) findViewById(R.id.textView_QuestionPage12_MultipleChoiceQuestion4);
        textView_quesion5 = (TextView) findViewById(R.id.textView_QuestionPage12_MultipleChoiceQuestion5);
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
                Toast.makeText(LESSON12.this,
                        String.valueOf(numberOfCorrectAnswer) + " soal kamu jawab dengan benar",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // ***************************MY JOURNEY WITH JESUS*******************************
        // get layout ID
        mjwj_answer1 = (EditText) findViewById(R.id.editText_tanggalHidupSepertiKristus);
        mjwj_answer2 = (EditText) findViewById(R.id.editText_QuestionPage12_kesaksianExplorerSatu);
        getAnswerMJWJ = (Button) findViewById(R.id.button_CheckAnswer_MJWJ);
        // Ok button clicked MY JOURNEY WITH JESUS
        getAnswerMJWJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    // create instance
                    TestimonyAnswer testimonyAnswer = new TestimonyAnswer(
                            numberOfCorrectAnswer,
                            mjwj_answer1.getText().toString().trim(),
                            mjwj_answer2.getText().toString().trim(),
                            answers_ayojawab[0],
                            answers_ayojawab[1],
                            answers_ayojawab[2],
                            answers_ayojawab[3],
                            answers_ayojawab[4]
                    );
                    // write data base method
                    writeUserAnswerToDataBase(testimonyAnswer);
                }catch (Exception e){
                    return;
                }
                // check if text box is empty
                if (TextUtils.isEmpty(mjwj_answer1.getText().toString().trim())) {
                    Toast.makeText(LESSON12.this,
                            "Kamu melum menjawab keputusan mu",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mjwj_answer2.getText().toString().trim())) {
                    Toast.makeText(LESSON12.this,
                            "Kamu melum menulis kesaksian mu",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                // save preferences
                SavePreferences();
                // move to home page
                startActivity(new Intent(LESSON12.this, HomeApp.class));
            }
        });

        LoadPreferences();
    }


    // WRITE DATA TO FIRE STORE DATA BASE
    public void writeUserAnswerToDataBase(TestimonyAnswer testimonyAnswer) {
        // get the content
        String className = this.getClass().getSimpleName().toString();
        // question page answers
        Map<String, Object> answers_qp = new HashMap<>();
        answers_qp.put("Correct answer", testimonyAnswer.getNumberOfCorrectAnswer());
        answers_qp.put(questions_ayojawab[0].replace(".",""),testimonyAnswer.getUserAnswerAyoJawab1());
        answers_qp.put(questions_ayojawab[1].replace(".",""),testimonyAnswer.getUserAnswerAyoJawab2());
        answers_qp.put(questions_ayojawab[2].replace(".",""),testimonyAnswer.getUserAnswerAyoJawab3());
        answers_qp.put(questions_ayojawab[3].replace(".",""),testimonyAnswer.getUserAnswerAyoJawab4());
        answers_qp.put(questions_ayojawab[4].replace(".",""),testimonyAnswer.getUserAnswerAyoJawab5());
        // my journey with Jesus answers
        Map<String, Object> answers_mjwj = new HashMap<>();
        answers_mjwj.put(getResources().getString(R.string.MJWJ1_question1).replace(".",""), testimonyAnswer.getUserAnswerMJWJ1());
        answers_mjwj.put(getResources().getString(R.string.MJWJ1_question2).replace(".",""), testimonyAnswer.getUserAnswerMJWJ2());
        // create fire base instance
        firebaseFirestore = FirebaseFirestore.getInstance();
        userName = getUserNameFromDataBase(this);
        // write on Fire Base
        // _id displayName email
        firebaseAuth = firebaseAuth.getInstance();
        String userID = firebaseAuth.getCurrentUser().getUid();
        String userEmail = firebaseAuth.getCurrentUser().getEmail();
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
        editor.commit();
    }

    private void LoadPreferences() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        // reset number
        numberOfCorrectAnswer = 0;
        // load user answer MULTIPLE CHOICE
        rgqp_question1.check(sharedPreferences.getInt(key_rb_question1,rgqp_question1.getCheckedRadioButtonId()));
        rgqp_question2.check(sharedPreferences.getInt(key_rb_question2,rgqp_question2.getCheckedRadioButtonId()));
        rgqp_question3.check(sharedPreferences.getInt(key_rb_question3,rgqp_question3.getCheckedRadioButtonId()));
        rgqp_question4.check(sharedPreferences.getInt(key_rb_question4,rgqp_question4.getCheckedRadioButtonId()));
        rgqp_question5.check(sharedPreferences.getInt(key_rb_question5,rgqp_question5.getCheckedRadioButtonId()));
        // set text just like when the user leave it (back pressed)
        mjwj_answer1.setText(sharedPreferences.getString(key_mjwj_answer1, mjwj_answer1.getText().toString()));
        mjwj_answer2.setText(sharedPreferences.getString(key_mjwj_answer2, mjwj_answer2.getText().toString()));
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