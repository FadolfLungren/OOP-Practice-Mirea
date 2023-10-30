package Models.DAO;

import Models.Entities.Product;
import utils.Mapper.MapperFactory;

import java.util.ArrayList;

public class ProductDAO extends BaseDAO<Product>{

    public ProductDAO() {
        super(MapperFactory.getMapper("Product"));
    }

    public Product getById(String id) {
        String query = "SELECT * FROM PRODUCT WHERE ID = ?";
        return this.getRecordSingle(query, new Object[] {id});
    }

    public ArrayList<Product> getList(int limit) {
        String query = "SELECT * FROM PRODUCT LIMIT ?";
        return this.getRecordArray(query, new Object[] {limit});
    }

    public boolean addProduct(Product product) {
        String query = """
				INSERT INTO product(id, title, cost, description, category, imgUrl)
				VALUES(?, ?, ?, ?, ?, ?);
		""";
        return this.executeQuery(query, new Object[] { product.getId(), product.getTitle(),  product.getCost(), product.getDescription(), product.getCategory(), product.getUrl() });
    }
    public boolean deleteProduct(String id) {
        String query = "DELETE FROM Product WHERE ID = ?";
        return this.executeQuery(query, new Object[] {id});
    }
    public boolean updateProduct(Product product) {
        String query = """	
				UPDATE PRODUCT SET ID = ?, TITLE = ?, COST = ?, DESCRIPTION = ?, CATEGORY = ?, IMGURL = ?
				WHERE ID = ?
		""";
        return this.executeQuery(query, new Object[]{product.getId(), product.getTitle(),  product.getCost(), product.getDescription(), product.getCategory(), product.getUrl() });
    }
}
