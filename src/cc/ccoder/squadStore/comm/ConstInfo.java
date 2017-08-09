package cc.ccoder.squadStore.comm;


public enum ConstInfo {

	PARAM_ERROR("参数错误",1),
	LOGIN_FAILE("登录失败",2),
	LOGIN_SUCCESS("登录成功",3),
	USERNAME_EXISTS("用户名已经存在",4),
	USER_NOT_LOGIN("用户未登录",5),
	ERROR("操作失败",6),
	SUCCESS("操作成功",7),
	FILE_PATH("user_login.txt",8);
	
	
	private String name;
	private int index;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	private ConstInfo(String name,int index){
		this.name = name;
		this.index = index;
	}
	
}
