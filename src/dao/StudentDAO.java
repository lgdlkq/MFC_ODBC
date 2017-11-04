package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import base.BaseDAO;
import model.Student;
import util.Constant;

public class StudentDAO extends BaseDAO {

	// 学生表查询方法
	public ResultSet stuSelecte(Student stu) {
		StringBuffer SELECT = new StringBuffer("select * from Student");
		if (Constant.isEmpty(stu.getSno())) {
			SELECT.append(" and Sno like '%" + stu.getSno() + "%'");
		}
		if (Constant.isEmpty(stu.getSname())) {
			SELECT.append(" and Sname like '%" + stu.getSname() + "%'");
		}
		if (Constant.isEmpty(stu.getSclass())) {
			SELECT.append(" and Sclass like '%" + stu.getSclass() + "%'");
		}
		if (Constant.isEmpty(stu.getSsex())) {
			SELECT.append(" and Ssex like '%" + stu.getSsex() + "%'");
		}
		if (stu.getSage() > 0) {
			SELECT.append(" and Sage = " + stu.getSage());
		}
		if (Constant.isEmpty(stu.getStel())) {
			SELECT.append(" and Stel like '%" + stu.getStel() + "%'");
		}
		if (Constant.isEmpty(stu.getSdept())) {
			SELECT.append(" and Sdept like '%" + stu.getSdept() + "%'");
		}
		if (Constant.isEmpty(stu.getSpass())) {
			SELECT.append(" and Password like '" + stu.getSpass() + "'");
		}
		connection = dbUtil.getConnection();
		try {
			ps = connection.prepareStatement(SELECT.toString().replaceFirst("and", "where"));
			rs = ps.executeQuery();
		} catch (SQLException e) {
			rs = null;
			e.printStackTrace();
		}

		return rs;
	}

	// 学生表修改信息
	public int stuModify(String sno, Student stu) {
		String STUDENT_MODIFY = "update student set Sclass = ?,Sno = ?,Sname = ?,Sage = ?,Ssex = ?, Stel = ?,Sdept = ?,Password = ?"
				+ "where Sno = " + sno;
		int result = excuting(stu, STUDENT_MODIFY);
		close();
		return result;
	}

	// 学生表插入信息
	public int stuInsert(Student stu) {
		String STUDENT_INSET = "inseRt into student(Sclass,Sno,Sname,Sage,Ssex,Stel,Sdept,Password) values(?,?,?,?,?,?,?,?);";
		int result = excuting(stu, STUDENT_INSET);
		return result;
	}

	// 学生表删除信息
	public int stuDelete(Student stu) {
		StringBuffer DELETE = new StringBuffer("delete from Student");
		if (Constant.isEmpty(stu.getSno())) {
			DELETE.append(" and Sno = '" + stu.getSno() + "'");
		}
		if (Constant.isEmpty(stu.getSname())) {
			DELETE.append(" and Sname = '" + stu.getSname() + "'");
		}
		if (Constant.isEmpty(stu.getSclass())) {
			DELETE.append(" and Sclass = '" + stu.getSclass() + "'");
		}
		if (Constant.isEmpty(stu.getSdept())) {
			DELETE.append(" and Sdept = '" + stu.getSdept() + "'");
		}

		connection = dbUtil.getConnection();
		try {
			System.out.println(DELETE.toString().replaceFirst("and", "where"));
			ps = connection.prepareStatement(DELETE.toString().replaceFirst("and", "where"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}	
	}

	// 更新与插入
	public int excuting(Student stu, String str) {
		connection = dbUtil.getConnection();
		try {
			ps = connection.prepareStatement(str);
			ps.setString(1, stu.getSclass());
			ps.setString(2, stu.getSno());
			ps.setString(3, stu.getSname());
			ps.setInt(4, stu.getSage());
			ps.setString(5, stu.getSsex());
			ps.setString(6, stu.getStel());
			ps.setString(7, stu.getSdept());
			if (stu.getSpass() != null) {
				ps.setString(8, stu.getSpass());
			}else{
				ps.setString(8, "123456");
			}
			return ps.executeUpdate();
		}catch (MySQLIntegrityConstraintViolationException e) {
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
