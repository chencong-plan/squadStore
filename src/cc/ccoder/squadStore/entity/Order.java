package cc.ccoder.squadStore.entity;

/**
 * 
 * @author chencong
 * 订单表
 * 
 * create table [order](
  id int primary key identity ,
  userId int not null,
  commodityId int not null,
  addressId int not null,
  price float not null,
  number int check(number >= 1) not null,
  totalPrice float not null,
  deliverState nchar(1) not null,
  state nvarchar(1) not null,
  createdTime smalldatetime ,
  updatedTime smalldatetime default getdate()
)
 */
public class Order {

	private Integer id;
	private Integer userId;
	private Integer commodityId ;
	private Integer addressId;
	private Double price;
	private Integer number;
	private Double totalPrice;
	private Integer state;
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
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public Order(Integer id, Integer userId, Integer commodityId, Integer addressId, Double price, Integer number,
			Double totalPrice, Integer state, String createdTime, String updatedTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.commodityId = commodityId;
		this.addressId = addressId;
		this.price = price;
		this.number = number;
		this.totalPrice = totalPrice;
		this.state = state;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}
	public Order() {
		super();
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", commodityId=" + commodityId + ", addressId=" + addressId
				+ ", price=" + price + ", number=" + number + ", totalPrice=" + totalPrice + ", state=" + state
				+ ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + "]";
	}
}
