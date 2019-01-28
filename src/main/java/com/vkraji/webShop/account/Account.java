package com.vkraji.webShop.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name="account")
@Data
@NoArgsConstructor
public class Account implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    private String role = "ROLE_USER";

    private Instant created;

    public Account(String email, String password, String role){
        this.email = email;
        this. password = password;
        this.role = role;
        this.created = Instant.now();
    }
}
