package app.fiipractic.repository;

import app.fiipractic.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MentionDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MentionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addMention(Post post){
        String sql = "INSERT into mentions (post, user) values (?, ?)";
        for (int i=0; i<post.getMentions().length; i++){
            String mentionUser = post.getMentions()[i];
            jdbcTemplate.update(sql, post.getPostId(), mentionUser);
        }
    }


    public List<String> getMentionsForUser(String mentionedUser) {
        String sql = "SELECT content FROM mentions m JOIN post p ON m.post=p.post_id WHERE user = ?";
        return jdbcTemplate.query(sql, new Object[]{mentionedUser}, (rs, rowNum) -> rs.getString("content"));
    }

}
