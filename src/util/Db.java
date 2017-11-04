package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
	private DbUtil du;

	// �������ݿ�
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
			// һ��ʼ������һ���Ѿ����ڵ����ݿ�
			String url = "jdbc:mysql://localhost:3306/mysql?useSSL=true&useUnicode=true&characterEncoding=utf-8";
			connection = DriverManager.getConnection(url, Constant.dbUserName, Constant.dbPassword);
			Statement stat = connection.createStatement();

			// �������ݿ�
			try {
				stat.executeUpdate("create database sams");// Student
															// achievement
															// management system
				System.out.println("�������ݿ�ɹ���");
			} catch (SQLException e) {
				e.printStackTrace();
			}

			// �򿪴��������ݿ�
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

	// �жϱ��Ľ���
	@SuppressWarnings("finally")
	public ResultSet getRes(Statement stat, String tableName) {
		ResultSet rSet = null;
		try {
			rSet = stat.getConnection().getMetaData().getTables(null, null, tableName, null);
		} catch (SQLException e) {
			System.out.println(tableName + "���ݿ⻹δ������");
		} finally {
			return rSet;
		}
	}

	// ѧ����
	public void stuTable(Statement stat) throws SQLException {
		if (getRes(stat, "Student").next()) {

		} else {
			try {
				stat.executeUpdate(
						"create table Student(" + "Sclass char(8)," + "Sno char(10) primary key," + "Sname char(20),"
								+ "Sage int," + "Ssex enum('��', 'Ů') not null," + "Stel char(11) unique," + "Sdept char(20),"
										+ "Password char(16) not null default '123456')");
				System.out.println("Student�������ɹ���");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Student������ʧ�ܣ�");
			}
		}
	}

	// ��ʦ��
	public void teaTable(Statement stat) throws SQLException {
		if (getRes(stat, "Teacher").next()) {

		} else {
			try {
				stat.executeUpdate("create table Teacher(" + "Tno char(10) primary key," + "Tname char(10),"
						+ "Tsex enum('��','Ů') not null," + "Ttel char(11) unique,"
								+ "Password char(16) not null default '123456')");
				System.out.println("Teacher�������ɹ���");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Teacher������ʧ�ܣ�");
			}
		}
	}

	// �γ̱�
	public void couTable(Statement stat) throws SQLException {
		if (getRes(stat, "Course").next()) {

		} else {
			try {
				stat.executeUpdate("create table Course(" + "Cno int primary key," + "Cname char(40) unique,"
						+ "Cpno int," + "Ccredit int," + "foreign key(Cpno) references Course(Cno))");
				System.out.println("Course�������ɹ���");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Course������ʧ�ܣ�");
			}
		}
	}

	// SC��
	public void SCTable(Statement stat) throws SQLException {
		if (getRes(stat, "SC").next()) {

		} else {
			try {
				stat.executeUpdate("create table SC(" + "Sno char(10)," + "Cno int," + "Tno char(10)," + "Grade float,"
						+ "primary key(Sno,Cno,Tno)," + "index (Sno)," + "index (Cno),"
						+ "foreign key(Sno) references Student(Sno) on delete cascade on update cascade,"
						+ "foreign key(Tno) references Teacher(Tno),"
						+ "foreign key(Cno) references Course(Cno) on delete cascade on update cascade)engine=InnoDB");
				System.out.println("SC�������ɹ���");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SC������ʧ�ܣ�");
			}
		}
	}

	// TC��
	public void TCTable(Statement stat) throws SQLException {
		if (getRes(stat, "TC").next()) {

		} else {
			try {
				stat.executeUpdate("create table TC(" + "Tno char(10)," + "Cno int," + "primary key(Tno,Cno),"
						+ "index (Tno)," + "index (Cno),"
						+ "foreign key(Tno) references Teacher(Tno) on delete cascade on update cascade,"
						+ "foreign key(Cno) references Course(Cno) on delete cascade on update cascade)engine=InnoDB");
				System.out.println("TC�������ɹ���");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("TC������ʧ�ܣ�");
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
				System.out.println("AU�������ɹ���");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("AU������ʧ�ܣ�");
			}
		}
	}
}