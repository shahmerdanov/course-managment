package az.englishschool.englishschool.controller;

import az.englishschool.englishschool.rest.model.Dto.StudentDto;
import az.englishschool.englishschool.rest.model.response.StudentResponse;
import az.englishschool.englishschool.service.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentsController {
    private final StudentsService studentsService;

    @GetMapping
    public ResponseEntity<StudentResponse> getAllStudents() {
        return ResponseEntity.ok(studentsService.getAllStudents());
    }

    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable("id") long id) {
        return studentsService.getStudent(id);
    }

    @GetMapping("/search")
    public StudentResponse getStudentByNameAndSurname(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname) {
        return studentsService.getStudentByNameAndSurname(name, surname);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insertStudents(@RequestBody StudentDto studentsDTO) {
        studentsService.insertStudents(studentsDTO);
    }

    @PutMapping("/{id}")
    public void updateStudents(@RequestBody StudentDto studentsDTO, @PathVariable("id") long id) {
        studentsService.updateStudents(studentsDTO, id);
    }

    @PatchMapping("/{id}")
    public void updateSomeStudents(@RequestBody StudentDto studentsDTO, @PathVariable("id") long id) {
        studentsService.updateSomeStudents(studentsDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable("id") long id) {
        studentsService.delete(id);
    }
}
