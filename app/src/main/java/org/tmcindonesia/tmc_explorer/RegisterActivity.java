package org.tmcindonesia.tmc_explorer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText emailRegister, passwordRegister, userNameRegister, cityRegister, institutionRegister;
    Button buttonRegister;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressBar progressBar;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailRegister = findViewById(R.id.editTextEmailRegister);
        passwordRegister = findViewById(R.id.editTextPasswordRegister);
        userNameRegister = findViewById(R.id.editTextUserNameRegister);
        cityRegister = findViewById(R.id.editTextCityRegister);
        institutionRegister = findViewById(R.id.editTextInstitutionRegister);
        buttonRegister = findViewById(R.id.buttonRegisterCreate);
        progressBar = findViewById(R.id.progressBar);

        // fire base authentication instance
        firebaseAuth = FirebaseAuth.getInstance();
        // if user is authenticated then go to home page
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Home.class));
            finish();
        }

        // fire store instance
        firebaseFirestore = FirebaseFirestore.getInstance();

        // REGISTER button clicked
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the text box string
                String email = emailRegister.getText().toString().trim();
                String password = passwordRegister.getText().toString().trim();
                String username = userNameRegister.getText().toString().trim();
                String city = cityRegister.getText().toString().trim();
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

                // progressBar set to visible
                progressBar.setVisibility(View.VISIBLE);

                //REGISTER USER IN FIRE BASE
                // handles user creation
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "daftar berhasil", Toast.LENGTH_SHORT).show();
                            // FireStore Data Base
                            userID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
                            Map<String, Object> user  = new HashMap<>();
                            user.put("FullName",username);
                            user.put("email",email);
                            user.put("kota", city);
                            user.put("institusi", institution);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG,"onSuccess: create user profile" + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"onFailure: "+ e.toString());
                                }
                            });
                            // go to HOME activity
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }else {
                            Toast.makeText(RegisterActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}