package az.englishschool.englishschool.service.impl;

import az.englishschool.englishschool.exception.StudentRestException;
import az.englishschool.englishschool.model.Students;
import az.englishschool.englishschool.repository.StudentsRepository;
import az.englishschool.englishschool.rest.model.Dto.StudentDto;
import az.englishschool.englishschool.rest.model.response.StudentResponse;
import az.englishschool.englishschool.service.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentsServiceImpl implements StudentsService {

    private final StudentsRepository studentsRepository;

    @Override
    public StudentResponse getAllStudents() {
        List<StudentDto> studentsDTOList = studentsRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return makeStudentResponse(studentsDTOList);
    }

    @Override
    public StudentDto getStudent(long id) {
        return studentsRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new StudentRestException("No such student found"));
    }

    @Override
    public StudentResponse getStudentByNameAndSurname(String name, String surname) {
        List<StudentDto> students = studentsRepository.findByNameAndSurname(name, surname)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return makeStudentResponse(students);
    }

    @Override
    public void insertStudents(StudentDto studentsDTO) {
        Students students = new Students();
        BeanUtils.copyProperties(studentsDTO, students);
        studentsRepository.save(students);
    }

    @Override
    public void updateStudents(StudentDto studentsDTO, long id) {
        Students students = getStudentById(id);
        BeanUtils.copyProperties(studentsDTO, students);
        studentsRepository.save(students);
    }

    @Override
    public void updateSomeStudents(StudentDto studentsDTO, long id) {
        Students students = getStudentById(id);

        if (studentsDTO.getName() != null) students.setName(studentsDTO.getName());
        if (studentsDTO.getSurname() != null) students.setSurname(studentsDTO.getSurname());
        if (studentsDTO.getAge() > 0) students.setAge(studentsDTO.getAge());
        if (studentsDTO.getGroupName() != null) students.setGroupName(studentsDTO.getGroupName());

        studentsRepository.save(students);
    }

    @Override
    public void delete(long id) {
        Students students = getStudentById(id);
        studentsRepository.delete(students);
    }

    private Students getStudentById(long id) {
        return studentsRepository.findById(id)
                .orElseThrow(() -> new StudentRestException("Student is Not Found"));
    }

    private StudentDto convertToDto(Students students) {
        StudentDto studentsDTO = new StudentDto();
        BeanUtils.copyProperties(students, studentsDTO);
        return studentsDTO;
    }

    private StudentResponse makeStudentResponse(List<StudentDto> students) {
        return StudentResponse.builder()
                .studentsDTOS(students)
                .build();
    }
}
