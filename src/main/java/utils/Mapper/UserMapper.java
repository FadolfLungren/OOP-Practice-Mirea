package utils.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Models.Entities.User;

public class UserMapper implements ISqlMapper<User>{

    @Override
    public User map(ResultSet row) {
        User User = new User();
        try {
            if (row == null || row.getRow() == 0)
                return null;
            User.setId(row.getInt("id"));
            User.setAccessLvl(row.getInt("accessLvl"));
            User.setUserName(row.getString("username"));
            User.setLogin(row.getString("login"));
            User.setPassword(row.getString("password"));
        } catch (SQLException e) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "an exception was thrown", e);
        }
        return User;
    }

}
