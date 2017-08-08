package cc.ccoder.squadStore.dao.impl;

import java.util.Arrays;
import java.util.List;

import cc.ccoder.squadStore.dao.IStoreDao;
import cc.ccoder.squadStore.entity.Store;
import cc.ccoder.squadStore.util.DBOperatorUtils;

public class StoreDaoImpl implements IStoreDao {

	@Override
	public Store storeLogin(String username, String password) {
		String sql = "select * from store where username=? and password=?";
		List<Object> params = Arrays.asList(username,password);
		return DBOperatorUtils.getSimpleResult(sql, params, Store.class);
	}

	@Override
	public boolean updateStore(Store store) {
		String sql = "update store set password=?,sotrename=?,phone=?, address=?,describe=? where id = ?";
		List<Object> params = Arrays.asList(
				store.getPassword(),
				store.getStorename(),
				store.getPhone(),
				store.getAddress(),
				store.getDescribe(),
				store.getId());
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

}
