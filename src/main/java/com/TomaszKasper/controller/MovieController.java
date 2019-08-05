package com.TomaszKasper.controller;

import com.TomaszKasper.model.Movie;
import com.TomaszKasper.service.MovieService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @RequestMapping("/add")
    public Movie add(@RequestParam String title, @RequestParam String premiereDate) {
        return movieService.add(title, premiereDate);
    }

    @RequestMapping("/remove")
    public String remove(@RequestParam Long id) {
        movieService.remove(id);
        return "Request processed";
    }

    @RequestMapping("/find")
    public List<Movie> find(@RequestParam String title) {
        return movieService.findByTitle(title);
    }

    @RequestMapping("/update")
    public Movie update(@RequestParam Long id, @RequestParam String newTitle, @RequestParam String newPremiereDate) throws NotFoundException {
        return movieService.update(id, newTitle, newPremiereDate);
    }

    @RequestMapping("/all")
    public List<Movie> getAll() {
        return movieService.getAll();
    }
}
