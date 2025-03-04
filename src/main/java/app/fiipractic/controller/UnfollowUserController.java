package app.fiipractic.controller;

import app.fiipractic.repository.UnfollowUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unfollow")
public class UnfollowUserController {

        private final UnfollowUserDao unfollowUserDao;

        @Autowired
        public UnfollowUserController(UnfollowUserDao unfollowUserDAO) {
            this.unfollowUserDao = unfollowUserDAO;
        }

        @PostMapping("/user")
        public ResponseEntity<String> unfollowUser(
                @RequestParam("followerId") Integer followerId,
                @RequestParam("followedUserId") Integer followedUserId
        ) {
            unfollowUserDao.unfollowUser(followerId, followedUserId);
            return ResponseEntity.ok("User unfollowed successfully");
        }
    }

