package com.example.demo.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.ion.IonException;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3service implements FileService {

    private final AmazonS3Client awsS3Client;

    public S3service(AmazonS3Client awsS3Client) {
        this.awsS3Client = awsS3Client;
    }


    @Override
    public String uploadFile(MultipartFile file){

        var filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());//dosya uzantısını alma(org:denem.jpg dosyasının jpg kısmını alır)

        var key= UUID.randomUUID().toString()+"."+filenameExtension;

        var metadata =new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            awsS3Client.putObject("myyoutubeclonebucket",key,file.getInputStream(),metadata);
        }catch (IonException | IOException exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"an exception");

        }


       // awsS3Client.setObjectAcl("myyoutubeclonebucket",key, CannedAccessControlList.PublicRead);
       return awsS3Client.getResourceUrl("myyoutubeclonebucket",key);
    }
}
