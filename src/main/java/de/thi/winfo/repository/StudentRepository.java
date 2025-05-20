package de.thi.winfo.repository;

import de.thi.winfo.model.Student;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/*
In this example only getAll and save is implemented.
Usually there is also getById, delete, update or getByLastName etc.
Error handling is not really implemented here, to keep this example lean.
 */
@Component
public class StudentRepository {
    private final DatabaseConnector dbConnector;

    public StudentRepository(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public Set<Student> getAll() {
        Set<Student> students = new HashSet<>();

        String query = "SELECT * FROM student;";
        try (PreparedStatement statement = dbConnector.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Student student = extractFromResultSet(resultSet);
                students.add(student);
            }
        } catch (SQLException e) {
            System.err.println("Connection to database failed. Try again later");
        }
        return students;
    }

    public Student getById(String id) {
        Student student = null;

        String query = "SELECT * FROM student WHERE id = ?;";
        try (PreparedStatement statement = dbConnector.getConnection().prepareStatement(query)) {
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();
            student = extractFromResultSet(resultSet);

        } catch (SQLException e) {
            System.err.println("Connection to database failed. Try again later");
        }
        return student;
    }

    public void save(Student student) {
        String query = "INSERT INTO student (id, first_name, last_name, date_of_birth) VALUES (?, ?, ?, ?);";

        try (PreparedStatement statement = dbConnector.getConnection().prepareStatement(query)) {
            statement.setString(1, student.getId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
            statement.setString(4, student.getDateOfBirth().format(formatter));

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Connection to database failed. Try again later");
        }
    }

    private Student extractFromResultSet(ResultSet resultSet) {
        try {
            String id = resultSet.getString("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
            LocalDate dateOfBirth = LocalDate.parse(resultSet.getString("date_of_birth"), formatter);

            return new Student(id, firstName, lastName, dateOfBirth);
        } catch (SQLException e) {
            System.err.println("Could not extract student from resultSet");
            throw new RuntimeException(e);
        }
    }
}
