package model;

public class Appointment {
    private Patient patient;
    private Doctor doctor;
    private Prescription prescription;
    private String date;

    public Appointment(Patient patient, Doctor doctor, Prescription prescription, String date) {
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

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    @Override
    public String toString() {
        String str = "Date: " + date;
        str += patient.toString() + '\n' + doctor.toString() + '\n' + prescription + '\n';
    }

}
