package main;

import model.*;
import service.MedicalOfficeService;

import javax.print.Doc;

public class Main {
    public static void main(String[] args) {
        MedicalOffice medicalOffice = new MedicalOffice();
        MedicalOfficeService medicalOfficeService = new MedicalOfficeService();

        // Add to "DB"

        Person person = new Person("person_name","person_surname",1,"person_address","person_phone");
        medicalOfficeService.addPerson(medicalOffice, person);

        Doctor doctor = new Doctor ("doctor_name","doctor_surname",2,"doctor_address","doctor_phone",2,"special");
        medicalOfficeService.addDoctor(medicalOffice, doctor);

        Patient patient = new Patient("patient_name","patient_surname",3,"patient_address","patient_phone","condition");
        medicalOfficeService.addPatient(medicalOffice, patient);

        Medication medication = new Medication("medication_name",4);
        medicalOfficeService.addMedication(medicalOffice, medication);

        Prescription prescription = new Prescription("prescription_date",new Medication[100]);
        medicalOfficeService.addPrescription(medicalOffice, prescription);

        Appointment appointment = new Appointment(patient, doctor, prescription,"appointment_date");
        medicalOfficeService.addAppointment(medicalOffice, appointment);

        // Check if they are in "DB"
        System.out.println("People:");
        System.out.println(medicalOffice.getPeople()[0].toString());
        System.out.println(medicalOffice.getPeople()[1].toString());
        System.out.println(medicalOffice.getPeople()[2].toString());
        System.out.println("Doctor:");
        System.out.println(medicalOffice.getDoctors()[0].toString());
        System.out.println("Patient:");
        System.out.println(medicalOffice.getPatients()[0].toString());
        System.out.println("Medication:");
        System.out.println(medicalOffice.getMedications()[0].toString());
        System.out.println("Prescription:");
        System.out.println(medicalOffice.getPrescriptions()[0].toString());
        System.out.println("Appointment:");
        System.out.println(medicalOffice.getAppointments()[0].toString());

        // Search by FullName

        System.out.println("PersonByFullName:");
        System.out.println(medicalOfficeService.searchPersonByFullName(medicalOffice,"person_name","person_surname").toString());
        System.out.println("DoctorByFullName:");
        System.out.println(medicalOfficeService.searchDoctorByFullName(medicalOffice, "doctor_name", "doctor_surname").toString());
        System.out.println("PatientByFullName:");
        System.out.println(medicalOfficeService.searchPatientByFullName(medicalOffice,"patient_name","patient_surname").toString());

        // Update

            // People

        medicalOfficeService.updateName(person, "person_name2");
        medicalOfficeService.updateSurname(person,"person_surname2");
        medicalOfficeService.updateAge(person,999);
        medicalOfficeService.updateAddress(person,"person_address2");
        medicalOfficeService.updatePhoneNo(person, "person_phone2");
        medicalOfficeService.updateSalary(doctor, 999);
        medicalOfficeService.updateSpecialization(doctor, "special2");
        medicalOfficeService.updateCondition(patient,"condition2");

        System.out.println("New People:");
        System.out.println(medicalOffice.getPeople()[0].toString());
        System.out.println(medicalOffice.getPeople()[1].toString());
        System.out.println(medicalOffice.getPeople()[2].toString());

            // Medication

        medicalOfficeService.updateName(medication, "medication_name2");
        medicalOfficeService.updatePrice(medication, 999);

        System.out.println("New Medication:");
        System.out.println(medicalOffice.getMedications()[0].toString());

            // Prescription

        medicalOfficeService.updateDate(prescription,"prescription_date2");

        System.out.println("New Prescription:");
        System.out.println(medicalOffice.getPrescriptions()[0].toString());


        // Add medication to prescription + check

        medicalOfficeService.addMedicationToPrescription(medication, prescription);
        System.out.println(medicalOfficeService.isMedicationOnPrescription(medication, prescription));

        Prescription prescription1 = new Prescription("prescription_date999",new Medication[100]);
        medicalOfficeService.addPrescription(medicalOffice, prescription1);
        medicalOfficeService.updatePrescription(appointment,prescription1);


        System.out.println("Nr of appointments per doctor:");
        System.out.println(medicalOfficeService.numberOfAppointmentsPerDoctor(medicalOffice, doctor));

        Appointment appointment1 = new Appointment(patient, doctor, prescription,"appointment_date999");
        medicalOfficeService.addAppointment(medicalOffice, appointment1);
        System.out.println(medicalOfficeService.numberOfAppointmentsPerDoctor(medicalOffice, doctor));

        System.out.println("Nr of prescriptions per medication:");
        System.out.println(medicalOfficeService.numberOfPrescriptionPerMedication(medicalOffice, medication));

        medicalOfficeService.addMedicationToPrescription(medication, prescription1);
        System.out.println(medicalOfficeService.numberOfPrescriptionPerMedication(medicalOffice, medication));


    }



}
