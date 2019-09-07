package com.platform.config.shiro;

import com.platform.shiro.CluterShiroSessionDao;
import com.platform.shiro.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: ShiroConfig
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-08-30
 * @Version: V2.0
 **/
@Configuration
public class ShiroConfig {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CluterShiroSessionDao sessionDao;

    // 自定义AuthorizingRealm
    @Bean
    public UserRealm userAuthRealm() {
        UserRealm userRealm = new UserRealm();
        log.info("Initialized shiro AuthorizingRealm of UserRealm.");
        return userRealm;
    }

    /**
     * Shiro默认会使用Servlet容器的Session,可通过sessionMode属性来指定使用Shiro原生Session
     * 即<property name="sessionMode" value="native"/>,详细说明见官方文档
     * 这里主要是设置自定义的单Realm应用,若有多个Realm,可使用'realms'属性代替
     * @return
     */
    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(60*60*1000); // 设置session过期时间为1小时(单位：毫秒)，默认为30分钟
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionDAO(sessionDao);
        return sessionManager;
    }
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userAuthRealm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setUnauthorizedUrl("/");
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        shiroFilterFactoryBean.setSuccessUrl("/success.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/audio/**", "anon");
        filterMap.put("/css/**", "anon");
        filterMap.put("/fonts/**", "anon");
        filterMap.put("/img/**", "anon");
        filterMap.put("/libs/**", "anon");
        filterMap.put("/plugins/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/static/**", "anon");

        filterMap.put("/api/**", "anon");
        filterMap.put("/api/**", "noSessionCreation");
        filterMap.put("/index.html", "anon");
        filterMap.put("/sys/login", "anon");
        filterMap.put("/captcha.jpg", "anon");
        filterMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }
}
