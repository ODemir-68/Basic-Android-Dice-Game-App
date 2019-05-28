package com.mateuszoz.dicegame;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;


public class GameScreen extends AppCompatActivity {
    //Declaring Variables
    Button Roll;
    Button Save;
    Button Load;
    ImageView Dice1 = null;    //First Dice
    ImageView Dice2 = null;    //Second Dice
    Random rNum = new Random();//Random Number
    private static int player = 1; //Player number
    String player_Name;
    //TODO Allow rotation of the screen
    //TODO Create a data storage
    //TODO Animate dice roll
    //TODO increase number of players ?
    //TODO Create HighScores Activity
    //TODO Create HighScores Activity
    //TODO Add some colour ?
    //TODO add some sound ?

    EditText playerName1;
    EditText playerName2;

    int player1Wins = 0;
    int player2Wins = 0;

    private static final String FILENAME = "SaveStorage.txt";   //save file name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

                Roll = (Button) findViewById(R.id.Roll); //Declare Roll button
                Save = (Button) findViewById(R.id.saveButton); //Declare Roll button
                Load = (Button) findViewById(R.id.loadTest); //Declare Roll button
                Dice1 = (ImageView) findViewById(R.id.Dice1);   //Declare Dice1 img
                Dice2 = (ImageView) findViewById(R.id.Dice2);   //Declare Dice2 img
                dialog_box();//Call class dialog_box
                play(); //Call class play

    }

    public void dialog_box(){//Get player names using a dialog box
        final Dialog dialog = new Dialog(GameScreen.this);      //creating the dialog screen
        dialog.setContentView(R.layout.activity_username_input);    //the layout of the dialog screen
        dialog.show();

        playerName1 = (EditText) dialog.findViewById(R.id.editText1);       //getting player 1 and 2 from the dialog box
        playerName2 = (EditText) dialog.findViewById(R.id.editText2);
        Button submitButton = (Button)dialog.findViewById(R.id.Submit);     //button to submit
        submitButton.setOnClickListener(new View.OnClickListener() {        //listener for the button
            @Override
            public void onClick(View v) {
                player_Name = playerName1.getText().toString();
                String text = playerName1.getText().toString();
                String text1 = playerName2.getText().toString();
                Toast.makeText(getApplicationContext(),"Submit button working"+text +" " +text1,Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
            });
    }

    public void save(){             //save file method
                try {
                    FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    BufferedWriter bw = new BufferedWriter(osw);

                    bw.write("Internal Storage : ");
                    bw.write(playerName1.getText().toString()+ " "+player1Wins+" ");    //saving these things to a text file
                    bw.write(playerName2.getText().toString()+ " "+player2Wins);


                    bw.close();
                    osw.close();
                    // fos.close();
                } catch (Exception e) {
                    // e.printStackTrace();
                    System.err.println(e.getMessage());
                }
            }

    public void loadTest() {        //loading test

                try {
                    FileInputStream fis = openFileInput(FILENAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);

                    String line = "", text = "";

                    while ((line = br.readLine()) != null) {
                        text = text + line + "\n";
                    }

                    text = text.trim();

                    Toast.makeText(this, text, Toast.LENGTH_LONG).show();

                    br.close();
                    isr.close();
                    // fis.close();
                } catch (Exception e) {
                    // e.printStackTrace();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
    }



    public void play() {
        Roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTest();
                TextView player_win = (TextView) findViewById(R.id.player_wins);
                player_win.setVisibility(View.INVISIBLE);//Make player_win invisible
                //Outputing Player Information
                TextView textView = (TextView) findViewById(R.id.PlayerLabel);
                textView.setText("Player: " + player_Name + " rolls :");//Change text in PlayerLabel
                //Disable Roll before continuing
                //Declare Dice 1 & 2
                int dice1;
                int dice2;

                //Assign random numbers to Dice 1 & 2
                dice1 = rNum.nextInt(6) + 1;
                dice2 = rNum.nextInt(6) + 1;

                //Choose image for Dice 1
                switch (dice1) {
                    case 1: {
                        Dice1.setImageResource(R.drawable.num1);
                        break;
                    }
                    case 2: {
                        Dice1.setImageResource(R.drawable.num2);
                        break;
                    }
                    case 3: {
                        Dice1.setImageResource(R.drawable.num3);
                        break;
                    }
                    case 4: {
                        Dice1.setImageResource(R.drawable.num4);
                        break;
                    }
                    case 5: {
                        Dice1.setImageResource(R.drawable.num5);
                        break;
                    }
                    case 6: {
                        Dice1.setImageResource(R.drawable.num6);
                        break;
                    }
                }
                //Choose image for Dice 2
                switch (dice2) {
                    case 1: {
                        Dice2.setImageResource(R.drawable.num1);
                        break;
                    }
                    case 2: {
                        Dice2.setImageResource(R.drawable.num2);
                        break;
                    }
                    case 3: {
                        Dice2.setImageResource(R.drawable.num3);
                        break;
                    }
                    case 4: {
                        Dice2.setImageResource(R.drawable.num4);
                        break;
                    }
                    case 5: {
                        Dice2.setImageResource(R.drawable.num5);
                        break;
                    }
                    case 6: {
                        Dice2.setImageResource(R.drawable.num6);
                        break;
                    }
                }
                //Win condition check
                if (dice1 == dice2) {
                        if (player == 1 ) {     //everytime a player wins it saves it to the file overwriting the older file
                            win_annimation();
                            player1Wins++;
                            ((TextView) findViewById(R.id.PlayerLabel)).setText(player1Wins + " Wins for " +  player_Name);
                            save();
                        } else{
                            win_annimation();
                            player2Wins++;
                            ((TextView) findViewById(R.id.PlayerLabel)).setText(player2Wins + " Wins for " +  player_Name);
                            save();
                        }
                }
                //Changing Player
                if (player == 1) {
                    player_Name = playerName2.getText().toString();//Changing player name
                    player = 2;
                } else {
                    player_Name = playerName1.getText().toString();//Changing player name
                    player = 1;
                }
            }
        });
    }

    public void win_annimation(){//Animating player win text
        TextView player_win = (TextView) findViewById(R.id.player_wins);
        player_win.setText("Player: " + player_Name + " WINS!!!");//Animation text
            Animation anim = new AlphaAnimation(1.0f, 0.0f);
            anim.setDuration(1000); //The blinking time
            anim.setStartOffset(20);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(2);//How many times to blink
            player_win.startAnimation(anim);//Initiating animation
            Dice1.startAnimation(anim);//Initiating image animation
            Dice2.startAnimation(anim);//Initiating image animation
            }

        }


