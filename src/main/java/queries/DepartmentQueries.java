package queries;

public class DepartmentQueries {

    public static final String GET = "SELECT id, name, patient_count FROM department WHERE id  = ";

    public static final String GET_ALL = "SELECT id, name, patient_count FROM department";

    public static final String CREATE = "INSERT INTO department(name, patient_count) VALUES(?, ?)";

    public static final String UPDATE = "UPDATE department SET name = ? patient_count = ? WHERE id = ?";

    public static final String DELETE = "DELETE FROM department WHERE id = ?";

    public static final String GET_BY_NAME = "SELECT id, name, patient_count FROM department WHERE name = ";

    public static final String DELETE_BY_NAME = "DELETE FROM department WHERE name = ?";

}
