package com.example.prodoreviewer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class myDatabasehelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "prodo.db";
    private static final int DATABASE_VERSION = 12;

    private static final String TABLE_NAME="list_task";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_NAME = "task_name";
    private static final String COLUMN_TASKICON = "task_icon";
    private static final String COLUMN_ICONCOLOR = "icon_color";

    private static final String COLUMN_DATE = "task_date";
    private static final String COLUMN_DESCRIPTION = "task_description";

    private static final String COLUMN_TASKLISTNAME = "task_list_name";


    myDatabasehelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_TASKICON + " TEXT, " +
                COLUMN_ICONCOLOR + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_TASKLISTNAME + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void addList(String task_name, String task_icon, String iconcolor, String task_date, String task_description, String task_list_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,task_name);
        cv.put(COLUMN_TASKICON,task_icon);
        cv.put(COLUMN_ICONCOLOR,iconcolor);
        cv.put(COLUMN_DATE, task_date);
        cv.put(COLUMN_DESCRIPTION,task_description);
        cv.put(COLUMN_TASKLISTNAME,task_list_name);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "failed!!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db!=null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id,String task_name, String task_icon, String iconcolor, String task_date, String task_description, String task_list_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, task_name);
        cv.put(COLUMN_TASKICON, task_icon);
        cv.put(COLUMN_ICONCOLOR, iconcolor);
        cv.put(COLUMN_DATE, task_date);
        cv.put(COLUMN_DESCRIPTION, task_description);
        cv.put(COLUMN_TASKLISTNAME, task_list_name);

        int rowsAffected = db.update(TABLE_NAME, cv, COLUMN_ID + "=?", new String[]{row_id});
        if (rowsAffected > 0) {
            Toast.makeText(context, "Successfully updated task.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed to update task.", Toast.LENGTH_SHORT).show();
        }
    }
    public void deleteData(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{row_id});
        if (rowsAffected > 0) {
            Toast.makeText(context, "Successfully deleted task.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed to delete task.", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor getTasksForToday() {
        SQLiteDatabase db = this.getReadableDatabase();

        // Get the current date
        String currentDate = getCurrentDate();

        // Build the query to fetch tasks for today
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_DATE + " = '" + currentDate + "'";

        // Execute the query and return the cursor
        return db.rawQuery(query, null);
    }

    Cursor getTasks(String list) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Get the current date
        String currentDate = getCurrentDate();

        // Build the query to fetch tasks for today
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_TASKLISTNAME + " = '" + list + "'";

        // Execute the query and return the cursor
        return db.rawQuery(query, null);
    }

    Cursor getTasksNotForToday() {
        SQLiteDatabase db = this.getReadableDatabase();

        // Get the current date
        String currentDate = getCurrentDate();

        // Build the query to fetch tasks not for today (tasks after the current date)
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_DATE + " > '" + currentDate + "'";

        // Execute the query and return the cursor
        return db.rawQuery(query, null);
    }

    private String getCurrentDate() {
        // Get the current date in the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy");
        SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        Date date = Calendar.getInstance().getTime();
        return dateFormat.format(date);
    }
}
