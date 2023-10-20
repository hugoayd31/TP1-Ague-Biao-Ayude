package Book;

import Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    public List<Book> findAll();
    public Optional<Book> findById(Integer id);
    public Optional<Book> findByCode(Integer code);

    public Optional<Book> findByName(String name);

   // @Query(
     //       value = "SELECT * FROM student u WHERE u.age > 20",
       //     nativeQuery = true)
    //public List<Student> findByAge(Integer age);

}
