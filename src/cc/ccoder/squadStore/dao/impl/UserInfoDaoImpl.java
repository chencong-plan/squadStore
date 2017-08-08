package cc.ccoder.squadStore.dao.impl;

import java.util.Arrays;
import java.util.List;

import cc.ccoder.squadStore.dao.IUserInfoDao;
import cc.ccoder.squadStore.entity.UserInfo;
import cc.ccoder.squadStore.util.DBOperatorUtils;

public class UserInfoDaoImpl implements IUserInfoDao {

	@Override
	public boolean insertUserInfo(UserInfo userInfo) {
		String sql = "insert into userInfo(username,password,nickname,email,note,createdTime,updatedTime) values(?,?,?,?,?,?,?)";
		List<Object> params = Arrays.asList(
				userInfo.getUsername(), 
				userInfo.getPassword(), 
				userInfo.getNickname(),
				userInfo.getEmail(), 
				userInfo.getNote(), 
				userInfo.getCreatedTime(), 
				userInfo.getUpdatedTime());
		boolean flag = DBOperatorUtils.excuteUpdateResult(sql, params);
		return flag;
	}

	@Override
	public UserInfo getSimpleUserInfo(String username) {
		String sql = "select * from userInfo where username=? ";
		List<Object> params = Arrays.asList(username);
		return DBOperatorUtils.getSimpleResult(sql, params, UserInfo.class);
	}

	@Override
	public UserInfo loginByUserInfo(String username, String password) {
		String sql = "select * from userInfo where username=? and password=?";
		List<Object> params = Arrays.asList(username, password);
		return DBOperatorUtils.getSimpleResult(sql, params, UserInfo.class);
	}

	@Override
	public boolean updateUserInfo(UserInfo userInfo) {
		String sql = "update userInfo set password=?,nickname=?,email=?,note=?,updatedTime=? where id=?";
		List<Object> params = Arrays.asList(
				userInfo.getPassword(),
				userInfo.getNickname(), 
				userInfo.getEmail(),
				userInfo.getNote(), 
				userInfo.getUpdatedTime(), 
				userInfo.getId());
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

	public static void main(String[] args) {
		UserInfoDaoImpl userInfoDaoImpl = new UserInfoDaoImpl();
		System.out.println(userInfoDaoImpl.getSimpleUserInfo("chencong"));
	}
}
