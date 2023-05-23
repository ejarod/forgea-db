package com.example.prodoreviewer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class myDatabasehelper2 extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "prodo2.db";
    private static final int DATABASE_VERSION = 12;

    private static final String TABLE_NAME="list_title";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_NAME = "list_name";

    private static final String COLUMN_LISTICON = "list_icon";

    private static final String COLUMN_LISTCOLOR = "list_color";


    public myDatabasehelper2(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_LISTICON + " TEXT, " +
                COLUMN_LISTCOLOR + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void addList(String list_name, String list_icon, String list_color){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,list_name);
        cv.put(COLUMN_LISTICON,list_icon);
        cv.put(COLUMN_LISTCOLOR,list_color);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "failed!!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "added successfully", Toast.LENGTH_SHORT).show();
        }
    }
    int countDataByListName(String listName) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Build the query to count the number of entries with the given list name
        String query = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = ?";
        String[] selectionArgs = {listName};

        // Execute the query and return the count
        Cursor cursor = db.rawQuery(query, selectionArgs);
        int count = 0;
        if (cursor != null && cursor.moveToFirst()) {
            count = cursor.getInt(0);
            cursor.close();
        }
        return count;
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
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db!=null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id,String list_name, String list_icon, String list_color){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, list_name);
        cv.put(COLUMN_LISTICON, list_icon);
        cv.put(COLUMN_LISTCOLOR, list_color);

        int rowsAffected = db.update(TABLE_NAME, cv, COLUMN_ID + "=?", new String[]{row_id});
        if (rowsAffected > 0) {
            Toast.makeText(context, "Successfully updated task.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed to update task.", Toast.LENGTH_SHORT).show();
        }
    }
}
