package app.fiipractic.service;

import app.fiipractic.repository.LikeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService{

   @Autowired
    private final LikeDao likeDao;

    public LikeServiceImpl(LikeDao likeDao) {
        this.likeDao = likeDao;
    }

    @Override
    public String likeDislikePost(int postId, int userId){
        return likeDao.likeDislikePost(postId, userId);
    }

    @Override
    public void deleteLikesByPostId(long postId) {
        likeDao.deleteLikesByPostId(postId);
    }


}

