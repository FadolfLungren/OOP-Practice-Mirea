package utils.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Models.Entities.Bucket;

public class BucketMapper implements ISqlMapper<Bucket>{

    @Override
    public Bucket map(ResultSet row) {
        Bucket bucket = new Bucket();
        try {
            if (row == null || row.getRow() == 0)
                return null;
            bucket.setId(row.getInt("id"));

            bucket.setItemId(row.getInt("itemId"));
            bucket.setOwnerId(row.getInt("ownerId"));

        } catch (SQLException e) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "an exception was thrown", e);
        }
        return bucket;
    }

}
