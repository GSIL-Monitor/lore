package mng.web.process;

//import org.activiti.api.task.runtime.TaskRuntime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.regex.Pattern;

/**
 * @author caopeihe
 */
@Controller
@RequestMapping("/process")
public class Process {
    /*@Autowired
    private TaskRuntime taskRuntime;

    public static void main(String[] args){
        String pattern = "application-[a-zA-Z0-9]+\\.properties";
        System.out.println("application-.properties".matches(pattern));
    }*/

}
