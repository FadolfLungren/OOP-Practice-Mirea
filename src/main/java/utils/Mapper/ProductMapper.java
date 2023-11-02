package utils.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Models.Entities.Product;

public class ProductMapper implements ISqlMapper<Product>{

    @Override
    public Product map(ResultSet row) {
        Product Product = new Product();
        try {
            if (row == null || row.getRow() == 0)
                return null;
            Product.setId(row.getInt("id"));
            Product.setTitle(row.getString("title"));
            Product.setCost(row.getString("cost"));
            Product.setDescription(row.getString("description"));
            Product.setCategory(row.getString("category"));
            Product.setProductImgUrl(row.getString("imgUrl"));
        } catch (SQLException e) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "an exception was thrown", e);
        }
        return Product;
    }

}
