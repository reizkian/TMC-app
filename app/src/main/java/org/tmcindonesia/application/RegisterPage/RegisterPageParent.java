package org.tmcindonesia.application.RegisterPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.tmcindonesia.R;
import org.tmcindonesia.application.DataBaseHandler;
import org.tmcindonesia.application.HomeApp;
import org.tmcindonesia.application.UserInput.UserChildrenAndParentData;
import org.tmcindonesia.application.UserInput.UserData;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class RegisterPageParent extends AppCompatActivity {
    // RENDER LAYOUT
    public EditText namaAnak, tglLahirAnak, institusiAnak;
    public EditText namaWali, emailWali, passwordWali, confirmPasswordWali, noHPWali, kotaWali, provinsiWali;
    private RadioGroup hubunganWali;
    private RadioButton orangTua, kakak, guru, guruSM;
    private EditText hubunganWaliLainnya;
    private CheckBox checkBoxPrivacyPolicy, checkBoxInfoParent, checkBoxGuidance;
    private TextView hyperLinkPrivacyPolicy, hyperLinkInfoParent;
    private Button buttonRegisterCreate;
    private ProgressBar creatingUser;
    // Sttring
    private String sNamaAnak, sTglLahir, sInstitusi;
    private String sNamaWali, sEmailWali, sPasswordWali, sConfirmPasswordWali, sNoHpWali, sKotaWali, sProvinsiWali, sHubunganWali;
    // FIRE BASE
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String userID;
    public static final String TAG = "FireBase";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_register_parent);

        // RENDER LAYOUT ID
        // data anak
        namaAnak = findViewById(R.id.editText_nama_anak);
        tglLahirAnak = findViewById(R.id.editText_tgllahir_anak);
        institusiAnak = findViewById(R.id.editText_institusi_anak);
        // data wali
        namaWali = findViewById(R.id.editText_nama_wali);
        emailWali = findViewById(R.id.editText_email_wali);
        passwordWali = findViewById(R.id.editTextPasswordRegister);
        confirmPasswordWali = findViewById(R.id.editTextConfirmPasswordRegister);
        noHPWali = findViewById(R.id.editTextUserPhone);
        kotaWali = findViewById(R.id.editTextCityRegister);
        provinsiWali = findViewById(R.id.editTextProvinceRegister);
        hubunganWali = findViewById(R.id.rg_hubungan_wali);
        orangTua = findViewById(R.id.orangtua);
        kakak = findViewById(R.id.kakak);
        guru = findViewById(R.id.guru);
        guruSM = findViewById(R.id.gurusm);
        hubunganWaliLainnya = findViewById(R.id.lainnya);
        // agreement
        checkBoxPrivacyPolicy = findViewById(R.id.konfirmasi_privacypolicy);
        checkBoxInfoParent = findViewById(R.id.konfirmasi_infoparent);
        checkBoxGuidance = findViewById(R.id.konfirmasi_guidance);
        // hyper link
        hyperLinkPrivacyPolicy = findViewById(R.id.hyperlink_privacypolicy);
        hyperLinkInfoParent= findViewById(R.id.hyperlink_infoparent);
        // button
        buttonRegisterCreate = findViewById(R.id.buttonRegisterCreate);
        // progress bar
        creatingUser = findViewById(R.id.progressBar);
        // RENDER: get username & birth date from data base
        namaAnak.setText(getUserNameFromDataBase(this));
        tglLahirAnak.setText(getUserBirthDateFromDataBase(this));
        // RENDER: date picker dialog instance
        DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                Log.w("date", "onDateSet: dd/mm/yyy is" + dayOfMonth +"/"+ month +"/"+ year);
                String date = dayOfMonth +"/"+ month +"/"+ year;
                tglLahirAnak.setText(date);
            }
        };

        // FUNCTIONALITY
        // button register
        buttonRegisterCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStringInput();
                checkUserInputComplete();
                createUserObject();
                checkAgreement();
                //writeUserDataToFireBase();
                //writeUserDataToDataBase(getApplicationContext());
                creatingUser.setVisibility(View.VISIBLE);
                // go to HOME activity
                // startActivity(new Intent(getApplicationContext(), HomeApp.class));

            }
        });

        // date picker (tanggal lahir)
        tglLahirAnak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONDAY);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                Log.w("date", "calendar: "+String.valueOf(year)+"/"+String.valueOf(month)+"/"+String.valueOf(day));
                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterPageParent.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
    }

    // M E T H O D S
    public String getUserNameFromDataBase(Context c){
        DataBaseHandler dataBaseHandler = new DataBaseHandler(c);
        SQLiteDatabase database = dataBaseHandler.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ UserData.UserDetails.TABLE_NAME,null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            String username = cursor.getString(1).toString().trim();
            return username;
        }
        else {
            return null;
        }
    }
    public String getUserBirthDateFromDataBase(Context c){
        DataBaseHandler dataBaseHandler = new DataBaseHandler(c);
        SQLiteDatabase database = dataBaseHandler.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ UserData.UserDetails.TABLE_NAME,null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            String username = cursor.getString(3).toString().trim();
            return username;
        }
        else {
            return null;
        }
    }
    private void getStringInput(){
        // data anak
        sNamaAnak = namaAnak.getText().toString().trim();
        sTglLahir = tglLahirAnak.getText().toString().trim();
        sInstitusi = institusiAnak.getText().toString().trim();
        // data wali
        sNamaWali = namaWali.getText().toString().trim();
        sEmailWali = emailWali.getText().toString().trim();
        sPasswordWali = passwordWali.getText().toString().trim();
        sConfirmPasswordWali = confirmPasswordWali.getText().toString().trim();
        sNoHpWali = noHPWali.getText().toString().trim();
        sKotaWali = kotaWali.getText().toString().trim();
        sProvinsiWali = provinsiWali.getText().toString().trim();
        sHubunganWali = hubunganWaliLainnya.getText().toString().trim();
        // hubungan wali
        int radioButtonGetID = hubunganWali.getCheckedRadioButtonId();
        int id_orangtua = hubunganWali.indexOfChild(orangTua);
        int id_kakak = hubunganWali.indexOfChild(kakak);
        int id_guru = hubunganWali.indexOfChild(guru);
        int id_guruSM = hubunganWali.indexOfChild(guruSM);
        int idCollection[] = {id_orangtua, id_kakak, id_guru, id_guruSM};
        // condition to extract STRING sHubunganWali
        if(!TextUtils.isEmpty(hubunganWaliLainnya.getText().toString().trim())){
            sHubunganWali = hubunganWaliLainnya.getText().toString().trim();
        }if(TextUtils.isEmpty(hubunganWaliLainnya.getText().toString().trim())){
            switch (radioButtonGetID) {
                case R.id.orangtua:
                    sHubunganWali = orangTua.getText().toString().trim();
                    break;
                case R.id.kakak:
                    sHubunganWali = kakak.getText().toString().trim();
                    break;
                case R.id.guru:
                    sHubunganWali= guru.getText().toString().trim();
                    break;
                case R.id.gurusm:
                    sHubunganWali = guruSM.getText().toString().trim();
                    break;
                default:
                    sHubunganWali = hubunganWaliLainnya.getText().toString().trim();
                    break;
            }
        }
        if(radioButtonGetID == -1 && TextUtils.isEmpty(hubunganWaliLainnya.getText().toString().trim())){
            hubunganWaliLainnya.setError("mohon masukan relasi/hubungan anda dengan anak");
        }
    }
    private void createUserObject(){
        UserChildrenAndParentData userObject = new UserChildrenAndParentData(sNamaAnak, sTglLahir, sInstitusi, sNamaWali, sEmailWali, sHubunganWali, sNoHpWali, sKotaWali, sProvinsiWali);
    }
    private void checkUserInputComplete(){
        // anak
        if (TextUtils.isEmpty(sNamaAnak)){namaAnak.setError("masukan nama kamu"); return;}
        if (TextUtils.isEmpty(sTglLahir)){tglLahirAnak.setError("masukan tanggal lahir kamu"); return;}
        if (TextUtils.isEmpty(sInstitusi)){namaAnak.setError("masukan sekolah atau gereja kamu"); return;}
        if (TextUtils.isEmpty(sNamaAnak)){namaAnak.setError("masukan nama kamu"); return;}
        // orang tua
        if (TextUtils.isEmpty(sNamaWali)){namaWali.setError("masukan nama anda"); return;}
        if (TextUtils.isEmpty(sEmailWali)){emailWali.setError("masukan email anda"); return;}
        if (TextUtils.isEmpty(sPasswordWali)){passwordWali.setError("masukan password anda"); return;}
        if (TextUtils.isEmpty(sConfirmPasswordWali)){confirmPasswordWali.setError("masukan password anda"); return;}
        if (TextUtils.isEmpty(sNoHpWali)){noHPWali.setError("masukan password anda"); return;}
        if (TextUtils.isEmpty(sKotaWali)){kotaWali.setError("masukan password anda"); return;}
        if (TextUtils.isEmpty(sProvinsiWali)){provinsiWali.setError("masukan password anda"); return;}
        if(!sPasswordWali.equals(sConfirmPasswordWali)) {
            // error message password cannot be confirmed
            Toast.makeText(RegisterPageParent.this, "ulangi password tidak sama", Toast.LENGTH_SHORT).show();
            return;
        }

    }
    private void writeUserDataToFireBase(){
        firebaseAuth.createUserWithEmailAndPassword(sEmailWali,sPasswordWali).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterPageParent.this, "daftar berhasil", Toast.LENGTH_SHORT).show();
                    userID = firebaseAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = firebaseFirestore.collection("TMC EXPLORER ONE USER").document(sNamaWali);
                    Map<String, Object> user  = new HashMap<>();
                    user.put("Children Name", sNamaAnak);
                    user.put("Children Birth Date", sTglLahir);
                    user.put("Children Institution", sInstitusi);
                    user.put("Parent Name", sNamaWali);
                    user.put("Parent Email", sEmailWali);
                    user.put("Relation",sHubunganWali);
                    user.put("Parent Phone Number", sNoHpWali);
                    user.put("City", sKotaWali);
                    user.put("Province/State",sProvinsiWali);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG,"onSuccess: create user profile" + userID);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("FireBaseRegisterAttempt","onFailure: "+ e.toString());
                            return;
                        }
                    });
                }
            }
        });
    }
    private void writeUserDataToDataBase(Context c){
        DataBaseHandler dataBaseHandler = new DataBaseHandler(c);
        boolean statusDataBase = dataBaseHandler.addChildrenAndParentUser(sNamaAnak, sTglLahir, sInstitusi, sNamaWali, sEmailWali, sHubunganWali, sNoHpWali, sKotaWali, sProvinsiWali);
        if(statusDataBase){
            Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Insertion Failed", Toast.LENGTH_SHORT).show();
            return;
        }
    }
    private void checkAgreement(){
        if(!(checkBoxPrivacyPolicy.isChecked())){
            Toast.makeText(RegisterPageParent.this, "Anda belum menyetujui Privacy Policy", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!(checkBoxInfoParent.isChecked())){
            Toast.makeText(RegisterPageParent.this, "Anda belum menyetujui Info Orang Tua", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!(checkBoxGuidance.isChecked())){
            Toast.makeText(RegisterPageParent.this, "Anda belum menyetujui untuk membimbing", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}