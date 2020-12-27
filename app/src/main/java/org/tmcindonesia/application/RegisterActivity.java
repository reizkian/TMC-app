package org.tmcindonesia.application;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.tmcindonesia.R;
import org.tmcindonesia.tmc_explorer1.HomeExplorer1;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "FireBase";
    public String userName;
    EditText emailRegister, passwordRegister, getPasswordRegisterConfirm, userNameRegister, userPhoneRegister, dateBirthRegister, cityRegister, provinceRegister, institutionRegister;
    Button buttonRegister;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressBar progressBar;
    String userID;
    UserData userData;
    DatePickerDialog.OnDateSetListener dateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_register);

        emailRegister = findViewById(R.id.editTextEmailRegister);
        passwordRegister = findViewById(R.id.editTextPasswordRegister);
        getPasswordRegisterConfirm = findViewById(R.id.editTextConfirmPasswordRegister);
        userNameRegister = findViewById(R.id.editTextUserNameRegister);
        userPhoneRegister = findViewById(R.id.editTextUserPhone);
        dateBirthRegister = findViewById(R.id.editTextDateBirth);
        cityRegister = findViewById(R.id.editTextCityRegister);
        provinceRegister = findViewById(R.id.editTextProvinceRegister);
        institutionRegister = findViewById(R.id.editTextInstitutionRegister);
        buttonRegister = findViewById(R.id.buttonRegisterCreate);
        progressBar = findViewById(R.id.progressBar);



        // FIRE BASE
        // fire base authentication instance
        firebaseAuth = FirebaseAuth.getInstance();
        // if user is authenticated then go to home page
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), HomeExplorer1.class));
            finish();
        }
        // fire store instance
        firebaseFirestore = FirebaseFirestore.getInstance();

        // DATE PICKER - user birth date
        // edit text user birth date on click
        dateBirthRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONDAY);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                Log.w("date", "calendar: "+String.valueOf(year)+"/"+String.valueOf(month)+"/"+String.valueOf(day));
                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterActivity.this,
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
                dateBirthRegister.setText(date);
            }
        };


        // REGISTER button clicked
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the text box string
                String email = emailRegister.getText().toString().trim();
                String password = passwordRegister.getText().toString().trim();
                String confirmpass = getPasswordRegisterConfirm.getText().toString().trim();
                String username = userNameRegister.getText().toString().trim();
                String phonenumber = userPhoneRegister.getText().toString().trim();
                String datebirth = dateBirthRegister.getText().toString().trim();
                String city = cityRegister.getText().toString().trim();
                String province = provinceRegister.getText().toString().trim();
                String institution = institutionRegister.getText().toString().trim();

                // check if text box is empty
                if (TextUtils.isEmpty(email)) {
                    emailRegister.setError("mohon masukan email anda");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    passwordRegister.setError("masukan password untuk mendaftar");
                    return;
                }
                if (TextUtils.isEmpty(username)) {
                    passwordRegister.setError("masukan nama untuk mendaftar");
                    return;
                }
                if (TextUtils.isEmpty(phonenumber)) {
                    passwordRegister.setError("masukan nomor hand phone untuk mendaftar");
                    return;
                }
                if(TextUtils.isEmpty(confirmpass)){
                    getPasswordRegisterConfirm.setError("masukan konfirmasi pasword untuk mendaftar");
                }
                if (TextUtils.isEmpty(city)) {
                    cityRegister.setError("masukan kota asal anda");
                    return;
                }
                if (TextUtils.isEmpty(institution)) {
                    institutionRegister.setError("masukan institusi asal anda");
                    return;
                }

                // error message password less than 6 words
                if (password.length() < 6) {
                    passwordRegister.setError("Password harus lebih dari 6 huruf");
                    return;
                }

                // REGISTER USER IN FIRE BASE
                // handles user creation
                if(!password.equals(confirmpass)) {
                    // error message password cannot be confirmed
                    Toast.makeText(RegisterActivity.this, "ulangi password tidak sama", Toast.LENGTH_SHORT).show();
                }
                if(password.equals(confirmpass)){
                    // progressBar set to visible
                    progressBar.setVisibility(View.VISIBLE);
                    // create user
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "daftar berhasil", Toast.LENGTH_SHORT).show();
                                // set user display name
                                FirebaseUser userProfile = FirebaseAuth.getInstance().getCurrentUser();
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
                                userProfile.updateProfile(profileUpdates);
                                // FireStore Data Base
                                userID = firebaseAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = firebaseFirestore.collection("TMC EXPLORER ONE USER").document(username);
                                Map<String, Object> user  = new HashMap<>();
                                user.put("Name",username);
                                user.put("Phone",phonenumber);
                                user.put("Birth Date", datebirth);
                                user.put("Email",email);
                                user.put("City", city);
                                user.put("Province", province);
                                user.put("Institution", institution);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG,"onSuccess: create user profile" + userID);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("FireBaseRegisterAttempt","onFailure: "+ e.toString());
                                    }
                                });
                                // write user data to local data base
                                UserData userData = new UserData(username, email, datebirth, phonenumber, city, province, institution);
                                DataBaseHandler dataBaseHandler = new DataBaseHandler(RegisterActivity.this);
                                boolean statusDataBase = dataBaseHandler.addUser(username, email, datebirth, phonenumber, city, province, institution);
                                if(statusDataBase){
                                    Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Insertion Failed", Toast.LENGTH_SHORT).show();
                                }
                                // go to HOME activity
                                startActivity(new Intent(getApplicationContext(), HomeExplorer1.class));
                            }else
                            {
                                Toast.makeText(RegisterActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}