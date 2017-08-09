package cc.ccoder.squadStore.service;

import java.util.List;

import cc.ccoder.squadStore.entity.Commodity;

public interface ICommodityService {

	/**
	 * 获得该店铺的所有商品信息
	 * @return 返回所有商品信息集合
	 */
	List<Commodity> getMoreCommodityInfos();
	
	/**
	 * 进行分页处理，根据当前新式页数pageNum和每页显示大小PageSize进行分页查询商品信息
	 * @param pageNum 当前显示页数
	 * @param pageSize 每页显示大小
	 * @return 返回指定分页信息的结果集
	 */
	List<Commodity> getMoreCommodityBySize(int pageNum,int pageSize);
	
	
}
