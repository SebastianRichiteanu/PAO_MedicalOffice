package service;

import model.MedicalOffice;
import model.*;

import javax.print.Doc;
import java.util.Set;

public class MedicalOfficeService {
    private final PersonService personService = new PersonService();
    private final DoctorService doctorService = new DoctorService();
    private final PatientService patientService = new PatientService();
    private final MedicationService medicationService = new MedicationService();
    private final PrescriptionService prescriptionService = new PrescriptionService();
    private final AppointmentService appointmentService = new AppointmentService();
    private final SupplierService supplierService = new SupplierService();
    private final AuditService auditService = new AuditService();
    private final ReadService readService = ReadService.getInstance();
    private final WriteService writeService = WriteService.getInstance();

    public void addDoctor (MedicalOffice medicalOffice, Doctor doctor) {
        auditService.logEvent("addDoctor");
        personService.addPerson(medicalOffice, doctor);
        doctorService.addDoctor(medicalOffice, doctor);
    }

    public void addPatient (MedicalOffice medicalOffice, Patient patient) {
        auditService.logEvent("addPatient");
        personService.addPerson(medicalOffice, patient);
        patientService.addPatient(medicalOffice, patient);
    }

    public void addMedication (MedicalOffice medicalOffice, Medication medication) {
        auditService.logEvent("addMedication");
        medicationService.addMedication(medicalOffice, medication);
    }

    public void addPrescription (MedicalOffice medicalOffice, Prescription prescription) {
        auditService.logEvent("addPrescription");
        prescriptionService.addPrescription(medicalOffice, prescription);
    }

    public void addAppointment (MedicalOffice medicalOffice, Appointment appointment) {
        auditService.logEvent("addAppointment");
        appointmentService.addAppointment(medicalOffice, appointment);
    }

    public void addSupplier (MedicalOffice medicalOffice, Supplier supplier) {
        auditService.logEvent("addSupplier");
        supplierService.addSupplier(medicalOffice, supplier);
    }

    public Supplier searchSupplierByName (MedicalOffice medicalOffice, String name) {
        auditService.logEvent("searchSupplierByName");
        return supplierService.searchSupplierByName(medicalOffice,name);
    }

    public Person searchPersonByFullName (MedicalOffice medicalOffice, String name, String surname) {
        auditService.logEvent("searchPersonByFullName");
        return personService.searchPersonByFullName(medicalOffice, name, surname);
    }

    public Doctor searchDoctorByFullName (MedicalOffice medicalOffice, String name, String surname) {
        auditService.logEvent("searchDoctorByFullName");
        return doctorService.searchDoctorByFullName(medicalOffice, name, surname);
    }

    public Patient searchPatientByFullName (MedicalOffice medicalOffice, String name, String surname) {
        auditService.logEvent("searchPatientByFullName");
        return patientService.searchPatientByFullName(medicalOffice, name, surname);
    }

    public void updateName (Person person, String name) {
        auditService.logEvent("updateName");
        personService.updateName(person, name);
    }

    public void updateSurname (Person person, String surname) {
        auditService.logEvent("updateSurname");
        personService.updateSurname(person, surname);
    }

    public void updateAge (Person person, int age) {
        auditService.logEvent("updateAge");
        personService.updateAge(person, age);
    }

    public void updateAddress (Person person, String address) {
        auditService.logEvent("updateAddress");
        personService.updateAddress(person, address);
    }

    public void updatePhoneNo (Person person, String phoneNo) {
        auditService.logEvent("updatePhoneNo");
        personService.updatePhonNo(person, phoneNo);
    }

    public void updateSalary (Doctor doctor, double salary) {
        auditService.logEvent("updateSalary");
        doctorService.updateSalary(doctor, salary);
    }

    public void updateSpecialization (Doctor doctor, String specialization) {
        auditService.logEvent("updateSpecialization");
        doctorService.updateSpecialization(doctor,specialization);
    }

    public void updateCondition (Patient patient, String condition) {
        auditService.logEvent("updateCondition");
        patientService.updateCondition(patient, condition);
    }

    public Medication searchMedicationByName (MedicalOffice medicalOffice, String name) {
        return medicationService.searchMedicationByName(medicalOffice, name);
    }

