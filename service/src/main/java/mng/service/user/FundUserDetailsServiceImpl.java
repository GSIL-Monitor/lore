package mng.service.user;

import mng.model.user.FundPermission;
import mng.model.user.FundRole;
import mng.model.user.FundUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author caopeihe
 */
@Service
public class FundUserDetailsServiceImpl implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(FundUserDetailsServiceImpl.class);

    private UserService userService;
    @Autowired
    public FundUserDetailsServiceImpl(UserService userService){
        this.userService = userService;
    }

    /**
     * 授权的时候是对角色授权，而认证的时候应该基于资源，而不是角色，因为资源是不变的，而用户的角色是会变的
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FundUser u = userService.findFundUserByName(username);
        if (null == u) {
            throw new UsernameNotFoundException(username);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        FundRole role = u.getRole();
        if(role != null){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
//            authorities.add(new SimpleGrantedAuthority(role.getActRole()));
//            authorities.add(new SimpleGrantedAuthority(role.getActGroup()));
            for (FundPermission permission : role.getPermissionList()) {
                authorities.add(new SimpleGrantedAuthority(permission.getCode()));
            }
        }
        User user = new User(username, u.getPassword(),authorities);
        return user;
    }
}
