package mng.model.user;

import mng.model.base.Base;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
@Proxy(lazy = false)
@Entity
@Table(name="sys_role")
public class FundRole{
    /* 管理员 */
    public final static int ROLE_ADMIN = 1;
    /* 一般普通用户 */
    public final static int ROLE_USER = 2;
    /* 其他 */
    public final static int ROLE_OTHER = 3;
    /* 具有特殊权限 */
    public final static int ROLE_SYSUSER = 4;

    @Id
    private int id;
    private String name;
    private String actRole;
    private String actGroup;
    private String dsc;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "role")
    private Set<FundPermission> permissionList;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "role")
//    private Set<FundUser> userList;

    public FundRole(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FundRole(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActRole() {
        return actRole;
    }

    public void setActRole(String actRole) {
        this.actRole = actRole;
    }

    public String getActGroup() {
        return actGroup;
    }

    public void setActGroup(String actGroup) {
        this.actGroup = actGroup;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    public Set<FundPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(Set<FundPermission> permissionList) {
        this.permissionList = permissionList;
    }

//    public Set<FundUser> getUserList() {
//        return userList;
//    }
//
//    public void setUserList(Set<FundUser> userList) {
//        this.userList = userList;
//    }

    /*public FundUser getUser() {
        return user;
    }

    public void setUser(FundUser user) {
        this.user = user;
    }*/

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
