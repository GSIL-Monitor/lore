package mng.config;

import mng.service.user.FundUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author caopeihe
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //  启用方法级别的权限认证
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private FundUserDetailsServiceImpl fundUserDetailsService;
    @Autowired
    public WebSecurityConfig(FundUserDetailsServiceImpl fundUserDetailsService){
        this.fundUserDetailsService = fundUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/three/**", "/lore/**").permitAll()
                .antMatchers("/", "/error/**","/index","/register","/user/registerSave","/static/**","/demo/**","/flowable-rest/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/funds/manage/**").hasAnyRole("ADMIN", "SYSUSER")
                .antMatchers("/home","/funds/index").hasAnyRole("ADMIN","USER","SYSUSER","OTHER")
                .antMatchers("/funds/apply/add").hasAnyRole("SYSUSER", "USER")
//                .anyRequest().hasAnyRole("ADMIN","USER","SYSUSER","OTHER")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login-error").successForwardUrl("/loginSuccess").permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/index");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(fundUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
