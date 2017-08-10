	package cc.ccoder.squadStore.entity;

/**
 * 
 * @author chencong
 * 购物车实体
 * create table shopping(
  id int primary key identity,
  userId int not null,
  commodityId int not null,
  number int not null,
  totalPrice float not null,
  createdTime smalldatetime ,
  updatedTime smalldatetime default getdate() 
)
 */
public class Shopping {

	private Integer id;
	private Integer userId;
	private Integer commodityId ;
	private Integer number;
	private Double totalPrice;
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
	public Shopping(Integer id, Integer userId, Integer commodityId, Integer number, Double totalPrice,
			String createdTime, String updatedTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.commodityId = commodityId;
		this.number = number;
		this.totalPrice = totalPrice;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}
	public Shopping() {
		super();
	}
	@Override
	public String toString() {
		return "Shopping [id=" + id + ", userId=" + userId + ", commodityId=" + commodityId + ", number=" + number
				+ ", totalPrice=" + totalPrice + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + "]";
	}
	
}
