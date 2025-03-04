package app.fiipractic.service;

public interface LikeService {
    String likeDislikePost(int postId, int userId);
    void deleteLikesByPostId(long postId);
}
