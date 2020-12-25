package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    T get(int id) throws SQLException;
    List<T> getAll();
    void create(T model);
    void update(int id, T model);
    void delete(int id);
    void setConnection(Connection connection);
}
