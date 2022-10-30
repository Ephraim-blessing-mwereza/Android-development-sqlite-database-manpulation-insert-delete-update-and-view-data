package com.example.android_insert_update_delete_view_sqldatabaselite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper( Context context) {
        super(context, "Userdata.DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table UserDetails(Name TEXT PRIMARY KEY, Contact TEXT, DOB TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS UserDetails");

    }
    public Boolean InsertUserData(String Name, String Contact, String DOB) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", Name);
        contentValues.put("Contact", Contact);
        contentValues.put("DOB", DOB);
        long result = DB.insert("UserDetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

        public Boolean UpdateUserData(String Name, String Contact, String DOB)
        {
            SQLiteDatabase DB= this .getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Contact", Contact);
            contentValues.put("DOB", DOB);
            Cursor cursor = DB.rawQuery("select*from UserDetails where Name =?", new String[]{Name});
            if (cursor.getCount()>0) {
                long result = DB.update("UserDetails", contentValues, "Name=?", new String[]{Name});
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }
            }
                else {
                    return false;
                }
    }

    public Boolean DeleteUserData(String Name)
    {
        SQLiteDatabase DB= this .getWritableDatabase();

        Cursor cursor = DB.rawQuery("select*from UserDetails where Name =?", new String[]{Name});
        if (cursor.getCount()>0) {
            long result = DB.delete("UserDetails", "Name=?", new String[]{Name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
            }
            else {
            return false;
        }

    }

    public Cursor getData()
    {
        SQLiteDatabase DB= this .getWritableDatabase();
        Cursor cursor = DB.rawQuery("select*from UserDetails", null);
       return cursor;
    }
}
