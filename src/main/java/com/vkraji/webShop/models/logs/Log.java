package com.vkraji.webShop.models.logs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Log {

    @Transient
    DateTimeFormatter formatter =
            DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
                    .withLocale( Locale.UK )
                    .withZone( ZoneId.systemDefault() );

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

    public String getTime(){
        return formatter.format(time);
    }
}
