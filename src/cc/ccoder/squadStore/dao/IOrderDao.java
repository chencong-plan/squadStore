package cc.ccoder.squadStore.dao;

import java.util.List;

import cc.ccoder.squadStore.entity.Commodity;
import cc.ccoder.squadStore.entity.Order;
import cc.ccoder.squadStore.entity.OrderDetails;

/**
 * 处理订单
 * 
 * @author chencong
 *
 */
public interface IOrderDao {

	/**
	 * 增加一条订单信息 同时返回该条记录的id
	 * 
	 * @param order
	 * @return
	 */
	Integer addToOrder(Order order);

	List<Order> getMoreOrdersByUserId();

	boolean updateOrderState(Integer id, Integer state);
	
	boolean updateOrderDeliverState(Integer id,Integer deliverState);

}
