package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
	private DbUtil du;

	// 创建数据库
	public void createDateBase() throws Exception {
		if (du == null) {
			du = new DbUtil();
		}
		try {
			Class.forName(Constant.jdbcName);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		Connection connection = du.getConnection();

		if (connection != null) {

		} else {
			// 一开始必须填一个已经存在的数据库
			String url = "jdbc:mysql://localhost:3306/mysql?useSSL=true&useUnicode=true&characterEncoding=utf-8";
			connection = DriverManager.getConnection(url, Constant.dbUserName, Constant.dbPassword);
			Statement stat = connection.createStatement();

			// 创建数据库
			try {
				stat.executeUpdate("create database sams");// Student
															// achievement
															// management system
				System.out.println("创建数据库成功！");
			} catch (SQLException e) {
				e.printStackTrace();
			}

			// 打开创建的数据库
			stat.close();
			connection.close();

			connection = du.getConnection();
			stat = (Statement) connection.createStatement();
			stuTable(stat);
			teaTable(stat);
			couTable(stat);
			SCTable(stat);
			TCTable(stat);
			AUTable(stat);
			stat.close();
			connection.close();
		}
	}

	// 判断表的建立
	@SuppressWarnings("finally")
	public ResultSet getRes(Statement stat, String tableName) {
		ResultSet rSet = null;
		try {
			rSet = stat.getConnection().getMetaData().getTables(null, null, tableName, null);
		} catch (SQLException e) {
			System.out.println(tableName + "数据库还未创建！");
		} finally {
			return rSet;
		}
	}

	// 学生表
	public void stuTable(Statement stat) throws SQLException {
		if (getRes(stat, "Student").next()) {

		} else {
			try {
				stat.executeUpdate(
						"create table Student(" + "Sclass char(8)," + "Sno char(10) primary key," + "Sname char(20),"
								+ "Sage int," + "Ssex enum('男', '女') not null," + "Stel char(11) unique," + "Sdept char(20),"
										+ "Password char(16) not null default '123456')");
				System.out.println("Student表创建成功！");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Student表创建失败！");
			}
		}
	}

	// 教师表
	public void teaTable(Statement stat) throws SQLException {
		if (getRes(stat, "Teacher").next()) {

		} else {
			try {
				stat.executeUpdate("create table Teacher(" + "Tno char(10) primary key," + "Tname char(10),"
						+ "Tsex enum('男','女') not null," + "Ttel char(11) unique,"
								+ "Password char(16) not null default '123456')");
				System.out.println("Teacher表创建成功！");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Teacher表创建失败！");
			}
		}
	}

	// 课程表
	public void couTable(Statement stat) throws SQLException {
		if (getRes(stat, "Course").next()) {

		} else {
			try {
				stat.executeUpdate("create table Course(" + "Cno int primary key," + "Cname char(40) unique,"
						+ "Cpno int," + "Ccredit int," + "foreign key(Cpno) references Course(Cno))");
				System.out.println("Course表创建成功！");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Course表创建失败！");
			}
		}
	}

	// SC表
	public void SCTable(Statement stat) throws SQLException {
		if (getRes(stat, "SC").next()) {

		} else {
			try {
				stat.executeUpdate("create table SC(" + "Sno char(10)," + "Cno int," + "Tno char(10)," + "Grade float,"
						+ "primary key(Sno,Cno,Tno)," + "index (Sno)," + "index (Cno),"
						+ "foreign key(Sno) references Student(Sno) on delete cascade on update cascade,"
						+ "foreign key(Tno) references Teacher(Tno),"
						+ "foreign key(Cno) references Course(Cno) on delete cascade on update cascade)engine=InnoDB");
				System.out.println("SC表创建成功！");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SC表创建失败！");
			}
		}
	}

	// TC表
	public void TCTable(Statement stat) throws SQLException {
		if (getRes(stat, "TC").next()) {

		} else {
			try {
				stat.executeUpdate("create table TC(" + "Tno char(10)," + "Cno int," + "primary key(Tno,Cno),"
						+ "index (Tno)," + "index (Cno),"
						+ "foreign key(Tno) references Teacher(Tno) on delete cascade on update cascade,"
						+ "foreign key(Cno) references Course(Cno) on delete cascade on update cascade)engine=InnoDB");
				System.out.println("TC表创建成功！");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("TC表创建失败！");
			}
		}
	}
	
	public void AUTable(Statement stat) throws SQLException{
		if(getRes(stat, "AU").next()){
			
		}else{
			try{
				stat.executeUpdate("create table AU("
						+ "Uno char(10) primary key,"
						+ "Password char(16) not null default 'nuc123')");
				System.out.println("AU表创建成功！");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("AU表创建失败！");
			}
		}
	}
}
