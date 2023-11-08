package utils.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Models.Entities.User;

public class UserMapper implements ISqlMapper<User>{

    @Override
    public User map(ResultSet row) {
        User user = new User();
        try {
            if (row == null || row.getRow() == 0)
                return null;
            user.setId(row.getInt("id"));

            user.setRole(row.getString("role"));
            user.setUserName(row.getString("username"));
            user.setLogin(row.getString("login"));
            user.setPassword(row.getString("password"));
        } catch (SQLException e) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "an exception was thrown", e);
        }
        return user;
    }

}
