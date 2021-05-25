package repository;

import config.DatabaseConnection;
import model.Medication;
import model.Patient;
import model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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


    public void updateMedicationById(int id, String field, String value) {
        if (field.equals("name")) {
            String sql = "update medications set name = ? where id = ?";
            try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
                statement.setString(1, value);
                statement.setInt(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (field.equals("price")) {
            String sql = "update medications set price = ? where id = ?";
            try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
                statement.setDouble(1, Double.parseDouble(value));
                statement.setInt(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getMedicationId (Medication medication) {
        String sql = "select id from medications where name = ?";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1,medication.getName());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


}
