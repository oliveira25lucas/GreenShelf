package br.com.oliveira25lucas.greenShelf.service;

import br.com.oliveira25lucas.greenShelf.controllers.docs.BookControllerImpl;
import br.com.oliveira25lucas.greenShelf.data.dto.BookDTO;
import br.com.oliveira25lucas.greenShelf.exception.RequiredObjectIsNullException;
import br.com.oliveira25lucas.greenShelf.exception.ResourceNotFoundException;
import br.com.oliveira25lucas.greenShelf.model.Book;
import br.com.oliveira25lucas.greenShelf.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.oliveira25lucas.greenShelf.mapper.ObjectMapper.parseListObjects;
import static br.com.oliveira25lucas.greenShelf.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    private Logger logger = LoggerFactory.getLogger(BookService.class.getName());

    @Autowired
    BookRepository repository;


    public List<BookDTO> findAll() {

        logger.info("Finding all People!");

        var books = parseListObjects(repository.findAll(), BookDTO.class);
        books.forEach(this::addHateoasLinks);
        return books;
    }

    public BookDTO findById(Long id) {
        logger.info("Finding one Book!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var dto = parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO create(BookDTO book) {

        if(book == null){
            throw new RequiredObjectIsNullException();
        }

        logger.info("Creating one Book!");
        var entity = parseObject(book, Book.class);

        var dto =  parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO update(BookDTO book) {

        if(book == null){
            throw new RequiredObjectIsNullException();
        }

        logger.info("Updating one Book!");
        Book entity = repository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {

        logger.info("Deleting one Book!");

        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookControllerImpl.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookControllerImpl.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookControllerImpl.class).findAll()).withRel("findALl").withType("GET"));
        dto.add(linkTo(methodOn(BookControllerImpl.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookControllerImpl.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}