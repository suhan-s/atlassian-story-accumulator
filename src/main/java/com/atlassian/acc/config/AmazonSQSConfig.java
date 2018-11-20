package com.atlassian.acc.config;

/**
 * Created by suhan.s on 11/20/2018.
 */
public class AmazonSQSConfig {
    String endPoint;
    String accessKey;
    String secretKey;
    String region;

    public AmazonSQSConfig(String endPoint, String accessKey, String secretKey, String region) {
        this.endPoint = endPoint;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.region = region;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getRegion() {
        return region;
    }
}
