package de.thi.winfo.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<body>");
        out.println("<h3>Servlet zur Ausgabe des aktuellen Datums und der aktuellen Uhrzeit</h3>");
        out.println("Datum: <b>" + new Date() + "/b");
        out.println("</body>");
        out.println("</html>");
    }
}
