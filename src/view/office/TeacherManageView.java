package view.office;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import base.BaseDAO;
import dao.TeacherDAO;
import model.Teacher;
import util.Constant;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TeacherManageView extends JInternalFrame {
	private JTable teacherTable ;
	private JTextField tnoTextField;
	private JTextField tnameTextField;
	private JTextField telTextField;
	private JRadioButton boyRadioButton;
	private JRadioButton girlRadioButton;
	private static int count = 0;
	
	private static String Tno;
	private static String Tname;
	private static String Tsex;
	private static String Ttel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherManageView frame = new TeacherManageView();
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
	public TeacherManageView() {
		setIconifiable(true);
		setClosable(true);
		setTitle("\u6559\u5E08\u4FE1\u606F\u7BA1\u7406");
		setBounds(100, 100, 961, 722);
		getContentPane().setLayout(null);
		
		JTabbedPane jp = new JTabbedPane(JTabbedPane.TOP);
		jp.setBounds(14, 13, 931, 673);
		getContentPane().add(jp);
	          JPanel teacherP=new JPanel() ;      //创建多个容器
	          jp.add("查询与更新教师信息", teacherP)  ;     //添加子容器  并且为选项卡添加名字
	          teacherP.setLayout(null);
	          
	          JScrollPane teaPane = new JScrollPane();
	          teaPane.setBounds(14, 235, 912, 406);
	          teacherP.add(teaPane);
	          
	          
	  		
	          teacherTable = new JTable();
	          teacherTable.addMouseListener(new MouseAdapter() {
	          	@Override
	          	public void mousePressed(MouseEvent arg0) {
	          		int row = teacherTable.getSelectedRow();
	          		if(row < count){
	          			tnoTextField.setText((String) teacherTable.getValueAt(row, 0));
	          			tnameTextField.setText((String) teacherTable.getValueAt(row, 1));
	          			telTextField.setText((String) teacherTable.getValueAt(row, 3));
	          			if (teacherTable.getValueAt(row, 2).equals("男")) {
							boyRadioButton.setSelected(true);
							girlRadioButton.setSelected(false);
						}else if (teacherTable.getValueAt(row, 2).equals("女")) {
							boyRadioButton.setSelected(false);
							girlRadioButton.setSelected(true);
						}
	          		}
	          	}
	          });
	          teaPane.setColumnHeaderView(teacherTable);
	          

	           teacherTable.setModel(new DefaultTableModel(
	           	new Object[][] {
	           	},
	           	new String[] {
	           		"\u6559\u5E08\u53F7", "\u59D3\u540D", "\u6027\u522B", "\u7535\u8BDD"
	           	}
	           ) {
	           	boolean[] columnEditables = new boolean[] {
	           		false, false, false, false
	           	};
	           	public boolean isCellEditable(int row, int column) {
	           		return columnEditables[column];
	           	}
	           });
	           teacherTable.getColumnModel().getColumn(2).setPreferredWidth(76);
	           teaPane.setViewportView(teacherTable);
	           
	           JLabel tnoLabel = new JLabel("\u6559\u5E08\u53F7\uFF1A");
	           tnoLabel.setBounds(90, 31, 72, 18);
	           teacherP.add(tnoLabel);
	           
	           JLabel tnameLabel = new JLabel("\u6559\u5E08\u59D3\u540D\uFF1A");
	           tnameLabel.setBounds(504, 31, 86, 18);
	           teacherP.add(tnameLabel);
	           
	           JLabel sexLabel = new JLabel("\u6027 \u522B\uFF1A");
	           sexLabel.setBounds(90, 107, 72, 18);
	           teacherP.add(sexLabel);
	           
	           boyRadioButton = new JRadioButton("\u7537");
	           boyRadioButton.addActionListener(new ActionListener() {
	           	public void actionPerformed(ActionEvent e) {
	           		if (boyRadioButton.isSelected()) {
						boyRadioButton.setSelected(true);
						girlRadioButton.setSelected(false);
					}
	           	}
	           });
	           boyRadioButton.setBounds(185, 103, 62, 27);
	           teacherP.add(boyRadioButton);
	           
	           girlRadioButton = new JRadioButton("\u5973");
	           girlRadioButton.addActionListener(new ActionListener() {
	           	public void actionPerformed(ActionEvent e) {
	           		if (girlRadioButton.isSelected()) {
						boyRadioButton.setSelected(false);
						girlRadioButton.setSelected(true);
					}
	           	}
	           });
	           girlRadioButton.setBounds(274, 103, 62, 27);
	           teacherP.add(girlRadioButton);
	           
	           JLabel telLabel = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");
	           telLabel.setBounds(504, 107, 86, 18);
	           teacherP.add(telLabel);
	           
	           tnoTextField = new JTextField();
	           tnoTextField.setBounds(185, 28, 151, 24);
	           teacherP.add(tnoTextField);
	           tnoTextField.setColumns(10);
	           
	           tnameTextField = new JTextField();
	           tnameTextField.setBounds(604, 28, 151, 24);
	           teacherP.add(tnameTextField);
	           tnameTextField.setColumns(10);
	           
	           telTextField = new JTextField();
	           telTextField.setBounds(604, 107, 157, 24);
	           teacherP.add(telTextField);
	           telTextField.setColumns(10);
	           
	           JButton quiryButton = new JButton("\u67E5\u8BE2");
	           quiryButton.addActionListener(new ActionListener() {
	           	public void actionPerformed(ActionEvent e) {
	           		fillTable();
	           	}
	           });
	           quiryButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\search.png"));
	           quiryButton.setBounds(292, 187, 80, 35);
	           teacherP.add(quiryButton);
	           
	           JButton addButton = new JButton("\u6DFB\u52A0");
	           addButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\add.png"));
	           addButton.addActionListener(new ActionListener() {
	           	public void actionPerformed(ActionEvent e) {
	           		messageGet();
	           		if (!Constant.isEmpty(Tno)) {
	           			JOptionPane.showConfirmDialog(null, "教师号不能为空!", "提示", JOptionPane.CLOSED_OPTION);
	           			return;
					}
	           		if (!Constant.isEmpty(Tname)) {
	           			JOptionPane.showConfirmDialog(null, "教师m名不能为空!", "提示", JOptionPane.CLOSED_OPTION);
	           			return;
					}
	           		if (Ttel.length() != 11) {
						JOptionPane.showConfirmDialog(null, "电话号码位数错误！", "提示", JOptionPane.CLOSED_OPTION);
						return;
					}
					if (!Constant.isNumeric(Ttel)) {
						JOptionPane.showConfirmDialog(null, "电话号码应为11位数字！", "提示", JOptionPane.CLOSED_OPTION);
						return;
					}
					
					TeacherDAO tDao = new TeacherDAO();
					if (tDao.teaInsert(new Teacher(Tno,Tname,Tsex,Ttel,null)) == 1) {
						JOptionPane.showConfirmDialog(null, "添加成功!", "提示", JOptionPane.CLOSED_OPTION);
						RSet();
						fillTable();
					}else {
						JOptionPane.showConfirmDialog(null, "添加失败!教师号或电话已被使用！", "提示", JOptionPane.CLOSED_OPTION);
					}
	           		
	           	}
	           });
	           addButton.setBounds(151, 187, 80, 35);
	           teacherP.add(addButton);
	           
	           JButton deleteButton = new JButton("\u5220\u9664");
	           deleteButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\delete.png"));
	           deleteButton.addActionListener(new ActionListener() {
	           	public void actionPerformed(ActionEvent e) {
	           		messageGet();
	           		if (!Constant.isEmpty(Tno)) {
	           			JOptionPane.showConfirmDialog(null, "教师号不能为空！", "提示", JOptionPane.CLOSED_OPTION);
	           			return;
					}
	           		TeacherDAO tDao = new TeacherDAO();
	           		if (tDao.teaDelete(new Teacher(Tno,Tname,Tsex,Ttel,null)) == 1){
	        	   JOptionPane.showConfirmDialog(null, "删除成功！", "提示", JOptionPane.CLOSED_OPTION);
					RSet();
					fillTable();
					}else {
						JOptionPane.showConfirmDialog(null, "删除失败！请检查相关输入信息", "提示", JOptionPane.CLOSED_OPTION);
						RSet();
						fillTable();
					}
	           	}
	           });
	           deleteButton.setBounds(583, 187, 80, 35);
	           teacherP.add(deleteButton);
	           
	           JButton modifyButton = new JButton("\u4FEE\u6539");
	           modifyButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u4FEE\u6539.png"));
	           modifyButton.addActionListener(new ActionListener() {
	           	public void actionPerformed(ActionEvent e) {
	           		messageGet();
	           		TeacherDAO tDao = new TeacherDAO();
	           		if (tDao.teaModify(new Teacher(Tno,Tname,Tsex,Ttel,null)) == 1) {
	           			JOptionPane.showConfirmDialog(null, "修改成功！", "提示", JOptionPane.CLOSED_OPTION);
						RSet();
						fillTable();
					}else {
						JOptionPane.showConfirmDialog(null, "修改失败！请检查相关输入信息", "提示", JOptionPane.CLOSED_OPTION);
						RSet();
						fillTable();
					}
	           	}
	           });
	           modifyButton.setBounds(440, 187, 80, 35);
	           teacherP.add(modifyButton);
	           
	           JButton resetButton = new JButton("\u91CD\u7F6E");
	           resetButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\reset.png"));
	           resetButton.addActionListener(new ActionListener() {
	           	public void actionPerformed(ActionEvent e) {
	           		RSet();
	           	}
	           });
	           resetButton.setBounds(713, 187, 80, 35);
	           teacherP.add(resetButton);
	           fillTable();
	}
	
	private void messageGet(){
		Tno = tnoTextField.getText().toString().trim();
		Tname = tnameTextField.getText().toString().trim();
		Ttel = telTextField.getText().toString().trim();
		if(boyRadioButton.isSelected()){
			Tsex = "男";
		}else if (girlRadioButton.isSelected()) {
			Tsex = "女";
		}else {
			Tsex = null;
		}
	}
	
	private void fillTable(){
		messageGet();
		DefaultTableModel dModel = (DefaultTableModel) teacherTable.getModel();
		dModel.setRowCount(0);
		count  = 0;
		
		TeacherDAO tDao = new TeacherDAO();
		ResultSet rSet = tDao.teaSelecte(new Teacher(Tno,Tname,Tsex,Ttel,null));

		try {
			while(rSet.next()){
				Vector vector = new Vector();
				vector.add(rSet.getString(1));
				vector.add(rSet.getString(2));
				vector.add(rSet.getString(3));
				vector.add(rSet.getString(4));
				dModel.addRow(vector);
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDAO.close();
		}
	}
	
	private void RSet(){
		tnoTextField.setText(null);
		tnameTextField.setText(null);
		telTextField.setText(null);
		boyRadioButton.setSelected(false);
		girlRadioButton.setSelected(false);
	}
}
