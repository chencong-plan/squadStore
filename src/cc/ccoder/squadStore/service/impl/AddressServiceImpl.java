package cc.ccoder.squadStore.service.impl;

import java.util.List;

import cc.ccoder.squadStore.dao.IAddressDao;
import cc.ccoder.squadStore.dao.impl.AddressDaoImpl;
import cc.ccoder.squadStore.entity.Address;
import cc.ccoder.squadStore.service.IAddressService;

public class AddressServiceImpl implements IAddressService {

	private IAddressDao iaddressDao = new AddressDaoImpl();

	@Override
	public boolean insertAddress(Address address) {
		if (address == null) {
			return false;
		}
		return iaddressDao.insertAddress(address);
	}

	@Override
	public boolean updateAddress(Address address) {
		if (address == null) {
			return false;
		}
		return iaddressDao.updateAddress(address);
	}

	@Override
	public boolean updateAddressState(Integer id, Integer state) {
		if (id == null || state == null) {
			return false;
		}
		return iaddressDao.updateAddressState(id, state);
	}

	@Override
	public Address getSimpleAddress(Integer id) {
		if (id == null) {
			return null;
		}
		return iaddressDao.getSimpleAddress(id);
	}

	@Override
	public List<Address> getMoreAddressInfos(Integer userId) {
		if (userId == null) {
			return null;
		}
		return iaddressDao.getMoreAddressInfos(userId);
	}

	@Override
	public boolean deleteAddress(Integer id) {
		if (id == null) {
			return false;
		}
		return iaddressDao.deleteAddress(id);
	}

	@Override
	public Address getSimpleAddressByuserIdAndState(Integer userId, Integer state) {
		if (userId == null || state == null) {
			return null;
		}
		return iaddressDao.getSimpleAddressByuserIdAndState(userId, state);
	}

}
