package com.eminimal.backend.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.UUID;

@Data

public class FileDto implements Serializable {
    private String id = UUID.randomUUID().toString();
    private String type;
    private MultipartFile attachment;
}
