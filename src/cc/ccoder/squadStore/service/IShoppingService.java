package cc.ccoder.squadStore.service;

import java.util.List;

import cc.ccoder.squadStore.entity.Shopping;

public interface IShoppingService {

	boolean addToShopping(Shopping shopping);
	
	List<Shopping> getMoreShoppings();
}
