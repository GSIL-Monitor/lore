package mng.web.user;

import mng.model.user.FundRole;
import mng.model.user.FundUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import mng.service.user.UserService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author caopeihe
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/registerSave", method = RequestMethod.POST)
    public String register(HttpServletRequest request, FundUser user, ModelMap model){
        FundUser fundUser = userService.save(user);
        if(fundUser != null && fundUser.getId() != null){
            model.put("regSuccess", true);
            return "login";
        }else{
            return "user/register";
        }
    }

    /**
     * 个人中心
     */
    @PreAuthorize("hasAuthority('UserIndex')")
    @GetMapping("/index")
    public String index() {
        return "user/index";
    }
}
