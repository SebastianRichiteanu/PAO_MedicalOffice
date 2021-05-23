package repository;

import config.DatabaseConnection;
import model.Doctor;
import model.Patient;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientRepository {

    public void addPatient(Patient patient) {
        String sql = "insert into patients values (null, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getSurname());
            statement.setInt(3, patient.getAge());
            statement.setString(4, patient.getAddress());
            statement.setString(5, patient.getPhoneNo());
            statement.setString(6, patient.getCondition());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removePatient (Patient patient) {
        String sql = "delete from patients where name = '" + patient.getName() + "' && surname = '" + patient.getSurname() + "'";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
