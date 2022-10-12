package com.eminimal.backend.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "User", uniqueConstraints = {@UniqueConstraint(columnNames = {"userID","userName", "userEmail"})})
public class User {
    @Id
    private UUID userID;
    private String userName;
    private String userPassword;
    private String userImage;
    private String userRole;
    private String userPhone;
    private String userEmail;
    private String userAddress;
    private String userCountry;
    private boolean userActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return userID != null && Objects.equals(userID, user.userID);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}