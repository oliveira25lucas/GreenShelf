package br.com.oliveira25lucas.greenShelf.repository;

import br.com.oliveira25lucas.greenShelf.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
