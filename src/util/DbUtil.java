package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private Connection conn;

	// ���ݿ�����
	public Connection getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				Class.forName(Constant.jdbcName);
				conn = (Connection) DriverManager.getConnection(Constant.dbUrl, Constant.dbUserName,
						Constant.dbPassword);
				System.out.println("���ݿ������ӣ�");
			}
		} catch (SQLException e) {
			System.out.println("���ݿ�����ʧ�ܣ����ݿ⻹δ����");
		} catch (ClassNotFoundException e) {
			System.out.println("���ݿ���������");
		}
		return conn;
	}

	// �ر����ݿ�����
	@SuppressWarnings("null")
	public void closeConnection(Connection con) throws SQLException {
		if (con != null || !con.isClosed()) {
			con.close();
			System.out.println("���ݿ������ѹر�");
		}
	}

	public static DbUtil getDBUtil() {
		return new DbUtil();
	}

}