package mng.web;

import mng.common.Constants;
import mng.model.funds.FundsRecord;
import mng.model.user.FundUser;
import mng.service.funds.FundsService;
import mng.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author oac
 */
@Controller
public class LoginController {
    private UserService userService;
    private FundsService fundsService;
    @Autowired
    public LoginController(FundsService fundsService, UserService userService){
        this.fundsService = fundsService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public String base() {
        return "index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login(ModelMap model) {
        return "login";
    }

    @RequestMapping("loginSuccess")
    public String loginSuccess(){
        return "redirect:/home";
    }

    @RequestMapping("/login-error")
    public String loginError(ModelMap model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "user/register";
    }

    @RequestMapping("/logout")
    public String loginOut(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        request.getSession().removeAttribute("user");
        return "login";
    }

    @RequestMapping("/home")
    public String home(HttpServletRequest request, ModelMap model){
        List<Map<String, String>> list = fundsService.getAllUsersFunds();
        double allApply = 0;
        for(Map<String, String> map:list){
            allApply += Double.valueOf(map.get("all"));
        }
        model.put("allApply", allApply);
        FundsRecord record = fundsService.findFundsRecordByValid(FundsRecord.RECORD_VALID_YES);
        if(record != null){
            model.put("funds",record.getMoney());
        }
        addUserSession(request);
        return "home";
    }

    private void addUserSession(HttpServletRequest request){
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        FundUser user = userService.findFundUserByName(username);
        request.getSession().setAttribute("user", user);
    }
}
