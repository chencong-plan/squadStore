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
		String sql = "update store set password=?,storename=?,phone=?, address=?,describe=? where id = ?";
		List<Object> params = Arrays.asList(
				store.getPassword(),
				store.getStorename(),
				store.getPhone(),
				store.getAddress(),
				store.getDescribe(),
				store.getId());
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}
	
	public static void main(String[] args) {
		StoreDaoImpl daoImpl = new StoreDaoImpl();
		//Store Store = daoImpl.storeLogin("admin", "admin");
		//System.out.println(Store);
		//daoImpl.updateStore(store);
		Store store = new Store();
		store.setId(1);
		store.setUsername("admin");
		store.setPassword("admin");
		store.setSotrename("黄焖鸡");
		store.setPhone("123");
		store.setAddress("江大");
		System.out.println(daoImpl.updateStore(store));
		//System.out.println(daoImpl.updateStore(store));
	}

}
