package cc.ccoder.squadStore.service;

import java.util.List;

import cc.ccoder.squadStore.entity.OrderDetails;

public interface IOrderDetailsService {

	/**
	 * 添加进入订单表
	 * @param order 将要插入的信息
	 * @return 返回此次操作是否成功
	 */
	boolean addToOrder(OrderDetails orderDetails);
	
	/**
	 * 根据id返回该id的所有信息
	 * @param id 将要查询order信息的id
	 * @return 返回id的order实体
	 */
	OrderDetails getSimpleById(Integer id);
	
	/**
	 * 返回所有的order集合
	 * @return 返回集合
	 */
	List<OrderDetails> getMoreOrders();
	
	/**
	 * 根据订单id 删除该订单
	 * @param id 将要删除的订单的id
	 * @return 返回此次操作是否成功
	 */
	boolean deletedOrderById(Integer id);


	/**
	 * 修改该订单的state状态,是否支付
	 * @param id 将要修改的order订单的id
	 * @param state 修改的目标state
	 * @return 返回此次操作是否成功
	 */
	boolean updateOrderStateById(Integer id,Integer state);

	
	/**
	 * 根据订单id修改deliverState 用户支付后 根据商家操作是是否进行接单
	 * @param id 将要操作订单id
	 * @param deliverState 是否接单状态
	 * @return 返回操作是否成功
	 */
	boolean updateOrderDeliStateById(Integer id,Integer deliverState);
	
	/**
	 * 获得当前用户的某一笔订单的订单详情
	 * @param userId 用户id
	 * @param orderId 将要查询的订单的订单id
	 * @return 返回的是该订单中的所有订单详情列表信息
	 */
	List<OrderDetails> getMoreOrderDetails(Integer userId,Integer orderId);
}
