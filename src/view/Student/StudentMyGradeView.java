package view.Student;

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

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class StudentMyGradeView extends JFrame {

	private JPanel contentPane;
	private JTable studentGradeTable;
	private JLabel sumCreditLabel;
	private JTextField creditField;
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
	public StudentMyGradeView(String uName) {
		this.uName = uName;
		setResizable(false);
		setTitle("\u5B66\u751F\u6210\u7EE9");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 643, 461);
		

		String src ="/img/成绩查询.png";
		Image imgae = null;
		try {
			imgae = ImageIO.read(this.getClass().getResource(src));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setIconImage(imgae);
		
		contentPane = new JPanel(){
			protected void paintComponent(Graphics g){
				ImageIcon icon = new ImageIcon("F://JavaProjects//MFC_ODBC//images//j.jpg");  
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, icon.getIconWidth(),  
                        icon.getIconHeight(), icon.getImageObserver());  
                jFrame.setSize(icon.getIconWidth(), icon.getIconHeight());  
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane gradePane = new JScrollPane();
		gradePane.setBounds(14, 13, 558, 253);
		contentPane.add(gradePane);
		
		
	       studentGradeTable = new JTable();
			gradePane.setColumnHeaderView(studentGradeTable);
			

	        studentGradeTable.setModel(new DefaultTableModel(
	        	new Object[][] {
	        		 
	        	},
	        	new String[] {
	        			"课程号","课程名","成绩","学分"
	        	}
	        ));
	        gradePane.setViewportView(studentGradeTable);
	        
	        sumCreditLabel = new JLabel("\u5DF2\u4FEE\u5B66\u5206\uFF1A");
	        sumCreditLabel.setBounds(137, 328, 88, 18);
	        contentPane.add(sumCreditLabel);
	        
	        creditField = new JTextField();
	        creditField.setEnabled(false);
	        creditField.setBounds(258, 323, 88, 28);
	        contentPane.add(creditField);
	        creditField.setColumns(10);
	        
	        fillTable();
	}
	
	private void fillTable() {
		DefaultTableModel dModel = (DefaultTableModel) studentGradeTable.getModel();
		dModel.setRowCount(0);
		SC_DAO sc_DAO = new SC_DAO();
		ResultSet rs = sc_DAO.SCSelectGrade(uName);

		try {
			while (rs.next()) {
				Vector vector = new Vector();
				vector.add(rs.getInt(2));
				vector.add(rs.getString(3));
				vector.add(rs.getFloat(4));
				vector.add(rs.getFloat(5));
				dModel.addRow(vector);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDAO.close();
		}
		
		rs = sc_DAO.SCSelectCredit(uName);
		try {
			if (rs.next()) {
				creditField.setText(String.valueOf(rs.getFloat(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


