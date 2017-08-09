package cc.ccoder.squadStore.service;

import cc.ccoder.squadStore.entity.Store;

public interface IStoreService {

	boolean isStoreLogin(String username,String password);
	
	boolean updateStore(Store store);
}
