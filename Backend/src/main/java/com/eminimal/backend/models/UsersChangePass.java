package com.eminimal.backend.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersChangePass {
    private String userId;
    private String oldPass;
    private String newPass;
}
