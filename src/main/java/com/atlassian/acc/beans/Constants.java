package com.atlassian.acc.beans;

import com.atlassian.acc.util.ApplicationProperties;

/**
 * Created by suhan.s on 11/20/2018.
 */
public class Constants {

    public static final String SQS_ENDPOINT = ApplicationProperties.getProperty("SQS_ENDPOINT");
    public static final String SQS_REGION = ApplicationProperties.getProperty("SQS_REGION");
    public static final String SQS_ACCESS_KEY = ApplicationProperties.getProperty("SQS_ACCESS_KEY");
    public static final String SQS_SECRET_KEY = ApplicationProperties.getProperty("SQS_SECRET_KEY");
    public static final String SQS_QUEUE_URL = ApplicationProperties.getProperty("SQS_QUEUE_PATH");

    public static final String JIRA_BASE_URL = ApplicationProperties.getProperty("JIRA_BASE_URL");
    public static final String JIRA_API_PATH = "/rest/api/2/search";
    public static final String JIRA_API_URL = JIRA_BASE_URL + JIRA_API_PATH;
}
