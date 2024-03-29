package com.example.demo.student;

import com.example.demo.book.BookRepository;
import com.example.demo.book.Book;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, BookRepository bookRepository) {
        this.studentRepository = studentRepository;
        this.bookRepository = bookRepository;

    }

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student s){
        if (studentRepository.findStudentByEmail(s.getEmail()).isPresent()){
            throw new IllegalStateException(" another student with same email already exists");
        }
        studentRepository.save(s);
    }

    public Student getStudentByEmail(String email) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);

        if (studentByEmail.isEmpty()) {
            throw new IllegalStateException(" no student found ");
        }
        return studentByEmail.get();
    }

    public List<Student> getStudentsByAge(Integer age) {
        return studentRepository.findAllByAge(age);
    }

    @Transactional
    public void modifyStudent(String email, String firstName) {
        Student s = studentRepository.findStudentByEmail(email).orElseThrow(()
                -> new IllegalStateException(" student with email " + email + " does not exists"));

        if (!firstName.isBlank() & !firstName.equals(s.getFirstName() )  ) {
            s.setFirstName(firstName);
        }


    }

    @Transactional
    public List<Student> modifyStudentsAge() {
        List<Student> studentList = studentRepository.findAll();
        if (studentList.isEmpty()) {
            throw new IllegalStateException("There is no student in your database");
        }
        for (Student s : studentList) {
            s.setAge(s.getAge() + 1);
        }
        return studentList;
    }

    public List<Student> getStudentsOlderThan20() {
        return studentRepository.findAllByAgeGreaterThan20();

    }

    /*@Transactional
    public void setBookList(String email , List<Book> list){
        Student s = this.getStudentByEmail(email);
        s.setBooks(list);
    }*/

    public void addNewBook( String email, Integer code) {
        List<Book> l = new ArrayList<>();
        Optional <Book> b = bookRepository.findByCode(code);
        if (b.isEmpty()){
            throw new IllegalStateException(" there is no book with the code "+ code + "in book table");
        }

        Student s = this.getStudentByEmail(email);
        b.get().setStudent(s);
        l = s.getBooks();
        System.out.println(l);
        System.out.println(b.get());
        l.add(b.get());
        System.out.println(l);
        s.setBooks(l);
        studentRepository.save(s);
        System.out.println(s.getBooks());

    }

    public void setBooks( String email, List<Integer> code) {
        List<Book> l = new ArrayList<>();

        Student s = this.getStudentByEmail(email);
        for(Integer i : code ) {
            Optional <Book> b = bookRepository.findByCode(i);
            if (b.isEmpty()){
                throw new IllegalStateException(" there is no book with the code "+ code + "in book table");
            }
            b.get().setStudent(s);
            l.add(b.get());
        }




        System.out.println(l);
        s.setBooks(l);
        studentRepository.save(s);
        System.out.println(s.getBooks());

    }
}
