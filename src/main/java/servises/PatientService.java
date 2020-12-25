package servises;

import dao2.DepartmentDao;
import dao2.PatientDao;
import domain.Department;
import domain.Patient;
import utills.ConnectionUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class PatientService {

    private PatientDao dao;
    private DepartmentDao departmentDao;

    public PatientService() {
        try (Connection connection = DriverManager.getConnection(ConnectionUtils.URL.value,
                ConnectionUtils.USER.value, ConnectionUtils.PASSWORD.value)
        ) {
            dao = new PatientDao(connection);
            departmentDao = new DepartmentDao(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Patient get(int id) {
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

    public List<Patient> getAll() {
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

    public void save(Patient patient) {
        try (Connection connection = DriverManager.getConnection(ConnectionUtils.URL.value,
                ConnectionUtils.USER.value, ConnectionUtils.PASSWORD.value)
        ) {
            dao.setConnection(connection);
            departmentDao.setConnection(connection);
            dao.create(patient);
            Department department = departmentDao.getByName(patient.getDepartmentName());
            department.incCountOfPatients(1);
            departmentDao.update(department.getId(), department);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Patient patient) {
        try (Connection connection = DriverManager.getConnection(ConnectionUtils.URL.value,
                ConnectionUtils.USER.value, ConnectionUtils.PASSWORD.value)
        ) {
            dao.setConnection(connection);
            departmentDao.setConnection(connection);
            Department oldDepartment = departmentDao.getByName(patient.getDepartmentName());
            dao.update(patient.getId(), patient);
            Department newDepartment = departmentDao.getByName(patient.getDepartmentName());
            if (oldDepartment.getId()!=newDepartment.getId()){
                oldDepartment.incCountOfPatients(-1);
                newDepartment.incCountOfPatients(1);
                departmentDao.update(oldDepartment.getId(), oldDepartment);
                departmentDao.update(newDepartment.getId(), newDepartment);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(ConnectionUtils.URL.value,
                ConnectionUtils.USER.value, ConnectionUtils.PASSWORD.value)
        ) {
            dao.setConnection(connection);
            departmentDao.setConnection(connection);
            String departmentName = dao.get(id).getDepartmentName();
            dao.delete(id);
            Department department = departmentDao.getByName(departmentName);
            department.incCountOfPatients(-1);
            departmentDao.update(department.getId(), department);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Patient getByName(String name){
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
            departmentDao.setConnection(connection);
            String departmentName = dao.getByName(name).getDepartmentName();
            dao.deleteByName(name);
            Department department = departmentDao.getByName(departmentName);
            department.incCountOfPatients(-1);
            departmentDao.update(department.getId(), department);

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public void deleteAll(){
        try (Connection connection = DriverManager.getConnection(ConnectionUtils.URL.value,
                ConnectionUtils.USER.value, ConnectionUtils.PASSWORD.value)
        ) {
            dao.setConnection(connection);
            departmentDao.setConnection(connection);
            List<Patient> patients = dao.getAll();
            for (Patient patient: patients
                 ) {
                dao.delete(patient.getId());
            }
            List<Department> departments = departmentDao.getAll();
            for (Department department: departments
            ) {
                department.setCountOfPatients(0);
                departmentDao.update(department.getId(),  department);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addPatientToDep(String name, String departmentName){
        try (Connection connection = DriverManager.getConnection(ConnectionUtils.URL.value,
                ConnectionUtils.USER.value, ConnectionUtils.PASSWORD.value)
        ) {
            dao.setConnection(connection);
            departmentDao.setConnection(connection);
            Patient patient = dao.getByName(name);
            Department oldDepartment = departmentDao.getByName(patient.getDepartmentName());
            Department department = departmentDao.getByName(departmentName);
            patient.setDepartmentName(departmentName);
            dao.update(patient.getId(), patient);
            department.incCountOfPatients(1);
            departmentDao.update(department.getId(), department);
            oldDepartment.incCountOfPatients(-1);
            departmentDao.update(oldDepartment.getId(), oldDepartment);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removePatientFromDep(String name){
        try (Connection connection = DriverManager.getConnection(ConnectionUtils.URL.value,
                ConnectionUtils.USER.value, ConnectionUtils.PASSWORD.value)
        ) {
            dao.setConnection(connection);
            departmentDao.setConnection(connection);
            Patient patient = dao.getByName(name);
            Department oldDepartment = departmentDao.getByName(patient.getDepartmentName());
            patient.setDepartmentName(null);
            dao.update(patient.getId(), patient);
            oldDepartment.incCountOfPatients(-1);
            departmentDao.update(oldDepartment.getId(), oldDepartment);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
