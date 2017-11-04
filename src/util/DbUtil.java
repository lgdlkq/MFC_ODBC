package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private Connection conn;

	// 数据库连接
	public Connection getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				Class.forName(Constant.jdbcName);
				conn = (Connection) DriverManager.getConnection(Constant.dbUrl, Constant.dbUserName,
						Constant.dbPassword);
				System.out.println("数据库已连接！");
			}
		} catch (SQLException e) {
			System.out.println("数据库连接失败！数据库还未创建");
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动错误！");
		}
		return conn;
	}

	// 关闭数据库连接
	@SuppressWarnings("null")
	public void closeConnection(Connection con) throws SQLException {
		if (con != null || !con.isClosed()) {
			con.close();
			System.out.println("数据库连接已关闭");
		}
	}

	public static DbUtil getDBUtil() {
		return new DbUtil();
	}

}
