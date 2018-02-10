package com.example.isthisangela.miniproj1;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    final int total = 54;
    int q;
    int pts;
    ImageButton i;
    Button b1, b2, b3, b4, quit;
    TextView countdown, score;
    CountDownTimer timer;
    Answer current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Question number
        q = 0;
        pts = 0;

        //Buttons n text
        i = findViewById(R.id.picture);
        b1 = findViewById(R.id.option1);
        b2 = findViewById(R.id.option2);
        b3 = findViewById(R.id.option3);
        b4 = findViewById(R.id.option4);
        quit = findViewById(R.id.endgame);
        countdown = findViewById(R.id.countdown);
        score = findViewById(R.id.score);

        //Timer
        timer = new CountDownTimer(5000, 1000){
            public void onTick(long millisUntilFinished) {
                countdown.setText((int) ((millisUntilFinished / 1000) + 1));
            }
            public void onFinish() {
                tryAgain();
                q++;
                newQ();
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
                contactIntent.putExtra(ContactsContract.Intents.Insert.NAME, current.getName());
                startActivityForResult(contactIntent, 1);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incorrect();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incorrect();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incorrect();
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
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog quitDialog = builder.show();
            }
        });
    }

    private void tryAgain() {
        Toast.makeText(QuizActivity.this, "Try again!", Toast.LENGTH_SHORT).show();
    }

    private void newQ() {
        q += 1;
        int rand = (int) (Math.random() * total);
        current = new Answer();
        i.setImageResource(current.getPic());
        b1.setText(current.getName());
        b2.setText(current.getWrong1());
        b3.setText(current.getWrong2());
        b4.setText(current.getWrong3());
        timer.start();
    }

    private void correct() {
        pts += 1;
        score.setText(pts);
        newQ();
    }

    private void incorrect() {
        tryAgain();
        newQ();
    }
}
