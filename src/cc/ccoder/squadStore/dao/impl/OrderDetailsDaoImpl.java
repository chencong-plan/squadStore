package cc.ccoder.squadStore.dao.impl;

import java.util.Arrays;
import java.util.List;

import cc.ccoder.squadStore.dao.IOrderDetailsDao;
import cc.ccoder.squadStore.entity.OrderDetails;
import cc.ccoder.squadStore.util.DBOperatorUtils;

public class OrderDetailsDaoImpl implements IOrderDetailsDao {

	@Override
	public boolean addToOrder(OrderDetails orderDetails) {
		String sql = "insert into Order_details(userId,commodityId,addressId,price,number,totalPrice,deliverState,state,orderId,createdTime,updatedTime) values(?,?,?,?,?,?,?,?,?,?,?)";
		List<Object> params = Arrays.asList(
				orderDetails.getUserId(),
				orderDetails.getCommodityId(),
				orderDetails.getAddressId(),
				orderDetails.getPrice(),
				orderDetails.getNumber(),
				orderDetails.getTotalPrice(),
				orderDetails.getDeliverState(),
				orderDetails.getState(),
				orderDetails.getOrderId(),
				orderDetails.getCreatedTime(),
				orderDetails.getUpdatedTime());
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

	@Override
	public OrderDetails getSimpleById(Integer id) {
		String sql = "select * from Order_details where id=?";
		List<Object> params = Arrays.asList(id);
		return DBOperatorUtils.getSimpleResult(sql, params, OrderDetails.class);
	}

	@Override
	public List<OrderDetails> getMoreOrders() {
		String sql = "select * from Order_details";
		List<Object> params = Arrays.asList();
		return DBOperatorUtils.getMoreResult(sql, params, OrderDetails.class);
	}

	@Override
	public boolean deletedOrderById(Integer id) {
		String sql = "delete from Order_details where id =?";
		List<Object> params = Arrays.asList(id);
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

	@Override
	public boolean updateOrderStateById(Integer id, Integer state) {
		String sql = "update Order_details set state=? where id = ?";
		List<Object> params = Arrays.asList(state,id);
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

	@Override
	public boolean updateOrderDeliStateById(Integer id, Integer deliverState) {
		String sql = "update Order_details set deliverState =? where id =?";
		List<Object> params = Arrays.asList(deliverState,id);
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}
	
	
	@Override
	public List<OrderDetails> getMoreOrderDetails(Integer userId, Integer orderId) {
		String sql = "select * from Order_details where userId = ? and orderId=?";
		List<Object> params = Arrays.asList(userId,orderId);
		return DBOperatorUtils.getMoreResult(sql, params, OrderDetails.class);
	}
	
	
	public static void main(String[] args) {
		OrderDetailsDaoImpl orderDaoImpl = new OrderDetailsDaoImpl();
		for (OrderDetails order : orderDaoImpl.getMoreOrders()) {
			System.out.println(order);
		}
//		System.out.println(orderDaoImpl.getSimpleById(1));
		//System.out.println(orderDaoImpl.deletedOrderById(1));
//		System.out.println(orderDaoImpl.updateOrderStateById(2, 1));
//		System.out.println(orderDaoImpl.updateOrderDeliStateById(2, 1));
	}

	

}
