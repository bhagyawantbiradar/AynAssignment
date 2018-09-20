package aynassignment.bhagyawant.com.aynassignment.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import aynassignment.bhagyawant.com.aynassignment.pojo.User;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    private Dao<User, Integer> studentDao;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addUser(User user) {
        try {
            getDao(User.class).create(user);
            Toast.makeText(context, "User Added successfully", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            getDao(User.class).update(user);
            Toast.makeText(context, "User updated successfully", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(User user) {
        try {
            getDao(User.class).delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<User> getUsers() {
        try {
            return getDao(User.class).queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public User getUser(int userID) {
        try {
            return getDao(User.class).queryBuilder().where().eq(User.ID_FIELD,userID).query().get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
