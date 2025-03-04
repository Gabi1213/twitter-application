package app.fiipractic.controller;

import app.fiipractic.repository.FollowDao;
import app.fiipractic.repository.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowDao followDao;
    private final PostDao postDao;

    @Autowired
    public FollowController(FollowDao followDao, PostDao postDao) {
        this.followDao = followDao;
        this.postDao = postDao;
    }

    @PostMapping("/user")
    public ResponseEntity<Map<String, Object>> followUserAndRetrievePosts(
            @RequestParam("followerId") Long followerId,
            @RequestParam("followedUserId") Long followedUserId
    ) {
        followDao.followUser(followerId, followedUserId);
        List<String> posts = postDao.getPostsByUserId(followedUserId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User followed successfully");
        response.put("followerId", followerId);
        response.put("followedUserId", followedUserId);
        response.put("posts", posts);
        return ResponseEntity.ok(response);
    }
}
