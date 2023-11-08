package Services;

import Models.DAO.UserDAO;
import Models.Entities.User;

import java.util.ArrayList;

public class UserService {
    private final UserDAO userDAO;

    public UserService(){
        userDAO = new UserDAO();
    }

    public User getById(String id) {
        return userDAO.getById(id);
    }

    public User getByLogin(String login) {
        return userDAO.getByLogin(login);
    }

    public ArrayList<User> getList(int limit){
        return userDAO.getList(limit);
    }

    public boolean add(User user){
        return userDAO.add(user);
    }

    public boolean remove(String id){
        return userDAO.deleteUser(id);
    }
    public boolean update(User user){
        return userDAO.updateUser(user);
    }
}
