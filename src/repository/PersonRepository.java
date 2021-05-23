package repository;

import config.DatabaseConnection;
import model.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonRepository {

    public void addPerson(Person person) {
        String sql = "insert into persons values (null, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, person.getName());
            statement.setString(2, person.getSurname());
            statement.setInt(3, person.getAge());
            statement.setString(4, person.getAddress());
            statement.setString(5, person.getPhoneNo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removePerson(Person person) {
        String sql = "delete from persons where name = '" + person.getName() + "' && surname = '" + person.getSurname() + "'";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
