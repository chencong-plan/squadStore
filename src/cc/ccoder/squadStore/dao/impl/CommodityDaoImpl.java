package cc.ccoder.squadStore.dao.impl;

import java.util.Arrays;
import java.util.List;
import cc.ccoder.squadStore.dao.ICommodityDao;
import cc.ccoder.squadStore.entity.Commodity;
import cc.ccoder.squadStore.util.DBOperatorUtils;

public class CommodityDaoImpl implements ICommodityDao {

	@Override
	public boolean insertCommodity(Commodity commodity) {
		String sql = "insert into commodity (storeId,name,state,price,describe,pricture,createdTime,updatedTime) values(?,?,?,?,?,?,?,?)";
		List<Object> params = Arrays.asList(
				commodity.getStoreId(),
				commodity.getName(),
				commodity.getState(),
				commodity.getPrice(),
				commodity.getDescribe(),
				commodity.getPricture(),
				commodity.getCreatedTime(),
				commodity.getUpdatedTime());
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

	@Override
	public boolean updateCommodity(Commodity commodity) {
		String sql = "update commodtity set name=?,state=?,price=?,describe=?,pricture=?,updtedTime=? where id = ?";
		List<Object> params = Arrays.asList(
				commodity.getName(),
				commodity.getState(),
				commodity.getPrice(),
				commodity.getDescribe(),
				commodity.getPrice(),
				commodity.getUpdatedTime(),
				commodity.getId());
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

	@Override
	public Commodity getSimpleCommodity(Integer id) {
		String sql = "select * from address where id = ?";
		List<Object> params = Arrays.asList(id);
		return DBOperatorUtils.getSimpleResult(sql, params, Commodity.class);
	}

	@Override
	public List<Commodity> getMoreCommodityInfos() {
		String sql = "select * from commodity 1=?";
		List<Object> params = Arrays.asList(1);
		return DBOperatorUtils.getMoreResult(sql, params, Commodity.class);
	}

	@Override
	public List<Commodity> getMoreCommodityBySize(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateCommodityState(Integer id, Integer state) {
		String sql = "update commodity set state=? where id = ?";
		List<Object> params = Arrays.asList(state,id);
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

}
