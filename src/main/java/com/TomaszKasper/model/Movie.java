package com.TomaszKasper.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private LocalDateTime premiereDate;

    public Movie (final String title, final LocalDateTime premiereDate) {
        this.title = title;
        this.premiereDate = premiereDate;
    }
}
