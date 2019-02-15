package mng;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class HelloWebXml extends SpringBootServletInitializer {
    @Override  
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FundsApplication.class);
    }  
  
} 
