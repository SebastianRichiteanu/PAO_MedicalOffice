package repository;

import config.DatabaseConnection;
import model.Doctor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DoctorRepository {

    public void addDoctor(Doctor doctor) {
        String sql = "insert into doctors values (null, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getSurname());
            statement.setInt(3, doctor.getAge());
            statement.setString(4, doctor.getAddress());
            statement.setString(5, doctor.getPhoneNo());
            statement.setDouble(6, doctor.getSalary());
            statement.setString(7, doctor.getSpecialization());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeDoctor(Doctor doctor) {
        String sql = "delete from doctors where name = '" + doctor.getName() + "' && surname = '" + doctor.getSurname() + "'";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
