package com.example.voteapp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class activity_main2 extends AppCompatActivity {
    private Spinner candidate_spinner;
    ToggleButton togBtn;
    Button submitBtn;
    EditText txtname, txtid;
    private ArrayList<Candidates> candidateArray;
    ArrayList<Voters> votersArray;
    private boolean accepted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_main2);

        votersArray = new ArrayList<Voters>();
        candidate_spinner = findViewById(R.id.spinner);
        togBtn = findViewById(R.id.toggleButton);
        submitBtn = findViewById(R.id.btnVote2);
        txtname = findViewById(R.id.et_name);
        txtid = findViewById(R.id.edit_id);


        Intent intent = getIntent();
        ArrayList<Candidates> candidates = (ArrayList<Candidates>) intent.getSerializableExtra("candidates");
        candidateArray = candidates;
        ArrayAdapter<Candidates> adapter = new ArrayAdapter<Candidates>(this,
                android.R.layout.simple_spinner_item, candidateArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        candidate_spinner.setAdapter(adapter);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtname.getText().toString().isEmpty()){
                    Toast.makeText(activity_main2.this, "Name field ic required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(txtid.getText().toString().isEmpty()){
                    Toast.makeText(activity_main2.this, " Id field is reqrired", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (Voters V : votersArray) {
                    if(V.getId() == Integer.parseInt(txtid.getText().toString())){
                        Toast.makeText(activity_main2.this, " This Id already present", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if(!togBtn.isChecked()){
                    Toast.makeText(activity_main2.this, "Please accept the terms ", Toast.LENGTH_SHORT).show();
                    return;
                }

                votersArray.add(new Voters(Integer.parseInt(txtid.getText().toString()), txtname.getText().toString()));
                int selectedCandidateIndex = candidate_spinner.getSelectedItemPosition();
                Candidates selectedCandidate = candidateArray.get(selectedCandidateIndex);
                selectedCandidate.setnoOfVotes(selectedCandidate.getnoOfVotes() + 1);

                Toast.makeText(activity_main2.this, "Great! You are done!!", Toast.LENGTH_SHORT).show();


            }
        });

        togBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {

                    togBtn.setTextOn("Refuse");

                } else {

                    togBtn.setTextOff("Accept ");
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(activity_main2.this, MainActivity.class);
        intent.putExtra("candidates", candidateArray);
        startActivity(intent);
    }
}
