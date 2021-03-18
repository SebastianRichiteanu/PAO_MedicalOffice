package service;

import model.MedicalOffice;
import model.Person;

public class PersonService {
    public void addPerson (MedicalOffice medicalOffice, Person person) {
        int nextAvailableIndex = getNumberOfPeople(medicalOffice);
        medicalOffice.getPeople()[nextAvailableIndex] = person;
    }

    public void updateName (Person person, String name) {
        person.setName(name);
    }

    public void updateSurname (Person person, String surname) {
        person.setSurname(surname);
    }

    public void updateAge (Person person, int age) {
        person.setAge(age);
    }

    public void updateAddress (Person person, String address) {
        person.setAddress(address);
    }

    public void updatePhonNo (Person person, String phoneNo) {
        person.setPhoneNo(phoneNo);
    }

    public Person searchPersonByFullName (MedicalOffice medicalOffice, String name, String surname) {
        Person src = null;
        for (Person p : medicalOffice.getPeople())
            if (p != null && p.getName().equals(name) && p.getSurname().equals(surname))
                src = p;
        return src;
    }
    private int getNumberOfPeople(MedicalOffice medicalOffice) {
        int numberOfPeople = 0;
        for (Person p : medicalOffice.getPeople())
            if (p != null)
                numberOfPeople++;
        return numberOfPeople;
    }
}
