package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }


    public Student getStudentByEmail(String email) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);

        if (studentByEmail.isEmpty()) {
            throw new IllegalStateException(" no student find ");
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

        if (!firstName.isBlank() & !firstName.equals(s.getFirstName())) {
            s.setFirstName(firstName);
            //studentRepository.save(s);
        }


    }

    @Transactional
    public void modifyStudentsAge(int age) {
        List<Student> studentList = studentRepository.findAllByAge(age);
        if (studentList.isEmpty()) {
            throw new IllegalStateException("student with age greater than " + age + " do not exist");
        }
        for (Student s : studentList) {
            s.setAge(s.getAge() + 1);
        }

    }


}
