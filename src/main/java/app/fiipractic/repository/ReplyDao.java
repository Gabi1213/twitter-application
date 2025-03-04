package app.fiipractic.repository;

import app.fiipractic.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Reply addReply(int parentPostId, String content, boolean isPublic) {
        String sql = "INSERT INTO reply (content, is_public, parent_post_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, content, isPublic, parentPostId);

        Long generatedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        Reply reply = new Reply();
        reply.setId(generatedId);
        reply.setContent(content);
        reply.setPublic(isPublic);
        reply.setParentPostId(parentPostId);
        return reply;
    }

    public void deleteReply(Long parent_post_Id) {
        String deleteRepliesSql = "DELETE FROM reply WHERE parent_post_id = ?";
        jdbcTemplate.update(deleteRepliesSql, parent_post_Id);
    }



}
