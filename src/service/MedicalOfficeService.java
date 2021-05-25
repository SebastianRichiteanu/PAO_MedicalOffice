package service;

import model.MedicalOffice;
import model.*;

import java.sql.Date;

public class MedicalOfficeService {

    private static MedicalOfficeService INSTANCE;


    private MedicalOfficeService () {}

    public static MedicalOfficeService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MedicalOfficeService();
        }
        return INSTANCE;
    }

    private final PersonService personService = PersonService.getInstance();
    private final DoctorService doctorService = DoctorService.getInstance();
    private final PatientService patientService = PatientService.getInstance();
    private final MedicationService medicationService = MedicationService.getInstance();
    private final PrescriptionService prescriptionService = PrescriptionService.getInstance();
    private final AppointmentService appointmentService = AppointmentService.getInstance();
    private final SupplierService supplierService = SupplierService.getInstance();
    private final AuditService auditService = AuditService.getInstance();
    private final ReadService readService = ReadService.getInstance();
    private final WriteService writeService = WriteService.getInstance();

    public void addDoctor (Doctor doctor) {
        auditService.logEvent("addDoctor");
        personService.addPerson(doctor);
        doctorService.addDoctor(doctor);
    }

    public void addPatient (Patient patient) {
        auditService.logEvent("addPatient");
        personService.addPerson(patient);
        patientService.addPatient(patient);
    }

    public void addMedication (Medication medication) {
        auditService.logEvent("addMedication");
        medicationService.addMedication(medication);
    }

    public void addPrescription (Prescription prescription) {
        auditService.logEvent("addPrescription");
        prescriptionService.addPrescription(prescription);
    }

    public void addAppointment (Appointment appointment) {
        auditService.logEvent("addAppointment");
        appointmentService.addAppointment(appointment);
    }

    public void addSupplier (Supplier supplier) {
        auditService.logEvent("addSupplier");
        supplierService.addSupplier(supplier);
    }

    public void removeDoctor (Doctor doctor) {
        auditService.logEvent("removeDoctor");
        personService.removePerson(doctor);
        doctorService.removeDoctor(doctor);
    }

    public void removePatient(Patient patient) {
        auditService.logEvent("removePatient");
        personService.removePerson(patient);
        patientService.removePatient(patient);
    }

    public void removeMedication(Medication medication) {
        auditService.logEvent("removeMedication");
        medicationService.removeMedication(medication);
    }

    public void removeSupplier(Supplier supplier) {
        auditService.logEvent("removeSupplier");
        supplierService.removeSupplier(supplier);
    }

    public void removePrescription(Prescription prescription) {
        auditService.logEvent("removePrescription");
        prescriptionService.removePrescription(prescription);
    }

    public void removeAppointment(Appointment appointment) {
        auditService.logEvent("removeAppointment");
        appointmentService.removeAppointment(appointment);
    }

    public Supplier searchSupplierByName (String name) {
        auditService.logEvent("searchSupplierByName");
        return supplierService.searchSupplierByName(name);
    }

    public Doctor searchDoctorByFullName (String name, String surname) {
        auditService.logEvent("searchDoctorByFullName");
        return doctorService.searchDoctorByFullName(name, surname);
    }

    public Patient searchPatientByFullName (String name, String surname) {
        auditService.logEvent("searchPatientByFullName");
        return patientService.searchPatientByFullName(name, surname);
    }

    public void updateName (Person person, String name) {
        auditService.logEvent("updateName");
        personService.updateName(person, name);
        if (person instanceof Doctor) {
            doctorService.updateName((Doctor) person, name);
        } else if (person instanceof Patient) {
            patientService.updateName((Patient) person, name);
        }
    }

    public void updateSurname (Person person, String surname) {
        auditService.logEvent("updateSurname");
        personService.updateSurname(person, surname);
        if (person instanceof Doctor) {
            doctorService.updateSurname((Doctor) person, surname);
        } else if (person instanceof Patient) {
            patientService.updateSurname((Patient) person, surname);
        }
    }

    public void updateAge (Person person, int age) {
        auditService.logEvent("updateAge");
        personService.updateAge(person, age);
        if (person instanceof Doctor) {
            doctorService.updateAge((Doctor) person, age);
        } else if (person instanceof Patient) {
            patientService.updateAge((Patient) person, age);
        }
    }

    public void updateAddress (Person person, String address) {
        auditService.logEvent("updateAddress");
        personService.updateAddress(person, address);
        if (person instanceof Doctor) {
            doctorService.updateAddress((Doctor) person, address);
        } else if (person instanceof Patient) {
            patientService.updateAddress((Patient) person, address);
        }
    }

    public void updatePhoneNo (Person person, String phoneNo) {
        auditService.logEvent("updatePhoneNo");
        personService.updatePhoneNo(person, phoneNo);
        if (person instanceof Doctor) {
            doctorService.updatePhoneNo((Doctor) person, phoneNo);
        } else if (person instanceof Patient) {
            patientService.updatePhoneNo((Patient) person, phoneNo);
        }
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

    public void updateDate (Prescription prescription, Date date) {
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

    public Prescription getPrescriptionByBarCode (MedicalOffice medicalOffice, String barCode) {
        return prescriptionService.getPrescriptionByBarCode(medicalOffice,barCode);
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

    public void updateAppointmentDate (Appointment appointment, Date date) {
        auditService.logEvent("updateAppointmentDate");
        appointmentService.updateDate(appointment, date);
    }

    public int numberOfAppointmentsPerDoctor (Doctor doctor) {
        auditService.logEvent("numberOfAppointmentsPerDoctor");
        return appointmentService.numberOfAppointmentsPerDoctor(doctor);
    }

    public int getNumberOfMedicationsOnPrescription (Prescription prescription) {
        auditService.logEvent("getNumberOfMedicationsOnPrescription");
        return prescriptionService.getNumberOfMedicationsOnPrescription(prescription);
    }

    public void printPeople() {
        auditService.logEvent("printPeople");
        personService.printPeople();
    }

    public void printDoctors() {
        auditService.logEvent("printDoctors");
        doctorService.printDoctors();
    }

    public void printPatients() {
        auditService.logEvent("printPatients");
        patientService.printPatients();
    }

    public void printSuppliers() {
        auditService.logEvent("printSuppliers");
        supplierService.printSuppliers();
    }

    public void printAppointments() {
        auditService.logEvent("printAppointments");
        appointmentService.printAppointments();
    }

    public void printMedications () {
        auditService.logEvent("printMedications");
        medicationService.printMedications();
    }

    public void printPrecriptions () {
        auditService.logEvent("printPrescriptions");
        prescriptionService.printPrescriptions();
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
