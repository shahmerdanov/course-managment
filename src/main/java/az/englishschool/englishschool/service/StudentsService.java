package az.englishschool.englishschool.service;

import az.englishschool.englishschool.rest.model.Dto.StudentDto;
import az.englishschool.englishschool.rest.model.response.StudentResponse;

public interface StudentsService {

    StudentResponse getAllStudents();
    StudentDto getStudent (long id);
    StudentResponse getStudentByNameAndSurname (String name, String surname);
    void insertStudents(StudentDto studentsDTO);
    void updateStudents(StudentDto studentsDTO, long id);

    void updateSomeStudents(StudentDto studentsDTO, long id);
    void delete(long id);
}
