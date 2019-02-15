package mng.dao.user;

import mng.model.user.FundRole;
import mng.model.user.FundUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<FundUser, String> {
    /**
     * 根据名字查找用户
     * @param username 用户名
     * @return 用户
     */
    @Query("from FundUser fu where fu.name=?1")
    FundUser findFundUserByName(String username);

    /**
     * 根据角色查找用户
     * @param role 角色
     * @return 用户集合
     */
    List<FundUser> findAllByRole(FundRole role);

    /**
     * 多角色查找用户
     * @param roles 角色集合
     * @return 用户集合
     */
    List<FundUser> findAllByRoleIn(List<FundRole> roles);

    @Query("delete from FundUser where id=?1")
    @Override
    void deleteById(String id);
}
