package com.mateuszoz.dicegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class UserNameInput extends AppCompatActivity {

  /*  EditText playerName1;
    EditText playerName2;
*/
   // @Override



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_input);

      /*  playerName1 = (EditText)findViewById(R.id.editText1);
        playerName2 = (EditText)findViewById(R.id.editText2);
*/
        Button Submit = (Button) findViewById(R.id.Submit);
        Submit.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent loadGame = new Intent(UserNameInput.this, GameScreen.class);
                startActivity(loadGame);
            }
        });
    }
}
