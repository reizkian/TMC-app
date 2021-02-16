package org.tmcindonesia.content.tmc_Explorer2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.tmcindonesia.R;
import org.tmcindonesia.application.HomeApp;


public class HomeExplorer2 extends AppCompatActivity {
    //Variable for Button List
    ListView listView;
    String mTitle[] =
            {"Yesus Kristus adalah Allah",
                    "Yesus Kristus adalah Tuhan atas segalanya",
                    "Allah di pihakku",
                    "Aku berada dalam keluarga Allah",
                    "Aku berada dalam Kristus",
                    "KRISTUS hidup dalamku!",
                    "Musuhku \"dunia\"",
                    "Musuhku diriku sendiri!",
                    "Musuhku setan",
                    "Allah ingin memakai aku",
                    "Kamu dapat menjadi penjala jiwa",
                    "Yesus mengasihi aku!"};

    String mDescription[] =
            {"Allah ingin supaya kita mengenal siapa Yesus",
                    "Cerita yang paling berharga di dunia ini",
                    "Allah adalah Bapaku yang di Surga",
                    "Aku dilahirkan kembali",
                    "Apa rencana Allah untuk kita?",
                    "Menjadi anak Allah adalah hal yang paling luar biasa",
                    "Alkitab menggunakan kata \"dunia\" untuk tiga hal",
                    "Musuh di dalamku namanya \"kedagingan\"",
                    "Musuh mengerikan yang ingin menghancurkan kita",
                    "Apa yang harus kita lakukan bila kita ingin dipakai Allah?",
                    "Mengapa begitu penting untuk memberitakan Yesus?",
                    "Tuhan Yesus adalah pribadi yang luar biasa!"};

    int images[] =
            {R.drawable.exp2_icon1,
                    R.drawable.exp2_icon2,
                    R.drawable.exp2_icon3,
                    R.drawable.exp2_icon4,
                    R.drawable.exp2_icon5,
                    R.drawable.exp2_icon6,
                    R.drawable.exp2_icon7,
                    R.drawable.exp2_icon8,
                    R.drawable.exp2_icon9,
                    R.drawable.exp2_icon10,
                    R.drawable.exp2_icon11,
                    R.drawable.exp2_icon12};

    String mLessonNumber[] = {
            "Pelajaran 1",
            "Pelajaran 2",
            "Pelajaran 3",
            "Pelajaran 4",
            "Pelajaran 5",
            "Pelajaran 6",
            "Pelajaran 7",
            "Pelajaran 8",
            "Pelajaran 9",
            "Pelajaran 10",
            "Pelajaran 11",
            "Pelajaran 12"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Collapsing Toolbar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorer2_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("TMC Indonesia");
                    collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

        // Floating Button
//        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                // open TMC Indonesia website
//                // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tmcindonesia.org/")));
//                startActivity(new Intent(getApplicationContext(),TmcProfile.class));
//            }
//        });

        // List View Button
        listView = findViewById(R.id.listView_exp2);
        // now create an adapter class
        HomeExplorer2.MyAdapter adapter = new HomeExplorer2.MyAdapter(this, mTitle, mDescription, images, mLessonNumber);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson1Read.class));
                }
                if (position ==  1) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson2Read.class));
                }
                if (position ==  2) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson3Read.class));
                }
                if (position ==  3) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson4Read.class));
                }
                if (position ==  4) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson5Read.class));
                }
                if (position ==  5) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson6Read.class));
                }
                if (position ==  6) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson7Read.class));
                }
                if (position ==  7) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson8Read.class));
                }
                if (position ==  8) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson9Read.class));
                }
                if (position ==  9) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson10Read.class));
                }
                if (position ==  10) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson11Read.class));
                }
                if (position ==  11) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson12Read.class));
                }
            }
        });
    }

    // data adapter class for List View
    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];
        String[] rLessonNumber;

        MyAdapter(Context c, String title[], String description[], int imgs[], String lesson_number[]) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;
            this.rLessonNumber = lesson_number;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);
            TextView myLessonNumber = row.findViewById(R.id.textView3);

            //set our resources on views
            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);
            myLessonNumber.setText(rLessonNumber[position]);
            return row;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), HomeApp.class));
        super.onBackPressed();
    }
}