package cc.ccoder.squadStore.service;

import cc.ccoder.squadStore.entity.UserInfo;

/**
 * 
 * @author chencong
 * @Time 2017年8月8日 下午2:55:32
 * @TODO 用户业务逻辑层接口抽象
 */
public interface IUserInfoService {

	/**
	 * 判断用户是否登录
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return 返回是否登录
	 */
	boolean isLogin(String username, String password);

	/**
	 * 用户注册
	 * 
	 * @param userInfo
	 *            将要注册的用户信息
	 * @return 返回操作结果枚举
	 */
	Integer UserInfoRegister(UserInfo userInfo);

	/**
	 * 更新用户信息
	 * 
	 * @param userInfo
	 *            将要更新的用户信息
	 * @return 返回操作结果枚举
	 */
	Integer updateUserInfo(UserInfo userInfo);
}
