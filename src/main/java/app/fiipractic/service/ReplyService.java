package app.fiipractic.service;

import app.fiipractic.model.Reply;

public interface ReplyService {
    Reply addReply(int parentPostId, String content, boolean isPublic);
    public void deleteReply(Long parent_post_Id);
}
