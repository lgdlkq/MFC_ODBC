package view.teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import base.BaseDAO;
import dao.SC_DAO;
import dao.TC_DAO;
import model.Teacher;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TeacherCourseTable extends JFrame {

	private JPanel contentPane;
	private JTable teacherCourseTable;
	JFrame jFrame =new JFrame();

	private static String uName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	/**
	 * Create the frame.
	 */
	public TeacherCourseTable(String uName) {
		this.uName = uName;
		setTitle("\u6559\u5E08\u8BFE\u8868");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 614);
		contentPane = new JPanel(){
			protected void paintComponent(Graphics g){
				ImageIcon icon = new ImageIcon("F://JavaProjects//MFC_ODBC//images//g.jpg");  
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, icon.getIconWidth(),  
                        icon.getIconHeight(), icon.getImageObserver());  
                jFrame.setSize(icon.getIconWidth(), icon.getIconHeight());  
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane teacherPane = new JScrollPane();
		teacherPane.setBounds(0, 0, 432, 567);
		contentPane.add(teacherPane);

		// 设置窗口居中显示
		this.setLocationRelativeTo(null);
		
		
		String src ="/img/课程表.png";
		Image imgae = null;
		try {
			imgae = ImageIO.read(this.getClass().getResource(src));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setIconImage(imgae);
		

		teacherCourseTable = new JTable();
		teacherPane.setColumnHeaderView(teacherCourseTable);

		teacherCourseTable.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "课程号", "课程名" }));
		teacherPane.setViewportView(teacherCourseTable);

		fillTableSelect();
	}

	// 已选课程
	private void fillTableSelect() {
		DefaultTableModel dModel = (DefaultTableModel) teacherCourseTable.getModel();
		dModel.setRowCount(0);
		TC_DAO tc_DAO = new TC_DAO();
		ResultSet rs = tc_DAO.TCSelecte(new Teacher(uName));

		try {
			while (rs.next()) {
				Vector vector = new Vector();
				vector.add(rs.getInt(1));
				vector.add(rs.getString(2));
				dModel.addRow(vector);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDAO.close();
		}
	}
}
