package com.atlassian.acc.services;

import com.atlassian.acc.beans.IssueDetails;
import com.atlassian.acc.beans.StorySummary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by suhan.s on 11/20/2018.
 */
public class StorySummarizer {
    private static final Logger log = LogManager.getLogger(StorySummarizer.class);
    private static MessageQueue messageQueue = new AmazonSQSQueue();

    public static int getTotalStoryPoints(String query) throws IOException, URISyntaxException {
        List<IssueDetails> allIssues = ExternalApi.getAllIssues(query);
        int sum = 0;
        for (IssueDetails issue : allIssues) sum += issue.getStoryPoints();
        return sum;
    }

    public static void pushTotalStoryPoints(String name, String query) throws IOException, URISyntaxException {
        int totalStoryPoint = getTotalStoryPoints(query);
        StorySummary summary = new StorySummary(name, totalStoryPoint);
        messageQueue.sendMessage(summary.toString());
    }
}
