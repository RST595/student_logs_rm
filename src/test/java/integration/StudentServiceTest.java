package integration;

import com.aston.dto.request.StudentRequestDTO;
import com.aston.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StudentServiceTest {

    @Test
    void addStudentAndExpectOk() {
        StudentService service = Mockito.mock(StudentService.class);
        ResponseEntity<String> message = new ResponseEntity<>("Student added", HttpStatus.OK);
        StudentRequestDTO student = new StudentRequestDTO("Ivan", "Ivanov");
        Mockito.doReturn(message).when(service).addNewStudent(student);
        assertEquals(message, service.addNewStudent(student));
    }
}
