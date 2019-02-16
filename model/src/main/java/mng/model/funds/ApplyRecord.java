package mng.model.funds;

import mng.model.base.Base;
import mng.model.user.FundUser;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author ehoac
 */
@Proxy(lazy = false)
@Entity
@Table(name="apply_record")
@EntityListeners(AuditingEntityListener.class)
public class ApplyRecord extends Base {
    /** 需要审核的限额 */
    public final static double LIMIT_MONEY = 50.0;

    private double money;
    /**
     * 申请类型：饭费，出租，油费
     */
    private String type;
    /**
     * 状态：已报销，未报销
     */
    private String state;
    private String date;
    @CreatedDate
    @Column(updatable=false)
    private Date createTime;
    @LastModifiedDate
    private Date timestamps;
    private String reason;
    private String persons;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private FundUser user;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPersons() {
        return persons;
    }

    public void setPersons(String persons) {
        this.persons = persons;
    }

    public FundUser getUser() {
        return user;
    }

    public void setUser(FundUser user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplyRecord that = (ApplyRecord) o;
        return Double.compare(that.money, money) == 0 &&
                Objects.equals(type, that.type) &&
                Objects.equals(state, that.state) &&
                Objects.equals(date, that.date) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(timestamps, that.timestamps) &&
                Objects.equals(reason, that.reason) &&
                Objects.equals(persons, that.persons) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(money, type, state, date, createTime, timestamps, reason, persons, user);
    }
}
