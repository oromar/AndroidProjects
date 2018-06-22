package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int teamAPoints = 0;
    private int teamBPoints = 0;

    public void reset(View view){
        teamAPoints = 0;
        teamBPoints = 0;
        updatePoints();
    }

    public void updatePoints() {
        TextView teamATextView = findViewById(R.id.team_a_points_view);
        teamATextView.setText("" + teamAPoints);

        TextView teamBTextView = findViewById(R.id.team_b_points_view);
        teamBTextView.setText("" + teamBPoints);
    }

    public void add3Points(View v) {
        switch (v.getId()) {
            case R.id.team_a_3_points:
                teamAPoints += 3;
                break;
            case R.id.team_b_3_points:
                teamBPoints += 3;
                break;
        }
        updatePoints();
    }

    public void add2Points(View v) {
        switch (v.getId()) {
            case R.id.team_a_2_points:
                teamAPoints += 2;
                break;
            case R.id.team_b_2_points:
                teamBPoints += 2;
        }
        updatePoints();
    }

    public void addFreeThrow(View v) {
        switch (v.getId()) {
            case R.id.team_a_free_throw:
                teamAPoints++;
                break;
            case R.id.team_b_free_throw:
                teamBPoints++;
                break;
        }
        updatePoints();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
