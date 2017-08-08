package cc.ccoder.squadStore.dao;

import cc.ccoder.squadStore.entity.UserInfo;

/**
 * 
 * @author chencong
 * @Time   2017年8月8日 下午2:17:23
 * @TODO 用户管理的接口
 */
public interface IUserInfoDao {

	/**
	 * 插入一条用户记录
	 * @param userInfo  将要新增的用户对象
	 * @return 返回boolean记录,该条记录是否插入成功
	 */
	boolean insertUserInfo(UserInfo userInfo);

	/**
	 * 根据用户名，查询当前注册用户名是否允许被注册,是否存在于数据库当中
	 * @param username 将要注册的用户名
	 * @return 返回查询到的userinfo实体对象
	 */
	UserInfo getSimpleUserInfo(String username);
	
	/**
	 * 根据用户名和密码进行登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 返回该用户名和密码查询到的用户实体信息，该实体信息放在service中进行判断是否为空，来判断是否登录成功
	 */
	UserInfo loginByUserInfo(String username,String password);
	
	/**
	 * 更新用户信息
	 * @param userInfo 将要被更新的用户实体信息
	 * @return 返回是否更新成功
	 */
	boolean updateUserInfo(UserInfo userInfo);
	
}
