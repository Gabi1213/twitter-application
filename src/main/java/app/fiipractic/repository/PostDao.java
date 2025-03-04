package app.fiipractic.repository;

import app.fiipractic.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Repository
public class PostDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Post addPost(Post post) {
        String sql = "INSERT INTO post (CONTENT, USER_ID, order_by_time) VALUES (?, ?, ?)";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        jdbcTemplate.update(sql, post.getContent(), post.getUserId(), timestamp);
        Long postId = jdbcTemplate.queryForObject("SELECT post_id from post where order_by_time = ?", Long.class, timestamp);
        Post newPost = new Post();
        newPost.setPostId(postId);
        newPost.setContent(post.getContent());
        newPost.setUserId(post.getUserId());
        return newPost;
    }

    public List<String> getPostsByUserId(Long userId) {
        String sql = "SELECT content FROM post WHERE user_id = ? ORDER BY order_by_time DESC";
        return jdbcTemplate.queryForList(sql, String.class, userId);
    }

    public List<Post> getPostsByUsersFollowed(Long userId) {
        String sql = "SELECT p.post_id, p.USER_ID, p.content, p.order_by_time " +
                "FROM post p JOIN fiipractic.follower u ON u.followed_user_id = p.USER_ID " +
                "WHERE u.follower_id = ? " +
                "ORDER BY p.order_by_time DESC";
        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> {
            Post post = new Post();
            post.setPostId(rs.getLong("post_id"));
            post.setUserId(rs.getLong("user_id"));
            post.setContent(rs.getString("content"));
            post.setOrderByTime(rs.getString("order_by_time"));
            return post;
        });
    }


    public void deletePost(Long postId) throws Exception {
        String sql = "DELETE FROM post WHERE post_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, postId);
        if (rowsAffected == 0) {
            throw new Exception("No post found with id " + postId);
        } else {
            System.out.println("Deleted post with id " + postId);
        }
    }


    public void copyPostFromUser(Long postId, Long newUserId) {
        String selectSql = "SELECT * FROM post WHERE post_id = ?";
        Map<String, Object> postMap = jdbcTemplate.queryForMap(selectSql, postId);
        if (!postMap.isEmpty()) {
            String content = (String) postMap.get("content");
            String insertSql = "INSERT INTO post (user_id, content) VALUES (?, ?)";
            jdbcTemplate.update(insertSql, newUserId, content);
        } else {
            throw new RuntimeException("Post with ID " + postId + " not found.");
        }
    }
    

}

