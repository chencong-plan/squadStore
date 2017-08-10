package cc.ccoder.squadStore.dao;

import java.util.List;

import cc.ccoder.squadStore.entity.Commodity;

/**
 * @author chencong
 * @Time   2017年8月8日 下午2:40:31
 * @TODO  商品实体类的dao接口
 */
public interface ICommodityDao {
	
	/**
	 * 新增一条commodity记录
	 * @param commodity 将要被新增的实体信息
	 * @return 返回该操作是否成功
	 */
	boolean insertCommodity(Commodity commodity);

	/**
	 * 更新commodity实体信息,可以是修改信息，可以是上下架
	 * @param commodity 将要被更新的实体信息
	 * @return 返回该操作是否成功
	 */
	boolean updateCommodity(Commodity commodity);
	
	/**
	 * 根据商品ID获得到该商品的详细信息
	 * @param id 指定商品的ID
	 * @return 返回指定商品ID的实体信息
	 */
	Commodity getSimpleCommodity(Integer id);
	
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
	
	/**
	 * 进行分页处理，根据当前新式页数pageNum和每页显示大小PageSize进行分页查询商品信息
	 * @param pageNum 当前显示页数
	 * @param pageSize 每页显示大小
	 * @return 返回指定分页信息的结果集
	 */
	List<Commodity> getMoreCommodityByOnShelf(int pageNum,int pageSize);
	
	/**
	 * 进行分页处理，根据当前新式页数pageNum和每页显示大小PageSize进行分页查询商品信息
	 * @param pageNum 当前显示页数
	 * @param pageSize 每页显示大小
	 * @return 返回指定分页信息的结果集
	 */
	List<Commodity> getMoreCommodityByOffShelf(int pageNum,int pageSize);
	
	/**
	 * 修改商品的状态 上下架
	 * @param id 将要被修改的商品的id
	 * @param state 修改商品的状态
	 * @return 返回操作是否成功
	 */
	boolean updateCommodityState(Integer id,Integer state);
	
	/**
	 * 根据商品的id 将删除商品
	 * @param id
	 * @return
	 */
	boolean deleteCommodity(Integer id);
}
