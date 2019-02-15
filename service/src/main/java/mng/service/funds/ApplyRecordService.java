package mng.service.funds;

import com.alibaba.fastjson.JSONObject;
import mng.dao.user.FundRoleDao;
import mng.enums.ApplyState;
import mng.enums.ApplyType;
import mng.dao.funds.ApplyRecordDao;
import mng.dao.user.UserDao;
import mng.model.funds.ApplyRecord;
import mng.model.user.FundRole;
import mng.model.user.FundUser;
import mng.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author oac
 */
@Service
public class ApplyRecordService {
    private Logger logger = LoggerFactory.getLogger(ApplyRecordService.class);
    private ApplyRecordDao applyRecordDao;
    private UserDao userDao;
    private FundRoleDao roleDao;
    @Autowired
    public ApplyRecordService(ApplyRecordDao applyRecordDao,UserDao userDao, FundRoleDao roleDao){
        this.applyRecordDao = applyRecordDao;
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    public ApplyRecord save(ApplyRecord apply){
        apply.setState(ApplyState.UNREIMBURSE.getCode());
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        FundUser user = userDao.findFundUserByName(username);
        if(user != null){
            apply.setUser(user);
            String auditUserId = "";
            String approveUserId = "";
            if(Double.compare(apply.getMoney(),ApplyRecord.LIMIT_MONEY) >= 0){
                Map<String, Object> map = new HashMap<>(2);
                FundRole sysRole = roleDao.findRoleById(FundRole.ROLE_SYSUSER);
                FundRole admin = roleDao.findRoleById(FundRole.ROLE_ADMIN);
                List<FundUser> users = userDao.findAllByRole(sysRole);
                if(users != null && users.size() > 0 && !sysRole.equals(user.getRole())){
                    auditUserId = getRandomUserId(users);
                }else{
                    users = userDao.findAllByRole(admin);
                    auditUserId = getRandomUserId(users);
                }
                users = userDao.findAllByRole(admin);
                approveUserId = getRandomUserId(users);
            }
        }
        return applyRecordDao.save(apply);
    }

    public ApplyRecord findApplayRecordById(String id){
        return applyRecordDao.getOne(id);
    }

    public void deleteApplyRecord(ApplyRecord applyRecord){
        applyRecordDao.delete(applyRecord);
    }

    public List<ApplyRecord> findApplyRecordByUserId(FundUser user){
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        return applyRecordDao.findAllByUser(user,sort);
    }

    public Map<String, Double> getStatList(FundUser user){
        List<ApplyRecord> list = applyRecordDao.findAllByUserAndState(user, ApplyState.UNREIMBURSE.getCode());
        Map<String, Double> statMap = new HashMap<>(5);
        if(list != null && list.size() > 0){
            double all = 0.0;
            double meal = 0.0;
            double car = 0.0;
            double oil = 0.0;
            for(ApplyRecord apply:list){
                if(ApplyType.MEAL.getCode().equals(apply.getType())){
                    all += apply.getMoney();
                    meal += apply.getMoney();
                }else if(ApplyType.CAR.getCode().equals(apply.getType())){
                    all += apply.getMoney();
                    car += apply.getMoney();
                }else if(ApplyType.OIL.getCode().equals(apply.getType())){
                    all += apply.getMoney();
                    oil += apply.getMoney();
                }
            }
            statMap.put("all",all);
            statMap.put("meal",meal);
            statMap.put("car",car);
            statMap.put("oil",oil);
        }
        return statMap;
    }

    public Map<String, String> getApplyTypes(){
        Map<String, String> map = new HashMap<>(5);
        map.put(ApplyType.MEAL.getCode(), ApplyType.MEAL.getName());
        map.put(ApplyType.CAR.getCode(), ApplyType.CAR.getName());
        map.put(ApplyType.OIL.getCode(), ApplyType.OIL.getName());
        return map;
    }

    public Map<String, String> getApplyStates(){
        Map<String, String> map = new HashMap<>(5);
        map.put(ApplyState.UNREIMBURSE.getCode(), ApplyState.UNREIMBURSE.getName());
        map.put(ApplyState.HADREIMBURSE.getCode(), ApplyState.HADREIMBURSE.getName());
        return map;
    }

    private String getRandomUserId(List<FundUser> users){
        String userId = "";
        if(users != null && users.size() > 0){
            int num = users.size();
            int i = RandomUtil.getRandom(num);
            FundUser u = users.get(i);
            userId = u.getId();
        }
        return userId;
    }
}
