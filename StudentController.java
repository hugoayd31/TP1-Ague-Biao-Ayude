package Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="student")
public class StudentController {

    private final StudentService studentservice;

@Autowired
    public StudentController(StudentService studentservice) {
        this.studentservice = studentservice;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentservice.getStudents();
    }

    @RequestMapping(method=RequestMethod.GET, value="/students/{email}")
    public Optional<Student> getStudentByEmail(@PathVariable String email) {return studentservice.getByEmail(email);}

    @RequestMapping(method=RequestMethod.GET, value="/students/{age}")
    public List<Student> getStudentsByAge(@PathVariable Integer age) {
        return studentservice.getByAge(age);
    }

    @RequestMapping(method=RequestMethod.POST, value="/students")
    public void  save( @RequestBody Student s){
        studentservice.save(s);}

    @RequestMapping(method=RequestMethod.PUT, value="/students/{email}{firstName}")
    public void modifyStudent (@PathVariable String email, @PathVariable String firstName ){
        studentservice.modifyStudent(email, firstName);
    }
}
