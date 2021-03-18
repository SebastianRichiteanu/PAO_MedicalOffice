package service;

import model.Doctor;
import model.MedicalOffice;
import model.Person;

import javax.print.Doc;

public class DoctorService {
    public void addDoctor (MedicalOffice medicalOffice, Doctor doctor) {
        int nextAvailableIndex = getNumberOfDoctors(medicalOffice);
        medicalOffice.getDoctors()[nextAvailableIndex] = doctor;
    }

    public Doctor searchDoctorByFullName(MedicalOffice medicalOffice, String name, String surname) {
        Doctor doc = null;
        for (Doctor d : medicalOffice.getDoctors())
            if (d != null && d.getName().equals(name) && d.getSurname().equals(surname))
                doc = d;
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
            if (d != null)
                numberOfDoctors++;
        return numberOfDoctors;
    }
}
