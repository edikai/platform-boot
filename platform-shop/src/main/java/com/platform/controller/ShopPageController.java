package com.platform.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: 商城controller入口
 * @Author: qin_hqing
 * @Date: 2020/4/1
 * @Version: V1.0
 **/
@RestController
@RequestMapping("/shop")
public class ShopPageController {
    
    /**
     * 商品管理
     * @return
     */
    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public ModelAndView shopGoods() {
        return new ModelAndView("/page/shop/goods");
    }
    
    /**
     * 商品规格管理
     * @return
     */
    @RequestMapping(value = "/goods-specification", method = RequestMethod.GET)
    public ModelAndView goodsSpecification() {
        return new ModelAndView("/page/shop/goodsSpecification");
    }
    
    /**
     * 产品设置管理
     * @return
     */
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ModelAndView product() {
        return new ModelAndView("/page/shop/product");
    }
    
    /**
     * 用户评论
     * @return
     */
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public ModelAndView comment() {
        return new ModelAndView("/page/shop/comment");
    }
    
    /**
     * 商品回收站
     * @return
     */
    @RequestMapping(value = "/goods-history", method = RequestMethod.GET)
    public ModelAndView goodsHistory() {
        return new ModelAndView("/page/shop/goodsHistory");
    }
    
    /**
     * 商品属性种类
     * @return
     */
    @RequestMapping(value = "/attr-category", method = RequestMethod.GET)
    public ModelAndView attributeCategory() {
        return new ModelAndView("/page/shop/attributeCategory");
    }
    
    /**
     * 商品规格
     * @return
     */
    @RequestMapping(value = "/specification", method = RequestMethod.GET)
    public ModelAndView specification() {
        return new ModelAndView("/page/shop/specification");
    }
    
    /**
     * 渠道管理
     * @return
     */
    @RequestMapping(value = "/channel", method = RequestMethod.GET)
    public ModelAndView channel() {
        return new ModelAndView("/page/shop/channel");
    }
    
    /**
     * 商品类型管理
     * @return
     */
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ModelAndView category() {
        return new ModelAndView("/page/shop/category");
    }
    
    /**
     * 品牌制造商管理
     * @return
     */
    @RequestMapping(value = "/brand", method = RequestMethod.GET)
    public ModelAndView brand() {
        return new ModelAndView("/page/shop/brand");
    }
    
    /**
     * 商品问答管理
     * @return
     */
    @RequestMapping(value = "/goods-issue", method = RequestMethod.GET)
    public ModelAndView goodsIssue() {
        return new ModelAndView("/page/shop/goodsIssue");
    }
    
    /**
     * 反馈管理
     * @return
     */
    @RequestMapping(value = "/feedback", method = RequestMethod.GET)
    public ModelAndView feedback() {
        return new ModelAndView("/page/shop/feedback");
    }
    
    /**
     * 关键词管理
     * @return
     */
    @RequestMapping(value = "/keywords", method = RequestMethod.GET)
    public ModelAndView keywords() {
        return new ModelAndView("/page/shop/keywords");
    }
    
    /**
     * 会员等级管理
     * @return
     */
    @RequestMapping(value = "/user-level", method = RequestMethod.GET)
    public ModelAndView userLevel() {
        return new ModelAndView("/page/shop/userLevel");
    }
    
    /**
     * 会员管理
     * @return
     */
    @RequestMapping(value = "/shop-user", method = RequestMethod.GET)
    public ModelAndView shopUser() {
        return new ModelAndView("/page/shop/shopUser");
    }
    
    /**
     * 会员优惠券管理
     * @return
     */
    @RequestMapping(value = "/user-coupon", method = RequestMethod.GET)
    public ModelAndView userCoupon() {
        return new ModelAndView("/page/shop/userCoupon");
    }
    
    /**
     * 会员收藏管理
     * @return
     */
    @RequestMapping(value = "/collect", method = RequestMethod.GET)
    public ModelAndView collect() {
        return new ModelAndView("/page/shop/collect");
    }
    
    /**
     * 会员足迹管理
     * @return
     */
    @RequestMapping(value = "/footprint", method = RequestMethod.GET)
    public ModelAndView footprint() {
        return new ModelAndView("/page/shop/footprint");
    }
    
    /**
     * 搜索历史管理
     * @return
     */
    @RequestMapping(value = "/search-history", method = RequestMethod.GET)
    public ModelAndView searchHistory() {
        return new ModelAndView("/page/shop/searchHistory");
    }
    
    /**
     * 购物车管理
     * @return
     */
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ModelAndView cart() {
        return new ModelAndView("/page/shop/cart");
    }
    
    /**
     * 购物车管理
     * @return
     */
    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public ModelAndView address() {
        return new ModelAndView("/page/shop/address");
    }
    
    /**
     * 购物车管理
     * @return
     */
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView order() {
        return new ModelAndView("/page/shop/order");
    }
    
}
