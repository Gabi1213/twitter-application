package app.fiipractic.service;

import app.fiipractic.model.Post;
import app.fiipractic.repository.MentionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentionServiceImpl implements MentionService {

    @Autowired
    private MentionDao mentionDao;

    @Override
    public List<String> getMentionsForUser(String userId) {
        return mentionDao.getMentionsForUser(userId);
    }

    @Override
    public void addMention(Post post) {
        mentionDao.addMention(post);
    }


}
