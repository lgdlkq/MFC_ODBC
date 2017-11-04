package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import base.BaseDAO;
import model.SCGrade;
import util.Constant;

public class SC_DAO extends BaseDAO {

	// 选课查询
	public ResultSet SCSelect(String Sno, int Cno) {
		StringBuffer SELECT = new StringBuffer("select SC.Cno,Cname,Cpno,Ccredit,Tname,SC.Tno from SC,Teacher,Course");
		if (Constant.isEmpty(Sno)) {
			SELECT.append(" and Sno = '" + Sno + "'");
		}
		if (Cno > 0) {
			SELECT.append(" and SC.Cno = " + Cno);
		}
		SELECT.append(" and SC.Cno = Course.Cno and SC.Tno = Teacher.Tno");
		String SC_SELECT = SELECT.toString().replaceFirst("and", "where");
		return select(SC_SELECT);
	}

	// 成绩相关查询方法
	public ResultSet SCSelectGrade(String sno) {
		
		StringBuffer SELECT = new StringBuffer( "select Sno,SC.Cno,Cname,Grade,Ccredit,Tno from Course,SC");
		if (Constant.isEmpty(sno)) {
			SELECT.append(" and Sno = '" + sno + "'");
		}
		SELECT.append(" and SC.Cno = Course.Cno and grade >= 0");// 查询学生的所有课程成绩
		System.out.println(SELECT.toString().replaceFirst("and", "where"));
		return select(SELECT.toString().replaceFirst("and", "where"));
	}

	//已修学分查询
	public ResultSet SCSelectCredit(String sno) {
		String SC_SELECT = "select sum(Ccredit) from SC,Course where sno = '" + sno + "' and grade >= 0 and SC.cno = Course.Cno";
		System.out.println(SC_SELECT);
		return select(SC_SELECT);
	}


	// 修改成绩
	public int SCModiy(SCGrade sc) {
		String MODIFY = "update SC set Grade = " + sc.getCgrade() + " where Sno = '" + sc.getSno() + "' and Cno = " + sc.getCno()
				+ " and Tno = '" + sc.getTno() + "'";
		connection = dbUtil.getConnection();
		try {
			stat = connection.createStatement();
			return stat.executeUpdate(MODIFY);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			close();
		}

	}

	// 删除选课记录
	public int SCdelete(SCGrade sc) {
		if (checkFirst(sc)) {
			return -1;
		}
		String DELETE = "delete from SC where Sno = '" + sc.getSno() + "' and Cno = " + sc.getCno() + " and Tno = '"
				+ sc.getTno() + "'";
		connection = dbUtil.getConnection();
		try {
			stat = connection.createStatement();
			return stat.executeUpdate(DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			close();
		}
	}

	// 添加选课
	public int SCInsert(SCGrade sc) {
		if (check(sc)) {
			return -1;
		}
		String INSERT = "insert into SC(Sno,Cno,Tno,Grade) values(?,?,?,?);";
		connection = dbUtil.getConnection();
		try {
			ps = connection.prepareStatement(INSERT);
			ps.setString(1, sc.getSno());
			ps.setInt(2, sc.getCno());
			ps.setString(3, sc.getTno());
			ps.setFloat(4, sc.getCgrade());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			close();
		}
	}

	// 检查该课程是否已选修及其先行课是否选修（判断能否选修该课程）
	public boolean check(SCGrade sc) {
		String CHECK = "select * from SC where Sno = '" + sc.getSno() + "' and Cno = " + sc.getCno();
		ResultSet rSet = select(CHECK);
		try {
			if (rSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
		String SELECT = "SELECT Cno,Cpno FROM course where Cno = " + sc.getCno();
		rSet = select(SELECT);

		try {
			while (rSet.next()) {
				ResultSet rst = SCSelect(sc.getSno(), rSet.getInt("Cpno"));
				if (rst.next()) {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	// 检查要删除的课程是否是已选课程的先行课
	public boolean checkFirst(SCGrade sc) {
		ArrayList list = new ArrayList<>();
		String CHECK = "select Cno from course where Cpno = " + sc.getCno();
		System.out.println(CHECK);
		ResultSet rSet = select(CHECK);
		try {
			if (rSet.wasNull()) {
				return false;
			}

			while(rSet.next()){
				list.add(rSet.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		String SELECT = "SELECT Cno from SC where Sno = '" + sc.getSno() + "'";
		System.out.println(SELECT);
		ResultSet rt = select(SELECT);

		try {
			while (rt.next()) {
				Iterator iterator = list.iterator();
				while(iterator.hasNext()){
					if (rt.getInt(1) == (int)iterator.next()) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// 查询执行
	public ResultSet select(String SC_SELECT) {
		connection = dbUtil.getConnection();
		try {
			stat = connection.createStatement();
			rs = stat.executeQuery(SC_SELECT);
		} catch (SQLException e) {
			e.printStackTrace();
			rs = null;
		}
		return rs;
	}
}
