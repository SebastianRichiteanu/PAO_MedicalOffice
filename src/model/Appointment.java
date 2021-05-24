package model;

import java.sql.Date;

public class Appointment {
    private Patient patient;
    private Doctor doctor;
    private Prescription prescription;
    private Date date;

    public Appointment(Patient patient, Doctor doctor, Prescription prescription, Date date) {
        this.patient = patient;
        this.doctor = doctor;
        this.prescription = prescription;
        this.date = date;
    }

    public Patient getPatient() { return patient; }

    public void setPatient(Patient patient) { this.patient = patient; }

    public Doctor getDoctor() { return doctor; }

    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public Prescription getPrescription() { return prescription; }

    public void setPrescription(Prescription prescription) { this.prescription = prescription; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    @Override
    public String toString() {
        String str = "Date: " + date + '\n';
        str += "Patient | " + patient.toString() + "\nDoctor | " + doctor.toString() + "\nPrescription | " + prescription + '\n';
        return str;
    }

}
