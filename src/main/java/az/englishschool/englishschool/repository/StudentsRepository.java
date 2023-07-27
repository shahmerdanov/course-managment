package az.englishschool.englishschool.repository;

import az.englishschool.englishschool.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentsRepository extends JpaRepository <Students,Long>{
    List <Students>findByNameAndSurname(String name,String surname);
}
