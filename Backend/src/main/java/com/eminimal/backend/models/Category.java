package com.eminimal.backend.models;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "categoryID"))
public class Category {
    @Id
    private UUID categoryID = UUID.randomUUID();

    private String categoryName;
    private String categoryDesc;


}