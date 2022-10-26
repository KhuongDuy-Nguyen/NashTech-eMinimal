package com.eminimal.backend.controllers;

import com.dropbox.core.DbxException;
import com.eminimal.backend.dto.FileDto;
import com.eminimal.backend.services.impl.FileServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private FileServiceImpl fileService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) throws Exception {
        File convertFile = new File("upload/images/" + file.getOriginalFilename());

        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();

        logger.info("Path covert file: " + convertFile.getAbsolutePath());
        file.transferTo(convertFile.getAbsoluteFile());
        return new ResponseEntity<>("Upload success: " + fileService.upload(convertFile), HttpStatus.OK);

    }
}
