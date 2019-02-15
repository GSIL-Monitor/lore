package mng.model.funds;

import mng.model.base.Base;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author ehoac
 */
@Entity
@Table(name = "funds_record")
public class FundsRecord extends Base {
    /**  无效 */
    public final static String RECORD_VALID_NO = "0";
    /**  有效 */
    public final static String RECORD_VALID_YES = "1";
    private String operateDate;
    private double money;
    @CreatedDate
    @Column(updatable = false)
    private Date createTime;
    @LastModifiedDate
    private Date timestamps;
    private String valid;

    public String getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(Date timestamps) {
        this.timestamps = timestamps;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}
