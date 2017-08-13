package cc.ccoder.squadStore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.print.attribute.standard.Finishings;

import cc.ccoder.squadStore.dao.IOrderDao;
import cc.ccoder.squadStore.entity.Order;
import cc.ccoder.squadStore.entity.OrderDetails;
import cc.ccoder.squadStore.util.DBOperatorUtils;
import cc.ccoder.squadStore.util.DBUtils;
import cc.ccoder.squadStore.util.DateUtils;

/**
 * 订单接口实现层
 * 
 * @author chencong
 *
 */
public class OrderDaoImpl implements IOrderDao {

	/**
	 * 产生一条订单 同时将此订单的id
	 */
	@Override
	public Integer addToOrder(Order order) {
		Connection connection = DBUtils.getConnection();
		if (connection == null) {
			return null;
		}
		PreparedStatement pStatement = null;
		String sql = "insert into orders(name,address,phone,deliverState,state,createdTime) values(?,?,?,?,?,?)";
		String getKeySql = "select max(id) from orders";
		ResultSet resultSet = null;
		int primaryKey = 0;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, order.getName());
			pStatement.setString(2, order.getAddress());
			pStatement.setString(3, order.getPhone());
			pStatement.setInt(4, order.getDeliverState());
			pStatement.setInt(5, order.getState());
			pStatement.setString(6, order.getCreatedTime());
			int result = pStatement.executeUpdate();
			if (result >= 0) {
				pStatement = connection.prepareStatement(getKeySql);
				resultSet = pStatement.executeQuery();
				if (resultSet.next()) {
					primaryKey = resultSet.getInt(1);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (pStatement != null) {
					pStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return primaryKey;
	}

	@Override
	public List<Order> getMoreOrdersByUserId() {
		String sql = "select * from orders";
		List<Object> params = Arrays.asList();
		return DBOperatorUtils.getMoreResult(sql, params, Order.class);
	}

	@Override
	public boolean updateOrderState(Integer id,Integer state) {
		String sql = "update orders set state = ? where id = ?";
		List<Object> params = Arrays.asList(state,id);
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

	public static void main(String[] args) {
		OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
		Order order = new Order();
		order.setName("张三");
		order.setAddress("江汉大学");
		order.setPhone("15271917179");
		order.setCreatedTime(DateUtils.getNowTime());
		System.out.println(orderDaoImpl.addToOrder(order));
	}

	@Override
	public boolean updateOrderDeliverState(Integer id, Integer deliverState) {
		String sql = "update orders set deliverState = ? where id = ?";
		List<Object> params = Arrays.asList(deliverState,id);
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

}
