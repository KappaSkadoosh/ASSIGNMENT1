package com.example.kappa.assignment;

/**
 * Created by Kappa on 3/5/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kappa on 3/5/2017.
 */

public class DBHelper2 extends SQLiteOpenHelper {
    public static final String DataB_NAME="Kappa.db";
    public static final String TABLE_NAME = "smadequestion";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "question";
    public static final String COL_3 = "answer";
    public static final String COL_4 = "option1";
    public static final String COL_5 = "option2";

    public DBHelper2(Context context) {
        super(context, DataB_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,question TEXT,answer TEXT, option1 TEXT, option2 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String quest,String ans,String opt1, String opt2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,quest);
        contentValues.put(COL_3,ans);
        contentValues.put(COL_4,opt1);
        contentValues.put(COL_5,opt2);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public List<Question> getAllQuestions(){
        SQLiteDatabase db = this.getWritableDatabase();

        //create list
        List<Question> QList = new ArrayList<Question>();
        String Query = "SELECT * FROM " + TABLE_NAME;
        //make cursor get data
        Cursor cursor = db.rawQuery(Query, null);

        if (cursor.moveToFirst()){
            do{
                Question questioN= new Question();
                questioN.setID(cursor.getInt(0));
                questioN.setQuestion(cursor.getString(1));
                questioN.setAnswer(cursor.getString(2));
                questioN.setOption1(cursor.getString(3));
                questioN.setOption2(cursor.getString(4));
                QList.add(questioN);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return QList;
    }

}