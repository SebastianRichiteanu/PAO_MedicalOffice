package service;

import model.Doctor;
import model.MedicalOffice;

public class DoctorService {
    public void addDoctor (MedicalOffice medicalOffice, Doctor doctor) {
        int nextAvailableIndex = getNumberOfDoctors(medicalOffice);
        medicalOffice.getDoctors()[nextAvailableIndex] = doctor;
    }
    private int getNumberOfDoctors(MedicalOffice medicalOffice) {
        int numberOfDoctors = 0;
        for (Doctor d : medicalOffice.getDoctors())
            if (d != null)
                numberOfDoctors++;
        return numberOfDoctors;
    }
}
