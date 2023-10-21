package com.example.demo.book;

import com.example.demo.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="book")
public class BookController {

    private final BookService bookservice;

    @Autowired
    public BookController(BookService bookservice) {
        this.bookservice = bookservice;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookservice.getBooks();
    }

    @RequestMapping(method=RequestMethod.GET, value="/books/{code}")
    public Optional<Book> getBookByCode(@PathVariable Integer code) {return bookservice.getByCode(code);}


    @RequestMapping(method=RequestMethod.POST, value="/books/create")
    public void  createBook( @RequestBody Book book){
        bookservice.save(book);}

}

