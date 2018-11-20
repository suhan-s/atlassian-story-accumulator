package com.atlassian.acc.beans;

/**
 * Created by suhan.s on 11/20/2018.
 */


public class IssueDetails {
    String issueKey;
    int storyPoints;

    public IssueDetails(String issueKey, int storyPoints) {
        this.issueKey = issueKey;
        this.storyPoints = storyPoints;
    }

    public String getIssueKey() {
        return issueKey;
    }

    public int getStoryPoints() {
        return storyPoints;
    }
}
