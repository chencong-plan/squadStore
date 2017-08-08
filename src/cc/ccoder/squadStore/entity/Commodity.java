package cc.ccoder.squadStore.entity;

/**
 * 
 * @author chencong 商品详情实体
 * 
 * create table commodity(
  id int primary key identity,
  storeId int not null,
  name varchar(30) not null,
  state nvarchar(1) not null,
  price float not null,
  describe varchar(100) , 
  pricture varchar(200) ,
  createdTime smalldatetime ,
  updatedTime smalldatetime default getdate() 
)
 */
public class Commodity {
	
	private Integer id;
	private Integer storeId;
	private String name;
	private Integer state;
	private Double price;
	private String describe;
	private String pricture;
	private String createdTime;
	private String updatedTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getPricture() {
		return pricture;
	}
	public void setPricture(String pricture) {
		this.pricture = pricture;
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
	public Commodity(Integer id, Integer storeId, String name, Integer state, Double price, String describe,
			String pricture, String createdTime, String updatedTime) {
		super();
		this.id = id;
		this.storeId = storeId;
		this.name = name;
		this.state = state;
		this.price = price;
		this.describe = describe;
		this.pricture = pricture;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}
	public Commodity() {
		super();
	}
	@Override
	public String toString() {
		return "Commodity [id=" + id + ", storeId=" + storeId + ", name=" + name + ", state=" + state + ", price="
				+ price + ", describe=" + describe + ", pricture=" + pricture + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + "]";
	}
	
}
