package com.eminimal.backend.models.users;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
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

    private boolean userActive = false;
    private String userImage;
    private String userPhone;
    private String userAddress;
    private String userCountry;


    public Users(String userName, String userPassword, String userEmail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }
}