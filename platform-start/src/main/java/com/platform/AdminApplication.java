package com.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @ClassName: AdminApplication
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-08-29
 * @Version: V2.0
 **/
//@SpringBootApplication(scanBasePackages = {"com.platform", "com.platform.utils"})
@SpringBootApplication
@EnableTransactionManagement
//@MapperScan(value = "{com.platform.dao, com.platform.*.dao}")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
