package com.example.isthisangela.miniproj1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Question number
        int q = 0;
        int score = 0;

        //Buttons n text
        ImageButton i = findViewById(R.id.picture);
        Button first = findViewById(R.id.option1);
        Button second = findViewById(R.id.option2);
        Button third = findViewById(R.id.option3);
        Button fourth = findViewById(R.id.option4);
        Button b = findViewById(R.id.endgame);
        TextView countdown = findViewById(R.id.countdown);
        TextView score = findViewById(R.id.score);

        //Timer
        CountDownTimer timer = new CountDownTimer(5000, 1000){
            public void onTick(long millisUntilFinished) {
                countdown.setText((millisUntilFinished / 1000) + 1);
            }
            public void onFinish() {
                turn++;
                createQuestion(turn);
                timer.start();
            }
        };

        //Button to add contact
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
                contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                contactIntent.putExtra(ContactsContract.Intents.Insert.NAME, "Contact Name"); //CONTACT NAAAAAAAAAAME
                startActivityForResult(contactIntent, 1);
            }
        });

        //Alert to quit game
        final AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
        builder.setMessage("Quit game?");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(QuizActivity.this, Start.class));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        //End button
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog quitDialog = builder.show();
            }
        });
    }
}
