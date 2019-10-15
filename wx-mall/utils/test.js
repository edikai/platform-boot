/**
 * Copyright (C), 2018-2019, JEEEK
 * FileName: test
 * Author:   Edi_kai
 * Date:     2019/10/13 16:49
 * Description:
 * Version:  V1.0
 * <author>     <time>       <version>    <desc>
 * 作者姓名      修改时间       版本号       描述
 */
App({
	onLaunch: function () {
		console.log('App Launch')
//不在这里默认请求
	},
	/**
	 * 定义全局变量
	 */
	globalData: {
		openid: '', //用户openid
		userId: '', //用户编号
	},
	/**
	 * 用户登录请求封装(解决onlaunch和onload执行顺序问题)
	 */
	userLogin: function () {
		var that = this;
//定义promise方法
		return new Promise(function (resolve, reject) {
// 调用登录接口
			wx.login({
				success: function (res) {
					if (res.code) {
						console.log("用户登录授权code为：" + res.code);
//调用wx.request请求传递code凭证换取用户openid，并获取后台用户信息
						wx.request({
							url: 'https://www.xxxx.xxx.api', // 后台请求用户信息方法【注意，此处必须为https数字加密证书】
							data: {
								code: res.code //code凭证
							},
							header: {
								'content-type': 'application/json' // 默认值
							},
							success(res) {
								console.log(res.data)
								if (res.data.errcode == 0) {
//获取用户信息成功
									that.globalData.openid = res.data.openid;
									that.globalData.userId = res.data.UserId;
//存入session缓存中
									wx.setStorageSync("userId", that.globalData.userId)
									console.log(that.globalData.userId);
									console.log(that.globalData.openid);
//promise机制放回成功数据
									resolve(res.data);
								} else {
									reject('error');
								}
							},
							fail: function (res) {
								reject(res);
								wx.showToast({
									title: '系统错误'
								})
							},
							complete: () = > {} //complete接口执行后的回调函数，无论成功失败都会调用
					})
					}
					else {
						reject("error");
					}
				}
			})
		})
	}
});