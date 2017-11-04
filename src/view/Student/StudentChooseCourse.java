package view.Student;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import base.BaseDAO;
import dao.SC_DAO;
import dao.TC_DAO;
import model.SCGrade;
import util.Constant;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class StudentChooseCourse extends JFrame {

	private JPanel contentPane;
	private JTable courseAllList;
	private JTable courseChooseList;
	private JTable courseDeleteList;
	private JTextField cnoTextField;
	private JTextField cnameTextField;
	private JTextField dnotextField;
	private JTextField dnametextField;
	private JTextField tnameTextField;
	private JButton deleteButton;
	JFrame jFrame =new JFrame();

	private static String uName;
	private static int count = 0;
	private static int sum = 0;
	
	private static int Cno;
	private String Tno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	/**
	 * Create the frame.
	 */
	public StudentChooseCourse(String uName) {
		this.uName = uName;
		System.out.println(uName);
		setResizable(false);
		setTitle("\u9009\u8BFE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 设置只关闭当前窗口
		setBounds(100, 100, 1069, 924);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 设置窗口居中显示
		this.setLocationRelativeTo(null);
		
		
		String src ="/img/选课.png";
		Image imgae = null;
		try {
			imgae = ImageIO.read(this.getClass().getResource(src));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setIconImage(imgae);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 12, 1039, 865);
		contentPane.add(tabbedPane);

		JPanel selectShow = new JPanel(){
			protected void paintComponent(Graphics g){
				ImageIcon icon = new ImageIcon("F://JavaProjects//MFC_ODBC//images//m.jpg");  
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, icon.getIconWidth(),  
                        icon.getIconHeight(), icon.getImageObserver());  
                jFrame.setSize(icon.getIconWidth(), icon.getIconHeight());  
			}
		};
		JPanel deleteShow = new JPanel(){
			protected void paintComponent(Graphics g){
				ImageIcon icon = new ImageIcon("F://JavaProjects//MFC_ODBC//images//m.jpg");  
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, icon.getIconWidth(),  
                        icon.getIconHeight(), icon.getImageObserver());  
                jFrame.setSize(icon.getIconWidth(), icon.getIconHeight());  
			}
			
		};
		tabbedPane.add("选择课程", selectShow);
		selectShow.setLayout(null);

		JScrollPane courseList = new JScrollPane();
		courseList.setBounds(55, 24, 900, 318);
		selectShow.add(courseList);

		JLabel cnoLable = new JLabel("\u8BFE\u7A0B\u53F7\uFF1A");
		cnoLable.setBounds(95, 388, 72, 18);
		selectShow.add(cnoLable);

		cnoTextField = new JTextField();
		cnoTextField.setEnabled(false);
		cnoTextField.setColumns(10);
		cnoTextField.setBounds(169, 385, 126, 24);
		selectShow.add(cnoTextField);

		JLabel cnameLable = new JLabel("\u8BFE\u7A0B\u540D\uFF1A");
		cnameLable.setBounds(377, 388, 72, 18);
		selectShow.add(cnameLable);

		cnameTextField = new JTextField();
		cnameTextField.setEnabled(false);
		cnameTextField.setColumns(10);
		cnameTextField.setBounds(455, 383, 164, 28);
		selectShow.add(cnameTextField);

		JButton addButton = new JButton("\u6DFB\u52A0");
		addButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\add.png"));
		addButton.setEnabled(false);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Constant.isEmpty(cnameLable.getText())) {
					SC_DAO sc = new SC_DAO();
					if(sc.SCInsert(new SCGrade(uName,Cno,null,Tno,-1)) != 1){
						JOptionPane.showConfirmDialog(null, "已选修该课程或未选修相应的先行课！","提示",JOptionPane.CLOSED_OPTION);
					}else{
						fillTableSelect(courseChooseList);
						fillTableSelect(courseDeleteList);
						JOptionPane.showConfirmDialog(null, "成功选修该课程！","提示",JOptionPane.CLOSED_OPTION);
					}
				}
			}
		});
		addButton.setBounds(815, 428, 80, 35);
		selectShow.add(addButton);

		JPanel selectPanel = new JPanel();
		selectPanel.setLayout(null);
		selectPanel.setBorder(
				new TitledBorder(null, "选课结果", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		selectPanel.setBounds(53, 476, 902, 308);
		selectShow.add(selectPanel);

		JScrollPane selectCoureList = new JScrollPane();
		selectCoureList.setBounds(0, 23, 902, 285);
		selectPanel.add(selectCoureList);
		tabbedPane.add("删除课程", deleteShow);
		deleteShow.setLayout(null);

		JPanel deletePanel = new JPanel();
		deletePanel.setBorder(
				new TitledBorder(null, "\u5DF2\u9009\u8BFE\u7A0B", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		deletePanel.setBounds(14, 13, 1011, 465);
		deleteShow.add(deletePanel);
		deletePanel.setLayout(null);

		JScrollPane deleteList = new JScrollPane();
		deleteList.setBounds(14, 23, 997, 442);
		deletePanel.add(deleteList);

		courseAllList = new JTable();
		courseAllList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int row = courseAllList.getSelectedRow();
				if (row < sum) {
				cnoTextField.setText(Integer.toString((int) courseAllList.getValueAt(row, 0)));
				cnameTextField.setText((String) courseAllList.getValueAt(row, 1));
				tnameTextField.setText((String) courseAllList.getValueAt(row, 4));
				
				Cno = (int) courseAllList.getValueAt(row, 0);
				Tno = (String) courseAllList.getValueAt(row, 5);
				addButton.setEnabled(true);
				}else{
					addButton.setEnabled(false);
				}
			}
		});
		courseList.setColumnHeaderView(courseAllList);

		courseAllList.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "\u8BFE\u7A0B\u53F7",
				"\u8BFE\u7A0B\u540D", "\u5148\u884C\u8BFE", "\u5B66\u5206", "教师","教师号" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		courseList.setViewportView(courseAllList);
		this.fillTableALL();

		courseChooseList = new JTable();
		selectCoureList.setColumnHeaderView(courseChooseList);

		courseChooseList.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "\u8BFE\u7A0B\u53F7",
				"\u8BFE\u7A0B\u540D", "\u5148\u884C\u8BFE", "\u5B66\u5206", "教师","教师号" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		selectCoureList.setViewportView(courseChooseList);
		this.fillTableSelect(courseChooseList);
		
		JLabel teacherLabel = new JLabel("\u6559\u5E08\uFF1A");
		teacherLabel.setBounds(657, 388, 45, 18);
		selectShow.add(teacherLabel);
		
		tnameTextField = new JTextField();
		tnameTextField.setEnabled(false);
		tnameTextField.setBounds(714, 383, 173, 28);
		selectShow.add(tnameTextField);
		tnameTextField.setColumns(10);

		courseDeleteList = new JTable();
		courseDeleteList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int row = courseDeleteList.getSelectedRow();
				if(row <= count){
					dnotextField.setText(Integer.toString((int) courseDeleteList.getValueAt(row, 0)));
					dnametextField.setText((String) courseDeleteList.getValueAt(row, 1));
					
					Cno = (int) courseDeleteList.getValueAt(row, 0);
					Tno = (String) courseDeleteList.getValueAt(row, 5);
					deleteButton.setEnabled(true);
				}else{
					deleteButton.setEnabled(false);
				}
			}
		});
		deleteList.setColumnHeaderView(courseDeleteList);

		courseDeleteList.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u8BFE\u7A0B\u53F7", "\u8BFE\u7A0B\u540D", "\u5148\u884C\u8BFE", "\u5B66\u5206" ,"教师","教师号"}) {
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		deleteList.setViewportView(courseDeleteList);
		this.fillTableSelect(courseDeleteList);
		
		JLabel dnoLabel = new JLabel("\u8BFE\u7A0B\u53F7\uFF1A");
		dnoLabel.setBounds(318, 512, 72, 18);
		deleteShow.add(dnoLabel);

		dnotextField = new JTextField();
		dnotextField.setEnabled(false);
		dnotextField.setBounds(466, 509, 184, 24);
		deleteShow.add(dnotextField);
		dnotextField.setColumns(10);

		JLabel dnameLabel = new JLabel("\u8BFE\u7A0B\u540D\uFF1A");
		dnameLabel.setBounds(318, 579, 72, 18);
		deleteShow.add(dnameLabel);

		dnametextField = new JTextField();
		dnametextField.setEnabled(false);
		dnametextField.setBounds(466, 576, 184, 24);
		deleteShow.add(dnametextField);
		dnametextField.setColumns(10);

		deleteButton = new JButton("\u5220\u9664");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Constant.isEmpty(dnameLabel.getText())) {
					SC_DAO sc = new SC_DAO();
					if(sc.SCdelete(new SCGrade(uName,Cno,null,Tno,-1)) != 1){
						JOptionPane.showConfirmDialog(null, "请先删除相应的先行课！","提示",JOptionPane.CLOSED_OPTION);
					}else{
						fillTableSelect(courseDeleteList);
						fillTableSelect(courseChooseList);
						JOptionPane.showConfirmDialog(null, "删除成功！","提示",JOptionPane.CLOSED_OPTION);
					}
				}
			}
		});
		deleteButton.setBounds(404, 696, 113, 27);
		deleteButton.setEnabled(false);
		deleteShow.add(deleteButton);
	}

	//所有课程
	private void fillTableALL() {
		sum = 0;
		DefaultTableModel dModel = (DefaultTableModel) courseAllList.getModel();
		dModel.setRowCount(0);
		TC_DAO tc = new TC_DAO();
		ResultSet rs = tc.TCQuery();
		
		try {
			while (rs.next()) {
				Vector vector = new Vector();
				vector.add(rs.getInt(1));
				vector.add(rs.getString(2));
				vector.add(rs.getInt(3));
				vector.add(rs.getFloat(4));
				vector.add(rs.getString(5));
				vector.add(rs.getString(6));
				dModel.addRow(vector);
				sum++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDAO.close();
		}
	}
	
	//已选课程
	private void fillTableSelect(JTable jt){
		count = 0;
		DefaultTableModel dModel = (DefaultTableModel) jt.getModel();
		dModel.setRowCount(0);
		SC_DAO sc = new SC_DAO();
		ResultSet rs = sc.SCSelect(uName, 0);
		
		try {
			while (rs.next()) {
				Vector vector = new Vector();
				vector.add(rs.getInt(1));
				vector.add(rs.getString(2));
				vector.add(rs.getInt(3));
				vector.add(rs.getFloat(4));
				vector.add(rs.getString(5));
				vector.add(rs.getString(6));
				dModel.addRow(vector);
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDAO.close();
		}
	}
}
