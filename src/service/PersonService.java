package service;

import model.MedicalOffice;
import model.Person;

public class PersonService {
    public void addPerson (MedicalOffice medicalOffice, Person person) {
        int nextAvailableIndex = getNumberOfPeople(medicalOffice);
        medicalOffice.getPeople()[nextAvailableIndex] = person;
    }

    private int getNumberOfPeople(MedicalOffice medicalOffice) {
        int numberOfPeople = 0;
        for (Person p : medicalOffice.getPeople())
            if (p != null)
                numberOfPeople++;
        return numberOfPeople;
    }
}
