package main;

import config.DatabaseConnection;
import model.*;
import service.MedicalOfficeService;

import javax.print.Doc;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        MedicalOfficeService medicalOfficeService = MedicalOfficeService.getInstance();

        Doctor doctor = new Doctor ("doctor_name","doctor_surname",2,"doctor_address","doctor_phone",2,"special");
        medicalOfficeService.addDoctor(doctor);

        Patient patient = new Patient("patient_name","patient_surname",3,"patient_address","patient_phone","condition");
        medicalOfficeService.addPatient(patient);

        Supplier supplier = new Supplier("supplier_name","supplier_location");
        medicalOfficeService.addSupplier(supplier);
        medicalOfficeService.updateName(supplier,"supplier_name2");
        medicalOfficeService.updateLocation(supplier,"supplier_location2");

        Medication medication = new Medication("medication_name",4, supplier);
        medicalOfficeService.addMedication(medication);

        medicalOfficeService.updateName(medication, "medication_name2");
        medicalOfficeService.updatePrice(medication, 123.2);

        Medication[] v_med = new Medication[100];
        v_med[0] = medication;

        Prescription prescription = new Prescription("1234", Date.valueOf("2020-02-13"),v_med);
        medicalOfficeService.addPrescription(prescription);

        Appointment appointment = new Appointment(patient, doctor, prescription,Date.valueOf("2020-02-13"));
        medicalOfficeService.addAppointment(appointment);


         // Check if they are in "DB"
        System.out.println("People:");
        medicalOfficeService.printPeople();
        System.out.println("Doctor:");
        medicalOfficeService.printDoctors();
        System.out.println("Patient:");
        medicalOfficeService.printPatients();
        System.out.println("Medication:");
        medicalOfficeService.printMedications();
        System.out.println("Prescription:");
        medicalOfficeService.printPrecriptions();
        System.out.println("Appointment:");
        medicalOfficeService.printAppointments();
        System.out.println("Supplier:");
        medicalOfficeService.printSuppliers();

        // Search by FullName

        System.out.println("DoctorByFullName:");
        System.out.println(medicalOfficeService.searchDoctorByFullName("doctor_name", "doctor_surname"));
        System.out.println("PatientByFullName:");
        System.out.println(medicalOfficeService.searchPatientByFullName("patient_name","patient_surname"));

        // Update

            // People
        medicalOfficeService.updateName(doctor, "doctor_name2");
        medicalOfficeService.updateSurname(doctor, "doctor_surname2");
        medicalOfficeService.updateAge(doctor,999);
        medicalOfficeService.updateAddress(doctor,"doctor_address2");
        medicalOfficeService.updatePhoneNo(doctor, "doctor_phone2");
        medicalOfficeService.updateSalary(doctor, 999);
        medicalOfficeService.updateSpecialization(doctor, "special2");
        medicalOfficeService.updateCondition(patient,"condition2");

        System.out.println("New People:");
        medicalOfficeService.printPeople();

            // Medication

        medicalOfficeService.updateName(medication, "medication_name2");
        medicalOfficeService.updatePrice(medication, 999);


            // Prescription

        medicalOfficeService.updateDate(prescription, Date.valueOf("2020-02-13"));
        medicalOfficeService.updateAppointmentDate(appointment, Date.valueOf("2021-04-04"));

        // Add medication to prescription + check

        Medication medication2 = new Medication("medication_name3",222,supplier);
        medicalOfficeService.addMedication(medication2);

        medicalOfficeService.addMedicationToPrescription(medication, prescription);
        medicalOfficeService.addMedicationToPrescription(medication2, prescription);
        System.out.println(medicalOfficeService.isMedicationOnPrescription(medication, prescription));

        Prescription prescription1 = new Prescription("abcd123",Date.valueOf("2021-02-13"),new Medication[100]);
        medicalOfficeService.addPrescription(prescription1);
        medicalOfficeService.updatePrescription(appointment,prescription1);


        System.out.println("Nr of appointments per doctor:");
        System.out.println(medicalOfficeService.numberOfAppointmentsPerDoctor(doctor));

        Appointment appointment1 = new Appointment(patient, doctor, prescription,Date.valueOf("2021-05-12"));
        medicalOfficeService.addAppointment(appointment1);
        System.out.println(medicalOfficeService.numberOfAppointmentsPerDoctor(doctor));

        System.out.println("Nr of medications on prescription:");
        System.out.println(medicalOfficeService.getNumberOfMedicationsOnPrescription(prescription));

        medicalOfficeService.addMedicationToPrescription(medication, prescription1);
        System.out.println(medicalOfficeService.getNumberOfMedicationsOnPrescription(prescription1));

        Doctor doctor1 = new Doctor ("doctor1_name","b_surname",1,"doctor1_address","doctor1_phone",1,"special1");
        medicalOfficeService.addDoctor(doctor1);
        Doctor doctor2 = new Doctor ("doctor2_name","a_surname",2,"doctor2_address","doctor2_phone",2,"special2");
        medicalOfficeService.addDoctor(doctor2);

        clearDB();
    }

    private static void clearDB() {

        String sql = "delete from appointments where id > 0";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "delete from doctors where id > 0";

        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "delete from medications where id > 0";

        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "delete from medtopresc where id_medication > 0 and id_prescription > 0";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "delete from patients where id > 0";

        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "delete from persons where id > 0";

        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "delete from prescriptions where id > 0";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "delete from suppliers where id > 0";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
