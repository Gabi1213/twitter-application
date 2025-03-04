package app.fiipractic.controller;

import app.fiipractic.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/{postId}/{userId}")
    public ResponseEntity<String> likePost(@PathVariable int postId, @PathVariable int userId) {
           String mesaj = likeService.likeDislikePost(postId, userId);
            return ResponseEntity.ok(mesaj);
    }
    
}
