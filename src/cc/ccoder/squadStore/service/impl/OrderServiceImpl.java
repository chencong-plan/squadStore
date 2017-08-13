package cc.ccoder.squadStore.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cc.ccoder.squadStore.dao.IOrderDao;
import cc.ccoder.squadStore.dao.impl.OrderDaoImpl;
import cc.ccoder.squadStore.entity.Address;
import cc.ccoder.squadStore.entity.Commodity;
import cc.ccoder.squadStore.entity.Order;
import cc.ccoder.squadStore.entity.OrderDetails;
import cc.ccoder.squadStore.entity.Shopping;
import cc.ccoder.squadStore.entity.UserInfo;
import cc.ccoder.squadStore.service.IAddressService;
import cc.ccoder.squadStore.service.ICommodityService;
import cc.ccoder.squadStore.service.IOrderDetailsService;
import cc.ccoder.squadStore.service.IOrderService;
import cc.ccoder.squadStore.service.IShoppingService;
import cc.ccoder.squadStore.service.IUserInfoService;
import cc.ccoder.squadStore.util.DBUtils;
import cc.ccoder.squadStore.util.DateUtils;
import cc.ccoder.squadStore.util.FileUtils;

public class OrderServiceImpl implements IOrderService {

	private IOrderDao iOrderDao = new OrderDaoImpl();
	private IShoppingService iShoppingService = new ShoppingServiceImpl();
	private IUserInfoService iUserInfoService = new UserInfoServiceImpl();
	private IAddressService iAddressService = new AddressServiceImpl();
	private IOrderDetailsService iOrderDetalService = new OrderDetailsServiceImpl();
	private ICommodityService iCommodityService = new CommodityServiceImpl();

	@Override
	public boolean addOrder(int[] shoppingIds) {
		if (shoppingIds == null || shoppingIds.length <= 0) {
			return false;
		}
		Connection connection = DBUtils.getConnection();
		try {
			connection.setAutoCommit(false);
			String username = FileUtils.getSomeByFile("user_login.txt");
			UserInfo userInfo = iUserInfoService.getSimpleUserInfo(username);
			if (userInfo == null) {
				return false;
			}
			Address address = iAddressService.getSimpleAddressByuserIdAndState(userInfo.getId(), 1);
			if (address == null) {
				return false;
			}
			Order order = new Order();
			order.setName(FileUtils.getSomeByFile("user_login.txt"));
			order.setAddress(address.getAddress());
			order.setPhone(address.getPhone());
			order.setDeliverState(0);
			order.setState(0);
			order.setCreatedTime(DateUtils.getNowTime());
			int orderId = iOrderDao.addToOrder(order);
			for (int i = 0; i < shoppingIds.length; i++) {
				Shopping shopping = iShoppingService.getSimpleShopping(shoppingIds[i]);
				if (shopping == null) {
					return false;
				}
				Commodity commodity = iCommodityService.getSimpleCommodity(shopping.getCommodityId());
				if (commodity == null) {
					return false;
				}
				double price = commodity.getPrice();
				OrderDetails orderDetails = new OrderDetails();
				orderDetails.setUserId(userInfo.getId());
				orderDetails.setCommodityId(commodity.getId());
				orderDetails.setAddressId(address.getId());
				orderDetails.setPrice(price);
				orderDetails.setNumber(shopping.getNumber());
				orderDetails.setTotalPrice(shopping.getTotalPrice());
				orderDetails.setDeliverState(0);
				orderDetails.setState(0);
				// 最重要的添加orderId
				orderDetails.setOrderId(orderId);
				orderDetails.setCreatedTime(DateUtils.getNowTime());
				orderDetails.setUpdatedTime(DateUtils.getNowTime());
				iOrderDetalService.addToOrder(orderDetails);
				// 删除购物车中数据
				iShoppingService.deleteShoppingById(shoppingIds[i]);
			}
			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		return true;
	}

	public List<Order> getMoreOrdersByUserId() {
		return iOrderDao.getMoreOrdersByUserId();
	}

	@Override
	public boolean updateOrderState(Integer id, Integer state) {
		if (id == null || state == null) {
			return false;
		}
		return iOrderDao.updateOrderState(id, state);
	}

	@Override
	public boolean updateOrderDeliverState(Integer id, Integer deliverState) {
		if (id == null || deliverState == null) {
			return false;
		}
		return iOrderDao.updateOrderDeliverState(id, deliverState);
	}

}
