package com.example.demo.student;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import com.example.demo.book.Book;
import lombok.ToString;


import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "age")
    private Integer age;

    @JsonManagedReference
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch =
            FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Book> books;

    public Student(int id, String firstName, String lastName, String email, int age) {
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.age=age;

    }

    public Student(){ }

    public Student(String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", last name='" + lastName + '\'' +
                ", first name='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

    public void setBooks( List<Book> l ){
        books = l;
    }
}
