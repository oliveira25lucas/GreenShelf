package br.com.oliveira25lucas.greenShelf.controllers;

import br.com.oliveira25lucas.greenShelf.model.Person;
import br.com.oliveira25lucas.greenShelf.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController   {

    @Autowired
    private PersonService personService;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Person> findAll(){
        return personService.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person findById(@PathVariable("id") Long id){
        return personService.findById(id);
    }
    
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person createPerson(@RequestBody Person person){
        return personService.createPerson(person);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person updatePerson(@RequestBody Person person){
        return personService.updatePerson(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") Long id){
         personService.deletePerson(id);
         return ResponseEntity.noContent().build();
    }


}
