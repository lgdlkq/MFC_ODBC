package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import base.BaseDAO;
import model.Teacher;
import util.Constant;

public class TC_DAO extends BaseDAO{

	//选课系统
	public ResultSet TCQuery(){
		String QUERY = "select TC.Cno,Cname,Cpno,Ccredit,Tname,TC.Tno from TC,Teacher,Course where TC.Tno = Teacher.Tno and TC.Cno = Course.Cno order by TC.Cno;";
		connection = dbUtil.getConnection();
		System.out.println(QUERY);
		try {
			ps = connection.prepareStatement(QUERY);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	//选择该老师所教某课程的学生成绩
	public ResultSet TCGrade(String Tno,String Cname){
		String GRADE = "select SC.Sno,Sname,Ssex,Stel,grade from SC,Student,Course where SC.Tno = '" + Tno + "' and SC.Cno = Course.Cno"
				+ " and Cname = '" + Cname + "' and SC.Sno = Student.Sno";
		
		connection = dbUtil.getConnection();
		System.out.println(GRADE);
		try {
			ps = connection.prepareStatement(GRADE);
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//教师所教课程查询
	public ResultSet TCSelecte(Teacher tea){
		StringBuffer SELECT = new StringBuffer("select TC.Cno,Cname,TC.Tno,Tname from TC,Course,Teacher");
		if (Constant.isEmpty(tea.getTno())) {
			SELECT.append(" and Tc.Tno = '" + tea.getTno() + "'");
		}
		if (Constant.isEmpty(tea.getTname())) {
			SELECT.append(" and Teacher.Tname = '" + tea.getTname() + "'");
		}
		if (tea.getCno() > 0) {
			SELECT.append(" and TC.Cno = " + tea.getCno());
		}
		if (Constant.isEmpty(tea.getCname())) {
			SELECT.append(" and Course.Cname like '%" + tea.getCname() + "%'");
		}
		SELECT.append(" and TC.Cno = Course.Cno and TC.Tno = Teacher.Tno");
		connection = dbUtil.getConnection();
		try {
			ps = connection.prepareStatement(SELECT.toString().replaceFirst("and", "where"));
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			rs = null;
		}
		return rs;
	}
	
	//添加教师教课记录
	public int TCInsert(Teacher tea){
		String INSERT = "insert into tc(Tno,Cno) values(?,?);";
		connection = dbUtil.getConnection();
		try {
			ps = connection.prepareStatement(INSERT);
			ps.setString(1, tea.getTno());
			ps.setInt(2, tea.getCno());
			return ps.executeUpdate();
		} catch (MySQLIntegrityConstraintViolationException e) {
			return 0;
		}catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	//删除教师教课记录
	public int TCDelete(Teacher tea){
		StringBuffer DELETE = new StringBuffer("delete from TC");
		if (Constant.isEmpty(tea.getTno())) {
			DELETE.append(" and Tc.Tno = '" + tea.getTno() + "'");
		}
		if (tea.getCno() > 0) {
			DELETE.append(" and TC.Cno = " + tea.getCno());
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
}
