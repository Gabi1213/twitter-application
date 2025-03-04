package app.fiipractic.repository;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LikeDao {

    private final JdbcTemplate jdbcTemplate;

    public LikeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String likeDislikePost(int postId, int userId){
        String selectSql = "SELECT COUNT(*) FROM likes WHERE post_id = ? AND user_id = ?";
        Integer liked = jdbcTemplate.queryForObject(selectSql, Integer.class, postId, userId);
        if (liked != null && liked == 0){
            String sql = "INSERT INTO likes (post_id, user_id) VALUES (?, ?)";
            jdbcTemplate.update(sql, postId, userId);
            return "Post liked!";
        }else {
            String sql = "SELECT like_id FROM likes WHERE post_id = ? AND user_id = ?";
            String sqlDelete = "DELETE FROM likes WHERE like_id = ?";
            Integer likedId = jdbcTemplate.queryForObject(sql, Integer.class, postId, userId);
            jdbcTemplate.update(sqlDelete, likedId);
            return "Post disliked!";
        }
    }

    public void deleteLikesByPostId(long postId) {
        String sql = "DELETE FROM likes WHERE post_id = ?";
        jdbcTemplate.update(sql, postId);
    }

}
