package integration;

import com.aston.dto.request.StudentRequestDTO;
import com.aston.service.StudentService;
import com.aston.dto.response.ResponseMessageDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StudentServiceTest {

    @Test
    void addStudentAndExpectOk() {
        StudentService service = Mockito.mock(StudentService.class);
        ResponseMessageDTO message = new ResponseMessageDTO(true, "Student added");
        StudentRequestDTO student = new StudentRequestDTO("Ivan", "Ivanov");
        Mockito.doReturn(message).when(service).addNewStudent(student);
        assertEquals(message, service.addNewStudent(student));
    }
}
