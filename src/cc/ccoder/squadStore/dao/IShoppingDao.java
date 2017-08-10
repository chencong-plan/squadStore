package cc.ccoder.squadStore.dao;

import java.util.List;

import cc.ccoder.squadStore.entity.Shopping;

/**
 * 购物车接口
 * @author chencong
 * @Time   2017年8月10日 下午2:46:05
 * @TODO
 */
public interface IShoppingDao {

	boolean addToShopping(Shopping shopping);

	List<Shopping> getMoreShoppings();
}
