package service;

import model.MedicalOffice;
import model.Patient;
import model.Prescription;
import model.Supplier;

public class SupplierService {

    private static SupplierService INSTANCE;

    private SupplierService () {}

    public static SupplierService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SupplierService();
        }
        return INSTANCE;
    }


    public void addSupplier (MedicalOffice medicalOffice, Supplier supplier) {
        //int nextAvailableIndex = getNumberOfSuppliers(medicalOffice);
        medicalOffice.getSuppliers().add(supplier);
    }

    public void updateName (Supplier supplier, String name) { supplier.setName(name); }

    public void updateLocation (Supplier supplier, String location) { supplier.setLocation(location); }

    private int getNumberOfSuppliers(MedicalOffice medicalOffice) {
        int numberOfSuppliers = 0;
        for (Supplier s : medicalOffice.getSuppliers())
            if (s != null) {
                numberOfSuppliers++;
            }
        return numberOfSuppliers;
    }

    public Supplier searchSupplierByName (MedicalOffice medicalOffice, String name) {
        for (Supplier s : medicalOffice.getSuppliers())
            if (s.getName().equals(name)) {
                return s;
            }
        return null;
    }

    public void printSuppliers(MedicalOffice medicalOffice) {
        for (Supplier s : medicalOffice.getSuppliers())
            if (s != null) {
                System.out.println(s);
            }
    }
}
