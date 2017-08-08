package cc.ccoder.squadStore.entity;

/**
 * 
 * @author chencong
 *  用户实体层
 *  create table userInfo(
  id int  primary key identity ,
  username varchar(20) not null,
  password varchar(20) not null,
  nickname varchar(30) ,
  email varchar(30) ,
  note varchar(200) ,
  createdTime smalldatetime,
  updatedTime smalldatetime default getdate()
)
 */
public class UserInfo {

	private Integer id;
	private String username;
	private String password;
	private String nickname;
	private String email;
	private String note;
	private String createdTime;
	private String updatedTime;
	
	
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	public UserInfo(Integer id, String username, String password, String nickname, String email, String note,
			String createdTime, String updatedTime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.note = note;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}
	public UserInfo() {
		super();
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", email=" + email + ", note=" + note + ", createdTime=" + createdTime + ", updatedTime="
				+ updatedTime + "]";
	}
}
