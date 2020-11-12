package org.tmcindonesia.tmc_explorer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button LoginPage, RegisterPage;
    EditText EmailLogin, PasswordLogin;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        boolean networkConnection;
        EmailLogin = findViewById(R.id.editTextEmailLogin);
        PasswordLogin = findViewById(R.id.editTextPasswordLogin);
        LoginPage = findViewById(R.id.buttonLogin);
        RegisterPage = findViewById(R.id.buttonRegister);
        firebaseAuth = FirebaseAuth.getInstance();


        // NETWORK CHECK
        // if no internet, login is not necessary for user to use the App
        networkConnection = isInternetActive();
        if(!networkConnection){
            startActivity(new Intent(getApplicationContext(), Home.class));
            finish();
        }

        // on create activity check if there is a user already login
        // if yes, then go to home page
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Home.class));
            finish();
        }

        // click-register-button
        RegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        // click-login-button
        LoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_login = EmailLogin.getText().toString().trim();
                String password_login = PasswordLogin.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(email_login,password_login).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Logged in  Successfull",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }else{
                            Toast.makeText(LoginActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    // METHOD - check for internet connection
    public boolean isInternetActive(){
        // connectivity manager instance
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        //check mobile connection
        boolean MobileConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED;
        // check wifi connection
        boolean WifiConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
        // return value
        if(MobileConnection || WifiConnection){
            return true;
        }else {
            return false;
        }
    }
}