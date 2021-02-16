package org.tmcindonesia.content.tmc_SeeAndDo;

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
import org.tmcindonesia.content.tmc_Explorer2.HomeExplorer2;

public class HomeSeeAndDo1 extends AppCompatActivity {
    ListView listView;
    String mTitle[] =
            {"Kereta Merah",
                    "Taman Tuhan yang Indah",
                    "Makna sesungguhnya dari Natal",
                    "Lebah yang marah",
                    "Apakah aku masih terlalu kecil?",
                    "Kado hebat dari Allah",
                    "Rumah Allah yang indah"
            };

    String mDescription[] =
            {"Ken dan Amy menarik sebuah kereta merah kepuncak bukit",
                    "Ken dan Amy menyiangi ilalang pada taman bunganya",
                    "Ken dan Amy memandangi barisan semut",
                    "Ken mengganggu sarang lebah",
                    "Ken dan Amy menyebrangi sungai",
                    "Amy ingin rok baru",
                    "Nenek Ken dan Amy sedang sakit"
            };

    int images[] =
            {R.drawable.snd1_icon1,
                    R.drawable.snd1_icon2,
                    R.drawable.snd1_icon3,
                    R.drawable.snd1_icon4,
                    R.drawable.snd1_icon5,
                    R.drawable.snd1_icon6,
                    R.drawable.snd1_icon7};

    String mLessonNumber[] = {
            "Pelajaran 1",
            "Pelajaran 2",
            "Pelajaran 3",
            "Pelajaran 4",
            "Pelajaran 5",
            "Pelajaran 6",
            "Pelajaran 7"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Collapsing Toolbar
        // Collapsing Toolbar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snd1_home);
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

        // List View Button
        listView = (ListView)findViewById(R.id.listView_snd1);
        // now create an adapter class
        HomeSeeAndDo1.MyAdapter adapter = new HomeSeeAndDo1.MyAdapter(this, mTitle, mDescription, images, mLessonNumber);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    //startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson1Read.class));
                }
                if (position ==  1) {
                    //startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson2Read.class));
                }
                if (position ==  2) {
                    //startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson3Read.class));
                }
                if (position ==  3) {
                    //startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson4Read.class));
                }
                if (position ==  4) {
                    //startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson5Read.class));
                }
                if (position ==  5) {
                    //startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson6Read.class));
                }
                if (position ==  6) {
                    //startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson7Read.class));
                }
                if (position ==  7) {
                    //startActivity(new Intent(getApplicationContext(), org.tmcindonesia.content.tmc_Explorer2.lessons.Exp2Lesson8Read.class));
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
