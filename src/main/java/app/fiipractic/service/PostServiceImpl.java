package app.fiipractic.service;


import app.fiipractic.model.Post;
import app.fiipractic.repository.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostDao postDao;

    @Override
    public Post addPost(Post post) {
        return postDao.addPost(post);
    }

    @Override
    public List<String> getPostsByUserId(Long userId) {
        return postDao.getPostsByUserId(userId);
    }

    @Override
    public List<Post> getPostsByUsersFollowed(Long userId) {
        return postDao.getPostsByUsersFollowed(userId);
    }

    @Override
    public void deletePost(Long postId) throws Exception {
        postDao.deletePost(postId);
    }

    @Override
    public void copyPostFromUser(Long postId, Long newUserId) {
        postDao.copyPostFromUser(postId, newUserId);
    }


}
