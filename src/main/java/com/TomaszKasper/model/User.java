package com.TomaszKasper.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    private String password;

    @ManyToOne
    private List<Movie> movies;

    public User(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.password = password;
    }
}
