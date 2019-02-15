package mng.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author caopeihe
 */
@Controller
@RequestMapping(value = "error")
public class BaseErrorController implements ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(BaseErrorController.class);

    @RequestMapping
    public String handleError(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == HttpStatus.UNAUTHORIZED.value()) {
            return "error/401";
        }else if(statusCode == HttpStatus.NOT_FOUND.value()){
            return "error/404";
        }else if(statusCode == HttpStatus.FORBIDDEN.value()){
            return "error/403";
        }else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
            return "error/500";
        }
        return "error";
    }
    @Override
    public String getErrorPath() {
        logger.info("出错啦！进入自定义错误控制器");
        return "/error";
    }
}
