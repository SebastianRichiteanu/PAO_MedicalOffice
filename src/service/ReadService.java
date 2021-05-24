package service;

import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.sql.Date;

public class ReadService {
    private static final String DIRECTORY_PATH = "resources/db";
    private static ReadService INSTANCE;

    private ReadService () {}

    public static ReadService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReadService();
        }
        return INSTANCE;
    }

    public void readDoctors (MedicalOffice medicalOffice, MedicalOfficeService medicalOfficeService) {
        String FILE_PATH = DIRECTORY_PATH + "/doctors.csv";
        try  {
            BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
            String line = "";
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] atr = line.split(",");
                String name = atr[0];
                String surname = atr[1];
                int age = Integer.parseInt(atr[2]);
                String address = atr[3];
                String phoneNo = atr[4];
                double salary = Double.parseDouble(atr[5]);
                String specialization = atr[6];
                Doctor doctor = new Doctor(name,surname,age,address,phoneNo,salary,specialization);
                medicalOfficeService.addDoctor(doctor);
            }
        } catch (NoSuchFileException e) {
            System.out.println("The file with the name '" + FILE_PATH + "' doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getClass() + " " + e.getMessage());
        }
    }

    public void readPatients (MedicalOffice medicalOffice, MedicalOfficeService medicalOfficeService) {
        String FILE_PATH = DIRECTORY_PATH + "/patients.csv";
        try  {
            BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
            String line = "";
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] atr = line.split(",");
                String name = atr[0];
                String surname = atr[1];
                int age = Integer.parseInt(atr[2]);
                String address = atr[3];
                String phoneNo = atr[4];
                String condition = atr[5];
                Patient patient = new Patient(name,surname,age,address,phoneNo,condition);
                medicalOfficeService.addPatient(patient);
            }
        } catch (NoSuchFileException e) {
            System.out.println("The file with the name '" + FILE_PATH + "' doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getClass() + " " + e.getMessage());
        }
    }

    public void readSupplier (MedicalOffice medicalOffice, MedicalOfficeService medicalOfficeService) {
        String FILE_PATH = DIRECTORY_PATH + "/suppliers.csv";
        try  {
            BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
            String line = "";
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] atr = line.split(",");
                String name = atr[0];
                String location = atr[1];
                Supplier supplier = new Supplier(name,location);
                medicalOfficeService.addSupplier(supplier);
            }
        } catch (NoSuchFileException e) {
            System.out.println("The file with the name '" + FILE_PATH + "' doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getClass() + " " + e.getMessage());
        }
    }

    public void readMedication (MedicalOffice medicalOffice, MedicalOfficeService medicalOfficeService) {
        String FILE_PATH = DIRECTORY_PATH + "/medication.csv";
        try  {
            BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
            String line = "";
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] atr = line.split(",");
                String name = atr[0];
                double price = Double.parseDouble(atr[1]);
                String supplierName = atr[2];
                Medication medication = new Medication(name,price,medicalOfficeService.searchSupplierByName(medicalOffice,supplierName));
                medicalOfficeService.addMedication(medication);
            }
        } catch (NoSuchFileException e) {
            System.out.println("The file with the name '" + FILE_PATH + "' doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getClass() + " " + e.getMessage());
        }
    }

    public void readAppointment (MedicalOffice medicalOffice, MedicalOfficeService medicalOfficeService) {
        String FILE_PATH = DIRECTORY_PATH + "/appointments.csv";
        try  {
            BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
            String line = "";
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] atr = line.split(",");
                String patientName = atr[0];
                String patientSurname = atr[1];
                String doctorName = atr[2];
                String doctorSurname = atr[3];
                String prescriptionBarCode = atr[4];
                Date date = Date.valueOf(atr[5]);
                Appointment appointment = new Appointment(medicalOfficeService.searchPatientByFullName(medicalOffice, patientName,patientSurname),
                        medicalOfficeService.searchDoctorByFullName(medicalOffice,doctorName,doctorSurname),
                        medicalOfficeService.getPrescriptionByBarCode(medicalOffice,prescriptionBarCode),
                        date);
                medicalOfficeService.addAppointment(appointment);
            }
        } catch (NoSuchFileException e) {
            System.out.println("The file with the name '" + FILE_PATH + "' doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getClass() + " " + e.getMessage());
        }
    }

    public void readPrescription (MedicalOffice medicalOffice, MedicalOfficeService medicalOfficeService) {
        String FILE_PATH = DIRECTORY_PATH + "/prescriptions.csv";
        try  {
            BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
            String line = "";
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] atr = line.split(",");
                String barCode = atr[0];
                Date date = Date.valueOf(atr[1]);
                String[] medications = atr[2].split(",");
                Prescription prescription = new Prescription(barCode, date, new Medication[100]);
                for (String med : medications) {
                    Medication m = medicalOfficeService.searchMedicationByName(medicalOffice, med);
                    medicalOfficeService.addMedicationToPrescription(m, prescription);
                }
                medicalOfficeService.addPrescription(prescription);
            }
        } catch (NoSuchFileException e) {
            System.out.println("The file with the name '" + FILE_PATH + "' doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getClass() + " " + e.getMessage());
        }
    }

}
