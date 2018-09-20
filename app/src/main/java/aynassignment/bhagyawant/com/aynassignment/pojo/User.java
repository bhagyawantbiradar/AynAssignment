package aynassignment.bhagyawant.com.aynassignment.pojo;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class User implements Serializable {

    public static final String ID_FIELD = "user_id";

    @DatabaseField(generatedId = true, columnName = ID_FIELD)
    public int userID;

    @DatabaseField(columnName = "user_name")
    public String userName;

    @DatabaseField(columnName = "mobile_no")
    public String mobileNo;

    @DatabaseField(columnName = "email_id")
    public String emailId;

    public User(String userName, String mobileNo, String emailId) {
        this.userName = userName;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
    }

    public String getUserName() {
        return userName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public User() {
    }
}
