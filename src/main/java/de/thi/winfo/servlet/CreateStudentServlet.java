package de.thi.winfo.servlet;

import de.thi.winfo.model.Student;
import de.thi.winfo.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "StudentServlet", urlPatterns = "/create-student")
public class CreateStudentServlet extends HttpServlet {

    @Autowired
    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        System.out.println("Servlet init done");
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        LocalDate dateOfBirth = LocalDate.parse(req.getParameter("dateOfBirth"));

        Student student = new Student(firstName, lastName, dateOfBirth);
        studentService.save(student);

        req.setAttribute("student", student);
        req.getRequestDispatcher("/WEB-INF/createStudentResult.jsp").forward(req, resp);
    }
}