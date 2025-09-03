package br.com.oliveira25lucas.greenShelf.service;

import br.com.oliveira25lucas.greenShelf.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonService.class.getName());


    public List<Person> findAll() {
        logger.info("Finding all People!");
        List<Person> persons = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            Person person =  mockPerson(i);
            persons.add(person);
        }
        return persons;
    }


    public Person findById(String id) {
        logger.info("Finding one Person! The id is " + id);

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Lucas");
        person.setLastName("Oliveira");
        person.setAddress("Belo Horizonte - MG");
        person.setGender("Male");
        return person;
    }

    public Person createPerson(Person person) {
        logger.info("Creating one person");

        return person;
    }

    public Person updatePerson(Person person) {
        logger.info("Update one person");

        return person;
    }

    public void deletePerson(String id) {
        logger.info("Deleting one Person! The id is " + id);
    }

        private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("First Name " + i);
        person.setLastName("Last Name " + i);
        person.setAddress("City " + i);
        person.setGender("Gender " + i);
        return person;
    }
}
