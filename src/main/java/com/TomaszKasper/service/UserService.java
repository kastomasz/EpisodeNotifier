package com.TomaszKasper.service;

import com.TomaszKasper.model.Movie;
import com.TomaszKasper.model.User;
import com.TomaszKasper.repository.MovieRepository;
import com.TomaszKasper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    public User create(final String username, final String password) throws Exception {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new Exception("Podana nazwa użytkownika jest zajęta");
        } else {
            return userRepository.save(new User(username, password));
        }
    }

    public List<Movie> getMovies(final String username) throws Exception {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get().getMovies();
        } else {
            throw new Exception("Podany użytkownik nie istnieje");
        }
    }

    public void addMovie(final String username, final String password, final Long movieId) throws Exception {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (!user.isPresent()) {
            throw new Exception("Podany użytkownik nie istnieje");
        }
        if (!movie.isPresent()) {
            throw new Exception("Film o podanym ID nie istnieje");
        }

        if (!user.get().getMovies().contains(movie)) {
            user.get().getMovies().add(movie.get());
            userRepository.save(user.get());
        }
    }

}
