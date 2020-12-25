package dao2;


import domain.Gender;
import domain.Patient;
import mappers.PatientsMappers;
import queries.PatientQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDao implements Dao<Patient>{
    private Connection connection;
    DepartmentDao departmentDao;

    public PatientDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Patient get(int id) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(PatientQueries.GET + id)) {
                while (rs.next()) {
                    return PatientsMappers.mapEntityToModel(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        throw new IllegalStateException("Department with id " + id + "not found");
    }

    @Override
    public List<Patient> getAll() {
        final List<Patient> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(PatientQueries.GET_ALL)) {
                while (rs.next()) {
                    result.add(PatientsMappers.mapEntityToModel(rs)
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            return null;
        }

        return result;
    }

    @Override
    public void create(Patient model) {
        int department_id = departmentDao.getByName(model.getDepartmentName()).getId();
        try (PreparedStatement preparedStatement = connection.prepareStatement(PatientQueries.CREATE)
        ){
            PatientsMappers.mapModelToEntity(model, preparedStatement, 1);
            preparedStatement.setInt(4, department_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

    @Override
    public void update(int id, Patient model) {
        int department_id = departmentDao.getByName(model.getDepartmentName()).getId();
        try (PreparedStatement preparedStatement = connection.prepareStatement(PatientQueries.UPDATE)) {
            PatientsMappers.mapModelToEntity(model, preparedStatement, 1);
            preparedStatement.setInt(4,department_id);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(PatientQueries.DELETE)
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
        departmentDao = new DepartmentDao(connection);
    }

    public Patient getByName(String name){
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(PatientQueries.GET_BY_NAME + name)) {
                while (rs.next()) {
                    return PatientsMappers.mapEntityToModel(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        throw new IllegalStateException("Department with name " + name + "not found");
    }

    public void deleteByName(String name){
        try (PreparedStatement preparedStatement = connection.prepareStatement(PatientQueries.DELETE_BY_NAME)
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
