package org.tmcindonesia.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.tmcindonesia.R;
import org.tmcindonesia.tmc_explorer1.HomeExplorer1;


public class CertificateGenerator extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate_generator);


        FloatingActionButton fab_finished;
        fab_finished = findViewById(R.id.fab_finished);
        fab_finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeExplorer1.class));
            }
        });
    }
}