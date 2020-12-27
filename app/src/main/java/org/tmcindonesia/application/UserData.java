package org.tmcindonesia.application;

import android.provider.BaseColumns;

public class UserData {
    private String user_name;
    private String user_email;
    private String user_birthday;
    private String user_phone;
    private String user_city;
    private String user_province;
    private String user_institution;

    public UserData(){}
    public static final class UserDetails implements BaseColumns{
        public static final String TABLE_NAME = "user_data";
        public static final String COLUMN_USER_NAME = "name";
        public static final String COLUMN_USER_EMAIL = "email";
        public static final String COLUMN_USER_BIRTHDAY = "birth_date";
        public static final String COLUMN_USER_PHONE = "phone";
        public static final String COLUMN_USER_CITY = "city";
        public static final String COLUMN_USER_PROVINCE = "province";
        public static final String COLUMN_USER_INSTITUTION = "institution";

    }

    // constructor
    public UserData(String user_name,
                    String user_email,
                    String user_birthday,
                    String user_phone,
                    String user_city,
                    String user_province,
                    String user_institution) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_birthday = user_birthday;
        this.user_phone = user_phone;
        this.user_city = user_city;
        this.user_province = user_province;
        this.user_institution = user_institution;
    }

    // getter
    public String getUser_name() {
        return user_name;
    }
    public String getUser_email() {
        return user_email;
    }
    public String getUser_birthday() {
        return user_birthday;
    }
    public String getUser_phone() {
        return user_phone;
    }
    public String getUser_city() {
        return user_city;
    }
    public String getUser_province() {
        return user_province;
    }
    public String getUser_institution() {
        return user_institution;
    }
}
