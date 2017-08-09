package cc.ccoder.squadStore.service.impl;

import java.util.List;

import cc.ccoder.squadStore.dao.ICommodityDao;
import cc.ccoder.squadStore.dao.impl.CommodityDaoImpl;
import cc.ccoder.squadStore.entity.Commodity;
import cc.ccoder.squadStore.service.ICommodityService;

public class CommodityServiceImpl implements ICommodityService {

	// 将Commodity的dao层接口注入进来
	private ICommodityDao iCommodityDao = new CommodityDaoImpl();

	@Override
	public List<Commodity> getMoreCommodityInfos() {
		return iCommodityDao.getMoreCommodityInfos();
	}

	@Override
	public List<Commodity> getMoreCommodityBySize(int pageNum, int pageSize) {
		if (pageNum <= 0 || pageSize <= 0) {
			return null;
		}
		return iCommodityDao.getMoreCommodityBySize(pageNum, pageSize);
	}

}
