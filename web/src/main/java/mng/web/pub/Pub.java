package mng.web.pub;

import com.alibaba.fastjson.JSONObject;
import mng.model.funds.ApplyRecord;
import mng.model.user.FundRole;
import mng.model.user.FundUser;
import mng.service.funds.ApplyRecordService;
import mng.service.funds.FundsService;
import mng.service.user.FundRoleService;
import mng.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author caopeihe
 */
@Controller
@RequestMapping("/pub")
public class Pub {
    private FundsService fundsService;
    private FundRoleService roleService;
    private UserService userService;
    private ApplyRecordService applyRecordService;
    @Autowired
    public Pub(FundsService fundsService,ApplyRecordService applyRecordService, UserService userService,FundRoleService roleService){
        this.fundsService = fundsService;
        this.applyRecordService = applyRecordService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @ResponseBody
    @RequestMapping("/changeAnother")
    public JSONObject changeAnother(){
        JSONObject ret = new JSONObject();
        List<FundRole> roles = roleService.findByIds(Arrays.asList(FundRole.ROLE_USER,FundRole.ROLE_SYSUSER));
        List<FundUser> users = userService.findAllByRoles(roles);
        if(users != null && users.size() > 0){
            Random random = new Random();
            int min = 0;
            int max = users.size() - 1;
            int r = random.nextInt(max)%(max-min+1) + min;
            FundUser user = users.get(r);
            List<ApplyRecord> applys =applyRecordService.findApplyRecordByUserId(user);
            ret.fluentPut("applys", applys);
        }
        ret.fluentPut("types", applyRecordService.getApplyTypes());
        ret.fluentPut("states", applyRecordService.getApplyStates());
        return ret;
    }
}
