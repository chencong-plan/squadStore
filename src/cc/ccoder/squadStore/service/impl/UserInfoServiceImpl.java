package cc.ccoder.squadStore.service.impl;

import cc.ccoder.squadStore.comm.ConstInfo;
import cc.ccoder.squadStore.dao.IUserInfoDao;
import cc.ccoder.squadStore.dao.impl.UserInfoDaoImpl;
import cc.ccoder.squadStore.entity.UserInfo;
import cc.ccoder.squadStore.service.IUserInfoService;

/**
 * 
 * @author chencong
 * @Time 2017年8月8日 下午2:56:47
 * @TODO 用户接口的具体实现
 */
public class UserInfoServiceImpl implements IUserInfoService {

	/**
	 * 相当于将用户接口userInfo注入进来
	 */
	private IUserInfoDao iUserInfoDao = new UserInfoDaoImpl();

	/**
	 * 使用用户名和密码进行登录，后期可以从文件当中获取存储的用户名和密码
	 */
	@Override
	public boolean isLogin(String username, String password) {
		if (username == null || password == null) {
			return false;
		}
		UserInfo userInfo = iUserInfoDao.loginByUserInfo(username, password);
		if (userInfo == null) {
			return false;
		}
		return true;
	}

	/**
	 * 注册新的用户,注册之前首先判断注册使用的用户名是否存在数据库当中，如果存在则不予注册，否则注册成功
	 */
	@Override
	public Integer UserInfoRegister(UserInfo userInfo) {
		if (userInfo == null) {
			return ConstInfo.PARAM_ERROR.getIndex();
		}
		// 首先判断将要注册的用户名是否存在于数据库当中
		UserInfo user = iUserInfoDao.getSimpleUserInfo(userInfo.getUsername());
		if (user != null) {
			// 当前用户名已经存在于数据库当中，不予许注册
			return ConstInfo.USERNAME_EXISTS.getIndex();
		}
		// 不存在则直接注册
		boolean flag = iUserInfoDao.insertUserInfo(userInfo);
		if (!flag) {
			return ConstInfo.ERROR.getIndex();
		}
		return ConstInfo.SUCCESS.getIndex();
	}

	/**
	 * 更新用户信息，在更新用户信息之前需要判断用户是否登录，如果登录在允许修改信息，否则不允许修改用户信息
	 */
	@Override
	public Integer updateUserInfo(UserInfo userInfo) {
		if (userInfo == null) {
			return ConstInfo.PARAM_ERROR.getIndex();
		}
		//首先判断用户是否登录
		if (!isLogin(userInfo.getUsername(), userInfo.getPassword())) {
			return ConstInfo.USER_NOT_LOGIN.getIndex();
		}
		//登录之后执行更新操作
		boolean flag = iUserInfoDao.updateUserInfo(userInfo);
		if (!flag) {
			return ConstInfo.ERROR.getIndex();
		}
		return ConstInfo.SUCCESS.getIndex();
	}

}
