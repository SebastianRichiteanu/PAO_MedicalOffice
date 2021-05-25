package repository;

import config.DatabaseConnection;
import model.Medication;
import model.Prescription;
import model.Supplier;


import java.sql.Date;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrescriptionRepository {

    public void addPrescription (Prescription prescription) {
        String sql = "insert into prescriptions values (null, ?, ?)";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, prescription.getBarCode());
            statement.setDate(2, prescription.getDate());
            statement.executeUpdate();
            addMedicationsToPrescription(prescription);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMedicationsToPrescription (Prescription prescription) {
        int idPrescription = getPrescriptionId(prescription);
        for (Medication med : prescription.getMedications()) {
            if (med != null) {
                int idMedication = getMedicationIdByName(med.getName());
                String sql = "insert into medtopresc values (?,?)";
                try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
                    statement.setInt(1, idMedication);
                    statement.setInt(2, idPrescription);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updatePrescriptionById(int id, String field, String value) {
        if (field.equals("date")) {
            String sql = "update prescriptions set date = ? where id = ?";
            try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
                statement.setDate(1, Date.valueOf(value));
                statement.setInt(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (field.equals("barCode")) {
            String sql = "update prescriptions set barcode = ? where id = ?";
            try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
                statement.setString(1, value);
                statement.setInt(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getPrescriptionId (Prescription prescription) {
        String sql = "select id from prescriptions where barCode = '" + prescription.getBarCode() + "'";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getMedicationIdByName (String name) {
        String sql = "select id from medications where name = '" + name + "'";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void removePrescription (Prescription prescription) {
        String sql = "delete from prescriptions where barcode = '" + prescription.getBarCode() + "'";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfMedicationsOnPrescription (int id) {
        String sql = "select count(*) from medtopresc where id_prescription = ? ";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean isMedicationOnPrescription(int idMed, int idPresc) {
        String sql = "select count(*) from medtopresc where id_medication = ? && id_prescription = ?";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, idMed);
            statement.setInt(2, idPresc);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int nr =  result.getInt(1);
                if (nr > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
