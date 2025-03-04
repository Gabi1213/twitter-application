package app.fiipractic.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

    @Repository
    public class UnregisterUserDao {
        private final JdbcTemplate jdbcTemplate;

        @Autowired
        public UnregisterUserDao(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        public void unregisterUserAndPosts(int userId) {
            String deletePostsQuery = "DELETE FROM post WHERE user_id = ?";
            jdbcTemplate.update(deletePostsQuery, userId);

            String deleteUserQuery = "DELETE FROM user WHERE USER_ID = ?";
            jdbcTemplate.update(deleteUserQuery, userId);

            System.out.println("User " + userId + " and their posts have been removed.");
        }
    }

