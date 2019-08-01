package com.TomaszKasper.service;

import com.TomaszKasper.model.Movie;
import com.TomaszKasper.repository.MovieRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Movie add(final String title, final String premiereTime) {
        LocalDateTime premiereTimeParsed = LocalDateTime.parse(premiereTime, DateTimeFormatter.BASIC_ISO_DATE);
        return movieRepository.save(new Movie(title, premiereTimeParsed));
    }

    public void remove(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> findByTitle(final String title) {
        return movieRepository.findAllByTitle(title);
    }

    public Movie update(final Long id, final String newTitle, final String newPremiereDate) throws NotFoundException {
        Optional<Movie> movie = movieRepository.findById(id);
        if (!movie.isPresent()) {
            throw new NotFoundException("Brak filmu o podanym id");
        } else {
            movieRepository.deleteById(id);
            movie.get().setTitle(newTitle);
            movie.get().setPremiereDate(LocalDateTime.parse(newPremiereDate, DateTimeFormatter.BASIC_ISO_DATE));
            return movieRepository.save(movie.get());
        }
    }
}
