package com.example.prodoreviewer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Signup.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Signup.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT)");
        MyDatabase.execSQL("create Table newuser(email TEXT primary key, assessed INTEGER)");
        MyDatabase.execSQL("create Table tblPersonality(email TEXT, personality TEXT, world INTEGER, information INTEGER, decisions INTEGER, structure INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {
        MyDatabase.execSQL("drop Table if exists allusers");
        MyDatabase.execSQL("drop Table if exists newuser");
        MyDatabase.execSQL("drop Table if exists tblPersonality");

        MyDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT)");
        MyDatabase.execSQL("create Table newuser(email TEXT primary key, assessed INTEGER)");
        MyDatabase.execSQL("create Table tblPersonality(email TEXT, personality TEXT, world INTEGER, information INTEGER, decisions INTEGER, structure INTEGER)");
    }

    public boolean insertData(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("Password", password);
        long result = MyDatabase.insert("allusers", null, contentValues);
        if(result == -1){
            return false;
        } else {
            ContentValues newUserValues = new ContentValues();
            newUserValues.put("email", email);
            newUserValues.put("assessed", 0);
            MyDatabase.insert("newuser", null, newUserValues);
            return true;
        }
    }

    public boolean insertPersonalityData(String email, String personality, int world, int information, int decisions, int structure) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("personality", personality);
        contentValues.put("world", world);
        contentValues.put("information", information);
        contentValues.put("decisions", decisions);
        contentValues.put("structure", structure);

        Cursor cursor = MyDatabase.query("tblPersonality", null, "email=?", new String[]{email}, null, null, null);
        if (cursor.getCount() > 0) {
            int updatedRows = MyDatabase.update("tblPersonality", contentValues, "email=?", new String[]{email});
            cursor.close();
            if (updatedRows == 0) {
                return false;
            } else {
                userAssess(email);
                return true;
            }
        } else {
            long result = MyDatabase.insert("tblPersonality", null, contentValues);
            if (result == -1) {
                return false;
            } else {
                userAssess(email);
                return true;
            }
        }
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ?",new String[]{email});

        if(cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from newuser where email = ?",new String[]{email});

        if(cursor.getCount() == 0) {
            ContentValues newUserValues = new ContentValues();
            newUserValues.put("email", email);
            newUserValues.put("assessed", 0);
            MyDatabase.insert("newuser", null, newUserValues);
        }

        cursor = MyDatabase.rawQuery("Select * from allusers where email = ? and password = ?",new String[]{email,password});
        if(cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void userAssess(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("assessed", 1);
        MyDatabase.update("newuser", contentValues, "email = ?", new String[]{email});
    }

    public boolean hasAssessed(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from newuser where email = ? and assessed = 1",new String[]{email});
        if(cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getPersonality(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select personality from tblPersonality where email = ?",new String[]{email});
        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndexOrThrow("personality"));
        }
        return null;
    }

    public int getPercentage(String email, String personalityTrait) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select " + personalityTrait + " from tblPersonality where email = ?",new String[]{email});
        if(cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndexOrThrow(personalityTrait));
        }
        return -1;
    }
}
