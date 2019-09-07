package com.platform.config.tomcat;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: TomcatConfig
 * @Description: 解决Tomcat8+版本后URL中特殊字符问题
 * @Author: qin_hqing
 * @Date: 2019-08-31
 * @Version: V2.0
 **/
@Configuration
public class TomcatConfig {

    @Bean
    public TomcatServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((Connector connector) -> {
//            connector.setProperty("relaxedPathChars", "\"<>[\\]^`{|}");
//            connector.setProperty("relaxedQueryChars", "\"<>[\\]^`{|}");
            connector.setProperty("relaxedPathChars", "{}");
            connector.setProperty("relaxedQueryChars", "{}");
        });
        return factory;
    }

}
