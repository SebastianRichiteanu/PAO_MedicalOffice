package service;


import model.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Set;
import java.util.List;

public class WriteService {
    private static WriteService INSTANCE;
    private static final String DIRECTORY_PATH = "resources/db";

    private WriteService () {}

    public static WriteService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WriteService();
        }
        return INSTANCE;
    }

    public void writePersons (Set<Person> persons) {
        String FILE_PATH = DIRECTORY_PATH + "/persons.csv";
        if (!Files.exists(Paths.get(DIRECTORY_PATH))) {
            try {
                Files.createDirectories(Paths.get(DIRECTORY_PATH));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if (!Files.exists(Paths.get(FILE_PATH))) {
            try {
                Files.createFile(Paths.get(FILE_PATH));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH),
                    StandardOpenOption.TRUNCATE_EXISTING);
            writer.write("name,surname,age,address,phoneNo\n");
            writer.flush();
            for (Person person : persons)
                if (person != null) {
                    writer.write(person.getName() + "," + person.getSurname() + "," + person.getAge() + "," +
                            person.getAddress() + "," + person.getPhoneNo() + "\n");
                    writer.flush();
                }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeDoctors (Set<Doctor> doctors) {
        String FILE_PATH = DIRECTORY_PATH + "/doctors.csv";
        if (!Files.exists(Paths.get(DIRECTORY_PATH))) {
            try {
                Files.createDirectories(Paths.get(DIRECTORY_PATH));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if (!Files.exists(Paths.get(FILE_PATH))) {
            try {
                Files.createFile(Paths.get(FILE_PATH));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH),
                    StandardOpenOption.TRUNCATE_EXISTING);
            writer.write("name,surname,age,address,phoneNo,salary,specialization\n");
            writer.flush();
            for (Doctor doctor : doctors)
                if (doctor != null) {
                    writer.write(doctor.getName() + "," + doctor.getSurname() + "," + doctor.getAge() + "," +
                            doctor.getAddress() + "," + doctor.getPhoneNo() + "," + doctor.getSalary() + "," +
                            doctor.getSpecialization() + "\n");
                    writer.flush();
                }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writePatients (Set<Patient> patients) {
        String FILE_PATH = DIRECTORY_PATH + "/patients.csv";
        if (!Files.exists(Paths.get(DIRECTORY_PATH))) {
            try {
                Files.createDirectories(Paths.get(DIRECTORY_PATH));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if (!Files.exists(Paths.get(FILE_PATH))) {
            try {
                Files.createFile(Paths.get(FILE_PATH));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH),
                    StandardOpenOption.TRUNCATE_EXISTING);
            writer.write("name,surname,age,address,phoneNo,condition\n");
            writer.flush();
            for (Patient patient : patients)
                if (patient != null) {
                    writer.write(patient.getName() + "," + patient.getSurname() + "," + patient.getAge() + "," +
                            patient.getAddress() + "," + patient.getPhoneNo() + "," + patient.getCondition() + "\n");
                    writer.flush();
                }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeSuppliers (List<Supplier> suppliers) {
        String FILE_PATH = DIRECTORY_PATH + "/suppliers.csv";
        if (!Files.exists(Paths.get(DIRECTORY_PATH))) {
            try {
                Files.createDirectories(Paths.get(DIRECTORY_PATH));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if (!Files.exists(Paths.get(FILE_PATH))) {
            try {
                Files.createFile(Paths.get(FILE_PATH));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH),
                    StandardOpenOption.TRUNCATE_EXISTING);
            writer.write("name,location\n");
            writer.flush();
            for (Supplier supplier : suppliers)
                if (supplier != null) {
                    writer.write(supplier.getName() + "," + supplier.getLocation() + "\n");
                    writer.flush();
                }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeMedication (List<Medication> medications) {
        String FILE_PATH = DIRECTORY_PATH + "/medication.csv";
        if (!Files.exists(Paths.get(DIRECTORY_PATH))) {
            try {
                Files.createDirectories(Paths.get(DIRECTORY_PATH));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if (!Files.exists(Paths.get(FILE_PATH))) {
            try {
                Files.createFile(Paths.get(FILE_PATH));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH),
                    StandardOpenOption.TRUNCATE_EXISTING);
            writer.write("name,price,supplier_name\n");
            writer.flush();
            for (Medication medication : medications)
                if (medication != null) {
                    writer.write(medication.getName() + "," + medication.getPrice() + "," + medication.getSupplier().getName() + "\n");
                    writer.flush();
                }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeAppointments (List<Appointment> appointments) {
        String FILE_PATH = DIRECTORY_PATH + "/appointments.csv";
        if (!Files.exists(Paths.get(DIRECTORY_PATH))) {
            try {
                Files.createDirectories(Paths.get(DIRECTORY_PATH));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if (!Files.exists(Paths.get(FILE_PATH))) {
            try {
                Files.createFile(Paths.get(FILE_PATH));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH),
                    StandardOpenOption.TRUNCATE_EXISTING);
            writer.write("patientName,patientSurname,doctorName,doctorSurname,prescriptionId,date\n");
            writer.flush();
            for (Appointment appointment : appointments)
                if (appointment != null) {
                    writer.write(appointment.getPatient().getName() + "," + appointment.getPatient().getSurname() + "," +
                            appointment.getDoctor().getName() + "," + appointment.getDoctor().getSurname() + "," +
                            appointment.getPrescription().getBarCode() + "," + appointment.getDate() + "\n");
                    writer.flush();
                }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writePrescriptions (List<Prescription> prescriptions) {
        String FILE_PATH = DIRECTORY_PATH + "/prescriptions.csv";
        if (!Files.exists(Paths.get(DIRECTORY_PATH))) {
            try {
                Files.createDirectories(Paths.get(DIRECTORY_PATH));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if (!Files.exists(Paths.get(FILE_PATH))) {
            try {
                Files.createFile(Paths.get(FILE_PATH));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH),
                    StandardOpenOption.TRUNCATE_EXISTING);
            writer.write("id,date,medicationsName\n");
            writer.flush();
            for (Prescription prescription : prescriptions)
                if (prescription != null) {
                    StringBuilder medicationsName = new StringBuilder();
                    for (Medication m : prescription.getMedications())
                        if (m != null) {
                            medicationsName.append(m.getName()).append(",");
                        }
                    writer.write(prescription.getBarCode() + "," + prescription.getDate() + "," + medicationsName + "\n");
                    writer.flush();
                }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
