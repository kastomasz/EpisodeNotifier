package com.TomaszKasper.service;

import com.TomaszKasper.exception.UsernameNotAviableException;
import com.TomaszKasper.model.Movie;
import com.TomaszKasper.model.User;
import com.TomaszKasper.repository.MovieRepository;
import com.TomaszKasper.repository.UserRepository;
import javassist.NotFoundException;
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
            throw new UsernameNotAviableException("Given username is already occupied");
        } else {
            return userRepository.save(new User(username, password));
        }
    }

    public List<Movie> getMovies(final String username) throws Exception {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get().getMovies();
        } else {
            throw new NotFoundException("User doesn't exist");
        }
    }

    public void followMovie(final String username, final String password, final Long movieId) throws Exception {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (!user.isPresent()) {
            throw new NotFoundException("User doesn't exist");
        }
        if (!movie.isPresent()) {
            throw new NotFoundException("Movie doesn't exist");
        }

        if (!user.get().getMovies().contains(movie)) {
            user.get().getMovies().add(movie.get());
            userRepository.save(user.get());
        }
    }

}
