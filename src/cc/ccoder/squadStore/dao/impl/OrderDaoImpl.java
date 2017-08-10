package cc.ccoder.squadStore.dao.impl;

import java.util.Arrays;
import java.util.List;

import cc.ccoder.squadStore.dao.IOrderDao;
import cc.ccoder.squadStore.entity.Order;
import cc.ccoder.squadStore.util.DBOperatorUtils;

public class OrderDaoImpl implements IOrderDao {

	@Override
	public boolean addToOrder(Order order) {
		String sql = "insert into orders(userId,commodityId,addressId,price,number,totalPrice,deliverState,state,createdTime,updatedTime) values(?,?,?,?,?,?,?,?,?,?)";
		List<Object> params = Arrays.asList(
				order.getUserId(),
				order.getCommodityId(),
				order.getAddressId(),
				order.getPrice(),
				order.getNumber(),
				order.getTotalPrice(),
				order.getDeliverState(),
				order.getState(),
				order.getCreatedTime(),
				order.getUpdatedTime());
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

	@Override
	public Order getSimpleById(Integer id) {
		String sql = "select * from orders where id=?";
		List<Object> params = Arrays.asList(id);
		return DBOperatorUtils.getSimpleResult(sql, params, Order.class);
	}

	@Override
	public List<Order> getMoreOrders() {
		String sql = "select * from orders";
		List<Object> params = Arrays.asList();
		return DBOperatorUtils.getMoreResult(sql, params, Order.class);
	}

	@Override
	public boolean deletedOrderById(Integer id) {
		String sql = "delete from orders where id =?";
		List<Object> params = Arrays.asList(id);
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

	@Override
	public boolean updateOrderStateById(Integer id, Integer state) {
		String sql = "update orders set state=? where id = ?";
		List<Object> params = Arrays.asList(state,id);
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

	@Override
	public boolean updateOrderDeliStateById(Integer id, Integer deliverState) {
		String sql = "update orders set deliverState =? where id =?";
		List<Object> params = Arrays.asList(deliverState,id);
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}
	
	
	public static void main(String[] args) {
		OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
//		for (Order order : orderDaoImpl.getMoreOrders()) {
//			System.out.println(order);
//		}
//		System.out.println(orderDaoImpl.getSimpleById(1));
		//System.out.println(orderDaoImpl.deletedOrderById(1));
//		System.out.println(orderDaoImpl.updateOrderStateById(2, 1));
		System.out.println(orderDaoImpl.updateOrderDeliStateById(2, 1));
	}

}
