package com.mateuszoz.dicegame;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Change Activities from MainMenu to MainActivity
        Button play = (Button) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent loadUsernameInput = new Intent(MainMenu.this, GameScreen.class);
                startActivity(loadUsernameInput);
            }
        });

    }

}
