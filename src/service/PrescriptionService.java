package service;

import model.MedicalOffice;
import model.Prescription;

public class PrescriptionService {
    public void addPrescription (MedicalOffice medicalOffice, Prescription prescription) {
        int nextAvailableIndex = getNumberOfPrescriptions(medicalOffice);
        medicalOffice.getPrescriptions()[nextAvailableIndex] = prescription;
    }
    private int getNumberOfPrescriptions(MedicalOffice medicalOffice) {
        int numberOfPrescriptions = 0;
        for (Prescription p : medicalOffice.getPrescriptions())
            if (p != null)
                numberOfPrescriptions++;
        return numberOfPrescriptions;
    }

}
