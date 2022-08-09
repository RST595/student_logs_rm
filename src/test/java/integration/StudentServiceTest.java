package integration;

import com.aston.dto.StudentRequest;
import com.aston.service.StudentService;
import com.aston.util.ResponseMessage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StudentServiceTest {

    @Test
    void addStudentAndExpectOk() {
        StudentService service = Mockito.mock(StudentService.class);
        ResponseMessage message = new ResponseMessage(true, "Student added");
        StudentRequest student = new StudentRequest("Ivan", "Ivanov");
        Mockito.doReturn(message).when(service).addNewStudent(student);
        assertEquals(message, service.addNewStudent(student));
    }
}
