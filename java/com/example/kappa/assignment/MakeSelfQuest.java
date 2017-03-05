package com.example.kappa.assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MakeSelfQuest extends AppCompatActivity {


    EditText question, answer, opt1, opt2;
    Button add;
    DBHelper2 myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makeselfquest);
        myDB = new DBHelper2(this);

        question = (EditText) findViewById(R.id.question);
        answer = (EditText) findViewById(R.id.answer);
        opt1 = (EditText) findViewById(R.id.opt1);
        opt2 = (EditText) findViewById(R.id.opt2);
        add = (Button) findViewById(R.id.button);
        AddData();
    }


    public void AddData() {
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted=myDB.insertData(question.getText().toString(), answer.getText().toString(), opt1.getText().toString(),
                                opt2.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(MakeSelfQuest.this,"Question Added",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MakeSelfQuest.this,"Failed to Add Question",Toast.LENGTH_LONG).show();



                    }
                }
        );
    }
}