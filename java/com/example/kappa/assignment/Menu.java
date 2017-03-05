package com.example.kappa.assignment;

/**
 * Created by Kappa on 3/4/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;




public class Menu extends Activity {

    private Button playbutton;
    private Button highbutton;
    private Button makeownbutton;
    private Button playownbutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        //identify button id
        playbutton=(Button)findViewById(R.id.play);
        highbutton=(Button)findViewById(R.id.highscore);
        makeownbutton=(Button)findViewById(R.id.makeown);
        playownbutton=(Button)findViewById(R.id.playown);

        main();



    }

    public void main(){
        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launch mainactivity if clicked
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        highbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //launch highscore if clicked
                Intent intent1 = new Intent(getBaseContext(), Highscore.class);
                startActivity(intent1);
            }
        });

        makeownbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //launch highscore if clicked
                Intent intent2 = new Intent(getBaseContext(), MakeSelfQuest.class);
                startActivity(intent2);
            }
        });

        playownbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //launch highscore if clicked
                Intent intent3 = new Intent(getBaseContext(), PlaySelf.class);
                startActivity(intent3);
            }
        });
    }
}