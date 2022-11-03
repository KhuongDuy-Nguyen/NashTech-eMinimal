package com.eminimal.backend.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "category_id"))
public class Category {

    @Id
    @Column(name = "category_id")
    private String categoryID = UUID.randomUUID().toString();

    private String categoryName;
    private String categoryDesc;

    public Category(String categoryName, String categoryDesc) {
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
    }

    public Category(String categoryID, String categoryName, String categoryDesc) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
    }
}