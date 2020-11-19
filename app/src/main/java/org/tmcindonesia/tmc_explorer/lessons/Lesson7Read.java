package org.tmcindonesia.tmc_explorer.lessons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import org.tmcindonesia.tmc_explorer.R;

public class Lesson7Read extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_layout);

        // PDF View
        // pdf-view instance object
        PDFView pdfView = findViewById(R.id.pdfView);
        // pdf-view method
        pdfView.fromAsset("explorer1_published.pdf")
                .pages(50,51,52,53,54)
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
    }
}