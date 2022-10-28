package com.eminimal.backend.models.users;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
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

    @OneToOne(cascade = CascadeType.ALL)
    private UserDetails details;

    public Users(String userName, String userPassword, String userEmail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    public Users(String userName, String userPassword, String userEmail, UserDetails details) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.details = details;
    }
}