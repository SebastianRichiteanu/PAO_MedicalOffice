package service;

import model.*;

public class PrescriptionService {

    private static PrescriptionService INSTANCE;

    private PrescriptionService () {}

    public static PrescriptionService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PrescriptionService();
        }
        return INSTANCE;
    }




    public void addPrescription (MedicalOffice medicalOffice, Prescription prescription) {
       // int nextAvailableIndex = getNumberOfPrescriptions(medicalOffice);
        medicalOffice.getPrescriptions().add(prescription);
    }

    public void updateDate (Prescription prescription, String date) {
        prescription.setDate(date);
    }

    public boolean isMedicationOnPrescription (Medication medication, Prescription prescription) {
        for (Medication m : prescription.getMedications())
            if (m == medication) {
                return true;
            }
        return false;
    }

    public void addMedicationToPrescription (Medication medication, Prescription prescription) {
        int nextAvailableIndex = getNumberOfMedicationsOnPrescription(prescription);
        prescription.getMedications()[nextAvailableIndex] = medication;
    }

    public void printPrecriptions (MedicalOffice medicalOffice) {
        for (Prescription p : medicalOffice.getPrescriptions())
            if (p != null) {
                System.out.println(p);
            }
    }

    public int numberOfPrescriptionPerMedication (MedicalOffice medicalOffice, Medication medication) {
        int numberOfPrescriptions = 0;
        for (Prescription p : medicalOffice.getPrescriptions())
            if (p != null) {
                for (Medication m : p.getMedications())
                    if (m != null && m.equals(medication)) {
                        numberOfPrescriptions++;
                    }
            }
        return numberOfPrescriptions;
    }

    public Prescription getPrescriptionById (MedicalOffice medicalOffice, int id) {
        for (Prescription p : medicalOffice.getPrescriptions())
            if (p.getId() == id) {
                return p;
            }
        return null;
    }

    private int getNumberOfPrescriptions(MedicalOffice medicalOffice) {
        int numberOfPrescriptions = 0;
        for (Prescription p : medicalOffice.getPrescriptions())
            if (p != null) {
                numberOfPrescriptions++;
            }
        return numberOfPrescriptions;
    }

    private int getNumberOfMedicationsOnPrescription(Prescription prescription) {
        int numberOfMedications = 0;
        for (Medication m : prescription.getMedications())
            if (m != null) {
                numberOfMedications++;
            }
        return numberOfMedications;
    }

}
