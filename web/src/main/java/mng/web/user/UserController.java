package mng.web.user;

import mng.model.user.FundRole;
import mng.model.user.FundUser;
import mng.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import mng.service.user.UserService;

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

    @GetMapping("/update")
    public String update(ModelMap model) {
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        FundUser user = userService.findFundUserByName(username);
        model.put("user", user);
        return "user/update";
    }

    @RequestMapping(value = "/userUpdate", method = RequestMethod.POST)
    public String userUpdate(FundUser user){
        FundUser fundUser = userService.findById(user.getId());
        fundUser.setEmail(user.getEmail());
        fundUser.setAuthCode(Util.aesEncode(user.getAuthCode()));
        fundUser = userService.onlySave(fundUser);
        if(fundUser != null && fundUser.getId() != null){
            return "home";
        }else{
            return "login";
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
