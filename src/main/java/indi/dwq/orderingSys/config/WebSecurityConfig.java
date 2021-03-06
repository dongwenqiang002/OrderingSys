package indi.dwq.orderingSys.config;


import indi.dwq.orderingSys.app.loginfo.UserLogInfoAspect;
import indi.dwq.orderingSys.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author 董文强
 * @Time 2018/3/12 17:11
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    UserLogInfoAspect userLogInfoAspect;
    @Autowired
    UserService userService;

    @Autowired
    AAAAVerificationConfig aaaaVerificationConfig;
    @Autowired
    UserVerificationConfig userVerificationConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.info("登录拦截设置");
        http.authorizeRequests()
                .antMatchers(
                        /*不拦截的页面*/
                        "/img/**", "/fonts/**.**", "error/**",
                        "/css/**", "/js/**",
                        "/", "/register.html", "index.html", "/map/**",
                        "/user/reg**", "/favicon.ico"
                ).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login.html")
                .loginProcessingUrl("/login").passwordParameter("password").usernameParameter("username")
                .permitAll()
                .defaultSuccessUrl("/").permitAll()
                .and().logout().addLogoutHandler(userLogInfoAspect).logoutSuccessUrl("/").permitAll().and();
        http.csrf().disable();

       // ProviderManager

    }

    //设置登录规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //   this.disableLocalConfigureAuthenticationBldr = true;
        auth.userDetailsService(userService);
        auth.authenticationProvider(aaaaVerificationConfig).authenticationProvider(userVerificationConfig);
    }
}
