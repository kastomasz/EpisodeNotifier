package com.TomaszKasper.service;

import com.TomaszKasper.model.Movie;
import com.TomaszKasper.model.User;
import com.TomaszKasper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
}
