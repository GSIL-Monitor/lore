package mng.dao.funds;

import mng.model.funds.FundsRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundsRecordDao extends JpaRepository<FundsRecord, String> {
    FundsRecord findFundsRecordByValid(String valid);
}
