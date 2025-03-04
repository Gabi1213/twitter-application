package app.fiipractic.service;

import app.fiipractic.model.Post;

import java.util.List;

public interface PostService {
    Post addPost(Post post);
    List<String> getPostsByUserId(Long userId);
    List<Post> getPostsByUsersFollowed(Long userId);
    void deletePost(Long postId) throws Exception;
    void copyPostFromUser(Long postId, Long newUserId);

}
