package mng.service.funds;

import mng.enums.ApplyState;
import mng.enums.ApplyType;
import mng.dao.funds.ApplyRecordDao;
import mng.dao.funds.FundsRecordDao;
import mng.dao.user.FundRoleDao;
import mng.dao.user.UserDao;
import mng.model.funds.ApplyRecord;
import mng.model.funds.FundsRecord;
import mng.model.user.FundRole;
import mng.model.user.FundUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author caopeihe
 */
@Service
public class FundsService {
    private ApplyRecordDao applyRecordDao;
    private FundRoleDao fundRoleDao;
    private UserDao userDao;
    private FundsRecordDao fundsRecordDao;
    @Autowired
    public FundsService(ApplyRecordDao applyRecordDao,FundRoleDao fundRoleDao,UserDao userDao,FundsRecordDao fundsRecordDao){
        this.applyRecordDao = applyRecordDao;
        this.fundRoleDao = fundRoleDao;
        this.userDao = userDao;
        this.fundsRecordDao = fundsRecordDao;
    }

    public List<Map<String, String>> getAllUsersFunds(){
        List<FundRole> roles = fundRoleDao.findAllByIdIn(Arrays.asList(FundRole.ROLE_USER,FundRole.ROLE_SYSUSER));
        List<FundUser> users = userDao.findAllByRoleIn(roles);
        List<Map<String, String>> list = new ArrayList<>();
        for(FundUser user:users){
            Map<String, String> tmpMap = new HashMap<>(5);
            List<ApplyRecord> applys = applyRecordDao.findAllByUserAndState(user, ApplyState.UNREIMBURSE.getCode());
            double all = 0.0;
            double meal = 0.0;
            double car = 0.0;
            double oil = 0.0;
            for(ApplyRecord apply:applys){
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
            tmpMap.put("name",user.getNickName());
            tmpMap.put("id",user.getId());
            tmpMap.put("all",String.valueOf(all));
            tmpMap.put("meal",String.valueOf(meal));
            tmpMap.put("car",String.valueOf(car));
            tmpMap.put("oil",String.valueOf(oil));
            list.add(tmpMap);
        }
        return list;
    }

    public FundsRecord findFundsRecordByValid(String valid){
        return fundsRecordDao.findFundsRecordByValid(valid);
    }

    public FundsRecord saveFundsRecord(FundsRecord record){
        FundsRecord fundsRecord = this.findFundsRecordByValid(FundsRecord.RECORD_VALID_YES);
        FundsRecord saveRecord;
        if(fundsRecord != null){
            saveRecord = fundsRecord;
            saveRecord.setMoney(record.getMoney() + fundsRecord.getMoney());
            record = saveRecord;
        }else{
            record.setValid(FundsRecord.RECORD_VALID_YES);
        }
        return fundsRecordDao.save(record);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateFundsApplyState(FundUser user, String state){
        List<ApplyRecord> list = applyRecordDao.findAllByUserAndState(user, ApplyState.UNREIMBURSE.getCode());
        double all = 0;
        for(ApplyRecord record:list){
            all += record.getMoney();
        }
        FundsRecord fundsRecord = this.findFundsRecordByValid(FundsRecord.RECORD_VALID_YES);
        if(fundsRecord != null){
            fundsRecord.setMoney(fundsRecord.getMoney() - all);
        }
        fundsRecordDao.save(fundsRecord);
        applyRecordDao.updateFundsApplyState(user, state);
    }
}
