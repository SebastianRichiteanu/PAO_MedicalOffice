package service;

import model.*;


public class AppointmentService {
    private static AppointmentService INSTANCE;

    private AppointmentService () {}

    public static AppointmentService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppointmentService();
        }
        return INSTANCE;
    }

    public void addAppointment (MedicalOffice medicalOffice, Appointment appointment) {
        medicalOffice.getAppointments().add(appointment);
    }

    public void updatePrescription (Appointment appointment, Prescription prescription) {
        appointment.setPrescription(prescription);
    }

    public int numberOfAppointmentsPerDoctor (MedicalOffice medicalOffice, Doctor doctor) {
        int numberOfAppointments = 0;
        for (Appointment ap : medicalOffice.getAppointments())
            if (ap != null && ap.getDoctor() == doctor) {
                numberOfAppointments++;
            }
        return numberOfAppointments;
    }

    public void printAppointments(MedicalOffice medicalOffice) {
        for (Appointment a : medicalOffice.getAppointments())
            if (a != null) {
                System.out.println(a);
            }
    }

    private int getNumberOfAppointments(MedicalOffice medicalOffice) {
        int numberOfAppointments = 0;
        for (Appointment a : medicalOffice.getAppointments())
            if (a != null) {
                numberOfAppointments++;
            }
        return numberOfAppointments;
    }
}
