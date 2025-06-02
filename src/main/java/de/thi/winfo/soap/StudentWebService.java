package de.thi.winfo.soap;

import de.thi.winfo.model.Student;
import de.thi.winfo.service.StudentService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@WebService
public class StudentWebService {

    private final StudentService studentService;

    public StudentWebService(StudentService studentService) {
        this.studentService = studentService;
    }

    @WebMethod
    public Set<Student> getAll() {
        return this.studentService.getAll();
    }

    @WebMethod
    public Student getById(String id) {
        return this.studentService.getById(id);
    }

    @WebMethod
    public String create(Student studentRequest) {
        Student student = new Student(studentRequest.getFirstName(), studentRequest.getLastName(), studentRequest.getDateOfBirth());
        studentService.save(student);
        return student.getId();
    }
}
