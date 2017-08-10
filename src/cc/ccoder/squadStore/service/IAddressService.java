package cc.ccoder.squadStore.service;

import java.util.List;

import cc.ccoder.squadStore.entity.Address;

public interface IAddressService {

	/**
	 * 插入一条新的收货地址记录
	 * @param address 将要被插入的收货地址实体信息
	 * @return 返回该条记录是否成功插入
	 */
	boolean insertAddress(Address address);
	
	
	/**
	 * 更新某条收货地址的信息
	 * @param address 修改后将要被提交的收货地址信息
	 * @return 返回该条记录是否被更新成功
	 */
	boolean updateAddress(Address address);
	
	/**
	 * 修改某一条收货地址的状态,根据state是否将其设置为默认收货地址
	 * @param id 将要被被修改的收货地址的ID
	 * @param state 收货地址修改之后的状态
	 * @return  返回该条记录是否被修改成功
	 */
	boolean updateAddressState(Integer id,Integer state);
	
	/**
	 * 根据收货地址ID或得到该条收货地址的详细信息
	 * @param id 将要被查询的收货地址的ID
	 * @return 返回指定收货地址ID的实体信息
	 */
	Address getSimpleAddress(Integer id);
	
	/**
	 * 根据用户userId获得指定用户的所有收货地址信息
	 * @param userId 将要查询的地址信息的用户ID
	 * @return 返回该用户的所有收货地址信息，结果为集合
	 */
	List<Address> getMoreAddressInfos(Integer userId);
	
	/**
	 * 根据收货地址ID进行删除该条收获地址记录
	 * @param id 将要被删除的地址ID
	 * @return 返回该操作是否操作成功boolean
	 */
	boolean deleteAddress(Integer id);
	
	/**
	 * 根据用户id 和状态id 获得该用户的默认地址信息
	 * @param userId 用户id
	 * @param state 默认地址状态 1
	 * @return 返回该用户的默认地址实体信息
	 */
	Address getSimpleAddressByuserIdAndState(Integer userId,Integer state);
}
