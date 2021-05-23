package service;

import model.Doctor;
import model.MedicalOffice;
import model.Patient;
import repository.PatientRepository;

public class PatientService {
    private static PatientService INSTANCE;
    private PatientRepository patientRepository;

    private PatientService () {
        this.patientRepository = new PatientRepository();
    }

    public static PatientService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PatientService();
        }
        return INSTANCE;
    }

    public void addPatient (Patient patient) {
        patientRepository.addPatient(patient);
    }

    public void removePatient(Patient patient) { patientRepository.removePatient(patient); }

    public Patient searchPatientByFullName (MedicalOffice medicalOffice, String name, String surname) {
        Patient patient = null;
        for (Patient p : medicalOffice.getPatients())
            if (p != null && p.getName().equals(name) && p.getSurname().equals(surname)) {
                patient = p;
            }
        return patient;
    }

    public void updateCondition (Patient patient, String condition) {
        patient.setCondition(condition);
    }

    private int getNumberOfPatients(MedicalOffice medicalOffice) {
        int numberOfPatients = 0;
        for (Patient p : medicalOffice.getPatients())
            if (p != null) {
                numberOfPatients++;
            }
        return numberOfPatients;
    }

    public void printPatients(MedicalOffice medicalOffice) {
        for (Patient p : medicalOffice.getPatients())
            if (p != null) {
                System.out.println(p);
            }
    }

}
