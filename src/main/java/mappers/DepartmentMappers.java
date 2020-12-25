package mappers;

import domain.Department;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMappers {

    public static Department mapEntityToModel(ResultSet rs){
        try {
            return new Department(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("patient_count")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void mapModelToEntity(Department model,
                                        PreparedStatement statement, int start)
    {
        try {
            statement.setString(start++, model.getName());
            statement.setInt(start, model.getCountOfPatients());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
