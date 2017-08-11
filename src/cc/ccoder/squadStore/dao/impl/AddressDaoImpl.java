package cc.ccoder.squadStore.dao.impl;

import java.util.Arrays;
import java.util.List;


import cc.ccoder.squadStore.dao.IAddressDao;
import cc.ccoder.squadStore.entity.Address;
import cc.ccoder.squadStore.util.DBOperatorUtils;

/**
 * 用户收货地址接口实现
 * @author chencong
 *
 */
public class AddressDaoImpl implements IAddressDao {

	@Override
	public boolean insertAddress(Address address) {
		String sql = "insert into address (userId,province,city,area,address,state,phone,createdTime,updatedTime) "
				+ "values (?,?,?,?,?,?,?,?,?)";
		List<Object> params = Arrays.asList(
				address.getUserId(),
				address.getProvince(),
				address.getCity(),
				address.getArea(),
				address.getAddress(),
				address.getArea(),
				address.getPhone(),
				address.getCreatedTime(),
				address.getUpdatedTime());
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

	@Override
	public boolean updateAddress(Address address) {
		String sql = "update address set province=? ,city=?,area=?,address=?,state=?,phone=?,updatedTime=? where id=?";
		List<Object> params = Arrays.asList(
				address.getProvince(),
				address.getCity(),
				address.getArea(),
				address.getAddress(),
				address.getState(),
				address.getPhone(),
				address.getUpdatedTime(),
				address.getId());
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

	@Override
	public boolean updateAddressState(Integer id, Integer state) {
		String sql="update address set state=? where id=?";
		List<Object> params = Arrays.asList(state,id);
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}

	@Override
	public Address getSimpleAddress(Integer id) {
		String sql = "select * from address where id =?";
		List<Object> params = Arrays.asList(id);
		return DBOperatorUtils.getSimpleResult(sql, params, Address.class);
	}

	@Override
	public List<Address> getMoreAddressInfos(Integer userId) {
		String sql = "select * from address where userId = ?";
		List<Object> params = Arrays.asList(userId);
		return DBOperatorUtils.getMoreResult(sql, params, Address.class);
	}

	@Override
	public boolean deleteAddress(Integer id) {
		String sql = "delete from address where id = ? ";
		List<Object> params = Arrays.asList(id);
		return DBOperatorUtils.excuteUpdateResult(sql, params);
	}
	
	@Override
	public Address getSimpleAddressByuserIdAndState(Integer userId, Integer state) {
		String sql = "select * from address where userId=? and state=?";
		List<Object> params = Arrays.asList(userId,state);
		return DBOperatorUtils.getSimpleResult(sql, params, Address.class);
	}

	
	public static void main(String[] args) {
		AddressDaoImpl addressDaoImpl = new AddressDaoImpl();
		for (Address address : addressDaoImpl.getMoreAddressInfos(1)) {
			System.out.println(address);
		}
	}

	
}
