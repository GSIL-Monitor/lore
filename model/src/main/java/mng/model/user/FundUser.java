package mng.model.user;

import mng.model.base.Base;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Objects;
@Proxy(lazy = false)
@Entity
@Table(name="user")
public class FundUser extends Base {
    private String name;
    private String nickName;
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private FundRole role;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "user")
//    private Set<ApplyRecord> applyList;


    public FundUser(){}

    public FundUser(String name, String password){
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public FundRole getRole() {
        return role;
    }

    public void setRole(FundRole role) {
        this.role = role;
    }

//    public Set<ApplyRecord> getApplyList() {
//        return applyList;
//    }
//
//    public void setApplyList(Set<ApplyRecord> applyList) {
//        this.applyList = applyList;
//    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FundUser fundUser = (FundUser) o;
        return Objects.equals(name, fundUser.name) &&
                Objects.equals(nickName, fundUser.nickName) &&
                Objects.equals(password, fundUser.password) &&
                Objects.equals(role, fundUser.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nickName, password, role);
    }
}
