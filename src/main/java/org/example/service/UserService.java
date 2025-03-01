package org.example.service;


import org.example.model.User;

public interface UserService {
    User get(long id);

    User add(User user);
}
