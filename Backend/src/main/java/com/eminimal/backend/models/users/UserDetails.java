package com.eminimal.backend.models.users;

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

    private String userImage;
    private String userPhone;
    private String userAddress;
    private String userCountry;
    private boolean userActive = false;
    private String userRole = "GUEST";


}
