package com.example.demo.book;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getByCode(Integer code) { return bookRepository.findByCode(code);
    }
    public void save(Book book) {
        if (bookRepository.findByCode(book.getCode()).isPresent()){
            throw new IllegalStateException(" another book with same code already exists");
        }

        bookRepository.save(book);
    }
}
