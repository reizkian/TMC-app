package org.tmcindonesia.tmc_explorer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    //Variable for Button List
    ListView listView;
    String mTitle[] =
            {"Seperti apakah Allah itu?",
                    "Seperti apakah Allah itu?",
                    "Darimana kita berasal?",
                    "Siapakah musuh kita?",
                    "Siapakah Juru Selamat yang di janjikan?",
                    "Yesus hidup sekarang!",
                    "Empat pertanyaan",
                    "Aku menjadi anak Allah!",
                    "Yesus akan datang kembali!",
                    "Empat hal yang Allah ingin aku tahu",
                    "Manusia baru dalam Kristus",
                    "Karunia terbesar dari Allah"};

    String mDescription[] =
            {"Bagian 1: Allah begitu hebat dan ajaib",
                    "Bagian 2: Hanya ada satu Allah, namun tiga pribadi",
                    "Allah adalah sang pencipta",
                    "Seorang musuh membawa dosa ke dunia",
                    "Dia yang menyelamatkan umat-Nya",
                    "Dia duduk di sebelah kanan Allah Bapa",
                    "Apa itu dosa?",
                    "Bagaimana aku dapat diselamatkan",
                    "Bagaimana kita tahu Tuhan Yesus akan datang kembali?",
                    "Aku menerima kehidupan yang kekal",
                    "Allah menjadikan aku baru",
                    "Yesus Kristus anak-Nya"};

    int images[] =
            {R.drawable.icon_satu,
                    R.drawable.icon_dua,
                    R.drawable.icon_tiga,
                    R.drawable.icon_empat,
                    R.drawable.icon_lima,
                    R.drawable.icon_enam,
                    R.drawable.icon_tujuh,
                    R.drawable.icon_delapan,
                    R.drawable.icon_sembilan,
                    R.drawable.icon_sepuluh,
                    R.drawable.icon_sebelas,
                    R.drawable.icon_duabelas};

    String mLessonNumber[] = {
            "pelajaran 1",
            "pelajaran 2",
            "pelajaran 3",
            "pelajaran 4",
            "pelajaran 5",
            "pelajaran 6",
            "pelajaran 7",
            "pelajaran 8",
            "pelajaran 9",
            "pelajaran 10",
            "pelajaran 11",
            "pelajaran 12"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Collapsing Toolbar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // open TMC Indonesia website
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tmcindonesia.org/")));
            }
        });

        // List View Button
        listView = findViewById(R.id.listView);
        // now create an adapter class
        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images, mLessonNumber);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.tmc_explorer.lessons.Lesson1Read.class));
                }
                if (position ==  1) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.tmc_explorer.lessons.Lesson2Read.class));
                }
                if (position ==  2) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.tmc_explorer.lessons.Lesson3Read.class));
                }
                if (position ==  3) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.tmc_explorer.lessons.Lesson4Read.class));
                }
                if (position ==  4) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.tmc_explorer.lessons.Lesson5Read.class));
                }
                if (position ==  5) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.tmc_explorer.lessons.Lesson6Read.class));
                }
                if (position ==  6) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.tmc_explorer.lessons.Lesson7Read.class));
                }
                if (position ==  7) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.tmc_explorer.lessons.Lesson8Read.class));
                }
                if (position ==  8) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.tmc_explorer.lessons.Lesson9Read.class));
                }
                if (position ==  9) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.tmc_explorer.lessons.Lesson10Read.class));
                }
                if (position ==  10) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.tmc_explorer.lessons.Lesson11Read.class));
                }
                if (position ==  11) {
                    startActivity(new Intent(getApplicationContext(), org.tmcindonesia.tmc_explorer.lessons.Lesson12Read.class));
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
}