package service;

import model.*;
import repository.GetRepository;
import repository.PrescriptionRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class PrescriptionService {

    private static PrescriptionService INSTANCE;

    private PrescriptionRepository prescriptionRepository;
    private GetRepository getRepository;

    private PrescriptionService () {
        this.prescriptionRepository = new PrescriptionRepository();
        this.getRepository = new GetRepository();
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
        int id = prescriptionRepository.getPrescriptionId(prescription);
        if (id == -1) {
            System.out.println("This prescription does not exist");
        } else {
            prescriptionRepository.updatePrescriptionById(id, "date", date.toString());
            prescription.setDate(date);
        }
    }

    public void updateBarCode (Prescription prescription, String barCode) {
        int id = prescriptionRepository.getPrescriptionId(prescription);
        if (id == -1) {
            System.out.println("This prescription does not exist");
        } else {
            prescriptionRepository.updatePrescriptionById(id, "barCode", barCode);
            prescription.setBarCode(barCode);
        }
    }

    public boolean isMedicationOnPrescription (Medication medication, Prescription prescription) {
        int idMed = prescriptionRepository.getMedicationIdByName(medication.getName());
        int idPresc = prescriptionRepository.getPrescriptionId(prescription);
        return prescriptionRepository.isMedicationOnPrescription(idMed, idPresc);
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

    public Optional<Prescription> getPrescriptionById(int id) { return getRepository.getPrescriptionById(id); }

    public Prescription getPrescriptionByBarCode (MedicalOffice medicalOffice, String barCode) {
        for (Prescription p : medicalOffice.getPrescriptions())
            if (p.getBarCode() == barCode) {
                return p;
            }
        return null;
    }

    public void printPrescriptions () {
        List<Prescription> prescriptions = getRepository.getAllPrescriptions();
        if (prescriptions != null) {
            for (Prescription p : prescriptions)
                if (p != null) {
                    System.out.println(p);
                }
        }
    }

    public int getNumberOfMedicationsOnPrescription(Prescription prescription) {
        int id = prescriptionRepository.getPrescriptionId(prescription);
        return prescriptionRepository.getNumberOfMedicationsOnPrescription(id);
    }



}
