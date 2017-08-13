package cc.ccoder.squadStore.entity;

/**
 * 订单表
 * 
 * @author chencong
 *
 */
public class Order {

	private Integer id;
	private String name;
	private String address;
	private String phone;
	private Integer deliverState;
	private Integer state;
	private String createdTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getDeliverState() {
		return deliverState;
	}

	public void setDeliverState(Integer deliverState) {
		this.deliverState = deliverState;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public Order(Integer id, String name, String address, String phone, Integer deliverState, Integer state,
			String createdTime) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.deliverState = deliverState;
		this.state = state;
		this.createdTime = createdTime;
	}

	public Order() {
		super();
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", deliverState="
				+ deliverState + ", state=" + state + ", createdTime=" + createdTime + "]";
	}

}
