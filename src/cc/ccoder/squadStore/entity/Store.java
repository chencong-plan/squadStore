package cc.ccoder.squadStore.entity;

/**
 * 
 * @author chencong
 *  店铺实体
 *  
 *  create table store(
  id int primary key identity not null,
  username varchar(20) not null,
  password varchar(20) not null,
  storename varchar(30) not null,
  phone varchar(20) not null,
  address varchar(50) not null,
  describe varchar(200) ,
)
 */
public class Store {

	private Integer id;
	private String username;
	private String password;
	private String storename;
	private String phone;
	private String address;
	private String describe;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStorename() {
		return storename;
	}
	public void setSotrename(String storename) {
		this.storename = storename;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Store(Integer id, String username, String password, String storename, String phone, String address,
			String describe) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.storename = storename;
		this.phone = phone;
		this.address = address;
		this.describe = describe;
	}
	public Store() {
		super();
	}
	@Override
	public String toString() {
		return "Store [id=" + id + ", username=" + username + ", password=" + password + ", storename=" + storename
				+ ", phone=" + phone + ", address=" + address + ", describe=" + describe + "]";
	}
}
