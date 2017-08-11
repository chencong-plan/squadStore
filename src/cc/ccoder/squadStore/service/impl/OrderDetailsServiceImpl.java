package cc.ccoder.squadStore.service.impl;

import java.util.List;

import cc.ccoder.squadStore.dao.IOrderDetailsDao;
import cc.ccoder.squadStore.dao.impl.OrderDetailsDaoImpl;
import cc.ccoder.squadStore.entity.OrderDetails;
import cc.ccoder.squadStore.service.IOrderDetailsService;

public class OrderDetailsServiceImpl  implements IOrderDetailsService{

	private IOrderDetailsDao iOrderDetailsDao = new OrderDetailsDaoImpl();
	
	@Override
	public boolean addToOrder(OrderDetails orderDetails) {
		if (orderDetails == null) {
			return false;
		}
		return iOrderDetailsDao.addToOrder(orderDetails);
	}

	@Override
	public OrderDetails getSimpleById(Integer id) {
		if (id == null || id<=0) {
			return null;
		}
		return iOrderDetailsDao.getSimpleById(id);
	}

	@Override
	public List<OrderDetails> getMoreOrders() {
		return iOrderDetailsDao.getMoreOrders();
	}

	@Override
	public boolean deletedOrderById(Integer id) {
		if (id == null || id<=0) {
			return false;
		}
		return iOrderDetailsDao.deletedOrderById(id);
	}

	@Override
	public boolean updateOrderStateById(Integer id, Integer state) {
		if (id == null || state == null) {
			return false;
		}
		return iOrderDetailsDao.updateOrderStateById(id, state);
	}

	@Override
	public boolean updateOrderDeliStateById(Integer id, Integer deliverState) {
		if (id == null || deliverState == null) {
			return false;
		}
		return iOrderDetailsDao.updateOrderDeliStateById(id, deliverState);
	}

}
