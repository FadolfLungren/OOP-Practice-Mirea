package Services;

import Models.DAO.ProductDAO;
import Models.Entities.Product;

import java.util.ArrayList;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService(){
        productDAO = new ProductDAO();
    }

    public Product getById(String id) {
        return productDAO.getById(id);
    }

    public ArrayList<Product> getList(int limit){
        return productDAO.getList(limit);
    }

    public boolean addProduct(Product product){
        return productDAO.addProduct(product);
    }

    public boolean remove(String id){
        return productDAO.deleteProduct(id);
    }
    public boolean update(Product product){
        return productDAO.updateProduct(product);
    }
}
