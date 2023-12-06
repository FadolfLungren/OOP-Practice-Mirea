package Models.DAO;

import Models.Entities.User;
import utils.Mapper.MapperFactory;

import java.util.ArrayList;

public class UserDAO extends BaseDAO<User>{

    public UserDAO() {
        super(MapperFactory.getMapper("User"));
    }

    public User getById(String id) {
        String query = "SELECT * FROM USER WHERE ID = ?";
        return this.getRecordSingle(query, new Object[] {id});
    }

    public User getByLogin(String login) {
        String query = "SELECT * FROM USER WHERE LOGIN = ?";
        return this.getRecordSingle(query, new Object[] {login});
    }

    public ArrayList<User> getList(int limit) {
        String query = "SELECT * FROM USER LIMIT ?";
        return this.getRecordArray(query, new Object[] {limit});
    }
    
    public boolean add(User user) {
        String query = """
				INSERT INTO user(id, role, username, password, login, img)
				VALUES(?, ?, ?, ?, ?, ?);
		""";
        return this.executeQuery(query, new Object[] { user.getId(), "USER", user.getUserName(),  user.getPassword(), user.getLogin(), user.getImg()});
    }
    public boolean deleteUser(String id) {
        String query = "DELETE FROM User WHERE ID = ?";
        return this.executeQuery(query, new Object[] {id});
    }
    public boolean updateUser(User user) {
        String query = """	
				UPDATE USER SET ID = ?, LOGIN = ?, PASSWORD = ?, USERNAME = ?
				WHERE ID = ?
		""";
        return this.executeQuery(query, new Object[]{user.getId(), user.getLogin(), user.getPassword(), user.getUserName(), user.getId()});
    }
}
