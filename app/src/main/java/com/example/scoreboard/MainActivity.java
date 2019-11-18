package com.example.scoreboard;


import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int scoreA = 0,scoreB = 0,foulA = 0,foulB = 0, shotsA = 0,shotsB = 0;
    TextView textView ;

    Button start, pause, reset, A1p, A2p, A3p, Asp, Afp, B1p, B2p, B3p, Bsp, Bfp;

    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;

    Handler handler;

    int Seconds, Minutes, MilliSeconds ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView)findViewById(R.id.stopwatch);
        start = (Button) findViewById(R.id.start1);
        pause = (Button)findViewById(R.id.pause);
        reset = (Button)findViewById(R.id.reset);
        A1p = (Button)findViewById(R.id.teamA_1p);
        A2p = (Button)findViewById(R.id.teamA_2p);
        A3p = (Button)findViewById(R.id.teamA_3p);
        Afp = (Button)findViewById(R.id.teamA_foul);
        Asp = (Button)findViewById(R.id.teamA_shots);
        B1p = (Button)findViewById(R.id.teamB_1p);
        B2p = (Button)findViewById(R.id.teamB_2p);
        B3p = (Button)findViewById(R.id.teamB_3p);
        Bfp = (Button)findViewById(R.id.teamB_foul);
        Bsp = (Button)findViewById(R.id.teamB_shots);

        A1p.setEnabled(false); A2p.setEnabled(false); A3p.setEnabled(false); Asp.setEnabled(false); Afp.setEnabled(false);
        B1p.setEnabled(false); B2p.setEnabled(false); B3p.setEnabled(false); Bsp.setEnabled(false); Bfp.setEnabled(false);

        handler = new Handler() ;

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

                reset.setEnabled(false);
                A1p.setEnabled(true); A2p.setEnabled(true); A3p.setEnabled(true); Asp.setEnabled(true); Afp.setEnabled(true);
                B1p.setEnabled(true); B2p.setEnabled(true); B3p.setEnabled(true); Bsp.setEnabled(true); Bfp.setEnabled(true);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimeBuff += MillisecondTime;

                handler.removeCallbacks(runnable);

                reset.setEnabled(true);

                A1p.setEnabled(false); A2p.setEnabled(false); A3p.setEnabled(false); Asp.setEnabled(false); Afp.setEnabled(false);
                B1p.setEnabled(false); B2p.setEnabled(false); B3p.setEnabled(false); Bsp.setEnabled(false); Bfp.setEnabled(false);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreA = 0;
                scoreB = 0;
                foulA = 0;
                foulB = 0;
                shotsA = 0;
                shotsB = 0;

                displayScoreA(scoreA);
                displayScoreB(scoreB);
                displayFoulA(foulA);
                displayFoulB(foulB);
                displayShotsA(shotsA);
                displayShotsB(shotsB);

                MillisecondTime = 0L ;
                StartTime = 0L ;
                TimeBuff = 0L ;
                UpdateTime = 0L ;
                Seconds = 0 ;
                Minutes = 0 ;
                MilliSeconds = 0 ;

                textView.setText("00:00:00");
                A1p.setEnabled(false); A2p.setEnabled(false); A3p.setEnabled(false); Asp.setEnabled(false); Afp.setEnabled(false);
                B1p.setEnabled(false); B2p.setEnabled(false); B3p.setEnabled(false); Bsp.setEnabled(false); Bfp.setEnabled(false);
            }
        });
    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            textView.setText("" + String.format("%02d", Minutes) + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }
    };

        public void displayScoreA(int scoreA){
        TextView teamAscore = (TextView) findViewById(R.id.teamA_score);
        teamAscore.setText(""+scoreA);
    }

    public void displayScoreB(int scoreB)
    {
        TextView teamBscore = (TextView) findViewById(R.id.teamB_score);
        teamBscore.setText(""+scoreB);
    }

    public void displayFoulA(int foulA)
    {
        TextView teamAscore = (TextView) findViewById(R.id.teamA_foultxt);
        teamAscore.setText(""+foulA);
    }

    public void displayFoulB(int foulB)
    {
        TextView teamBscore = (TextView) findViewById(R.id.teamB_foultxt);
        teamBscore.setText(""+foulB);
    }

    public void displayShotsA(int shotsA)
    {
        TextView teamAscore = (TextView) findViewById(R.id.teamA_shotstxt);
        teamAscore.setText(""+shotsA);
    }

    public void displayShotsB(int shotsB)
    {
        TextView teamBscore = (TextView) findViewById(R.id.teamB_shotstxt);
        teamBscore.setText(""+shotsB);
    }

    public void Apoint1(View view)
    {
        scoreA += 1;
        shotsA += 1;
        displayScoreA(scoreA);
        displayShotsA(shotsA);
    }

    public void Bpoint1(View view)
    {
        scoreB += 1;
        shotsB += 1;
        displayScoreB(scoreB);
        displayShotsB(shotsB);
    }

    public void Apoint2(View view)
    {
        scoreA += 2;
        shotsA += 1;
        displayScoreA(scoreA);
        displayShotsA(shotsA);
    }

    public void Bpoint2(View view)
    {
        scoreB += 2;
        shotsB += 1;
        displayScoreB(scoreB);
        displayShotsB(shotsB);
    }

    public void Apoint3(View view)
    {
        scoreA += 3;
        shotsA += 1;
        displayScoreA(scoreA);
        displayShotsA(shotsA);
    }

    public void Bpoint3(View view)
    {
        scoreB += 3;
        shotsB += 1;
        displayScoreB(scoreB);
        displayShotsB(shotsB);
    }

    public void Afoul(View view)
    {
        foulA += 1;
        displayFoulA(foulA);
    }

    public void Bfoul(View view)
    {
        foulB += 1;
        displayFoulB(foulB);
    }

    public void Ashots(View view)
    {
        shotsA += 1;
        displayShotsA(shotsA);
    }

    public void Bshots(View view)
    {
        shotsB += 1;
        displayShotsB(shotsB);
    }
}
