package mng.service.user;

import mng.dao.user.FundRoleDao;
import mng.model.user.FundRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FundRoleService {
    private FundRoleDao fundRoleDao;
    @Autowired
    public FundRoleService(FundRoleDao fundRoleDao){
        this.fundRoleDao = fundRoleDao;
    }

    public List<FundRole> findAll(){
        return fundRoleDao.findAll();
    }

    public FundRole findById(String id){
        return fundRoleDao.findRoleById(Integer.valueOf(id));
    }

    public List<FundRole> findByIds(List<Integer> ids){
        return fundRoleDao.findAllByIdIn(ids);
    }

    public FundRole findRoleByName(String name){
        return fundRoleDao.findFundRoleByName(name);
    }
}
