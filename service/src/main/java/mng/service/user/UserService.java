package mng.service.user;

import mng.dao.user.FundRoleDao;
import mng.dao.user.UserDao;
import mng.model.user.FundRole;
import mng.model.user.FundUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    private UserDao userDao;
    private FundRoleDao fundRoleDao;
    @Autowired
    public UserService(UserDao userDao, FundRoleDao fundRoleDao){
        this.userDao = userDao;
        this.fundRoleDao = fundRoleDao;
    }

    public FundUser save(FundUser user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(fundRoleDao.findRoleById(FundRole.ROLE_USER));
        return userDao.save(user);
    }

    public FundUser onlySave(FundUser user){
        return userDao.save(user);
    }

    public List<FundUser> findAll(){
        return userDao.findAll();
    }

    public void deleteUserById(String id){
        userDao.deleteById(id);
    }

    public List<FundUser> findAllByRole(FundRole role){
        return userDao.findAllByRole(role);
    }

    public List<FundUser> findAllByRoles(List<FundRole> roles){
        return userDao.findAllByRoleIn(roles);
    }

    @Cacheable(cacheNames = "authority", key = "#username")
    public FundUser findFundUserByName(String username){
        return userDao.findFundUserByName(username);
    }

    public FundUser findById(String id){
        return userDao.getOne(id);
    }
}
