package com.example.kappa.assignment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by Kappa on 3/3/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.example.kappa.assignment/databases/";
    private static String DB_NAME = "Question.db";
    private static final String TABLE = "questions";
    private SQLiteDatabase myDataBase;
    private final Context myContext;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    //Creates a empty database on the system and rewrites it with your own database.
    public void createDataBase() throws IOException{

        boolean dbExist = checkDataBase();

        if(dbExist){

        }else{
            this.getReadableDatabase();


            try {
                copyDataBase();

            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    //Check if the database already exist to avoid re-copying the file each time you open the application.
    //return true if it exists, false if it doesn't

    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException{

        //Open your local databaseb as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty databaseb
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public List<Question> getAllQuestions(){
        //open database
        String Path = DB_PATH + DB_NAME;

        myDataBase = SQLiteDatabase.openDatabase(Path, null, SQLiteDatabase.OPEN_READONLY);

        //create list
        List<Question> QList = new ArrayList<Question>();
        String Query = "SELECT * FROM " + TABLE;
        //make cursor get data
        Cursor cursor = myDataBase.rawQuery(Query, null);

        if (cursor.moveToFirst()){
            do{
                Question question= new Question();
                question.setID(cursor.getInt(0));
                question.setQuestion(cursor.getString(1));
                question.setAnswer(cursor.getString(2));
                question.setOption1(cursor.getString(3));
                question.setOption2(cursor.getString(4));
                question.setImage(cursor.getString(5));
                QList.add(question);
            }while(cursor.moveToNext());
        }

        cursor.close();
        myDataBase.close();
        return QList;
    }


    public void openDataBase() throws SQLException {

        //access the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion>oldVersion){
            myContext.deleteDatabase(DB_NAME);
            try{
                copyDataBase();
            } catch (IOException e){
                e.printStackTrace();
            }
        }else{

        }
    }
}
