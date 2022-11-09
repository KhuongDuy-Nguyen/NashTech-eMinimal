package com.eminimal.backend.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","user_name", "user_email"})})
public class Users{

    @Id
    @Column(name = "user_id")
    private String userId = UUID.randomUUID().toString();

    @Column(name = "user_name")
    private String userName;
    private String userPassword;
    @Column(name = "user_email")
    private String userEmail;

    private String userPhone;
    private String userAddress;
    private String userCountry;
    private boolean userActive = false;
    private String userRole = "GUEST";

    public Users(String userName, String userPassword, String userEmail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    public Users(String userName, String userPassword, String userEmail, String userPhone, String userAddress, String userCountry) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userCountry = userCountry;
    }

    public Users(String userName, String userPassword, String userEmail, String userPhone, String userAddress, String userCountry, boolean userActive, String userRole) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userCountry = userCountry;
        this.userActive = userActive;
        this.userRole = userRole;
    }


}