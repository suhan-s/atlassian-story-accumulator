package com.atlassian.acc.services;

import com.atlassian.acc.beans.Constants;
import com.atlassian.acc.beans.IssueDetails;
import com.atlassian.acc.util.Util;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suhan.s on 11/20/2018.
 */
public class ExternalApi {
    private static final Logger log = LogManager.getLogger(ExternalApi.class);

    public static List<IssueDetails> getAllIssues(String query){
        List<IssueDetails> issueDetailsList = new ArrayList<>();
        try {
            String response = Util.getExternalApiResponse(Constants.JIRA_API_URL + "?q=" + query);
            JsonArray jsonArray = new JsonParser().parse(response).getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                try{
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    String issueKey = jsonObject.get("issueKey").getAsString();
                    int storyPoints = jsonObject.get("fields").getAsJsonObject().get("storyPoints").getAsInt();
                    issueDetailsList.add(new IssueDetails(issueKey,storyPoints));
                }catch (Exception e){
                    log.error("Error parsing json", e);
                }
            }
        } catch (Exception e) {
            log.error("Error while getting api response for query " + query, e);
        }
        return issueDetailsList;
    }
}
