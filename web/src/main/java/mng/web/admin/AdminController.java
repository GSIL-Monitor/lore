package mng.web.admin;

import com.alibaba.fastjson.JSONObject;
import mng.enums.ApplyState;
import mng.model.funds.ApplyRecord;
import mng.model.funds.FundsRecord;
import mng.model.user.FundRole;
import mng.model.user.FundUser;
import mng.service.funds.ApplyRecordService;
import mng.service.funds.FundsService;
import mng.service.user.FundRoleService;
import mng.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author caopeihe
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private FundsService fundsService;
    private FundRoleService fundRoleService;
    private ApplyRecordService applyRecordService;
    @Autowired
    public AdminController(UserService userService, FundsService fundsService, FundRoleService fundRoleService,ApplyRecordService applyRecordService){
        this.userService = userService;
        this.fundsService = fundsService;
        this.fundRoleService = fundRoleService;
        this.applyRecordService = applyRecordService;
    }

    @GetMapping("index")
    public String index(){
//        return "admin/index";
        return "admin/admin_index";
    }

    @GetMapping("manageUser")
    public String manageUser(ModelMap model){
        List<FundUser> users = userService.findAll();
        List<FundRole> roles = fundRoleService.findAll();
        model.put("users", users);
        model.put("roles", roles);
        return "admin/userManage";
    }

    @RequestMapping("addUser")
    public String addUser(){
        return "admin/addUser";
    }

    @RequestMapping("editUser")
    public String editUser(HttpServletRequest request, ModelMap model){
        String id = request.getParameter("id");
        FundUser user = userService.findById(id);
        model.put("user", user);
        return "admin/addUser";
    }

    @RequestMapping("saveUser")
    public String saveUser(FundUser user){
        userService.save(user);
        return "redirect:/admin/manageUser";
    }

    @RequestMapping("user/delete")
    public String deleteUser(String id){
        userService.deleteUserById(id);
        return "redirect:/admin/manageUser";
    }

    @RequestMapping("/manageFunds")
    public String manageFunds(ModelMap model){
        List<Map<String, String>> listMap = fundsService.getAllUsersFunds();
        model.put("listMap", listMap);
        return "admin/manageFunds";
    }

    @RequestMapping("/manageFundsDetail")
    public String manageFundsDetail(HttpServletRequest request, ModelMap model){
        String userId = request.getParameter("id");
        FundUser user = userService.findById(userId);
        List<ApplyRecord> applys = applyRecordService.findApplyRecordByUserId(user);
        model.put("applys", applys);
        model.put("types", applyRecordService.getApplyTypes());
        model.put("states", applyRecordService.getApplyStates());
        return "admin/eachDetail";
    }

    @RequestMapping("/addFunds")
    public String addFunds(ModelMap model){
        model.put("record", fundsService.findFundsRecordByValid(FundsRecord.RECORD_VALID_YES));
        return "admin/addFunds";
    }

    @RequestMapping("/saveFunds")
    public String saveFunds(FundsRecord record, ModelMap model){
        FundsRecord ret = fundsService.saveFundsRecord(record);
        model.put("record", ret);
        return "admin/addFunds";
    }

    @RequestMapping("/changeState")
    public String changeState(HttpServletRequest request, ModelMap model){
        String userId = request.getParameter("id");
        FundUser user = userService.findById(userId);
        if(user != null){
            fundsService.updateFundsApplyState(user, ApplyState.HADREIMBURSE.getCode());
        }
        return "redirect:/admin/manageFunds";
    }

    @RequestMapping("/changeRole")
    @ResponseBody
    public JSONObject changeRole(@RequestBody JSONObject params){
        JSONObject ret = new JSONObject();
        FundUser user = userService.findById(params.getString("userId"));
        FundRole role = fundRoleService.findById(params.getString("roleId"));
        user.setRole(role);
        user = userService.onlySave(user);
        if(user != null && user.getId() != null){
            ret.fluentPut("success","true");
        }else{
            ret.fluentPut("success","false");
        }
        return ret;
    }
}
