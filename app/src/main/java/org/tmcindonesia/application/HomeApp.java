package org.tmcindonesia.application;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.tmcindonesia.R;
import org.tmcindonesia.application.HomeAppUI.Home.HomeFragment;
import org.tmcindonesia.application.HomeAppUI.PrivacyPolicy.PrivacyPolicyFragment;
import org.tmcindonesia.tmc_explorer1.questions.LESSON1;


public class HomeApp extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private AppBarConfiguration mAppBarConfiguration;
    public DrawerLayout drawer;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_privacy_policy)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            // load fragment
            loadFragment(new HomeFragment());
            getSupportActionBar().setTitle("the Mail Box Club");
            // close navigation drawer
            drawer.closeDrawer(GravityCompat.START);
        }
        if (id == R.id.nav_website) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tmcindonesia.org/")));
        }
        if (id == R.id.nav_privacy_policy) {
            // load fragment
            loadFragment(new PrivacyPolicyFragment());
            getSupportActionBar().setTitle("Privacy Policy");
            // close navigation drawer
            drawer.closeDrawer(GravityCompat.START);
        }
        if (id == R.id.nav_logout){
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeApp.this, R.style.AlertDialogTheme);
            View view = LayoutInflater.from(HomeApp.this).inflate(
                    R.layout.activity_app_logout_dialog, (ConstraintLayout) findViewById(R.id.dialogContainer_logout)
            );

            builder.setView(view);
            ((TextView) view.findViewById(R.id.textView_titleDialog)).setText("LOGOUT");
            ((TextView) view.findViewById(R.id.textView_message)).setText("Apakah kamu yakin ingin keluar dari aplikasi?");
            ((Button) view.findViewById(R.id.logout_yes)).setText("YA");
            ((Button) view.findViewById(R.id.logout_no)).setText("TIDAK");

            final AlertDialog alertDialog = builder.create();

            view.findViewById(R.id.logout_yes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.signOut();
                    startActivity(new Intent(HomeApp.this, LoginActivity.class));
                }
            });
            view.findViewById(R.id.logout_no).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    drawer.closeDrawer(GravityCompat.START);
                }
            });

            if(alertDialog.getWindow() != null){
                alertDialog.getWindow().setBackgroundDrawable((new ColorDrawable(0)));
            }
            alertDialog.show();
        }
        return true;

    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.commit();
    }

    private void logoutYes(){

    }

    private void logoutNo(){

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
     public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}