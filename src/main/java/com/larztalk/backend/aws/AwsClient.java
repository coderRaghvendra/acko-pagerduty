package com.larztalk.backend.aws;

import static com.larztalk.backend.config.SystemConfig.AWS_ACCESS_KEY;
import static com.larztalk.backend.config.SystemConfig.AWS_SECREET_KEY;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.AmazonRDSClient;
import com.amazonaws.services.rds.AmazonRDSClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

/**
 * Created by raghvendra.mishra on 31/07/20.
 */
public class AwsClient {

    public static AWSCredentials AWS_CREDENTIALS;
    public static AWSSecretsManager SMS_CLIENT;
    public static AmazonS3 S3_CLIENT;
    public static AmazonSQS SQS_CLIENT;
    public static AmazonDynamoDB DYNAMO_DB_CLIENT;
    public static AmazonSNS SNS_CLIENT;

//    @PostConstruct
    public static void init() {
        AWS_CREDENTIALS = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECREET_KEY);
        AWSCredentialsProvider awscp = new AWSStaticCredentialsProvider(AWS_CREDENTIALS);

        SMS_CLIENT  = AWSSecretsManagerClientBuilder.standard()
            .withCredentials(awscp)
            .withRegion(Regions.AP_SOUTH_1)
            .build();
        S3_CLIENT = AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.AP_SOUTH_1)
            .withCredentials(awscp)
            .build();
        SQS_CLIENT = AmazonSQSClientBuilder
            .standard()
            .withRegion(Regions.AP_SOUTH_1)
            .withCredentials(awscp)
            .build();
        DYNAMO_DB_CLIENT = AmazonDynamoDBClientBuilder.standard()
            .withCredentials(awscp)
            .withRegion(Regions.AP_SOUTH_1)
            .build();
        SNS_CLIENT = AmazonSNSClientBuilder.standard()
            .withCredentials(awscp)
            .withRegion(Regions.AP_SOUTH_1)
            .build();
    }
}
