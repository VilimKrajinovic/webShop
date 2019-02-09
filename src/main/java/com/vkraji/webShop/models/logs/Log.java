package com.vkraji.webShop.models.logs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.security.Principal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Log {

    @Id
    @GeneratedValue()
    private long id;

    private String principal;
    private String ip;
    private Instant time;

    public Log(String principal, String ip, Instant time){
        this.principal = principal;
        this.ip = ip;
        this.time = time;
    }
}
