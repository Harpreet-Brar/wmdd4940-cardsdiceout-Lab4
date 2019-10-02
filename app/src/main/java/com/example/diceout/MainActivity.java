package com.example.diceout;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Field to hold the roll result text
    TextView rollResult;

    // Field to hold the score
    int score;

    // Field to hold the score text
    TextView scoreText;

    // ArrayList to hold all three dice ImageViews
    ArrayList<DieView> diceViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view);
            }
        });

        // Set initial score
        score = 0;

        // Create greeting
        Toast.makeText(getApplicationContext(),"Welcome to DiceOut!", Toast.LENGTH_SHORT).show();

        // Link instances to widgets in the activity view
        rollResult = (TextView) findViewById(R.id.rollResult);
        scoreText = (TextView) findViewById(R.id.scoreText);

        diceViews = new ArrayList<>();

        // Access the dice ImageView widgets
        diceViews.add((DieView) findViewById(R.id.die1Image));
        diceViews.add((DieView) findViewById(R.id.die2Image));
        diceViews.add((DieView) findViewById(R.id.die3Image));

    }

    public void rollDice(View v) {

        for (DieView d : diceViews) {
            d.roll();
        }

        // Build message with the result
        String msg;

        String die1 = diceViews.get(0).getValue();
        String die2 = diceViews.get(1).getValue();
        String die3 = diceViews.get(2).getValue();

        // Run the scoring logic to determine points scored for the roll
        if (die1 == die2 && die1 == die3) {
            // Triples
            msg = "You rolled a triple " + die1 + "! You scored 100 points!";
            score += 100;

        } else if (die1 == die2 || die1 == die3 || die2 == die3) {
            // Doubles
            msg = "You rolled doubles for 50 points! ";
            score += 50;
        } else {
            msg = "You didn't score this roll. Try again!";
        }

        // Update the app to display the result message
        rollResult.setText(msg);
        scoreText.setText("Score: " + score);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
