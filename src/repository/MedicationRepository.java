package repository;

import config.DatabaseConnection;
import model.Medication;
import model.Patient;
import model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicationRepository {

    public void addMedication (Medication medication) {
        String sql = "insert into medications values (null, ?, ?, ?)";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, medication.getName());
            statement.setDouble(2, medication.getPrice());
            int supplier_id = getSupplierId(medication.getSupplier());
            if (supplier_id > 0) {
                statement.setInt(3, getSupplierId(medication.getSupplier()));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getSupplierId (Supplier supplier) {
        String sql = "select id from suppliers where name = ?";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, supplier.getName());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }



    public void removeMedication (Medication medication) {
        String sql = "delete from medications where name = '" + medication.getName() + "'";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
