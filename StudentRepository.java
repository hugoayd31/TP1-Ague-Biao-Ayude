package Student;

import Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    public List<Student> findAll();
    public Optional<Student> findById(Integer id);
    public Optional<Student> findByEmail(String email);
    public Optional<Student> findByFirstNameAndLastName(String firstName,
                                                        String lastName);
    @Query(
            value = "SELECT * FROM student u WHERE u.age > 20",
            nativeQuery = true)
    public List<Student> findByAge(Integer age);

}