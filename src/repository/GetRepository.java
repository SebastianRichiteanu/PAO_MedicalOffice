package repository;

import config.DatabaseConnection;
import model.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class GetRepository {

    public Optional<Doctor> getDoctorById(int id) {
        String sql = "select * from doctors where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                String name = result.getString(2);
                String surname = result.getString(3);
                int age = result.getInt(4);
                String address = result.getString(5);
                String phoneNo = result.getString(6);
                double salary = result.getDouble(7);
                String specialization = result.getString(8);
                return Optional.of(new Doctor(name, surname, age, address, phoneNo, salary, specialization));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Patient> getPatientById(int id) {
        String sql = "select * from patients where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                String name = result.getString(2);
                String surname = result.getString(3);
                int age = result.getInt(4);
                String address = result.getString(5);
                String phoneNo = result.getString(6);
                String condition = result.getString(7);
                return Optional.of(new Patient(name, surname, age, address, phoneNo, condition));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Supplier> getSupplierById(int id) {
        String sql = "select * from suppliers where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                String name = result.getString(2);
                String location = result.getString(3);
                return Optional.of(new Supplier(name, location));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Medication> getMedicationById(int id) {
        String sql = "select * from medications where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                String name = result.getString(2);
                Double price = result.getDouble(3);
                int idSupplier = result.getInt(4);
                Optional<Supplier> supplier = getSupplierById(idSupplier);
                if (supplier.isPresent()) {
                    return Optional.of(new Medication(name, price, supplier.get()));
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Prescription> getPrescriptionById(int id) {
        String sql = "select * from prescriptions where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                String barCode = result.getString(2);
                Date date = result.getDate(3);
                Medication[] med = new Medication[100];
                int cnt = 0;
                String sql2 = "select id_medication from medtopresc where id_prescription = ?";
                try (PreparedStatement statement1 = DatabaseConnection.getInstance().prepareStatement(sql2)) {
                    statement1.setInt(1, id);
                    ResultSet result1 = statement1.executeQuery();
                    while (result1.next()) {
                        int idMedication = result1.getInt(1);
                        Optional<Medication> medi = getMedicationById(idMedication);
                        if (medi.isPresent()) {
                            med[cnt] = medi.get();
                            ++cnt;
                        }
                    }
                }
                return Optional.of(new Prescription(barCode, date, med));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Appointment> getAppointmentById(int id) {
        String sql = "select * from appointments where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                int idPatient = result.getInt(2);
                int idDoctor = result.getInt(3);
                int idPrescription = result.getInt(4);
                Date date = result.getDate(5);

                Optional<Patient> patient = getPatientById(idPatient);
                Optional<Doctor> doctor = getDoctorById(idDoctor);
                Optional<Prescription> prescription = getPrescriptionById(idPrescription);
                if (patient.isPresent() && doctor.isPresent()) {
                    if (prescription.isPresent()) {
                        return Optional.of(new Appointment(patient.get(), doctor.get(), prescription.get(), date));
                    } else {
                        return Optional.of(new Appointment(patient.get(), doctor.get(), null, date));
                    }
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private int isDoctor (String name, String surname, int age, String address, String phoneNo) {
        String sql = "select id from doctors where name = '" + name + "' && surname = '" + surname + "' && age = "
                + age + " && address = '" + address + "' && phoneNo = '" + phoneNo + "'";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                int id = result.getInt(1);
                return id;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int isPatient (String name, String surname, int age, String address, String phoneNo) {
        String sql = "select id from patients where name = '" + name + "' && surname = '" + surname + "' && age = "
                + age + " && address = '" + address + "' && phoneNo = '" + phoneNo + "'";
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                int id = result.getInt(1);
                return id;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Set<Person> getAllPeople () {
        String sql = "select * from persons";
        Set<Person> people = new TreeSet<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                String name = result.getString(2);
                String surname = result.getString(3);
                int age = result.getInt(4);
                String address = result.getString(5);
                String phoneNo = result.getString(6);
                int id = isDoctor(name, surname, age, address, phoneNo);
                if (id == -1) {
                    id = isPatient(name, surname, age, address, phoneNo);
                    Optional<Patient> patient = getPatientById(id);
                    if (patient.isPresent()) {
                        people.add(patient.get());
                    }
                } else {
                    Optional<Doctor> doctor = getDoctorById(id);
                    if (doctor.isPresent()) {
                        people.add(doctor.get());
                    }
                }
            }
            return people;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<Doctor> getAllDoctors () {
        String sql = "select id from doctors";
        Set<Doctor> docs = new TreeSet<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                int id = result.getInt(1);
                Optional<Doctor> doc = getDoctorById(id);
                if (doc.isPresent()) {
                    docs.add(doc.get());
                }
            }
            return docs;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<Patient> getAllPatients () {
        String sql = "select id from patients";
        Set<Patient> patients = new TreeSet<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                int id = result.getInt(1);
                Optional<Patient> pat = getPatientById(id);
                if (pat.isPresent()) {
                    patients.add(pat.get());
                }
            }
            return patients;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Supplier> getAllSuppliers () {
        String sql = "select * from suppliers";
        List<Supplier> suppliers = new ArrayList<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                int id = result.getInt(1);
                Optional<Supplier> sup = getSupplierById(id);
                if (sup.isPresent()) {
                    suppliers.add(sup.get());
                }
            }
            return suppliers;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Medication> getAllMedications () {
        String sql = "select * from medications";
        List<Medication> meds = new ArrayList<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                int id = result.getInt(1);
                Optional<Medication> med = getMedicationById(id);
                if (med.isPresent()) {
                    meds.add(med.get());
                }
            }
            return meds;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Prescription> getAllPrescriptions () {
        String sql = "select * from prescriptions";
        List<Prescription> prescriptions = new ArrayList<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                int id = result.getInt(1);
                Optional<Prescription> presc = getPrescriptionById(id);
                if (presc.isPresent()) {
                    prescriptions.add(presc.get());
                }
            }
            return prescriptions;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Appointment> getAllAppointments () {
        String sql = "select * from appointments";
        List<Appointment> appointments = new ArrayList<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                int id = result.getInt(1);
                Optional<Appointment> appointment = getAppointmentById(id);
                if (appointment.isPresent()) {
                    appointments.add(appointment.get());
                }
            }
            return appointments;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