    public void updateName (Medication medication, String name) {
        auditService.logEvent("updateName");
        medicationService.updateName(medication, name);
    }

    public void updatePrice (Medication medication, double price) {
        auditService.logEvent("updatePrice");
        medicationService.updatePrice(medication, price);
    }

    public void updateDate (Prescription prescription, String date) {
        auditService.logEvent("updateDate");
        prescriptionService.updateDate(prescription, date);
    }

    public void updateName (Supplier supplier, String name) {
        auditService.logEvent("updateName");
        supplierService.updateName(supplier, name);
    }

    public void updateLocation (Supplier supplier, String location) {
        auditService.logEvent("updateLocation");
        supplierService.updateLocation(supplier, location);
    }

    public Prescription getPrescriptionById (MedicalOffice medicalOffice, int id) {
        return prescriptionService.getPrescriptionById(medicalOffice,id);
    }

    public boolean isMedicationOnPrescription (Medication medication, Prescription prescription) {
        auditService.logEvent("isMedicationOnPrescription");
        return prescriptionService.isMedicationOnPrescription(medication, prescription);
    }

    public void addMedicationToPrescription (Medication medication, Prescription prescription) {
        auditService.logEvent("addMedicationToPrescription");
        prescriptionService.addMedicationToPrescription(medication, prescription);
    }

    public void updatePrescription (Appointment appointment, Prescription prescription) {
        auditService.logEvent("updatePrescription");
        appointmentService.updatePrescription(appointment, prescription);
    }

    public int numberOfAppointmentsPerDoctor (MedicalOffice medicalOffice, Doctor doctor) {
        auditService.logEvent("numberOfAppointmentsPerDoctor");
        return appointmentService.numberOfAppointmentsPerDoctor(medicalOffice, doctor);
    }

    public int numberOfPrescriptionPerMedication (MedicalOffice medicalOffice, Medication medication) {
        auditService.logEvent("numberOfPrescriptionPerMedication");
        return prescriptionService.numberOfPrescriptionPerMedication(medicalOffice, medication);
    }

    public void printPeople(MedicalOffice medicalOffice) {
        auditService.logEvent("printPeople");
        personService.printPeople(medicalOffice);
    }

    public void printDoctors(MedicalOffice medicalOffice) {
        auditService.logEvent("printDoctors");
        doctorService.printDoctors(medicalOffice);
    }

    public void printPatients(MedicalOffice medicalOffice) {
        auditService.logEvent("printPatients");
        patientService.printPatients(medicalOffice);
    }

    public void printSuppliers(MedicalOffice medicalOffice) {
        auditService.logEvent("printSuppliers");
        supplierService.printSuppliers(medicalOffice);
    }

    public void printAppointments(MedicalOffice medicalOffice) {
        auditService.logEvent("printAppointments");
        appointmentService.printAppointments(medicalOffice);
    }

    public void printMedications (MedicalOffice medicalOffice) {
        auditService.logEvent("printMedications");
        medicationService.printMedications(medicalOffice);
    }

    public void printPrecriptions (MedicalOffice medicalOffice) {
        auditService.logEvent("printPrescriptions");
        medicationService.printMedications(medicalOffice);
    }

    public void read(MedicalOffice medicalOffice) {
        auditService.logEvent("read");
        readService.readDoctors(medicalOffice, this);
        readService.readPatients(medicalOffice, this);
        readService.readSupplier(medicalOffice, this);
        readService.readMedication(medicalOffice,this);
        readService.readPrescription(medicalOffice,this);
        readService.readAppointment(medicalOffice, this);
    }

    public void write(MedicalOffice medicalOffice) {
        auditService.logEvent("write");
        writeService.writeDoctors(medicalOffice.getDoctors());
        writeService.writePatients(medicalOffice.getPatients());
        writeService.writeSuppliers(medicalOffice.getSuppliers());
        writeService.writeMedication(medicalOffice.getMedications());
        writeService.writeAppointments(medicalOffice.getAppointments());
        writeService.writePrescriptions(medicalOffice.getPrescriptions());
        writeService.writePersons(medicalOffice.getPeople());
    }
}
