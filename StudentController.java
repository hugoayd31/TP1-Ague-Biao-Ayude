package com.example.demo.student;

import com.example.demo.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path= "/student")
public class StudentController {

    public final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudent() {
        return studentService.getStudent();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/students/email/{email}")
    public Student getStudentByEmail(@PathVariable String email) {
        return studentService.getStudentByEmail(email);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/students/age/{age}")
    public List<Student> getStudentsByAge(@PathVariable Integer age) {
        return studentService.getStudentsByAge(age);
    }

    @PostMapping(path = "/students/create")
    public void creatStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/students/modify/{email}")
    public void modifyStudent(@PathVariable String email, @RequestParam String firstName ) {
        studentService.modifyStudent(email, firstName);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/students/modifyAge")
    public List<Student> modifyStudentAge() {
     return   studentService.modifyStudentsAge();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/students/older20")
    public List<Student> getOlderThan20() {
        return studentService.getStudentsOlderThan20();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/students/add-book/{code}")
    public void addBook (@PathVariable Integer code, @RequestParam String email){
        studentService.addNewBook(code, email);
    }

}
