package cc.ccoder.squadStore.entity;

/**
 * 
 * @author chencong 订单表
 * 
 *         create table [order_details]( id int primary key identity , userId
 *         int not null, commodityId int not null, addressId int not null, price
 *         float not null, number int check(number >= 1) not null, totalPrice
 *         float not null, deliverState nchar(1) not null, state nvarchar(1) not
 *         null, createdTime smalldatetime , updatedTime smalldatetime default
 *         getdate() )
 */
public class OrderDetails {

	private Integer id;
	private Integer userId;
	private Integer commodityId;
	private Integer addressId;
	private Double price;
	private Integer number;
	private Double totalPrice;
	private Integer deliverState;
	private Integer state;
	private Integer orderId;
	private String createdTime;
	private String updatedTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public OrderDetails(Integer id, Integer userId, Integer commodityId, Integer addressId, Double price,
			Integer number, Double totalPrice, Integer deliverState, Integer state, Integer orderId, String createdTime,
			String updatedTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.commodityId = commodityId;
		this.addressId = addressId;
		this.price = price;
		this.number = number;
		this.totalPrice = totalPrice;
		this.deliverState = deliverState;
		this.state = state;
		this.orderId = orderId;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public OrderDetails() {
		super();
	}

	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", userId=" + userId + ", commodityId=" + commodityId + ", addressId="
				+ addressId + ", price=" + price + ", number=" + number + ", totalPrice=" + totalPrice
				+ ", deliverState=" + deliverState + ", state=" + state + ", orderId=" + orderId + ", createdTime="
				+ createdTime + ", updatedTime=" + updatedTime + "]";
	}

}
