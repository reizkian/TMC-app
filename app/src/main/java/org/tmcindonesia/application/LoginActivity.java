package org.tmcindonesia.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.tmcindonesia.R;
import org.tmcindonesia.tmc_explorer1.HomeExplorer1;

public class LoginActivity extends AppCompatActivity {
    Button LoginPage;
    TextView RegisterPage;
    EditText EmailLogin, PasswordLogin;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_login);

        boolean networkConnection;
        EmailLogin = findViewById(R.id.email);
        PasswordLogin = findViewById(R.id.password);
        LoginPage = findViewById(R.id.buttonLogin);
        RegisterPage = findViewById(R.id.buttonRegister);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBarLogin = findViewById(R.id.progressBarLogin);


        // NETWORK CHECK
        // if no internet, login is not necessary for user to use the App
        networkConnection = isInternetActive();
        if(!networkConnection){
            startActivity(new Intent(getApplicationContext(), HomeApp.class));
            finish();
        }

        // on create activity check if there is a user already login
        // if yes, then go to home page
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), HomeApp.class));
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
                // set progressBar to visible
                progressBarLogin.setVisibility(View.VISIBLE);

                String email_login = EmailLogin.getText().toString().trim();
                String password_login = PasswordLogin.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(email_login,password_login).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Logged in  Successfull",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeApp.class));
                        }else{
                            Toast.makeText(LoginActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("FireBaseLoginAttempt", task.getException().getMessage()+" email: "+email_login+", password: "+ password_login);
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