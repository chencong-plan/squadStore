package cc.ccoder.squadStore.service.impl;

import cc.ccoder.squadStore.dao.IStoreDao;
import cc.ccoder.squadStore.dao.impl.StoreDaoImpl;
import cc.ccoder.squadStore.entity.Store;
import cc.ccoder.squadStore.service.IStoreService;

public class StoreServiceImpl implements IStoreService {

	//先将商家dao层接口注入进来
	private IStoreDao iStoreDao = new StoreDaoImpl();
	
	@Override
	public boolean isStoreLogin(String username, String password) {
		if (username==null || password == null) {
			return false;
		}
		Store store = iStoreDao.storeLogin(username, password);
		if (store == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateStore(Store store) {
		if (store == null) {
			return false;
		}
		return iStoreDao.updateStore(store);
	}

}
