package com.platform.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: StaticPageController
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-09-11
 * @Version: V2.0
 **/
@RestController
@RequestMapping("/sys")
public class StaticPageController {

    /**
     * 用户管理
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView sysMain() {
        return new ModelAndView("/page/sys/user");
    }

    /**
     * 通用字典管理
     * @return
     */
    @RequestMapping(value = "/macro", method = RequestMethod.GET)
    public ModelAndView sysMacro() {
        return new ModelAndView("/page/sys/macro");
    }

    /**
     * 部门管理
     * @return
     */
    @RequestMapping(value = "/dept", method = RequestMethod.GET)
    public ModelAndView sysDept() {
        return new ModelAndView("/page/sys/dept");
    }

    /**
     * 角色管理
     * @return
     */
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public ModelAndView sysRole() {
        return new ModelAndView("/page/sys/role");
    }

    /**
     * 菜单管理
     * @return
     */
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public ModelAndView sysMenu() {
        return new ModelAndView("/page/sys/menu");
    }

    /**
     * 定时任务管理
     * @return
     */
//    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
//    public ModelAndView sysSchedule() {
//        return new ModelAndView("/page/sys/schedule");
//    }

    /**
     * 文件上传管理
     * @return
     */
    @RequestMapping(value = "/oss", method = RequestMethod.GET)
    public ModelAndView sysOss() {
        return new ModelAndView("/page/sys/oss");
    }

    /**
     * 系统参数管理
     * @return
     */
    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public ModelAndView sysConfig() {
        return new ModelAndView("/page/sys/config");
    }

    /**
     * 系统日志管理
     * @return
     */
    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public ModelAndView sysLog() {
        return new ModelAndView("/page/sys/log");
    }

    /**
     * 地区管理
     * @return
     */
    @RequestMapping(value = "/region", method = RequestMethod.GET)
    public ModelAndView sysRegion() {
        return new ModelAndView("/page/sys/region");
    }

    /**
     * 图标
     * @return
     */
    @RequestMapping(value = "/icon", method = RequestMethod.GET)
    public ModelAndView sysDruidSql() {
        return new ModelAndView("/page/sys/icon");
    }

    /**
     * 代码生成器
     * @return
     */
    @RequestMapping(value = "/generator", method = RequestMethod.GET)
    public ModelAndView sysGenerator() {
        return new ModelAndView("/page/sys/generator");
    }

}
