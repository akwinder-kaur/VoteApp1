package com.example.voteapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Candidates> candidateArr;
    private TextView candidate1, candidate2, candidate3;
    private Button voteBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        candidate1 = findViewById(R.id.tv_liberal);
        candidate2 = findViewById(R.id.tv_democratic);
        candidate3 = findViewById(R.id.tv_conservative);

        voteBtn = findViewById(R.id.btnVote);

        candidateArr = new ArrayList<Candidates>();
        Intent intent = getIntent();

        ArrayList<Candidates> candidates = (ArrayList<Candidates>) intent.getSerializableExtra("candidates");
        if(candidates == null){
            candidateArr.add(new Candidates(1,"Liberal",100));
            candidateArr.add(new Candidates(2,"New Democratic",200));
            candidateArr.add(new Candidates(3,"Conservative",250));
        }
        else{
            candidateArr = candidates;
        }

        candidate1.setText(candidateArr.get(0).getName()+" : " + candidateArr.get(0).getnoOfVotes());
        candidate2.setText(candidateArr.get(1).getName()+" : " + candidateArr.get(1).getnoOfVotes());
        candidate3.setText(candidateArr.get(2).getName()+" : " + candidateArr.get(2).getnoOfVotes());

        voteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_main2.class);
                intent.putExtra("candidates", candidateArr);
                startActivity(intent);
            }
        });

    }
}