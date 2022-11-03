package com.eminimal.backend.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.eminimal.backend.models.Category} entity
 */
@Data
public class CategoryResponseDto implements Serializable {
    private final String categoryID;
    private final String categoryName;
    private final String categoryDesc;
}