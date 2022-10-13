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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userID","userName", "userEmail"})})
public class User {
    @Id
    private UUID userID = UUID.randomUUID();

    private String userName;
    private String userPassword;
    private String userImage;
    private String userRole;
    private String userPhone;
    private String userEmail;
    private String userAddress;
    private String userCountry;

    @Column(columnDefinition = "boolean  default false")
    private boolean userActive;

    public User() { }

    public User(String userName, String userPassword, String userImage, String userRole, String userPhone, String userEmail, String userAddress, String userCountry, boolean userActive) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userImage = userImage;
        this.userRole = userRole;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userCountry = userCountry;
        this.userActive = userActive;
    }


}