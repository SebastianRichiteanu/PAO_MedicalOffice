package service;

import model.*;
import repository.GetRepository;
import repository.SupplierRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SupplierService {

    private static SupplierService INSTANCE;
    private SupplierRepository supplierRepository;
    private GetRepository getRepository;

    private SupplierService () {
        this.supplierRepository = new SupplierRepository();
        this.getRepository = new GetRepository();
    }

    public static SupplierService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SupplierService();
        }
        return INSTANCE;
    }

    public void addSupplier (Supplier supplier) {
        supplierRepository.addSupplier(supplier);
    }

    public void removeSupplier (Supplier supplier) { supplierRepository.removeSupplier(supplier); }

    public void updateName (Supplier supplier, String name) {
        int id = supplierRepository.getSupplierId(supplier);
        if (id == -1) {
            System.out.println("This supplier does not exist!");
        } else {
            supplierRepository.updateSupplierById(id, "name", name);
            supplier.setName(name);
        }
    }

    public void updateLocation (Supplier supplier, String location) {
        int id = supplierRepository.getSupplierId(supplier);
        if (id == -1) {
            System.out.println("This supplier does not exist!");
        } else {
            supplierRepository.updateSupplierById(id, "location", location);
            supplier.setLocation(location);
        }
    }

    public Optional<Supplier> getSupplierById(int id) { return getRepository.getSupplierById(id); }

    private int getNumberOfSuppliers(MedicalOffice medicalOffice) {
        int numberOfSuppliers = 0;
        for (Supplier s : medicalOffice.getSuppliers())
            if (s != null) {
                numberOfSuppliers++;
            }
        return numberOfSuppliers;
    }

    public Supplier searchSupplierByName (String name) {
        int id = supplierRepository.getSupplierIdByName(name);
        if (id == -1) {
            System.out.println("This supplier does not exist");
        } else {
            Optional<Supplier> sup = getRepository.getSupplierById(id);
            if (sup.isPresent()) {
                return sup.get();
            }
        }
        return null;
    }

    public void printSuppliers() {
        List<Supplier> suppliers = getRepository.getAllSuppliers();
        if (suppliers != null) {
            for (Supplier s : suppliers)
                if (s != null) {
                    System.out.println(s);
                }
        }
    }
}
