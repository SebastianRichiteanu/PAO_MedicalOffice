# PAO_MedicalOffice

Classes:

1. DatabaseConnection
2. Main
3. Appointment (pacient, doctor, prescription, date)
4. Doctor (salary, specialization)  extends Person
5. MedicalOffice (people[], doctor[], patient[], medication[], prescription[], appointment[], suppliers[])
6. Medication (name, price, supplier)
7. Patient(condition)  extends Person
8. Person (name, surname, age, address, phone no) 
9. Prescription (barcode, date, medication[])
10. Supplier (name, location)
11. AppointmentRepository
12. DoctorRepository
13. GetRepository
14. MedicationRepository
15. PatientRepository
16. PersonRepository
17. PrescriptionRepository
18. SupplierRepository
19. AppointmentService
20. AuditService
21. DoctorService
22. MedicalOfficeService 
23. MedicationService
24. PatientService
25. PersonService
26. PrescriptionService
27. ReadService
28. SupplierService
29. WriteService


DB tables:
1. Appointments(id, id_patient, id_doctor, id_prescription, date)
2. Doctors(id, name, surname, age, address, phoneNo, salary, specialization)
3. Medications(id, name, price, id_supplier)
4. MedToPresc(id_medication, id_prescription) // To treat Many-To-Many relationship between medication and prescription
5. Patients(id, name, surname, age, address, phoneNo, condition)
6. Persons(id, name, surname, age, address, phoneNo)
7. Prescriptions(id, barcode, date)
8. Suppliers(id, name, location)
