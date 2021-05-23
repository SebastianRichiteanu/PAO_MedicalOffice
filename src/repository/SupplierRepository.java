package repository;

import config.DatabaseConnection;
import model.Patient;
import model.Supplier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupplierRepository {

    public void addSupplier(Supplier supplier) {
        String sql = "insert into suppliers values (null, ?, ?)";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getLocation());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeSupplier (Supplier supplier) {
        String sql = "delete from suppliers where name = '" + supplier.getName() + "'";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
