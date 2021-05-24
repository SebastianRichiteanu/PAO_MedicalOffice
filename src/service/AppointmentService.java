package service;

import model.*;
import repository.AppointmentRepository;


public class AppointmentService {
    private static AppointmentService INSTANCE;
    private AppointmentRepository appointmentRepository;

    private AppointmentService () { this.appointmentRepository = new AppointmentRepository(); }

    public static AppointmentService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppointmentService();
        }
        return INSTANCE;
    }

    public void addAppointment (Appointment appointment) {
        appointmentRepository.addAppointment(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        appointmentRepository.removeAppointment(appointment);
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
