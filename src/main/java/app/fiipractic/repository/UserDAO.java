package app.fiipractic.repository;

import app.fiipractic.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> getUsersByUsername(String username) {
        String sql = "SELECT * FROM user WHERE FIRST_NAME = ?";
        return jdbcTemplate.query(sql, new Object[]{username}, (rs, rowNum) ->
        {
            User user = new User();
            user.setPassword(rs.getString("PASSWORD"));
            user.setEmail(rs.getString("EMAIL"));
            user.setLastName(rs.getString("LAST_NAME"));
            user.setFirstName(rs.getString("FIRST_NAME"));
            return user;
        });

    }

    public User registerUser(User user) throws Exception {
        String retrieveSql = "SELECT * FROM user WHERE email = ?";

        try {
            jdbcTemplate.queryForObject(retrieveSql, new Object[]{user.getEmail()}, new BeanPropertyRowMapper<>(User.class));
            throw new Exception("User already exists");
        } catch (EmptyResultDataAccessException e) {
        }

        String sql = "INSERT INTO user (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());

        return jdbcTemplate.queryForObject(retrieveSql, new Object[]{user.getEmail()}, new BeanPropertyRowMapper<>(User.class));
    }
}

