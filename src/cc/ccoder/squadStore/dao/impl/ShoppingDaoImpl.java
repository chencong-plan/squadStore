package cc.ccoder.squadStore.dao.impl;

import java.util.Arrays;
import java.util.List;

import cc.ccoder.squadStore.dao.IShoppingDao;
import cc.ccoder.squadStore.entity.Shopping;
import cc.ccoder.squadStore.util.DBOperatorUtils;
import cc.ccoder.squadStore.util.DateUtils;

public class ShoppingDaoImpl implements IShoppingDao {

	@Override
	public boolean addToShopping(Shopping shopping) {
		String sql = "insert into shopping (userId,commodityId,number,totalPrice,createdTime,updatedTime) values(?,?,?,?,?,?)";
		List<Object> params = Arrays.asList(shopping.getUserId(), shopping.getCommodityId(), shopping.getNumber(),
				shopping.getTotalPrice(), shopping.getCreatedTime(), shopping.getUpdatedTime());
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

	@Override
	public List<Shopping> getMoreShoppings() {
		String sql = "select * from shopping";
		List<Object> params = Arrays.asList();
		return DBOperatorUtils.getMoreResult(sql, params, Shopping.class);
	}

	@Override
	public boolean deleteShoppingById(Integer id) {
		String sql = "delete from shopping where id =?";
		List<Object> params = Arrays.asList(id);
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

	public static void main(String[] args) {
		ShoppingDaoImpl shoppingDaoImpl = new ShoppingDaoImpl();
		Shopping shopping = new Shopping();
		shopping.setUserId(1);
		shopping.setCommodityId(20);
		shopping.setNumber(1);
		shopping.setTotalPrice(23.8);
		shopping.setCreatedTime(DateUtils.getNowTime());
		shopping.setUpdatedTime(DateUtils.getNowTime());
		System.out.println(shoppingDaoImpl.addToShopping(shopping));
	}

	@Override
	public Shopping getSimpleShopping(Integer id) {
		String sql = "select * from shopping where id = ?";
		List<Object> params = Arrays.asList(id);
		return DBOperatorUtils.getSimpleResult(sql, params, Shopping.class);
	}

}
