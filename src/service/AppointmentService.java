package service;

import model.*;
import repository.AppointmentRepository;
import repository.GetRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


public class AppointmentService {
    private static AppointmentService INSTANCE;
    private AppointmentRepository appointmentRepository;
    private GetRepository getRepository;

    private AppointmentService () {
        this.appointmentRepository = new AppointmentRepository();
        this.getRepository = new GetRepository();
    }

    public static AppointmentService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppointmentService();
        }
        return INSTANCE;
    }

    public void addAppointment (Appointment appointment) {
        appointmentRepository.addAppointment(appointment);
    }

    public void removeAppointment(Appointment appointment) { appointmentRepository.removeAppointment(appointment); }

    public void updateDate (Appointment appointment, Date date) {
        int id = appointmentRepository.getAppointmentId(appointment);
        if (id == -1) {
            System.out.println("This appointment does not exist!");
        } else {
            appointmentRepository.updateAppointmentById(id, "date", date.toString());
            appointment.setDate(date);
        }
    }

    public void updatePrescription (Appointment appointment, Prescription prescription) {
        int id = appointmentRepository.getAppointmentId(appointment);
        if (id == -1) {
            System.out.println("This appointment does not exist!");
        } else {
            int idPrescription = appointmentRepository.getPrescriptionIdByBarCode(prescription.getBarCode());
            appointmentRepository.updateAppointmentById(id, "prescription", String.valueOf(idPrescription));
            appointment.setPrescription(prescription);
        }

    }

    public Optional<Appointment> getAppointmentById(int id) { return getRepository.getAppointmentById(id); }

    public int numberOfAppointmentsPerDoctor (Doctor doctor) {
        int id = appointmentRepository.getDoctorIdByName(doctor.getName(), doctor.getSurname());
        return appointmentRepository.numberOfAppointmentsPerDoctor(id);
    }

    public void printAppointments() {
        List<Appointment> appointments = getRepository.getAllAppointments();
        if (appointments != null) {
            for (Appointment a : appointments)
                if (a != null) {
                    System.out.println(a);
                }
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
