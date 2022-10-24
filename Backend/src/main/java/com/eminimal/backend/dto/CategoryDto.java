package com.eminimal.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link com.eminimal.backend.models.Category} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CategoryDto implements Serializable {
    private String categoryID;
    private String categoryName;
    private String categoryDesc;
}