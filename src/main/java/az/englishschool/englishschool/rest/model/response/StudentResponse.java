package az.englishschool.englishschool.rest.model.response;

import az.englishschool.englishschool.rest.model.Dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    private List <StudentDto> studentsDTOS;
}
