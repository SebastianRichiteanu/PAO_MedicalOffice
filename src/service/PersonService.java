package service;

import model.*;
import repository.GetRepository;
import repository.PersonRepository;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class PersonService {
    private static PersonService INSTANCE;
    private PersonRepository personRepository;
    private GetRepository getRepository;

    private PersonService () {
        this.personRepository = new PersonRepository();
        this.getRepository = new GetRepository();
    }

    public static PersonService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PersonService();
        }
        return INSTANCE;
    }

    public void addPerson (Person person) {
        personRepository.addPerson(person);
    }

    public void removePerson(Person person) {
        personRepository.removePerson(person);
    }

    public void updateName (Person person, String name) {
        int id = personRepository.getPersonId(person);
        if (id == -1) {
            System.out.println("This person does not exist");
        } else {
            personRepository.updatePersonById(id, "name", name);
        }
    }

    public void updateSurname (Person person, String surname) {
        int id = personRepository.getPersonId(person);
        if (id == -1) {
            System.out.println("This person does not exist");
        } else {
            personRepository.updatePersonById(id, "surname", surname);
        }
    }

    public void updateAge (Person person, int age) {
        int id = personRepository.getPersonId(person);
        if (id == -1) {
            System.out.println("This person does not exist");
        } else {
            personRepository.updatePersonById(id, "age", String.valueOf(age));
        }
    }

    public void updateAddress (Person person, String address) {
        int id = personRepository.getPersonId(person);
        if (id == -1) {
            System.out.println("This person does not exist");
        } else {
            personRepository.updatePersonById(id, "address", address);
        }
    }

    public void updatePhoneNo (Person person, String phoneNo) {
        int id = personRepository.getPersonId(person);
        if (id == -1) {
            System.out.println("This person does not exist");
        } else {
            personRepository.updatePersonById(id, "phoneNo", phoneNo);
        }
    }

    public Person searchPersonByFullName (MedicalOffice medicalOffice, String name, String surname) {
        Person src = null;
        for (Person p : medicalOffice.getPeople())
            if (p != null && p.getName().equals(name) && p.getSurname().equals(surname)) {
                src = p;
            }
        return src;
    }

    public void printPeople() {
        Set<Person> people = getRepository.getAllPeople();
        if (people != null) {
            for (Person p : people)
                if (p != null) {
                    System.out.println(p);
                }
        }
    }

    public void printPeople2(MedicalOffice medicalOffice) {
        for (Person p : medicalOffice.getPeople())
            if (p != null) {
                System.out.println(p);
            }
    }
}
