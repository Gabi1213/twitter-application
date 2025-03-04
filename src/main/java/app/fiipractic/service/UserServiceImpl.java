package app.fiipractic.service;

import app.fiipractic.model.User;
import app.fiipractic.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> getUser(String username) {
        return userDAO.getUsersByUsername(username);
    }

    @Override
    public User registerUser(User user) throws Exception {
        return userDAO.registerUser(user);
    }

}
