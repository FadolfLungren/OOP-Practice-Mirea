package Models.DAO;

import utils.Mapper.ISqlMapper;

import java.sql.*;
import java.util.ArrayList;

public abstract class BaseDAO<T> {
    private ISqlMapper<T> _mapper;

    protected BaseDAO(ISqlMapper<T> mapper) {
        _mapper = mapper;
    }
    protected Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DatabaseConfig.getConnectionString(), DatabaseConfig.username, DatabaseConfig.password);
            return connection;
        }catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // select to get multi record
    protected ArrayList<T> getRecordArray(String sql) {
        ArrayList<T> data = new ArrayList<T>();
        Connection connection = getConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);
                while (result.next()) {
                    T entity = _mapper.map(result);
                    data.add(entity);
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return new ArrayList<T>();
            }
            return data;
        } else {
            return new ArrayList<T>();
        }
    }

    protected ArrayList<T> getRecordArray(String sql, Object ...params) {
        ArrayList<T> data = new ArrayList<T>();
        Connection connection = getConnection();
        if (connection != null) {
            try {
                PreparedStatement statement = getPrepareStatement(connection, sql, params);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    T entity = _mapper.map(result);
                    data.add(entity);
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return new ArrayList<T>();
            }
            return data;
        } else {
            return new ArrayList<T>();
        }
    }

    // select to get one record
    protected T getRecordSingle(String sql) {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);
                result.next();
                T entity = _mapper.map(result);
                connection.close();
                return entity;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    protected T getRecordSingle(String sql, Object ...params) {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                PreparedStatement statement = getPrepareStatement(connection, sql, params);
                ResultSet result = statement.executeQuery();
                result.next();
                T entity = _mapper.map(result);
                connection.close();
                return entity;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    // insert/delete/update
    protected boolean executeQuery(String sql) {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                boolean result = statement.execute(sql);
                connection.close();
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    protected boolean executeQuery(String sql, Object ...params) {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                PreparedStatement statement = getPrepareStatement(connection, sql, params);
                int result = statement.executeUpdate();
                connection.close();
                return result > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    protected PreparedStatement getPrepareStatement(Connection connection, String sql, Object ...params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0;i < params.length;i++) {
            if (params[i] instanceof Integer) {
                statement.setInt(i + 1, (int)params[i]);
            }else if (params[i] instanceof Float) {
                statement.setFloat(i + 1, (float)params[i]);
            }else if (params[i] instanceof String) {
                statement.setString(i + 1, (String)params[i]);
            }else if (params[i] instanceof Boolean) {
                statement.setBoolean(i + 1, (boolean)params[i]);
            }else if (params[i] instanceof Date) {
                statement.setDate(i + 1, (Date)params[i]);
            }else if (params[i] instanceof Timestamp) {
                statement.setTimestamp(i + 1, (Timestamp)params[i]);
            }else {
                statement.setObject(i + 1, params[i]);
            }
        }
        return statement;
    }
}