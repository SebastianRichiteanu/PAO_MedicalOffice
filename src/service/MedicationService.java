package service;

import model.Doctor;
import model.MedicalOffice;
import model.Medication;
import repository.MedicationRepository;

public class MedicationService {
    private static MedicationService INSTANCE;
    private MedicationRepository medicationRepository;

    private MedicationService () {
        this.medicationRepository = new MedicationRepository();
    }

    public static MedicationService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MedicationService();
        }
        return INSTANCE;
    }

    public void addMedication (Medication medication) {
        medicationRepository.addMedication(medication);
    }

    public void removeMedication (Medication medication) {
        medicationRepository.removeMedication(medication);
    }

    public void updateName (Medication medication, String name) {
        medication.setName(name);
    }

    public void updatePrice (Medication medication, double price) {
        medication.setPrice(price);
    }

    public Medication searchMedicationByName (MedicalOffice medicalOffice, String name) {
        for (Medication m : medicalOffice.getMedications())
            if (m.getName().equals(name)) {
                return m;
            }
        return null;
    }

    public void printMedications (MedicalOffice medicalOffice) {
        for (Medication m : medicalOffice.getMedications())
            if (m != null) {
                System.out.println(m);
            }
    }

    private int getNumberOfMedications(MedicalOffice medicalOffice) {
        int numberOfMedications = 0;
        for (Medication m : medicalOffice.getMedications())
            if (m != null) {
                numberOfMedications++;
            }
        return numberOfMedications;
    }
}
