package service;

import model.MedicalOffice;
import model.Medication;

public class MedicationService {
    public void addMedication (MedicalOffice medicalOffice, Medication medication) {
        int nextAvailableIndex = getNumberOfMedications(medicalOffice);
        medicalOffice.getMedications()[nextAvailableIndex] = medication;
    }
    private int getNumberOfMedications(MedicalOffice medicalOffice) {
        int numberOfMedications = 0;
        for (Medication m : medicalOffice.getMedications())
            if (m != null)
                numberOfMedications++;
        return numberOfMedications;
    }
}
