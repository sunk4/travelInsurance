package com.roman.Insurance.s3Bucket;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {
    private static final Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);
    @Value("${aws.s3.access-key}")
    private String awsS3AccessKey;

    @Value("${aws.s3.secret-key}")
    private String awsS3SecretKey;

    @Value("${aws.s3.bucket-name}")
    private String awsS3BucketName;

    @Value("${aws.s3.region}")
    private String awsS3Region;

    @Override
    public String uploadFileToS3 (byte[] file, String fileName) {
        try {
            AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(awsS3AccessKey, awsS3SecretKey);

            S3Client s3Client = S3Client.builder()
                    .region(Region.of(awsS3Region))
                    .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                    .build();

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(awsS3BucketName)
                    .key(fileName)
                    .contentType("application/pdf")
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file));

            return "https://" + awsS3BucketName + ".s3." + awsS3Region +
                    ".amazonaws.com/" + fileName;

        } catch (Exception e) {
            logger.error("Failed to upload file to S3: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }

}
