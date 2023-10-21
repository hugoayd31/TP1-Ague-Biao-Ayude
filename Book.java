package com.example.demo.book;
import com.example.demo.student.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "book")
@Data
public class Book {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private Integer code;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "student_id")
    @EqualsAndHashCode.Exclude
    @ToString.Include
    private Student student;


    public Book(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public Book(Integer id, String name, Integer code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Book() {

    }

    @Override
    public String toString(){
        return " name "+ name + "\t code = "+code;
    }




