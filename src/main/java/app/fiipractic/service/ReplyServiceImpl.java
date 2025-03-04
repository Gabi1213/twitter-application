package app.fiipractic.service;

import app.fiipractic.model.Reply;
import app.fiipractic.repository.ReplyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {


    @Autowired
    private ReplyDao replyDao;


    @Override
    public Reply addReply(int parentPostId, String content, boolean isPublic) {
        return replyDao.addReply(parentPostId, content, isPublic);
    }

    @Override
    public void deleteReply(Long parent_post_Id) {
        replyDao.deleteReply(parent_post_Id);
    }
}
