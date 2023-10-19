package Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudents() {
    return studentRepository.findAll();
    }

    public Optional<Student> getByEmail(String email){
        return studentRepository.findByEmail(email);
    }

    public List<Student> getByAge(Integer age){
        return studentRepository.findByAge(age);
    }

    public void modifyStudent (String email, String firstName){
       Optional <Student> student ;
       student=getByEmail(email);
       if (student.isPresent())
       {
           student.get().setFirstName(firstName);
           studentRepository.save(student.get());
       }
       else
       {
           System.out.println("L'étudiant n'existe pas");
       }
    }

    public void modifyAge (){
        List <Student> L ;
        L = studentRepository.findAll();
        

    }


    public void save(Student student) {
        studentRepository.save(student);
    }
}
