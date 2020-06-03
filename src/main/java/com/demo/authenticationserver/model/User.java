package com.demo.authenticationserver.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class User {
    @Id
    private String username;
    @NotNull
    private String password;

    @Email
    @NotNull
    private String email;
}
