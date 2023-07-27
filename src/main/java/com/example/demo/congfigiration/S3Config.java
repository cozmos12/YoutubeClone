package com.example.demo.congfigiration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    private static final String ACCESS_KEY = "AKIA3VNG3DAK3UVRKLEV";
    private static final String SECRET_KEY = "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";
    private static final String REGION = "eu-central-1";
    @Bean
    public static AmazonS3Client amazonS3Client() {
        BasicAWSCredentials basicAWSCredentials=new BasicAWSCredentials(ACCESS_KEY,SECRET_KEY);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withRegion(Regions. EU_CENTRAL_1)
                .build();
    }
}