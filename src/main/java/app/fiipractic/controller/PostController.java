package app.fiipractic.controller;


import app.fiipractic.model.Post;
import app.fiipractic.service.LikeService;
import app.fiipractic.service.MentionService;
import app.fiipractic.service.PostService;
import app.fiipractic.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class PostController {

    private final PostService postService;
    private final MentionService mentionService;
    private final LikeService likeService;
    private final ReplyService replyService;

    public PostController(PostService postService, MentionService mentionService, LikeService likeService, ReplyService replyService) {
        this.postService = postService;
        this.mentionService = mentionService;
        this.likeService = likeService;
        this.replyService = replyService;
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        Post savedPost = postService.addPost(post);
        Post mentionPost = new Post();
        mentionPost.setPostId(savedPost.getPostId());
        mentionPost.setMentions(post.getMentions());
        mentionService.addMention(mentionPost);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @GetMapping("/feed/{userId}")
    public List<Post> getFeed(@PathVariable Long userId) {
        return postService.getPostsByUsersFollowed(userId);
    }

    @GetMapping("/currentposts/{currentUserId}")
    public List<String> getPostsByUserId(@PathVariable Long currentUserId) {
        return postService.getPostsByUserId(currentUserId);
    }

    @DeleteMapping("/deletepost/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable long postId) {
          try {
              likeService.deleteLikesByPostId(postId);
              replyService.deleteReply(postId);
              postService.deletePost(postId);
              return ResponseEntity.ok("Post,associated likes and all reply's have been deleted.");
          }catch (Exception e) {
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
          }

    }


    @PostMapping("/copyPost/{postId}/toUser/{newUserId}")
    public ResponseEntity<String> copyPostToUser(@PathVariable Long postId, @PathVariable Long newUserId) {
        try {
            postService.copyPostFromUser(postId, newUserId);
            return ResponseEntity.ok("Post copied successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
