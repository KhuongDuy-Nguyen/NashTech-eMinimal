package com.eminimal.backend.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

    @Id
    private String userDetailsID = UUID.randomUUID().toString();

    private String userPhone;
    private String userAddress;
    private String userCountry;
    private boolean userActive = false;
    private String userRole = "GUEST";

    public UserDetails(String userDetailsID, String userPhone, String userAddress, String userCountry) {
        this.userDetailsID = userDetailsID;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userCountry = userCountry;
    }

    public UserDetails(String userPhone, String userAddress, String userCountry, boolean userActive, String userRole) {
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userCountry = userCountry;
        this.userActive = userActive;
        this.userRole = userRole;
    }
}
