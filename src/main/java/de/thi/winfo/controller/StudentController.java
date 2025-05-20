package de.thi.winfo.controller;

import de.thi.winfo.model.Student;
import de.thi.winfo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Set<Student> getAll() {
        return this.studentService.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable String id) {
        return this.studentService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Student studentRequest) throws URISyntaxException {
        Student student = new Student(studentRequest.getFirstName(), studentRequest.getLastName(), studentRequest.getDateOfBirth());
        studentService.save(student);
        return ResponseEntity.created(new URI("localhost:8080/api/students/" + student.getId())).build();
    }
}
