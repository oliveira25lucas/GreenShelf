package br.com.oliveira25lucas.greenShelf.service;

import br.com.oliveira25lucas.greenShelf.exception.ResourceNotFoundException;
import br.com.oliveira25lucas.greenShelf.model.Person;
import br.com.oliveira25lucas.greenShelf.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {
        logger.info("Finding all People!");

        return repository.findAll();
    }


    public Person findById(Long id) {
        logger.info("Finding one Person! The id is " + id);

        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this id!"));
    }

    public Person createPerson(Person person) {
        logger.info("Creating one person");

        return repository.save(person);
    }

    public Person updatePerson(Person person) {

        logger.info("Update one person");
        Person entity = repository.findById(person.getId())
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this id!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void deletePerson(Long id) {
        logger.info("Deleting one Person! The id is " + id);

        Person entity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this id!"));

        repository.delete(entity);
    }
}
