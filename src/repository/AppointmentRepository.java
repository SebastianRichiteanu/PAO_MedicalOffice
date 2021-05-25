package repository;

import config.DatabaseConnection;
import model.Appointment;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AppointmentRepository {

    public void addAppointment(Appointment appointment) {
        String sql = "insert into appointments values (null, ?, ?, ?, ?)";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, getPatientIdByName(appointment.getPatient().getName(), appointment.getPatient().getSurname()));
            statement.setInt(2, getDoctorIdByName(appointment.getDoctor().getName(), appointment.getDoctor().getSurname()));
            statement.setInt(3, getPrescriptionIdByBarCode(appointment.getPrescription().getBarCode()));
            statement.setDate(4, appointment.getDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getPatientIdByName (String name, String surname) {
        String sql = "select id from patients where name = '" + name + "' && surname = '" + surname + "'";
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

    public int getDoctorIdByName (String name, String surname) {
        String sql = "select id from doctors where name = '" + name + "' && surname = '" + surname + "'";
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

    public int getPrescriptionIdByBarCode (String barCode) {
        String sql = "select id from prescriptions where barCode = '" + barCode + "'";
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


    public void removeAppointment (Appointment appointment) {
        int idPatient = getPatientIdByName(appointment.getPatient().getName(), appointment.getPatient().getSurname());
        int idDoctor = getDoctorIdByName(appointment.getDoctor().getName(), appointment.getDoctor().getSurname());
        Date date = appointment.getDate();
        String sql = "delete from appointments where id_patient = " + idPatient + " && id_doctor = " + idDoctor + " && date = '" + date + "'";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public int getAppointmentId (Appointment appointment) {
        int idPatient = getPatientIdByName(appointment.getPatient().getName(), appointment.getPatient().getSurname());
        int idDoctor = getDoctorIdByName(appointment.getDoctor().getName(), appointment.getDoctor().getSurname());
        Date date = appointment.getDate();
        String sql = "select id from appointments where id_patient = " + idPatient + " && id_doctor = " + idDoctor + " && date = '" + date + "'";
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

    public void updateAppointmentById(int id, String field, String value) {
        if (field.equals("date")) {
            String sql = "update appointments set date = ? where id = ?";
            try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
                statement.setDate(1, Date.valueOf(value));
                statement.setInt(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (field.equals("prescription")) {
            String sql = "update appointments set id_prescription = ? where id = ?";
            try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(value));
                statement.setInt(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int numberOfAppointmentsPerDoctor (int id) {
        String sql = "select count(*) from appointments where id_doctor = ? ";
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

}
