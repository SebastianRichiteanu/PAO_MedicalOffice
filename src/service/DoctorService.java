package service;

import model.Appointment;
import model.Doctor;
import model.MedicalOffice;
import model.Person;
import repository.DoctorRepository;
import repository.GetRepository;

import java.util.*;

public class DoctorService {
    private static DoctorService INSTANCE;
    private DoctorRepository doctorRepository;
    private GetRepository getRepository;

    private DoctorService () {
        this.doctorRepository = new DoctorRepository();
        this.getRepository = new GetRepository();
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

    public Doctor searchDoctorByFullName(String name, String surname) {
        int id = doctorRepository.getDoctorIdByName(name, surname);
        if (id == -1) {
            System.out.println("This doctor does not exist");
        } else {
            Optional<Doctor> doc = getRepository.getDoctorById(id);
            if (doc.isPresent()) {
                return doc.get();
            }
        }
        return null;
    }

    public void updateName (Doctor doctor, String name) {
        int id = doctorRepository.getDoctorId(doctor);
        if (id == -1) {
            System.out.println("This doctor does not exist");
        } else {
            doctorRepository.updateDoctorById(id, "name", name);
            doctor.setName(name);
        }
    }

    public void updateSurname (Doctor doctor, String surname) {
        int id = doctorRepository.getDoctorId(doctor);
        if (id == -1) {
            System.out.println("This doctor does not exist");
        } else {
            doctorRepository.updateDoctorById(id, "surname", surname);
            doctor.setSurname(surname);
        }
    }

    public void updateAge (Doctor doctor, int age) {
        int id = doctorRepository.getDoctorId(doctor);
        if (id == -1) {
            System.out.println("This doctor does not exist");
        } else {
            doctorRepository.updateDoctorById(id, "age", String.valueOf(age));
            doctor.setAge(age);
        }
    }

    public void updateAddress (Doctor doctor, String address) {
        int id = doctorRepository.getDoctorId(doctor);
        if (id == -1) {
            System.out.println("This doctor does not exist");
        } else {
            doctorRepository.updateDoctorById(id, "address", address);
            doctor.setAddress(address);
        }
    }

    public void updatePhoneNo (Doctor doctor, String phoneNo) {
        int id = doctorRepository.getDoctorId(doctor);
        if (id == -1) {
            System.out.println("This doctor does not exist");
        } else {
            doctorRepository.updateDoctorById(id, "phoneNo", phoneNo);
            doctor.setPhoneNo(phoneNo);
        }
    }

    public void updateSalary (Doctor doctor, double salary) {
        int id = doctorRepository.getDoctorId(doctor);
        if (id == -1) {
            System.out.println("This doctor does not exist");
        } else {
            doctorRepository.updateDoctorById(id, "salary", String.valueOf(salary));
            doctor.setSalary(salary);
        }
    }

    public void updateSpecialization (Doctor doctor, String specialization) {
        int id = doctorRepository.getDoctorId(doctor);
        if (id == -1) {
            System.out.println("This doctor does not exist");
        } else {
            doctorRepository.updateDoctorById(id, "specialization", specialization);
            doctor.setSpecialization(specialization);
        }
    }

    public Optional<Doctor> getDoctorById(int id) { return getRepository.getDoctorById(id); }


    public void printDoctors() {
        Set<Doctor> docs = getRepository.getAllDoctors();
        if (docs != null) {
            for (Doctor d : docs)
                if (d != null) {
                    System.out.println(d);
                }
        }
    }
}
