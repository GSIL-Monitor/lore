package mng.web.funds;

import mng.model.funds.ApplyRecord;
import mng.model.user.FundRole;
import mng.model.user.FundUser;
import mng.service.funds.ApplyRecordService;
import mng.service.user.FundRoleService;
import mng.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author oac
 */
@Controller
@RequestMapping("/funds")
public class FundsController {
    private Logger logger = LoggerFactory.getLogger(FundsController.class);

    private UserService userService;
    private FundRoleService roleService;
    private ApplyRecordService applyRecordService;
    public FundsController(UserService userService,ApplyRecordService applyRecordService, FundRoleService roleService){
        this.userService = userService;
        this.applyRecordService = applyRecordService;
        this.roleService = roleService;
    }

    @GetMapping("/index")
    public String index(ModelMap model) {
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        FundUser user = userService.findFundUserByName(username);
        List<ApplyRecord> applyRecords = applyRecordService.findApplyRecordByUserId(user);
        Map<String, Double> statMap = applyRecordService.getStatList(user);
        model.put("statMap", statMap);
        model.put("applys", applyRecords);
        model.put("types", applyRecordService.getApplyTypes());
        model.put("states", applyRecordService.getApplyStates());
        return "funds/index";
    }

    @GetMapping("/apply/add")
    public String applyAdd(ModelMap model){
        model.put("types", applyRecordService.getApplyTypes());
        model.put("states", applyRecordService.getApplyStates());
        List<FundRole> roles = roleService.findByIds(Arrays.asList(FundRole.ROLE_USER,FundRole.ROLE_SYSUSER));
        if(roles != null){
            List<FundUser> users = userService.findAllByRoles(roles);
            model.put("users", users);
        }
        return "funds/addApply";
    }

    @GetMapping("/apply/edit")
    public String applyEdit(HttpServletRequest request, ModelMap model){
        String id = request.getParameter("id");
        model.put("apply", applyRecordService.findApplayRecordById(id));
        model.put("types", applyRecordService.getApplyTypes());
        model.put("states", applyRecordService.getApplyStates());
        FundRole role = roleService.findById(String.valueOf(FundRole.ROLE_USER));
        if(role != null){
            List<FundUser> users = userService.findAllByRole(role);
            model.put("users", users);
        }
        return "funds/addApply";
    }

    @RequestMapping("/apply/save")
    public String applySave(HttpServletRequest request,ApplyRecord applyRecord){
        ApplyRecord apply = applyRecordService.save(applyRecord);
        if(apply != null && apply.getId() != null){
            return "redirect:/funds/index";
        }else{
            return "redirect:/funds/apply/add";
        }
    }

    @RequestMapping("/apply/delete")
    public String applyDelete(HttpServletRequest request, String id){
        ApplyRecord apply = applyRecordService.findApplayRecordById(id);
        applyRecordService.deleteApplyRecord(apply);
        return "redirect:/funds/index";
    }
}
