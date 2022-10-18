package com.eminimal.backend.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","user_name", "user_email"})})
public class Users {
    @Id
    @Column(name = "user_id")
    private UUID userId = UUID.randomUUID();

    private String userName;
    private String userPassword;
    private String userImage;
    private String userRole;
    private String userPhone;
    private String userEmail;
    private String userAddress;
    private String userCountry;

    @Column(columnDefinition = "boolean default false")
    private boolean userActive;

    @ManyToOne
    private Orders orders;

    public Users(String userName, String userPassword, String userEmail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }
}