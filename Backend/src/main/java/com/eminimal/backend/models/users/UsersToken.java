package com.eminimal.backend.models.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UsersToken{
    @Id
    private String tokenID = UUID.randomUUID().toString();

    private String userId;

    private String token;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+7")
    private Date createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+7")
    private Date expiryDate;

    public UsersToken(String userId, String token, Date createdDate, Date expiryDate) {
        this.userId = userId;
        this.token = token;
        this.createdDate = createdDate;
        this.expiryDate = expiryDate;
    }
}
