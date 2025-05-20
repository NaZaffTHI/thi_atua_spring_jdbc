package de.thi.winfo.service;

import de.thi.winfo.model.Student;
import de.thi.winfo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Set<Student> getAll() {
        return studentRepository.getAll();
    }

    public Student getById(String id) {
        return studentRepository.getById(id);
    }

    public void save(Student student) {
        studentRepository.save(student);
    }
}
