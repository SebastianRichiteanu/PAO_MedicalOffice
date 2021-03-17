package model;

public class MedicalOffice {
    private final int length = 100;
    private Person[] people = new Person[length];
    private Doctor[] doctors = new Doctor[length];
    private Patient[] patients = new Patient[length];
    private Medication[] medications = new Medication[length];
    private Prescription[] prescriptions = new Prescription[length];
    private Appointment[] appointments = new Appointment[length];

    public Person[] getPeople() {
        return people;
    }

    public void setPeople(Person[] people) {
        this.people = people;
    }

    public Doctor[] getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctor[] doctors) {
        this.doctors = doctors;
    }

    public Patient[] getPatients() {
        return patients;
    }

    public void setPatients(Patient[] patients) {
        this.patients = patients;
    }

    public Medication[] getMedications() {
        return medications;
    }

    public void setMedications(Medication[] medications) {
        this.medications = medications;
    }

    public Prescription[] getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Prescription[] prescriptions) {
        this.prescriptions = prescriptions;
    }

    public Appointment[] getAppointments() {
        return appointments;
    }

    public void setAppointments(Appointment[] appointments) {
        this.appointments = appointments;
    }



}
