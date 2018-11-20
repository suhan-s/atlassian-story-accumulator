package com.atlassian.acc.beans;

import com.google.gson.Gson;

/**
 * Created by suhan.s on 11/20/2018.
 */
public class StorySummary {
    String name;
    int totalPoints;

    public StorySummary(String name, int totalPoints) {
        this.name = name;
        this.totalPoints = totalPoints;
    }

    public String getName() {
        return name;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
