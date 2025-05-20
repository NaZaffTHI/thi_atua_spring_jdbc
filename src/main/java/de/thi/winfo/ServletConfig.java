package de.thi.winfo;

import de.thi.winfo.servlet.CreateStudentServlet;
import de.thi.winfo.servlet.HelloServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<HelloServlet> helloServlet() {
        return new ServletRegistrationBean<>(new HelloServlet(), "/hello");
    }
}