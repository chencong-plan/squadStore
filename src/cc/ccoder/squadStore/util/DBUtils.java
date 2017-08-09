package cc.ccoder.squadStore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * 
 * @author chencong
 * @Time 2017年8月8日 上午11:03:05
 * @TODO 提供数据库的一系列方法
 */
public class DBUtils {

//	private static final String Driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	private static final String url = "jdbc:sqlserver://localhost:1433;DataBaseName=squadStore";
//	private static final String user = "sa";
//	private static final String password = "123456";
	
	private static final String Driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/squadStore";
	private static final String user = "root";
	private static final String password = "123456";
	
	private static Connection connection = null;
	
	private  DBUtils(){
		try {
			Class.forName(Driver);
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (connection == null) {
			new DBUtils();
			return connection;
		}
		return connection;
	}
	
	public static void main(String[] args) {
		if (getConnection() == null) {
			System.out.println("eror");
		}else {
			System.out.println("ok");
		}
	}
}
