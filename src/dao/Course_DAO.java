package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import base.BaseDAO;
import model.Course;
import model.Student;
import util.Constant;

public class Course_DAO extends BaseDAO {

	public ResultSet CSelect(Course course) {
		StringBuffer sql = new StringBuffer("select * from course");
		if (course.getCno() > 0) {
			sql.append(" and cno = " + course.getCno());
		}
		if (Constant.isEmpty(course.getCname())) {
			sql.append(" and cname like '%" + course.getCname() + "%'");
		}
		if (course.getCpno() > 0) {
			sql.append(" and cpno = " + course.getCpno());
		}
		if (course.getCcredit() > 0) {
			sql.append(" and ccredit = " + course.getCcredit());
		}
		sql.append(" and cno > 0");
		connection = dbUtil.getConnection();
		try {
			System.out.println(sql.toString().replaceFirst("and", "where"));
			ps = connection.prepareStatement(sql.toString().replaceFirst("and", "where"));
			rs = ps.executeQuery();
		} catch (SQLException e) {
			rs = null;
			e.printStackTrace();
		}

		return rs;
	}

	public int CModify(Course course, int Cno) {
		String MODIFY = "update course set cno = ?,cname = ?,cpno = ?,ccredit = ? where cno = " + Cno;
		int result = excuting(course, MODIFY);
		return result;
	}

	public int CInsert(Course course){
		String sql = "insert into Course(Cno,Cname,Cpno,Ccredit) values(?,?,?,?)";
		return excuting(course, sql);
	}
	
	public int Cdelete(Course course){
		String sql = "delete from Course where Cno = " + course.getCno();
		connection = dbUtil.getConnection();
		try {
			stat = connection.createStatement();
			return stat.executeUpdate(sql);
		} catch (MySQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			return 0;
		}catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	// 更新与插入
	public int excuting(Course course, String str) {
		connection = dbUtil.getConnection();
		try {
			ps = connection.prepareStatement(str);
			ps.setInt(1, course.getCno());
			ps.setString(2, course.getCname());
			ps.setInt(3, course.getCpno());
			ps.setFloat(4, course.getCcredit());
			return ps.executeUpdate();
		} catch (MySQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
