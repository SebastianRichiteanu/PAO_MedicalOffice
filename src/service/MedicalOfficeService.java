package service;

import model.MedicalOffice;
import model.*;

import javax.print.Doc;

public class MedicalOfficeService {
    private final PersonService personService = new PersonService();
    private final DoctorService doctorService = new DoctorService();
    private final PatientService patientService = new PatientService();
    private final MedicationService medicationService = new MedicationService();
    private final PrescriptionService prescriptionService = new PrescriptionService();
    private final AppointmentService appointmentService = new AppointmentService();

    public void addPerson (MedicalOffice medicalOffice, Person person) {
        personService.addPerson(medicalOffice, person);
    }

    public void addDoctor (MedicalOffice medicalOffice, Doctor doctor) {
        personService.addPerson(medicalOffice, doctor);
        doctorService.addDoctor(medicalOffice, doctor);
    }

    public void addPatient (MedicalOffice medicalOffice, Patient patient) {
        personService.addPerson(medicalOffice, patient);
        patientService.addPatient(medicalOffice, patient);
    }

    public void addMedication (MedicalOffice medicalOffice, Medication medication) {
        medicationService.addMedication(medicalOffice, medication);
    }

    public void addPrescription (MedicalOffice medicalOffice, Prescription prescription) {
        prescriptionService.addPrescription(medicalOffice, prescription);
    }

    public void addAppointment (MedicalOffice medicalOffice, Appointment appointment) {
        appointmentService.addAppointment(medicalOffice, appointment);
    }

}
