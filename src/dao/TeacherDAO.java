package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import base.BaseDAO;
import model.Teacher;
import util.Constant;

public class TeacherDAO extends BaseDAO {

	// 教师表查询方法
	public ResultSet teaSelecte(Teacher tea){
		
		StringBuffer SELECT = new StringBuffer("select * from teacher");
		if(Constant.isEmpty(tea.getTno())){
			SELECT.append(" and Tno like '%" + tea.getTno() + "%'");
		}
		if(Constant.isEmpty(tea.getTname())){
			SELECT.append(" and Tname like '%" + tea.getTname() + "%'");
		}
		if(Constant.isEmpty(tea.getTsex())){
			SELECT.append(" and Tsex like '%" + tea.getTsex() + "%'");
		}
		if(Constant.isEmpty(tea.getTtel())){
			SELECT.append(" and Ttel like '%" + tea.getTtel() + "%'");
		}
		if(Constant.isEmpty(tea.getTpass())){
			SELECT.append(" and Password like '%" + tea.getTpass() + "%'");
		}
	
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

	// 教师表信息的修改
	public int teaModify(Teacher teacher) {
		String TEACHER_MODIFY = "update teacher set Tno = ?,Tname = ?,Tsex = ?,Ttel = ?, Password = ? "
				+ "where Tno = '" + teacher.getTno() + "'";
		return excuting(teacher, TEACHER_MODIFY);
		
	}

	//教师表信息的插入
	public int teaInsert(Teacher teacher){
		String TEACHER_INSERT = "insert into teacher(Tno,Tname,Tsex,Ttel,Password) values(?,?,?,?,?);";
		int result = excuting(teacher, TEACHER_INSERT);
		close();
		return result; 
		
	}
	
	//教师表信息的删除
	public int teaDelete(Teacher tea){
		String TEACHER_DELETE = "delete from teacher where Tno = " + tea.getTno() + ";";
		connection = dbUtil.getConnection();
		try {
			stat = connection.createStatement();
			return stat.executeUpdate(TEACHER_DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return 0;
	}

	//更新与插入
	public int excuting(Teacher tea, String str){
		connection = dbUtil.getConnection();
		try {
			ps =connection.prepareStatement(str);
			ps.setString(1, tea.getTno());
			ps.setString(2, tea.getTname());
			ps.setString(3, tea.getTsex());
			ps.setString(4, tea.getTtel());
			if (tea.getTpass() != null) {
				ps.setString(5, tea.getTpass());
			}else {
				ps.setString(5, "123456");
			}
			return ps.executeUpdate();
		}catch (MySQLIntegrityConstraintViolationException e) {
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
