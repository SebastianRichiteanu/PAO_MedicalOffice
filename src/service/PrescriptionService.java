package service;

import model.MedicalOffice;
import model.Medication;
import model.Prescription;

public class PrescriptionService {
    public void addPrescription (MedicalOffice medicalOffice, Prescription prescription) {
        int nextAvailableIndex = getNumberOfPrescriptions(medicalOffice);
        medicalOffice.getPrescriptions()[nextAvailableIndex] = prescription;
    }

    public void updateDate (Prescription prescription, String date) {
        prescription.setDate(date);
    }

    public Boolean isMedicationOnPrescription (Medication medication, Prescription prescription) {
        for (Medication m : prescription.getMedications())
            if (m == medication)
                return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public void addMedicationToPrescription (Medication medication, Prescription prescription) {
        int nextAvailableIndex = getNumberOfMedicationsOnPrescription(prescription);
        prescription.getMedications()[nextAvailableIndex] = medication;
    }

    public int numberOfPrescriptionPerMedication (MedicalOffice medicalOffice, Medication medication) {
        int numberOfPrescriptions = 0;
        for (Prescription p : medicalOffice.getPrescriptions())
            if (p != null) {
                for (Medication m : p.getMedications())
                    if (m == medication)
                        numberOfPrescriptions++;
            }
        return numberOfPrescriptions;
    }

    private int getNumberOfPrescriptions(MedicalOffice medicalOffice) {
        int numberOfPrescriptions = 0;
        for (Prescription p : medicalOffice.getPrescriptions())
            if (p != null)
                numberOfPrescriptions++;
        return numberOfPrescriptions;
    }

    private int getNumberOfMedicationsOnPrescription(Prescription prescription) {
        int numberOfMedications = 0;
        for (Medication m : prescription.getMedications())
            if (m != null)
                numberOfMedications++;

        return numberOfMedications;
    }

}
