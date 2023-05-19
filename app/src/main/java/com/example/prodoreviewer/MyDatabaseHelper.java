package com.example.prodoreviewer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Prodo.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "cards";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "card_name";
    private static final String COLUMN_DIFFICULTY = "card_difficulty";
    private static final String COLUMN_CONTENT = "card_content";
    private static final String COLUMN_TOPIC = "card_topic";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE '"+TABLE_NAME+"' ('"+COLUMN_ID+"' INTEGER PRIMARY KEY AUTOINCREMENT, '"+COLUMN_NAME+"' TEXT, '"+COLUMN_DIFFICULTY+"' TEXT, '"+COLUMN_CONTENT+"' TEXT, '"+COLUMN_TOPIC+"' TEXT);";
        db.execSQL(query);

        query = "CREATE TABLE TOPIC ('"+COLUMN_ID+"' INTEGER PRIMARY KEY AUTOINCREMENT, topic_name TEXT);";
        db.execSQL(query);

        query = "CREATE TABLE Settings (timer INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS '"+TABLE_NAME+"'");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS TOPIC");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS Settings");
        onCreate(db);
    }

    ArrayList<Card> getCards() {
        ArrayList<Card> cards = new ArrayList<>();

        String query = "SELECT * FROM cards";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String difficulty = cursor.getString(2);
            String content = cursor.getString(3);
            String topicName = cursor.getString(4);
            Card card = new Card(name, difficulty, content, topicName);
            cards.add(card);
        }

        cursor.close();

        return cards;
    }

    ArrayList<Card> getCards(String topic) {
        ArrayList<Card> cards = new ArrayList<>();

        String query = "SELECT * FROM cards WHERE card_topic='"+topic+"'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String difficulty = cursor.getString(2);
            String content = cursor.getString(3);
            String topicName = cursor.getString(4);
            Card card = new Card(name, difficulty, content, topicName);
            cards.add(card);
        }

        cursor.close();

        return cards;
    }

    void addCard(String name, String difficulty, String content, String topic){
        Toast.makeText(context, topic, Toast.LENGTH_SHORT).show();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DIFFICULTY, difficulty);
        cv.put(COLUMN_CONTENT, content);
        cv.put(COLUMN_TOPIC, topic);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1) {
            Toast.makeText(context, "Could not create card", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Card created", Toast.LENGTH_SHORT).show();
        }

    }

    void editCard(String name, String newContent, String newName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, newName);
        cv.put(COLUMN_CONTENT, newContent);

        int result = db.update(TABLE_NAME, cv, COLUMN_NAME + " = ?", new String[]{name});
        if (result > 0) {
            Toast.makeText(context, "Card updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Could not update card", Toast.LENGTH_SHORT).show();
        }
    }

    void addTopic(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("topic_name", name);

        long result = db.insert("TOPIC", null, cv);
        if(result == -1) {
            Toast.makeText(context, "Could not create topic", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Topic created", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteAllTopics() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM TOPIC");
        db.close();
    }

    void deleteAllCards() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM cards");
    }

    void deleteAllCards(String topic) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM cards WHERE card_topic='"+topic+"'");
    }

    void deleteCard(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM cards where card_name='"+name+"'");
    }

    int getTimer() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("Settings", new String[] { "timer" }, null, null, null, null, null);
        int timer = 99999;
        try{
            if (cursor.moveToFirst()) {
                timer = cursor.getInt(0);
                Toast.makeText(context, timer, Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            timer = 10;
        }


        cursor.close();
        return timer;
    }

    void saveSettings(int timer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("timer", timer);

        long result = db.insert("Settings", null, cv);
        if(result == -1) {
            Toast.makeText(context, "Settings not saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Saved Settings", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteTopic(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM TOPIC WHERE topic_name='"+name+"'");
    }

    int topicCardNo(String topic) {
        String query = "SELECT * FROM cards WHERE card_topic = '"+topic+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        cursor.moveToNext();
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    Card getCard(String name) {
        String query = "SELECT * FROM cards WHERE card_name = '"+name+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        cursor.moveToNext();
        Card card = new Card(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        cursor.close();
        return card;
    }

    int cardNo() {
        String query = "SELECT * FROM cards";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        cursor.moveToNext();
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    Cursor readTopicData() {
        String query = "SELECT * FROM TOPIC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readCardData(String topic) {
        String query = "SELECT * FROM cards where card_topic='"+topic+"'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    boolean topicExists(String topic) {
        String query = "SELECT * FROM TOPIC where topic_name='"+topic+"'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToNext()) {
            return true;
        }
        return false;
    }

    boolean cardExists(String name) {
        String query = "SELECT * FROM cards where card_name='"+name+"'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToNext()) {
            return true;
        }
        return false;
    }
}
