package org.tmcindonesia.tmc_Explorer1;

import androidx.appcompat.app.AppCompatActivity;
import org.tmcindonesia.R;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TmcProfile extends AppCompatActivity {
    private Button websiteTMC;
    private Button privacyPolicy;
    private Button infoParents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmcprofile);

        websiteTMC = findViewById(R.id.button_websitetmc);
        privacyPolicy = findViewById(R.id.button_privacypolicy);
        infoParents = findViewById(R.id.button_infoforparents);

        websiteTMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tmcindonesia.org/")));
            }
        });

        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.privacypolicies.com/live/3a96b097-2db7-4d6e-be14-7f2395073578")));
            }
        });
    }
}