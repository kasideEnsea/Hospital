package mappers;

import domain.Gender;
import domain.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientsMappers {

    public static Patient mapEntityToModel(ResultSet rs){
        try {
            return new Patient(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getTimestamp("birthday"),
                    Gender.valueOf(rs.getString("gender")),
                    rs.getString("department")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void mapModelToEntity(Patient model,
                                        PreparedStatement statement, int start)
    {
        try {
            statement.setString(start++, model.getName());
            statement.setTimestamp(start++, model.getBirthday());
            statement.setString(start,model.getGender().toString());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
