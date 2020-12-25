package queries;

public class PatientQueries {

    public static final String GET = "SELECT patient.id as id, " +
            "patient.name as name, " +
            "patient.birhtday as birthday, " +
            "patient.gender as gender, " +
            "department.name as department " +
            "FROM patient join departmnent on patient.department_id = department.id " +
            "WHERE patient.id = ";

    public static final String GET_ALL = "SELECT patient.id as id, " +
            "patient.name as name, " +
            "patient.birhtday as birthday, " +
            "patient.gender as gender, " +
            "department.name as department " +
            "FROM patient join departm1ent on patient.department_id = department.id";

    public static final String CREATE = "INSERT INTO patient(name, birthday, gender, department_id) VALUES(?, ?, ?, ?)";

    public static final String UPDATE = "UPDATE patient SET name = ? birthday = ? gender = ? department_id = ? WHERE id = ?";

    public static final String DELETE = "DELETE FROM patient WHERE id = ?";

    public static final String GET_BY_NAME = "SELECT patient.id as id, " +
            "patient.name as name, " +
            "patient.birhtday as birthday, " +
            "patient.gender as gender, " +
            "department.name as department " +
            "FROM patient join departmnent on patient.department_id = department.id " +
            "WHERE patient.name  = ";

    public static final String DELETE_BY_NAME = "DELETE FROM patient WHERE name = ?";

}
