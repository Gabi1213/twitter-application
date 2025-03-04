package app.fiipractic.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class FollowDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FollowDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void followUser(Long followerId, Long followedUserId) {
        String checkSql = "SELECT COUNT(*) FROM follower WHERE follower_id = ? AND followed_user_id = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, followerId, followedUserId);

        if (count == 0) {
            String insertSql = "INSERT INTO follower (follower_id, followed_user_id) VALUES (?, ?)";
            jdbcTemplate.update(insertSql, followerId, followedUserId);
        } else {
            throw new IllegalStateException("You are already following this user.");
        }
    }

}
