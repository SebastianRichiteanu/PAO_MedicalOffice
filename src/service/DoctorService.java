package service;

import model.Doctor;
import model.MedicalOffice;
import model.Person;
import repository.DoctorRepository;

import java.util.*;

public class DoctorService {
    private static DoctorService INSTANCE;
    private DoctorRepository doctorRepository;

    private DoctorService () {
        this.doctorRepository = new DoctorRepository();
    }

    public static DoctorService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DoctorService();
        }
        return INSTANCE;
    }

    public void addDoctor (Doctor doctor) {
        doctorRepository.addDoctor(doctor);
    }

    public void removeDoctor (Doctor doctor) {
        doctorRepository.removeDoctor(doctor);
    }

    public Doctor searchDoctorByFullName(MedicalOffice medicalOffice, String name, String surname) {
        Doctor doc = null;
        for (Doctor d : medicalOffice.getDoctors())
            if (d != null && d.getName().equals(name) && d.getSurname().equals(surname)) {
                doc = d;
            }
        return doc;
    }

    public void updateSalary (Doctor doctor, double salary) {
        doctor.setSalary(salary);
    }

    public void updateSpecialization (Doctor doctor, String specialization) {
        doctor.setSpecialization(specialization);
    }

    private int getNumberOfDoctors(MedicalOffice medicalOffice) {
        int numberOfDoctors = 0;
        for (Doctor d : medicalOffice.getDoctors())
            if (d != null) {
                numberOfDoctors++;
            }
        return numberOfDoctors;
    }

    public void printDoctors(MedicalOffice medicalOffice) {
        for (Doctor d : medicalOffice.getDoctors())
            if (d != null) {
                System.out.println(d);
            }
    }
}
