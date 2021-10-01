package com.maltsevve.springBootApp.service.amazon;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class AmazonClient {
    private AmazonS3 s3Client;

    @Value("${endpointUrl}")
    private String endpointUrl;

    @Value("${bucketName}")
    private String bucketName;

//    @Value("${amazonProperties.accessKey}")
//    private String accessKey;
//
//    @Value("${amazonProperties.secretKey}")
//    private String secretKey;

    @PostConstruct
    private void initAmazon() {
//        AWSCredentialsProvider credentialsProvider = new AWSCredentialsProvider() {
//            @Override
//            public AWSCredentials getCredentials() {
//                return new BasicAWSCredentials(accessKey, secretKey);
//            }
//
//            @Override
//            public void refresh() {
//
//            }
//        };

        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.EU_NORTH_1)
//                .withCredentials(credentialsProvider)
//                .withCredentials(new ProfileCredentialsProvider())
                .build();
    }

    private File convertMultiPartToFile(MultipartFile file) {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));

        try (FileOutputStream fos = new FileOutputStream(convFile)){
            fos.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return convFile;
    }

    private void uploadFileToS3Bucket(String fileName, File file) {
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
    }

    public String uploadFile(MultipartFile multipartFile) {
        File file = convertMultiPartToFile(multipartFile);
        String fileName = multipartFile.getOriginalFilename();
        String fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
        uploadFileToS3Bucket(fileName, file);

        file.delete();

        return fileUrl;
    }

//    public File downloadFile(String fileName) {
//        InputStream inputStream = s3Client.getObject(new GetObjectRequest(bucketName, fileName)).getObjectContent();
//        File convFile = new File(Objects.requireNonNull(fileName));
//        try {
//            Files.copy(inputStream, convFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return convFile;
//    }

    public void deleteFileFromS3Bucket(String fileName) {
        s3Client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
    }
}
