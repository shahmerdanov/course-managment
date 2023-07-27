package az.englishschool.englishschool.rest.model.Dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDto {
    String name;
    String surname;
    String groupName;
    String email;
    int age;
}
