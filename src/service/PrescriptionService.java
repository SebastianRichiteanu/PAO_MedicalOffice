package service;

import model.*;
import repository.PrescriptionRepository;

import java.sql.Date;

public class PrescriptionService {

    private static PrescriptionService INSTANCE;

    private PrescriptionRepository prescriptionRepository;

    private PrescriptionService () {
        this.prescriptionRepository = new PrescriptionRepository();
    }

    public static PrescriptionService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PrescriptionService();
        }
        return INSTANCE;
    }

    public void addPrescription (Prescription prescription) {
        prescriptionRepository.addPrescription(prescription);
    }

    public void removePrescription(Prescription prescription) {
        prescriptionRepository.removePrescription(prescription);
    }

    public void updateDate (Prescription prescription, Date date) {
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

    public Prescription getPrescriptionByBarCode (MedicalOffice medicalOffice, String barCode) {
        for (Prescription p : medicalOffice.getPrescriptions())
            if (p.getBarCode() == barCode) {
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
