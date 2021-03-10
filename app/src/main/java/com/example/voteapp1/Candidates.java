package com.example.voteapp1;

import java.io.Serializable;

public class Candidates implements Serializable {
    private int id;
    private String name;
    private int noOfVotes;

    public Candidates(int id, String name, int noOfVotes) {
        this.id = id;
        this.name = name;
        this.noOfVotes = noOfVotes;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getnoOfVotes() {
        return noOfVotes;
    }

    public void setnoOfVotes(int noOfVotes) {
        this.noOfVotes= noOfVotes;
    }

    @Override
    public String toString() {
        return name;
    }
}
