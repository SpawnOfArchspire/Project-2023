package com.ascendingdc.learnrestapi.Controller;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.mturk.model.ServiceException;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.ascendingdc.learnrestapi.service.AWSS3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public class AWSS3Controller {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AWSS3Service awss3Service;

    @Value("$(aws.s3.bucket")
    private String bucketName;

    @ResponseBody
    @RequestMapping(value = "/picture", method = RequestMethod.POST, consumes = MULTIPART_FORM_DATA_VALUE)
    public void uploadMultiPartFile(@RequestParam(value = "FileName")MultipartFile fileName){
        try{
            String file = awss3Service.uploadMultipartFile(bucketName, fileName);
        } catch (ServiceException|IOException e){
            logger.error("error when uploading multipart file with message{}",e.getMessage());
        }
    }
    @RequestMapping(value = "/picture", method = RequestMethod.POST, consumes = MULTIPART_FORM_DATA_VALUE)
    public void uploadPicture() {
        awss3Service.uploadObject("class-1801", "123.jpg", "/User/Download");

    }

    @RequestMapping(value = "/presignedurl", method = RequestMethod.GET)
    public String generatePresignedURL(@RequestParam(value = "key")String key){
        String presignedUrl = awss3Service.generatePresignedURL("class-1801", key, HttpMethod.GET.toString());
        return presignedUrl;
    }


}
