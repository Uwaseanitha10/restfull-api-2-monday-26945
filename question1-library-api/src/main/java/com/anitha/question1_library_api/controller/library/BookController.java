package com.anitha.question1_library_api.controller.library;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anitha.question1_library_api.model.library.Book;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    private List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book(1L, "Clean Code", "Robert Martin", "978-0132350884", 2008));
        books.add(new Book(2L, "The Pragmatic Programmer", "Andrew Hunt", "978-0135957059", 1999));
        books.add(new Book(3L, "Refactoring", "Martin Fowler", "978-0134757599", 2018));
    }

    // Get request for all books
    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    // get request for a particular book using the book id 
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        return books.stream()
        .filter(b -> b.getId().equals(id))
        .findFirst()
        .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // request to search 
    @GetMapping("/search")
    public List<Book> searchByTitle(@RequestParam String title){
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    // end point for adding books 
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        books.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // endpont to delete books 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean removed = books.removeIf(b -> b.getId().equals(id));
        return removed ? new ResponseEntity<>(HttpStatus.NO_CONTENT) 
                       : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
