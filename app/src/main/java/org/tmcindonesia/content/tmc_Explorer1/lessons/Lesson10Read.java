package org.tmcindonesia.content.tmc_Explorer1.lessons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.tmcindonesia.R;
import org.tmcindonesia.content.tmc_Explorer1.questions.LESSON10;

public class Lesson10Read extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_layout);

        // PDF View
        // pdf-view instance object
        PDFView pdfView = findViewById(R.id.pdfView);
        // pdf-view method
        pdfView.fromAsset("explorer1_published.pdf")
                .pages(74,75,76,77,78,79)
                .enableSwipe(true)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true)
                .autoSpacing(false)
                .fitEachPage(true)
                .spacing(0)
                .pageSnap(false)
                .pageFling(false)
                .nightMode(false)
                .load();

        FloatingActionButton fab_goToQuestion;
        fab_goToQuestion = findViewById(R.id.fab_question);
        fab_goToQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LESSON10.class));
            }
        });
    }
}