package de.thi.winfo.soap;

import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoapConfig {
    @Autowired
    private Bus cxfBus;

    @Bean
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean<>(new CXFServlet(), "/soap/*");
    }

    @Bean
    public Endpoint studentGetAllEndpoint(StudentWebService studentWebService) {
        EndpointImpl endpoint = new EndpointImpl(cxfBus, studentWebService);
        endpoint.publish("/students");
        return endpoint;
    }

}
