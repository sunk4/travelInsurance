package com.roman.Insurance.s3Bucket;

public interface UploadService {
    String uploadFileToS3 (byte[] file, String fileName);
}
