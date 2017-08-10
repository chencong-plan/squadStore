package cc.ccoder.squadStore.service.impl;

import java.util.List;

import cc.ccoder.squadStore.dao.IShoppingDao;
import cc.ccoder.squadStore.dao.impl.ShoppingDaoImpl;
import cc.ccoder.squadStore.entity.Shopping;
import cc.ccoder.squadStore.service.IShoppingService;

public class ShoppingServiceImpl implements IShoppingService {

	//注入dao层shopping接口
	private IShoppingDao iShoppingDao = new ShoppingDaoImpl();
	@Override
	public boolean addToShopping(Shopping shopping) {
		if (shopping == null) {
			return false;
		}
		return iShoppingDao.addToShopping(shopping) ;
	}
	@Override
	public List<Shopping> getMoreShoppings() {
		return iShoppingDao.getMoreShoppings();
	}

}
