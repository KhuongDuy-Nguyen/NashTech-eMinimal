package com.eminimal.backend.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "categoryID"))
public class Category {
    @Id
    private UUID categoryID = UUID.randomUUID();

    private String categoryName;
    private String categoryDesc;

    public Category() {}

    public Category(String categoryName, String categoryDesc) {
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
    }
}