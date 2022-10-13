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

    @Column(columnDefinition = "boolean default false")
    private boolean userActive;

    @ManyToOne
    private Order orders;


}