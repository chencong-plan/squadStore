package cc.ccoder.squadStore.dao;

import cc.ccoder.squadStore.entity.Store;

/**
 * 商家操作接口
 * @author chencong
 *
 */
public interface IStoreDao {

	Store storeLogin(String username,String password);
	
	boolean updateStore(Store store);
}
