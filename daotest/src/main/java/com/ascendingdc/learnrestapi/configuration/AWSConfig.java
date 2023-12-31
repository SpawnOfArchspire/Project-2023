package com.ascendingdc.learnrestapi.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;



@Configuration
public class AWSConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Value("${aws.region}")
    private String awsRegion;
    @Value("${aws-accessKeyId}")
    private String myAWSAccessKeyId ;
    @Value("${aws.secretKey}")
    private String myAWSSecretKey ;

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonS3 getAmazonS3(){

        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(myAWSAccessKeyId, myAWSSecretKey);

        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withRegion(awsRegion)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

        return s3Client;
    }

    private AmazonS3 gets3ClientByDefaultCredential(String awsRegion, String bucketName) {

        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(awsRegion)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();

        return amazonS3;
    }

    private AmazonS3 getS3ClientWithEnvironmentCredentials() {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new EnvironmentVariableCredentialsProvider())
                .withRegion(awsRegion)
                .build();
        return s3Client;
    }
}
