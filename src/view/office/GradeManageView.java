package view.office;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import base.BaseDAO;
import dao.SC_DAO;
import dao.TC_DAO;
import model.SCGrade;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GradeManageView extends JInternalFrame {
	private JTextField snoField;

	
	private JTable GradeTable ;
	private JTextField cnoField;
	private JTextField cnameField;
	private JTextField gradeField;
	private JTextField tnoField;

	private static int count = 0;
	private static int cno;
	private static float grade;
	private static String tno;
	private static String sno;
	private JTextField snoTextField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GradeManageView frame = new GradeManageView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GradeManageView() {
		setFrameIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u4FEE\u6539.png"));
		setMaximizable(true);
		setClosable(true);
		setTitle("\u6210\u7EE9\u7BA1\u7406\u7CFB\u7EDF");
		setBounds(100, 100, 860, 600);
		getContentPane().setLayout(null);
		
		JLabel snoLabel = new JLabel("\u8BF7\u8F93\u5165\u5B66\u751F\u5B66\u53F7\uFF1A");
		snoLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		snoLabel.setBounds(166, 41, 143, 31);
		getContentPane().add(snoLabel);
		
		snoField = new JTextField();
		snoField.setBounds(339, 41, 250, 31);
		getContentPane().add(snoField);
		snoField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 95, 812, 247);
		getContentPane().add(scrollPane);
		
		JButton quertButton = new JButton("\u67E5\u8BE2");
		quertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillTable();
				snoTextField.setText(null);
				cnoField.setText(null);
				cnameField.setText(null);
				gradeField.setText(null);
				tnoField.setText(null);
			}
		});
		quertButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\search.png"));
		quertButton.setBounds(639, 44, 113, 27);
		getContentPane().add(quertButton);
		
		
	       GradeTable = new JTable();
	       GradeTable.addMouseListener(new MouseAdapter() {
	       	@Override
	       	public void mousePressed(MouseEvent arg0) {
	       		int row = GradeTable.getSelectedRow();
	       		if (row < count) {
	       			snoTextField.setText((String) GradeTable.getValueAt(row, 0));
	       			cnoField.setText(String.valueOf(GradeTable.getValueAt(row, 1)));
					cnameField.setText((String) GradeTable.getValueAt(row, 2));
					gradeField.setText(String.valueOf(GradeTable.getValueAt(row, 3)));
					tnoField.setText((String) GradeTable.getValueAt(row, 5));
				}
	       	}
	       });
			scrollPane.setColumnHeaderView(GradeTable);
			

	        GradeTable.setModel(new DefaultTableModel(
	        	new Object[][] {
	        		 
	        	},
	        	new String[] {
	        				"学号","课程号","课程名","成绩","学分","教师号"
	        	}
	        ));
	        scrollPane.setViewportView(GradeTable);
	        
	        JLabel cnoLabel = new JLabel("\u8BFE\u7A0B\u53F7\uFF1A");
	        cnoLabel.setBounds(80, 435, 60, 18);
	        getContentPane().add(cnoLabel);
	        
	        JLabel cnameLabel = new JLabel("\u8BFE\u7A0B\u540D\uFF1A");
	        cnameLabel.setBounds(477, 438, 60, 18);
	        getContentPane().add(cnameLabel);
	        
	        JLabel gradeLabel = new JLabel("\u6210\u7EE9\uFF1A");
	        gradeLabel.setBounds(95, 491, 45, 18);
	        getContentPane().add(gradeLabel);
	        
	        cnoField = new JTextField();
	        cnoField.setEnabled(false);
	        cnoField.setBounds(166, 432, 143, 24);
	        getContentPane().add(cnoField);
	        cnoField.setColumns(10);
	        
	        cnameField = new JTextField();
	        cnameField.setEnabled(false);
	        cnameField.setBounds(563, 435, 143, 24);
	        getContentPane().add(cnameField);
	        cnameField.setColumns(10);
	        
	        gradeField = new JTextField();
	        gradeField.setBounds(166, 488, 143, 24);
	        getContentPane().add(gradeField);
	        gradeField.setColumns(10);
	        
	        JButton modiyButton = new JButton("\u4FEE\u6539");
	        modiyButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\modify.png"));
	        modiyButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		messageGet();
	        		SC_DAO sDao = new SC_DAO();
	        		if (sDao.SCModiy(new SCGrade(sno,cno,null,tno,grade)) == 1) {
	        			fillTable();
						JOptionPane.showConfirmDialog(null, "成绩修改成功！", "提示", JOptionPane.CLOSED_OPTION);
					}else {
						JOptionPane.showConfirmDialog(null, "修改失败，请检查填入的成绩!", "提示", JOptionPane.CLOSED_OPTION);
					}
	        	}
	        });
	        modiyButton.setBounds(630, 499, 76, 27);
	        getContentPane().add(modiyButton);
	        
	        JLabel tnoLabel = new JLabel("\u6559\u5E08\u53F7\uFF1A");
	        tnoLabel.setBounds(477, 390, 60, 18);
	        getContentPane().add(tnoLabel);
	        
	        tnoField = new JTextField();
	        tnoField.setEnabled(false);
	        tnoField.setBounds(563, 387, 143, 24);
	        getContentPane().add(tnoField);
	        tnoField.setColumns(10);
	        
	        JLabel snolable = new JLabel("\u5B66\u53F7\uFF1A");
	        snolable.setBounds(95, 390, 51, 18);
	        getContentPane().add(snolable);
	        
	        snoTextField = new JTextField();
	        snoTextField.setEnabled(false);
	        snoTextField.setBounds(166, 387, 143, 24);
	        getContentPane().add(snoTextField);
	        snoTextField.setColumns(10);
	        fillTable();
	}
	
	
	private void messageGet(){
		sno = snoTextField.getText().toString().trim();
		cno = Integer.parseInt(cnoField.getText().toString().trim());
		if (gradeField.getText().toString().trim().equals("")) {
			grade = 0.0f;
		}else {
			grade = Float.parseFloat(gradeField.getText().toString().trim());
		}
		tno = tnoField.getText().toString().trim();
	}
	
	private void fillTable() {
		DefaultTableModel dModel = (DefaultTableModel) GradeTable.getModel();
		dModel.setRowCount(0);
		SC_DAO sc_DAO = new SC_DAO();
		ResultSet rs = sc_DAO.SCSelectGrade(snoField.getText().toString().trim());
		count = 0;
		try {
			while (rs.next()) {
				Vector vector = new Vector();
				vector.add(rs.getString(1));
				vector.add(rs.getInt(2));
				vector.add(rs.getString(3));
				vector.add(rs.getFloat(4));
				vector.add(rs.getFloat(5));
				vector.add(rs.getString(6));
				dModel.addRow(vector);
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDAO.close();
		}
	}
}
