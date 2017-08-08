package cc.ccoder.squadStore.service;

import cc.ccoder.squadStore.entity.UserInfo;

/**
 * 
 * @author chencong
 * @Time   2017年8月8日 下午2:55:32
 * @TODO 用户业务逻辑层接口抽象
 */
public interface IUserInfoService {

	boolean isLogin(String username, String password);
	
	Integer UserInfoRegister(UserInfo userInfo);
	
	Integer updateUserInfo(UserInfo userInfo);
}
