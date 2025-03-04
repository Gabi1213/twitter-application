package app.fiipractic.service;

import app.fiipractic.model.Post;

import java.util.List;

public interface MentionService {
    List<String> getMentionsForUser(String mentionedUser);
    void addMention(Post post);
}
