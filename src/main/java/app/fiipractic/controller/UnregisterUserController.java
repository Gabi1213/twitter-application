package app.fiipractic.controller;

import app.fiipractic.repository.UnregisterUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unregister")
public class UnregisterUserController {

    private final UnregisterUserDao unregisterUserDao;

    @Autowired
    public UnregisterUserController(UnregisterUserDao unregisterUserDao) {
        this.unregisterUserDao = unregisterUserDao;
    }

    @PostMapping("/user")
    public ResponseEntity<String> unregisterUser(
            @RequestParam("userId") int userId
    ) {
        unregisterUserDao.unregisterUserAndPosts(userId);
        return ResponseEntity.ok("User unregistered successfully");
    }
}
