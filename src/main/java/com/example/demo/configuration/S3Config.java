package com.example.demo.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class S3Config {
    private static final String ACCESS_KEY = "AKIA3VNG3DAK3UVRKLEV";
    private static final String SECRET_KEY = "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";
    private static final String REGION = "eu-central-1";
    @Bean
    @Primary
    public static AmazonS3Client amazonS3Client() {
        BasicAWSCredentials basicAWSCredentials=new BasicAWSCredentials(ACCESS_KEY,SECRET_KEY);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withRegion(Regions. EU_CENTRAL_1)
                .build();
    }
}