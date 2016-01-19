package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA=0;
    int scoreTeamB=0;

    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Add 3 points to Team A and display.
     */
    public void add3PointsA(View view) {
        scoreTeamA+=3;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Add 2 points to Team A and display.
     */
    public void add2PointsA(View view) {
        scoreTeamA+=2;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Add 1 points to Team A and display.
     */
    public void addFreeThrowA(View view) {
        scoreTeamA+=1;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Add 3 points to Team B and display.
     */
    public void add3PointsB(View view) {
        scoreTeamB+=3;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Add 2 points to Team B and display.
     */
    public void add2PointsB(View view) {
        scoreTeamB+=2;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Add 1 points to Team B and display.
     */
    public void addFreeThrowB(View view) {
        scoreTeamB+=1;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Reset Team A and Team B score to 0 and display.
     */
    public void reset(View view) {
        scoreTeamA=0;
        displayForTeamA(scoreTeamA);
        scoreTeamB=0;
        displayForTeamB(scoreTeamB);
    }
}
