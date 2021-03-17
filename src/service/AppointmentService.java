package service;

import model.Appointment;
import model.MedicalOffice;

public class AppointmentService {
    public void addAppointment (MedicalOffice medicalOffice, Appointment appointment) {
        int nextAvailableIndex = getNumberOfAppointments(medicalOffice);
        medicalOffice.getAppointments()[nextAvailableIndex] = appointment;
    }
    private int getNumberOfAppointments(MedicalOffice medicalOffice) {
        int numberOfAppointments = 0;
        for (Appointment a : medicalOffice.getAppointments())
            if (a != null)
                numberOfAppointments++;
        return numberOfAppointments;
    }
}
