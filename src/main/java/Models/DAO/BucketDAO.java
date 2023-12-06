package Models.DAO;

import Models.Entities.Bucket;
import Models.Entities.Product;
import utils.Mapper.ISqlMapper;
import utils.Mapper.MapperFactory;
import utils.Mapper.ProductMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BucketDAO extends BaseDAO<Bucket> {

    public BucketDAO() {
        super(MapperFactory.getMapper("Bucket"));
    }

    public ArrayList<Product> getAllByOwnerId(String id) {
        String query = "SELECT product.title as title, product.description as description, product.imgUrl as imgUrl," +
                " product.id as id, product.cost as cost, product.category as category" +
                " FROM bucket" +
                " JOIN product on bucket.itemId = product.id" +
                " WHERE ownerId = ?;";
        ArrayList<Product> data = new ArrayList<Product>();
        Connection connection = getConnection();
        if (connection != null) {
            try {
                PreparedStatement statement = getPrepareStatement(connection, query, new Object[] {id});
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    ISqlMapper<Product> _mapper = MapperFactory.getMapper("Product");
                    Product entity = (Product) _mapper.map(result);
                    data.add(entity);
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return new ArrayList<Product>();
            }
            return data;
        } else {
            return new ArrayList<Product>();
        }
    }
    public boolean addBucketItem(Bucket bucket) {
        String query = """
				INSERT INTO bucket(idbucket, ownerId, itemId)
				VALUES(?, ?, ?);
		""";
        return this.executeQuery(query, new Object[] { bucket.getId(), bucket.getOwnerId(), bucket.getItemId()});
    }

    public boolean deleteBucketItem(String id) {
        String query = "DELETE FROM bucket WHERE idbucket = ?";
        return this.executeQuery(query, new Object[] {id});
    }

}
