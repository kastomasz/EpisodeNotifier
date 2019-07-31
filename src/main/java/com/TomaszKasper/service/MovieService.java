package com.TomaszKasper.service;

import com.TomaszKasper.model.Movie;
import com.TomaszKasper.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Movie add(Movie movie) {
        return movieRepository.save(movie);
    }

    public void remove(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> findByTitle(final String title) {
        return movieRepository.findAllByTitle(title);
    }

    public Movie update(Movie movie) {
        movieRepository.deleteById(movie.getId());
        return movieRepository.save(movie);
    }
}
