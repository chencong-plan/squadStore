package cc.ccoder.squadStore.service;

import java.util.List;

import cc.ccoder.squadStore.entity.Order;

public interface IOrderService {

	/**
	 * 对将要下单的商品进行处理
	 * 
	 * @param commodityIds
	 *            将要进行下单的商品id
	 * @return 此次操作是否成功
	 */
	boolean addOrder(int[] shoppingIds);

	List<Order> getMoreOrdersByUserId();

	boolean updateOrderState(Integer id, Integer state);

	boolean updateOrderDeliverState(Integer id, Integer deliverState);
}
