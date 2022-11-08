package com.eminimal.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    private String ratingID = UUID.randomUUID().toString();

    private String userID;
    private int rating;

    public Rating(String userID, int rating) {
        this.userID = userID;
        this.rating = rating;
    }
}
