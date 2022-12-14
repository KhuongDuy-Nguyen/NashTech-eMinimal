package com.eminimal.backend.controllers;

import com.dropbox.core.InvalidAccessTokenException;
import com.eminimal.backend.dto.ErrorResponse;
import com.eminimal.backend.models.Product;
import com.eminimal.backend.services.interfaces.ProductService;
import com.eminimal.backend.utils.FileService;
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

@RestController
@RequestMapping("/api/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private FileService fileService;

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) throws Exception {
//        logger.info("Path file: " + file.getOriginalFilename());
        File convertFile = new File("upload/images/" + file.getOriginalFilename());

        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();

        logger.info("Path covert file: " + convertFile.getAbsolutePath());
        file.transferTo(convertFile.getAbsoluteFile());

        Product product = productService.findById(id);
        product.getProductImage().add(fileService.upload(convertFile));
        productService.save(product);

        return new ResponseEntity<>("Upload success", HttpStatus.OK);
//        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(InvalidAccessTokenException.class)
    ResponseEntity<ErrorResponse> resourceFoundException(){
        ErrorResponse errorResponse = new ErrorResponse("01", "Invalid dropbox token. Please reload it in BE");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
