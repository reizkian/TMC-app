package org.tmcindonesia.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler extends SQLiteOpenHelper {
    public static final String DataBaseName = "userdata.db";
    public static final int DataBaseVersion = 1;

    // entry data
    private static final String SQL_DATA_ENTRY =
            "CREATE TABLE "+ UserData.UserDetails.TABLE_NAME +" ("+
                    UserData.UserDetails._ID+" INTEGER PRIMARY KEY," +
                    UserData.UserDetails.COLUMN_USER_NAME+ " TEXT," +
                    UserData.UserDetails.COLUMN_USER_EMAIL+ " TEXT," +
                    UserData.UserDetails.COLUMN_USER_BIRTHDAY+ " TEXT," +
                    UserData.UserDetails.COLUMN_USER_PHONE+ " TEXT," +
                    UserData.UserDetails.COLUMN_USER_CITY+ " TEXT," +
                    UserData.UserDetails.COLUMN_USER_PROVINCE+ " TEXT," +
                    UserData.UserDetails.COLUMN_USER_INSTITUTION + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserData.UserDetails.TABLE_NAME;

    public DataBaseHandler(Context context) {
        super(context, DataBaseName, null, DataBaseVersion);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_DATA_ENTRY);
    }

    public boolean addUser(String user_name,
                           String user_email,
                           String user_birthday,
                           String user_phone,
                           String user_city,
                           String user_province,
                           String user_institution){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserData.UserDetails.COLUMN_USER_NAME, user_name);
        values.put(UserData.UserDetails.COLUMN_USER_EMAIL, user_email);
        values.put(UserData.UserDetails.COLUMN_USER_BIRTHDAY, user_birthday);
        values.put(UserData.UserDetails.COLUMN_USER_PHONE, user_phone);
        values.put(UserData.UserDetails.COLUMN_USER_CITY, user_city);
        values.put(UserData.UserDetails.COLUMN_USER_PROVINCE, user_province);
        values.put(UserData.UserDetails.COLUMN_USER_INSTITUTION, user_institution);

        long sid = db.insert(UserData.UserDetails.TABLE_NAME, null, values);

        if (sid>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
