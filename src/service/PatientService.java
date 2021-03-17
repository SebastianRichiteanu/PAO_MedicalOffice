package service;

import model.MedicalOffice;
import model.Patient;

public class PatientService {
    public void addPatient (MedicalOffice medicalOffice, Patient patient) {
        int nextAvailableIndex = getNumberOfPatients(medicalOffice);
        medicalOffice.getPatients()[nextAvailableIndex] = patient;
    }
    private int getNumberOfPatients(MedicalOffice medicalOffice) {
        int numberOfPatients = 0;
        for (Patient p : medicalOffice.getPatients())
            if (p != null)
                numberOfPatients++;
        return numberOfPatients;
    }

}
