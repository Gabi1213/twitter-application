package app.fiipractic.service;

import app.fiipractic.model.User;

import java.util.List;

public interface UserService{
    List<User> getUser(String username);
    User registerUser(User user) throws Exception;
}
