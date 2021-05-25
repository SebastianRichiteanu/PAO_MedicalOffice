package service;

import model.Appointment;
import model.Doctor;
import model.MedicalOffice;
import model.Patient;
import repository.GetRepository;
import repository.PatientRepository;

import java.util.Optional;
import java.util.Set;

public class PatientService {
    private static PatientService INSTANCE;
    private PatientRepository patientRepository;
    private GetRepository getRepository;

    private PatientService () {
        this.patientRepository = new PatientRepository();
        this.getRepository = new GetRepository();
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

    public Patient searchPatientByFullName (String name, String surname) {
        int id = patientRepository.getPatientIdByName(name, surname);
        if (id == -1) {
            System.out.println("This patient does not exist");
        } else {
            Optional<Patient> patient = getPatientById(id);
            if (patient.isPresent()) {
                return patient.get();
            }
        }
        return null;
    }

    public void updateName (Patient patient, String name) {
        int id = patientRepository.getPatientId(patient);
        if (id == -1) {
            System.out.println("This patient does not exist");
        } else {
            patientRepository.updatePatientById(id, "name", name);
            patient.setName(name);
        }
    }

    public void updateSurname (Patient patient, String surname) {
        int id = patientRepository.getPatientId(patient);
        if (id == -1) {
            System.out.println("This patient does not exist");
        } else {
            patientRepository.updatePatientById(id, "surname", surname);
            patient.setSurname(surname);
        }
    }

    public void updateAge (Patient patient, int age) {
        int id = patientRepository.getPatientId(patient);
        if (id == -1) {
            System.out.println("This patient does not exist");
        } else {
            patientRepository.updatePatientById(id, "age", String.valueOf(age));
            patient.setAge(age);
        }
    }

    public void updateAddress (Patient patient, String address) {
        int id = patientRepository.getPatientId(patient);
        if (id == -1) {
            System.out.println("This patient does not exist");
        } else {
            patientRepository.updatePatientById(id, "address", address);
            patient.setAddress(address);
        }
    }

    public void updatePhoneNo (Patient patient, String phoneNo) {
        int id = patientRepository.getPatientId(patient);
        if (id == -1) {
            System.out.println("This patient does not exist");
        } else {
            patientRepository.updatePatientById(id, "phoneNo", phoneNo);
            patient.setPhoneNo(phoneNo);
        }
    }
    
    public void updateCondition (Patient patient, String condition) {
        int id = patientRepository.getPatientId(patient);
        if (id == -1) {
            System.out.println("This patient does not exist");
        } else {
            patientRepository.updatePatientById(id, "condition", condition);
            patient.setCondition(condition);
        }
    }

    public Optional<Patient> getPatientById(int id) { return getRepository.getPatientById(id); }

    public void printPatients() {
        Set<Patient> pats = getRepository.getAllPatients();
        if (pats != null) {
            for (Patient p : pats)
                if (p != null) {
                    System.out.println(p);
                }
        }
    }



}
