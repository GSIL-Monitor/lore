package mng.dao.funds;

import mng.model.funds.ApplyRecord;
import mng.model.user.FundUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author caopeihe
 */
public interface ApplyRecordDao extends JpaRepository<ApplyRecord, String> {
    /**
     * 获取申请记录集合
     * @param user 用户信息
     * @param sort 排序信息
     * @return 申请记录集合
     */
    List<ApplyRecord> findAllByUser(FundUser user, Sort sort);

    /**
     * 根据状态和用户获取申请报销集合
     * @param user 用户信息
     * @param state 状态
     * @return 申请报销集合
     */
    List<ApplyRecord> findAllByUserAndState(FundUser user, String state);

    /**
     * 更新单个用户申请记录状态
     * @param user 用户信息
     * @param state 更新的状态
     */
    @Modifying
    @Query(value="update ApplyRecord a set a.state=?2 where a.user=?1")
    void updateFundsApplyState(FundUser user, String state);
}
