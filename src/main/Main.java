package main;

import model.*;
import service.MedicalOfficeService;

public class Main {
    public static void main(String[] args) {
        MedicalOffice medicalOffice = new MedicalOffice();
        MedicalOfficeService medicalOfficeService = new MedicalOfficeService();

        Doctor doctor = new Doctor("fdafdaf","a",1,"a","a",1,"a");

        medicalOfficeService.addDoctor(medicalOffice, doctor);

        System.out.println(medicalOffice.getDoctors()[0].getSpecialization());
        System.out.println(medicalOffice.getPeople()[0].getName());
    }
}
