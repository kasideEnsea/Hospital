package dao2;

import domain.Department;
import mappers.DepartmentMappers;
import queries.DepartmentQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao implements Dao<Department> {

    private Connection connection;


    public DepartmentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Department get(int id) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(DepartmentQueries.GET + id)) {
                while (rs.next()) {
                    return new Department(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("patient_count")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        throw new IllegalStateException("Department with id " + id + "not found");
    }

    @Override
    public List<Department> getAll() {
        final List<Department> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(DepartmentQueries.GET_ALL)) {
                while (rs.next()) {
                    result.add(new Department(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("patient_count")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            return null;
        }

        return result;
    }

    @Override
    public void create(Department model) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DepartmentQueries.CREATE)
        ){
            DepartmentMappers.mapModelToEntity(model, preparedStatement, 1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

    @Override
    public void update(int id, Department model) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DepartmentQueries.UPDATE)) {
            int count = 1;
            DepartmentMappers.mapModelToEntity(model, preparedStatement, 1);
            preparedStatement.setInt(count, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DepartmentQueries.DELETE)
        ) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException("Record with id = " + id + " not found");
            }
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Department getByName(String name){
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(DepartmentQueries.GET_BY_NAME + name)) {
                while (rs.next()) {
                    return DepartmentMappers.mapEntityToModel(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        throw new IllegalStateException("Department with name " + name + "not found");
    }

    public void deleteByName(String name){
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                DepartmentQueries.DELETE_BY_NAME)
        ) {
            preparedStatement.setString(1, name);
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException("Record with name = " + name + " not found");
            }
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

}
