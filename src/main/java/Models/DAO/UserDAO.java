package Models.DAO;

import Models.Entities.User;
import utils.Mapper.MapperFactory;

public class UserDAO extends BaseDAO<User>{

    public UserDAO() {
        super(MapperFactory.getMapper("User"));
    }

    public User getById(String id) {
        String query = "SELECT * FROM USER WHERE ID = ?";
        return this.getRecordSingle(query, new Object[] {id});
    }
}
