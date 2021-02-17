package org.tmcindonesia.content.tmc_SeeAndDo2;

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
import org.tmcindonesia.content.tmc_SeeAndDo2.lessons.SD2Lesson1Read;
import org.tmcindonesia.content.tmc_SeeAndDo2.lessons.SD2Lesson2Read;
import org.tmcindonesia.content.tmc_SeeAndDo2.lessons.SD2Lesson3Read;
import org.tmcindonesia.content.tmc_SeeAndDo2.lessons.SD2Lesson4Read;
import org.tmcindonesia.content.tmc_SeeAndDo2.lessons.SD2Lesson5Read;
import org.tmcindonesia.content.tmc_SeeAndDo2.lessons.SD2Lesson6Read;
import org.tmcindonesia.content.tmc_SeeAndDo2.lessons.SD2Lesson7Read;

public class HomeSeeAndDo2 extends AppCompatActivity {
    ListView listView;
    String mTitle[] =
            {"Amy punya 2 hari ulang tahun",
                    "Ken dan genangan lumpur",
                    "Yesus menjawab doa",
                    "Malam yang gelap",
                    "Frisky kuda poni yang melarikan diri",
                    "Sedih dan Senang",
                    "Jam dindin rusak"
            };

    String mDescription[] =
            {"Ken dan Amy menghadiri pesta ulang tahun Nancy",
                    "Ken bosan tinggal di dalam rumah ketika hujan",
                    "Ken dan Amy tersesat",
                    "Ken berkemah dengan teman-temannya",
                    "Ken dan Amy mengunjungi peternakan Billy",
                    "Ken sedang sakit cacar air",
                    "Jam dinding yang baru dibeli Ayah"
            };

    int images[] =
            {R.drawable.snd2_icon1,
                    R.drawable.snd2_icon2,
                    R.drawable.snd2_icon3,
                    R.drawable.snd2_icon4,
                    R.drawable.snd2_icon5,
                    R.drawable.snd2_icon6,
                    R.drawable.snd2_icon7};

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
        setContentView(R.layout.activity_snd2_home);
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
        listView = (ListView)findViewById(R.id.listView_snd2);
        // now create an adapter class
        HomeSeeAndDo2.MyAdapter adapter = new HomeSeeAndDo2.MyAdapter(this, mTitle, mDescription, images, mLessonNumber);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    startActivity(new Intent(getApplicationContext(), SD2Lesson1Read.class));
                }
                if (position ==  1) {
                    startActivity(new Intent(getApplicationContext(), SD2Lesson2Read.class));
                }
                if (position ==  2) {
                    startActivity(new Intent(getApplicationContext(), SD2Lesson3Read.class));
                }
                if (position ==  3) {
                    startActivity(new Intent(getApplicationContext(), SD2Lesson4Read.class));
                }
                if (position ==  4) {
                    startActivity(new Intent(getApplicationContext(), SD2Lesson5Read.class));
                }
                if (position ==  5) {
                    startActivity(new Intent(getApplicationContext(), SD2Lesson6Read.class));
                }
                if (position ==  6) {
                    startActivity(new Intent(getApplicationContext(), SD2Lesson7Read.class));
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
