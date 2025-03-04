package app.fiipractic.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UnfollowUserDao {


    private final JdbcTemplate jdbcTemplate;

    public UnfollowUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void unfollowUser(int followerId, int followedUserId) {
            String deleteFollowQuery = "DELETE FROM follower WHERE follower_id = ? AND followed_user_id = ?";
            jdbcTemplate.update(deleteFollowQuery, followerId, followedUserId);
            System.out.println("User " + followerId + " unfollowed user " + followedUserId);
        }
    }

