package servises;

import dao.DepartmentDao;
import domain.Department;
import utills.ConnectionUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DepartmentService {
    private DepartmentDao dao;

    public DepartmentService() {
        try (Connection connection = DriverManager.getConnection(ConnectionUtils.URL.value,
                ConnectionUtils.USER.value, ConnectionUtils.PASSWORD.value)
        ) {
            dao = new DepartmentDao(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Department get(int id) {
        try (Connection connection = DriverManager.getConnection(ConnectionUtils.URL.value,
                ConnectionUtils.USER.value, ConnectionUtils.PASSWORD.value)
        ) {
            dao.setConnection(connection);
            return dao.get(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Department> getAll() {
        try (Connection connection = DriverManager.getConnection(ConnectionUtils.URL.value,
                ConnectionUtils.USER.value, ConnectionUtils.PASSWORD.value)
        ) {
            dao.setConnection(connection);
            return dao.getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void save(Department department) {
        try (Connection connection = DriverManager.getConnection(ConnectionUtils.URL.value,
                ConnectionUtils.USER.value, ConnectionUtils.PASSWORD.value)
        ) {
            dao.setConnection(connection);
            dao.create(department);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Department department) {
        try (Connection connection = DriverManager.getConnection(ConnectionUtils.URL.value,
                ConnectionUtils.USER.value, ConnectionUtils.PASSWORD.value)
        ) {
            dao.setConnection(connection);
            dao.update(department.getId(), department);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(ConnectionUtils.URL.value,
                ConnectionUtils.USER.value, ConnectionUtils.PASSWORD.value)
        ) {
            dao.setConnection(connection);
            dao.delete(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Department getByName(String name){
        try (Connection connection = DriverManager.getConnection(ConnectionUtils.URL.value,
                ConnectionUtils.USER.value, ConnectionUtils.PASSWORD.value)
        ) {
            dao.setConnection(connection);
            return dao.getByName(name);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void deleteByName(String name){
        try (Connection connection = DriverManager.getConnection(ConnectionUtils.URL.value,
                ConnectionUtils.USER.value, ConnectionUtils.PASSWORD.value)
        ) {
            dao.setConnection(connection);
            dao.deleteByName(name);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public void deleteAllDepartments(){
        try (Connection connection = DriverManager.getConnection(ConnectionUtils.URL.value,
                ConnectionUtils.USER.value, ConnectionUtils.PASSWORD.value)
        ) {
            dao.setConnection(connection);
            List<Department> departments = dao.getAll();
            for (Department d: departments
                 )
                dao.delete(d.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

}
