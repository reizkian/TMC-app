package org.tmcindonesia.application.UserInput;

import android.provider.BaseColumns;

public class UserChildrenAndParentData {
    // children
    private String user_children_name;
    private String user_children_birthday;
    private String getUser_children_institution;
    // parent
    private String user_parent_name;
    private String user_parent_email;
    private String user_parent_relation;
    private String user_parent_phone;
    private String user_parent_city;
    private String user_parent_province;

    // Constructor
    public UserChildrenAndParentData(String user_children_name,
                                     String user_children_birthday,
                                     String user_children_institution,
                                     String user_parent_name,
                                     String user_parent_email,
                                     String user_parent_relation,
                                     String user_parent_phone,
                                     String user_parent_city,
                                     String user_parent_province) {
        this.user_children_name = user_children_name;
        this.user_children_birthday = user_children_birthday;
        this.getUser_children_institution = user_children_institution;
        this.user_parent_name = user_parent_name;
        this.user_parent_email = user_parent_email;
        this.user_parent_relation = user_parent_relation;
        this.user_parent_phone = user_parent_phone;
        this.user_parent_city = user_parent_city;
        this.user_parent_province = user_parent_province;
    }

    public UserChildrenAndParentData(){}
    public static final class UserDetails implements BaseColumns {
        public static final String TABLE_NAME = "user_children_and_parent_data";
        //children
        public static final String COLUMN_USER_CHILDREN_NAME = "children_name";
        public static final String COLUMN_USER_CHILDREN_BIRTHDAY = "children_birth_date";
        public static final String COLUMN_USER_CHILDREN_INSTITUTION = "children_institution";
        //parent
        public static final String COLUMN_USER_PARENT_NAME = "parent_name";
        public static final String COLUMN_USER_PARENT_EMAIL = "parent_email";
        public static final String COLUMN_USER_PARENT_RELATION = "relation";
        public static final String COLUMN_USER_PARENT_PHONE = "parent_phone";
        public static final String COLUMN_USER_PARENT_CITY = "parent_city";
        public static final String COLUMN_USER_PARENT_PROVINCE = "parent_province";
    }

    //getter
    public String getUser_children_name() { return user_children_name; }
    public String getUser_children_birthday() { return user_children_birthday; }
    public String getGetUser_children_institution() { return getUser_children_institution; }
    public String getUser_parent_name() { return user_parent_name; }
    public String getUser_parent_relation() { return user_parent_relation; }
    public String getUser_parent_email() { return user_parent_email; }
    public String getUser_parent_phone() { return user_parent_phone; }
    public String getUser_parent_city() { return user_parent_city; }
    public String getUser_parent_province() { return user_parent_province; }
}
