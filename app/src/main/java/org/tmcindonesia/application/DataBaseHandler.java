package org.tmcindonesia.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.firebase.firestore.auth.User;

import org.tmcindonesia.application.UserInput.UserChildrenAndParentData;
import org.tmcindonesia.application.UserInput.UserData;

public class DataBaseHandler extends SQLiteOpenHelper {
    public static final String DataBaseName = "userdata.db";
    public static final int DataBaseVersion = 1;

    // entry data
    private static final String SQL_USER_DATA_ENTRY =
            "CREATE TABLE "+ UserData.UserDetails.TABLE_NAME +" ("+
                    UserData.UserDetails._ID+" INTEGER PRIMARY KEY," +
                    UserData.UserDetails.COLUMN_USER_NAME+ " TEXT," +
                    UserData.UserDetails.COLUMN_USER_EMAIL+ " TEXT," +
                    UserData.UserDetails.COLUMN_USER_BIRTHDAY+ " TEXT," +
                    UserData.UserDetails.COLUMN_USER_PHONE+ " TEXT," +
                    UserData.UserDetails.COLUMN_USER_CITY+ " TEXT," +
                    UserData.UserDetails.COLUMN_USER_PROVINCE+ " TEXT," +
                    UserData.UserDetails.COLUMN_USER_INSTITUTION + " TEXT)";

    private static final String SQL_USER_CHILDREN_AND_PARENT_DATA_ENTRY =
            "CREATE TABLE "+ UserChildrenAndParentData.UserDetails.TABLE_NAME+" ("+
                    UserChildrenAndParentData.UserDetails._ID+" INTEGER PRIMARY KEY," +
                    UserChildrenAndParentData.UserDetails.COLUMN_USER_CHILDREN_NAME+ " TEXT," +
                    UserChildrenAndParentData.UserDetails.COLUMN_USER_CHILDREN_BIRTHDAY+ " TEXT," +
                    UserChildrenAndParentData.UserDetails.COLUMN_USER_CHILDREN_INSTITUTION+ " TEXT," +
                    UserChildrenAndParentData.UserDetails.COLUMN_USER_PARENT_NAME+ " TEXT," +
                    UserChildrenAndParentData.UserDetails.COLUMN_USER_PARENT_EMAIL+ " TEXT," +
                    UserChildrenAndParentData.UserDetails.COLUMN_USER_PARENT_RELATION+ " TEXT," +
                    UserChildrenAndParentData.UserDetails.COLUMN_USER_PARENT_PHONE+ " TEXT," +
                    UserChildrenAndParentData.UserDetails.COLUMN_USER_PARENT_CITY+ " TEXT," +
                    UserChildrenAndParentData.UserDetails.COLUMN_USER_PARENT_PROVINCE + " TEXT)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserData.UserDetails.TABLE_NAME;

    public DataBaseHandler(Context context) {
        super(context, DataBaseName, null, DataBaseVersion);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_USER_DATA_ENTRY);
        db.execSQL(SQL_USER_CHILDREN_AND_PARENT_DATA_ENTRY);
    }

    // ADD USER
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

    // ADD CHILDREN AND PARENT USER
    public boolean addChildrenAndParentUser(String user_children_name,
                                            String user_children_birthday,
                                            String user_children_institution,
                                            String user_parent_name,
                                            String user_parent_email,
                                            String user_parent_relation,
                                            String user_parent_phone,
                                            String user_parent_city,
                                            String user_parent_province){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserChildrenAndParentData.UserDetails.COLUMN_USER_CHILDREN_NAME, user_children_name);
        values.put(UserChildrenAndParentData.UserDetails.COLUMN_USER_CHILDREN_BIRTHDAY, user_children_birthday);
        values.put(UserChildrenAndParentData.UserDetails.COLUMN_USER_CHILDREN_INSTITUTION, user_children_institution);
        values.put(UserChildrenAndParentData.UserDetails.COLUMN_USER_PARENT_NAME,user_parent_name);
        values.put(UserChildrenAndParentData.UserDetails.COLUMN_USER_PARENT_EMAIL, user_parent_email);
        values.put(UserChildrenAndParentData.UserDetails.COLUMN_USER_PARENT_RELATION, user_parent_relation);
        values.put(UserChildrenAndParentData.UserDetails.COLUMN_USER_PARENT_PHONE, user_parent_phone);
        values.put(UserChildrenAndParentData.UserDetails.COLUMN_USER_PARENT_CITY, user_parent_city);
        values.put(UserChildrenAndParentData.UserDetails.COLUMN_USER_PARENT_PROVINCE, user_parent_province);
        long sid = db.insert(UserChildrenAndParentData.UserDetails.TABLE_NAME, null, values);
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