package mng.dao.user;

import mng.model.user.FundRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FundRoleDao  extends JpaRepository<FundRole, String> {
    /**
     * 根据id查找角色
     * @param id id
     * @return 角色
     */
    @Query("from FundRole fr where fr.id=?1")
    FundRole findRoleById(int id);

    /**
     * 根据id集合查询角色集合
     * @param ids id集合
     * @return 角色集合
     */
    List<FundRole> findAllByIdIn(List<Integer> ids);

    FundRole findFundRoleByName(String name);
}
