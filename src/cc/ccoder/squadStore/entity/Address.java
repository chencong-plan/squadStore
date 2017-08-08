package cc.ccoder.squadStore.entity;

/**
 * 
 * @author chencong 用户收货地址实体
 * 
 * create table address(
  id int primary key identity,
  userId int not null,
  province varchar(20) ,
  city varchar(20),
  area varchar(20),
  address varchar(50) not null,
  state nchar(1) default 0 not null,
  phone varchar(20) not null,
  createdTime smalldatetime ,
  updatedTime smalldatetime default getdate() 
)
 */
public class Address {
	
	private Integer id;
	private Integer userId;
	private String province;
	private String city;
	private String area;
	private String address;
	private Integer state;
	private String phone;
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
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public Address(Integer id, Integer userId, String province, String city, String area, String address, Integer state,
			String phone, String createdTime, String updatedTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.province = province;
		this.city = city;
		this.area = area;
		this.address = address;
		this.state = state;
		this.phone = phone;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}
	public Address() {
		super();
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", userId=" + userId + ", province=" + province + ", city=" + city + ", area="
				+ area + ", address=" + address + ", state=" + state + ", phone=" + phone + ", createdTime="
				+ createdTime + ", updatedTime=" + updatedTime + "]";
	}
}
