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
        System.out.println(medicalOffice.getPeople()[0]);
        System.out.println(medicalOffice.getPeople()[1]);
        System.out.println(medicalOffice.getPeople()[2]);
        System.out.println("Doctor:");
        System.out.println(medicalOffice.getDoctors()[0]);
        System.out.println("Patient:");
        System.out.println(medicalOffice.getPatients()[0]);
        System.out.println("Medication:");
        System.out.println(medicalOffice.getMedications()[0]);
        System.out.println("Prescription:");
        System.out.println(medicalOffice.getPrescriptions()[0]);
        System.out.println("Appointment:");
        System.out.println(medicalOffice.getAppointments()[0]);

        // Search by FullName

        System.out.println("PersonByFullName:");
        System.out.println(medicalOfficeService.searchPersonByFullName(medicalOffice,"person_name","person_surname"));
        System.out.println("DoctorByFullName:");
        System.out.println(medicalOfficeService.searchDoctorByFullName(medicalOffice, "doctor_name", "doctor_surname"));
        System.out.println("PatientByFullName:");
        System.out.println(medicalOfficeService.searchPatientByFullName(medicalOffice,"patient_name","patient_surname"));

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
        System.out.println(medicalOffice.getPeople()[0]);
        System.out.println(medicalOffice.getPeople()[1]);
        System.out.println(medicalOffice.getPeople()[2]);

            // Medication

        medicalOfficeService.updateName(medication, "medication_name2");
        medicalOfficeService.updatePrice(medication, 999);

        System.out.println("New Medication:");
        System.out.println(medicalOffice.getMedications()[0]);

            // Prescription

        medicalOfficeService.updateDate(prescription,"prescription_date2");

        System.out.println("New Prescription:");
        System.out.println(medicalOffice.getPrescriptions()[0]);


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

        // Add new doctors to "test" the sorted array

        Doctor doctor1 = new Doctor ("doctor1_name","doctor1_surname",1,"doctor1_address","doctor1_phone",1,"special1");
        medicalOfficeService.addDoctor(medicalOffice, doctor1);
        Doctor doctor2 = new Doctor ("doctor2_name","doctor2_surname",2,"doctor2_address","doctor2_phone",2,"special2");
        medicalOfficeService.addDoctor(medicalOffice, doctor2);


        for (Doctor d : medicalOffice.getDoctors()) {
            if (d != null)
                System.out.println(d);


        }

    }



}
