package service;

import model.*;
import repository.GetRepository;
import repository.MedicationRepository;

import java.util.List;
import java.util.Optional;

public class MedicationService {
    private static MedicationService INSTANCE;
    private MedicationRepository medicationRepository;
    private GetRepository getRepository;


    private MedicationService () {
        this.medicationRepository = new MedicationRepository();
        this.getRepository = new GetRepository();
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
        int id = medicationRepository.getMedicationId(medication);
        if (id == -1) {
            System.out.println("This medication does not exist!");
        } else {
            medicationRepository.updateMedicationById(id, "name", name);
            medication.setName(name);
        }
    }

    public void updatePrice (Medication medication, double price) {
        int id = medicationRepository.getMedicationId(medication);
        if (id == -1) {
            System.out.println("This medication does not exist!");
        } else {
            medicationRepository.updateMedicationById(id, "price", String.valueOf(price));
            medication.setPrice(price);
        }
    }

    public Optional<Medication> getMedicationById(int id) { return getRepository.getMedicationById(id); }

    public Medication searchMedicationByName (MedicalOffice medicalOffice, String name) {
        for (Medication m : medicalOffice.getMedications())
            if (m.getName().equals(name)) {
                return m;
            }
        return null;
    }

    public void printMedications () {
        List<Medication> medications = getRepository.getAllMedications();
        if (medications != null) {
            for (Medication m : medications)
                if (m != null) {
                    System.out.println(m);
                }
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
