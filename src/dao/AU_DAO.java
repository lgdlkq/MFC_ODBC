package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import base.BaseDAO;
import model.AU;

public class AU_DAO extends BaseDAO {

	//密码匹配
	public ResultSet AUSelect(AU au){
		String SELECT = "select * from au where Uno = '" + au.getUname() + "' and Password = '" + au.getUpassword() + "'";
		connection = dbUtil.getConnection();
		try {
			ps = connection.prepareStatement(SELECT);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			rs = null;
			e.printStackTrace();
		}
		return rs;
	}
	
	//修改管理员密码
	public int AUmodify(AU au) {
		String MODIFY = "update au set password = '" +au.getUpassword() + "' where uno = '" + au.getUname() + "'";
		connection = dbUtil.getConnection();
		try {
			stat = connection.createStatement();
			return stat.executeUpdate(MODIFY);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
