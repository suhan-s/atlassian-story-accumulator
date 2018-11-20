package com.atlassian.acc.services;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.atlassian.acc.beans.Constants;
import com.atlassian.acc.config.AmazonSQSConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by suhan.s on 11/20/2018.
 */
public class AmazonSQSQueue implements MessageQueue{
    private AmazonSQS client;
    private String queueUrl;
    private static final Logger log = LogManager.getLogger(AmazonSQSQueue.class);

    public AmazonSQSQueue() {
        client = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(Constants.SQS_ACCESS_KEY, Constants.SQS_SECRET_KEY)))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(Constants.SQS_ENDPOINT, Constants.SQS_REGION))
                .build();
        queueUrl = Constants.SQS_QUEUE_URL;
    }

    public AmazonSQSQueue(AmazonSQSConfig config) {
        client = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(config.getAccessKey(), config.getSecretKey())))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(config.getEndPoint(), config.getRegion()))
                .build();
        queueUrl = Constants.SQS_QUEUE_URL;
    }

    public AmazonSQSQueue(AmazonSQS client, String queueUrl) {
        this.client = client;
        this.queueUrl = queueUrl;
    }

    public void sendMessage(String message) {
        try {
            SendMessageRequest send_msg_request = new SendMessageRequest()
                    .withQueueUrl(queueUrl)
                    .withMessageBody(message);
            client.sendMessage(send_msg_request);
        } catch (Exception e) {
            log.error("Error while sending message " + message + " to queue" + queueUrl, e);
        }
    }

    public String receiveMessage() {
        List<String> messages = receiveBatchMessage(1);
        if (messages != null && messages.size() > 0)
            return messages.get(0);
        return null;
    }

    public List<String> receiveBatchMessage(int n) {
        try {
            ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest()
                    .withQueueUrl(queueUrl)
                    .withWaitTimeSeconds(1)
                    .withMaxNumberOfMessages(n);
            List<Message> messages = client.receiveMessage(receiveMessageRequest).getMessages();
            if (messages != null && messages.size() > 0) {
                return messages.stream().map(Message::getBody).collect(Collectors.toList());
            }
        } catch (Exception e) {
            log.error("Error while receiving message from " + queueUrl, e);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new AmazonSQSQueue().receiveMessage());
    }
}
