package org.tmcindonesia.application.RegisterPage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.tmcindonesia.R;
import org.tmcindonesia.application.DataBaseHandler;
import org.tmcindonesia.application.RegisterActivity;
import org.tmcindonesia.application.UserData;
import org.tmcindonesia.tmc_explorer1.questions.LESSON1;

import java.io.File;
import java.util.Calendar;

public class RegisterPageDate extends AppCompatActivity {
    int currentYear, userYear;
    EditText editTextUserName, editTextUserBirthDate;
    public String userName, userBirthDate;
    String keyUserName = "keyusername";
    String keyUserBirthDate = "keyuserbirthdate";
    Button buttonNext;
    DatePickerDialog.OnDateSetListener dateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_register_date);

        editTextUserName = (EditText) findViewById(R.id.TextInputEditText_fullName);
        editTextUserBirthDate = (EditText) findViewById(R.id.TextInputEditText_birthDate);
        buttonNext = (Button) findViewById(R.id.button_next);

        editTextUserBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                currentYear = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONDAY);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                Log.w("date", "calendar: "+String.valueOf(year)+"/"+String.valueOf(month)+"/"+String.valueOf(day));
                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterPageDate.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        // date picker dialog instance
        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                Log.w("date", "onDateSet: dd/mm/yyy is" + dayOfMonth +"/"+ month +"/"+ year);
                String date = dayOfMonth +"/"+ month +"/"+ year;
                userYear = year;
                editTextUserBirthDate.setText(date);
            }
        };

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = editTextUserName.getText().toString().trim();
                userBirthDate = editTextUserBirthDate.getText().toString().trim();

                // Write or Update database
                if(doesDatabaseExist(getApplicationContext(), DataBaseHandler.DataBaseName)){
                    updateEntryNameAndBirthDate(getApplicationContext(),userName,userBirthDate);
                }else {
                    writeEntryNameAndBirthDate(getApplicationContext(),userName,userBirthDate);
                }

                // check user ages
                if (currentYear-userYear<13){
                    Toast.makeText(getApplicationContext(), "umur kamu di bawah 13 tahun", Toast.LENGTH_SHORT).show();
                }
                else{
                    startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                }
            }
        });
    }

    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    public void updateEntryNameAndBirthDate(Context c, String userName, String userBirthDate){
        // create instance
        DataBaseHandler dataBaseHandler = new DataBaseHandler(c);
        SQLiteDatabase database = dataBaseHandler.getWritableDatabase();
        // put value
        ContentValues values = new ContentValues();
        values.put(UserData.UserDetails.COLUMN_USER_NAME, userName);
        values.put(UserData.UserDetails.COLUMN_USER_BIRTHDAY, userBirthDate);
        // update database
        database.update(UserData.UserDetails.TABLE_NAME,values,"_id = 1", null);
        // close database connection
        database.close();
    }

    public void writeEntryNameAndBirthDate(Context c, String userName, String userBirthDate){
        UserData userData = new UserData(userName, null, userBirthDate, null, null, null, null);
        DataBaseHandler dataBaseHandler = new DataBaseHandler(c);
        boolean statusDataBase = dataBaseHandler.addUser(userName, null, userBirthDate, null, null, null, null);
        if(statusDataBase){
            Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Insertion Failed", Toast.LENGTH_SHORT).show();
        }
    }
}