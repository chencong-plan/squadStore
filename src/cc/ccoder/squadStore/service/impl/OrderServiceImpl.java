package cc.ccoder.squadStore.service.impl;

import java.util.List;

import cc.ccoder.squadStore.dao.IOrderDao;
import cc.ccoder.squadStore.dao.impl.OrderDaoImpl;
import cc.ccoder.squadStore.entity.Order;
import cc.ccoder.squadStore.service.IOrderService;

public class OrderServiceImpl  implements IOrderService{

	private IOrderDao iOrderDao = new OrderDaoImpl();
	
	@Override
	public boolean addToOrder(Order order) {
		if (order == null) {
			return false;
		}
		return iOrderDao.addToOrder(order);
	}

	@Override
	public Order getSimpleById(Integer id) {
		if (id == null || id<=0) {
			return null;
		}
		return iOrderDao.getSimpleById(id);
	}

	@Override
	public List<Order> getMoreOrders() {
		return iOrderDao.getMoreOrders();
	}

	@Override
	public boolean deletedOrderById(Integer id) {
		if (id == null || id<=0) {
			return false;
		}
		return iOrderDao.deletedOrderById(id);
	}

	@Override
	public boolean updateOrderStateById(Integer id, Integer state) {
		if (id == null || state == null) {
			return false;
		}
		return iOrderDao.updateOrderStateById(id, state);
	}

	@Override
	public boolean updateOrderDeliStateById(Integer id, Integer deliverState) {
		if (id == null || deliverState == null) {
			return false;
		}
		return iOrderDao.updateOrderDeliStateById(id, deliverState);
	}

}
