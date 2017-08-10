package cc.ccoder.squadStore.dao.impl;

import java.util.Arrays;
import java.util.List;
import cc.ccoder.squadStore.dao.ICommodityDao;
import cc.ccoder.squadStore.entity.Commodity;
import cc.ccoder.squadStore.util.DBOperatorUtils;
import cc.ccoder.squadStore.util.DateUtils;

public class CommodityDaoImpl implements ICommodityDao {

	@Override
	public boolean insertCommodity(Commodity commodity) {
		String sql = "insert into commodity (storeId,name,state,price,describe,pricture,createdTime,updatedTime) values(?,?,?,?,?,?,?,?)";
		List<Object> params = Arrays.asList(commodity.getStoreId(), commodity.getName(), commodity.getState(),
				commodity.getPrice(), commodity.getDescribe(), commodity.getPricture(), commodity.getCreatedTime(),
				commodity.getUpdatedTime());
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

	@Override
	public boolean updateCommodity(Commodity commodity) {
		String sql = "update commodity set name=?,state=?,price=?,describe=?,pricture=?,updatedTime=? where id = ?";
		List<Object> params = Arrays.asList(commodity.getName(), commodity.getState(), commodity.getPrice(),
				commodity.getDescribe(), commodity.getPrice(), commodity.getUpdatedTime(), commodity.getId());
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

	@Override
	public Commodity getSimpleCommodity(Integer id) {
		String sql = "select * from commodity where id = ?";
		List<Object> params = Arrays.asList(id);
		return DBOperatorUtils.getSimpleResult(sql, params, Commodity.class);
	}

	@Override
	public List<Commodity> getMoreCommodityInfos() {
		String sql = "select * from commodity";
		List<Object> params = Arrays.asList();
		return DBOperatorUtils.getMoreResult(sql, params, Commodity.class);
	}

	@Override
	public List<Commodity> getMoreCommodityBySize(int pageNum, int pageSize) {
		String sql = "{call usp_getCommodityBySize(?,?)}";
		List<Object> params = Arrays.asList(pageNum, pageSize);
		return DBOperatorUtils.getMoreResultProcByPage(sql, params, Commodity.class);
	}

	@Override
	public boolean updateCommodityState(Integer id, Integer state) {
		String sql = "update commodity set state=? where id = ?";
		List<Object> params = Arrays.asList(state, id);
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

	@Override
	public boolean deleteCommodity(Integer id) {
		String sql = "delete from commodity where id =?";
		List<Object> params = Arrays.asList(id);
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}
	
	@Override
	public List<Commodity> getMoreCommodityByOnShelf(int pageNum, int pageSize) {
		String sql = "{call usp_getOnShelfCommodityBySize(?,?)}";
		List<Object> params = Arrays.asList(pageNum, pageSize);
		return DBOperatorUtils.getMoreResultProcByPage(sql, params, Commodity.class);
	}

	@Override
	public List<Commodity> getMoreCommodityByOffShelf(int pageNum, int pageSize) {
		String sql = "{call usp_getOffShelfCommodityBySize(?,?)}";
		List<Object> params = Arrays.asList(pageNum, pageSize);
		return DBOperatorUtils.getMoreResultProcByPage(sql, params, Commodity.class);
	}

	public static void main(String[] args) {
		CommodityDaoImpl commodityDaoImpl = new CommodityDaoImpl();
		Commodity commodity = new Commodity();
		// commodity.setId(1);
		commodity.setName("小辣椒");
		commodity.setPrice(24.5);
		commodity.setDescribe("好吃不贵黄焖鸡米饭,好吃不贵黄焖鸡米饭");
		commodity.setPricture("");
		commodity.setState(0);
		// commodity.setCreatedTime(DateUtils.getNowTime());
		commodity.setUpdatedTime(DateUtils.getNowTime());
		commodity.setStoreId(1);
		System.out.println(commodityDaoImpl.insertCommodity(commodity));
		// System.out.println(commodityDaoImpl.updateCommodity(commodity));
		// System.out.println(commodityDaoImpl.getSimpleCommodity(1));
		// for (Commodity commodityItem : commodityDaoImpl.getMoreCommodityInfos()) {
		// System.out.println(commodityItem);
		// }
		// System.out.println(commodityDaoImpl.updateCommodityState(2, 0));
		// for(Commodity com:commodityDaoImpl.getMoreCommodityBySize(2, 1)){
		// System.out.println(com);
		// }
		for (Commodity commodityItem : commodityDaoImpl.getMoreCommodityInfos()) {
			System.out.println(commodityItem);
		}
		// describe,pricture,createdTime,updatedTime) values(1,'小辣椒',0,24.5,'好吃' at line
		// 1
	}



}
