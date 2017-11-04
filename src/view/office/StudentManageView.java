package view.office;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import base.BaseDAO;
import dao.StudentDAO;
import model.Student;
import util.Constant;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentManageView extends JInternalFrame {
	private JTextField snoNewTextField;
	private JTextField snameNewTextField;
	private JTextField sclassNewTextField;
	private JTextField stelNewTextField;
	private JTextField sdeptNewTextField;
	private JTextField sageNewTextField;
	private JTable bookAddTable;
	private JTable stuUpSelTable;
	private JTextField snoTextField;
	private JTextField snameField;
	private JTextField sageField;
	private JTextField sclassField;
	private JTextField stelField;
	private JTextField sdeptField;
	private JRadioButton boyNewRadioButton;
	private JRadioButton girlNewRadioButton;
	private JRadioButton boyRadioButton;
	private JRadioButton girlRadioButton;

	private static String sno;
	private static String sname;
	private static int sage;
	private static String ssex;
	private static String stel;
	private static String sdept;
	private static String sclass;
	private static int count = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentManageView frame = new StudentManageView();
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
	public StudentManageView() {
		setIconifiable(true);
		setClosable(true);
		setTitle("\u5B66\u751F\u7BA1\u7406\u6A21\u5757");
		setBounds(100, 100, 963, 757);
		getContentPane().setLayout(null);

		JTabbedPane jp = new JTabbedPane(JTabbedPane.TOP);
		jp.setBounds(14, 13, 933, 708);
		getContentPane().add(jp);

		JPanel addP = new JPanel();

		jp.add("添加学生信息", addP);
		addP.setLayout(null);

		JLabel snoNewLabel = new JLabel("\u8BF7\u8F93\u5165\u5B66\u53F7\uFF1A");
		snoNewLabel.setBounds(283, 69, 106, 18);
		addP.add(snoNewLabel);

		JLabel snameNewLabel = new JLabel("\u8BF7\u8F93\u5165\u59D3\u540D\uFF1A");
		snameNewLabel.setBounds(283, 131, 106, 18);
		addP.add(snameNewLabel);

		JLabel sexNewLabel = new JLabel("\u8BF7\u9009\u62E9\u6027\u522B\uFF1A");
		sexNewLabel.setBounds(283, 196, 106, 18);
		addP.add(sexNewLabel);

		JLabel sclassNewLabel = new JLabel("\u8BF7\u8F93\u5165\u73ED\u7EA7\uFF1A");
		sclassNewLabel.setBounds(283, 266, 106, 18);
		addP.add(sclassNewLabel);

		JLabel stelNewLabel = new JLabel("\u8BF7\u8F93\u5165\u8054\u7CFB\u7535\u8BDD\uFF1A");
		stelNewLabel.setBounds(283, 333, 123, 18);
		addP.add(stelNewLabel);

		JLabel sdeptNewLabel = new JLabel("\u8BF7\u8F93\u5165\u6240\u5728\u7CFB\uFF1A");
		sdeptNewLabel.setBounds(283, 400, 106, 18);
		addP.add(sdeptNewLabel);

		JLabel sageNewLabel = new JLabel("\u8BF7\u8F93\u5165\u5E74\u9F84\uFF1A");
		sageNewLabel.setBounds(283, 463, 106, 18);
		addP.add(sageNewLabel);

		snoNewTextField = new JTextField();
		snoNewTextField.setBounds(420, 66, 175, 24);
		addP.add(snoNewTextField);
		snoNewTextField.setColumns(10);

		snameNewTextField = new JTextField();
		snameNewTextField.setBounds(420, 128, 175, 24);
		addP.add(snameNewTextField);
		snameNewTextField.setColumns(10);

		sclassNewTextField = new JTextField();
		sclassNewTextField.setBounds(420, 263, 175, 24);
		addP.add(sclassNewTextField);
		sclassNewTextField.setColumns(10);

		stelNewTextField = new JTextField();
		stelNewTextField.setBounds(420, 330, 175, 24);
		addP.add(stelNewTextField);
		stelNewTextField.setColumns(10);

		sdeptNewTextField = new JTextField();
		sdeptNewTextField.setBounds(420, 397, 175, 24);
		addP.add(sdeptNewTextField);
		sdeptNewTextField.setColumns(10);

		sageNewTextField = new JTextField();
		sageNewTextField.setBounds(420, 460, 175, 24);
		addP.add(sageNewTextField);
		sageNewTextField.setColumns(10);

		boyNewRadioButton = new JRadioButton("\u7537");
		boyNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (boyNewRadioButton.isSelected()) {
					boyNewRadioButton.setSelected(true);
					girlNewRadioButton.setSelected(false);
				}
			}
		});
		boyNewRadioButton.setBounds(420, 192, 72, 27);
		addP.add(boyNewRadioButton);

		girlNewRadioButton = new JRadioButton("\u5973");
		girlNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (girlNewRadioButton.isSelected()) {
					boyNewRadioButton.setSelected(false);
					girlNewRadioButton.setSelected(true);
				}
			}
		});
		girlNewRadioButton.setBounds(523, 192, 64, 27);
		addP.add(girlNewRadioButton);

		JButton addButton = new JButton("\u6DFB\u52A0");
		addButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\add.png"));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addGet();
				if (!Constant.isEmpty(sno)) {
					JOptionPane.showConfirmDialog(null, "学号不能为空!", "提示", JOptionPane.CLOSED_OPTION);
					return;
				}
				if (!Constant.isEmpty(sname)) {
					JOptionPane.showConfirmDialog(null, "姓名不能为空!", "提示", JOptionPane.CLOSED_OPTION);
					return;
				}
				if (!Constant.isEmpty(ssex)) {
					JOptionPane.showConfirmDialog(null, "性别不能为空!", "提示", JOptionPane.CLOSED_OPTION);
					return;
				}
				if (stel.length() != 11) {
					JOptionPane.showConfirmDialog(null, "电话号码位数错误！", "提示", JOptionPane.CLOSED_OPTION);
					return;
				}
				if (!Constant.isNumeric(stel)) {
					JOptionPane.showConfirmDialog(null, "电话号码应为11位数字！", "提示", JOptionPane.CLOSED_OPTION);
					return;
				}
				StudentDAO sDao = new StudentDAO();
				if (sDao.stuInsert(new Student(sclass, sno, sname, sage, ssex, stel, sdept, null)) == 1) {
					JOptionPane.showConfirmDialog(null, "添加成功!", "提示", JOptionPane.CLOSED_OPTION);
					RSet();
				} else {
					JOptionPane.showConfirmDialog(null, "学号或电话已被使用!请重新输入", "提示", JOptionPane.CLOSED_OPTION);
				}
				BaseDAO.close();
			}
		});
		addButton.setBounds(201, 565, 113, 27);
		addP.add(addButton);

		JButton resetNewButton = new JButton("\u91CD\u7F6E");
		resetNewButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\reset.png"));
		resetNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RSet();
			}
		});
		resetNewButton.setBounds(595, 565, 113, 27);
		addP.add(resetNewButton);
		JPanel sel_mod_delP = new JPanel();
		jp.add("查询学生信息", sel_mod_delP);
		sel_mod_delP.setLayout(null);

		JScrollPane sel_mod_del_Pane = new JScrollPane();
		sel_mod_del_Pane.setBounds(12, 271, 921, 391);
		sel_mod_delP.add(sel_mod_del_Pane);

		JLabel snoLabel = new JLabel("\u5B66\u53F7\uFF1A");
		snoLabel.setBounds(76, 29, 55, 18);
		sel_mod_delP.add(snoLabel);

		snoTextField = new JTextField();
		snoTextField.setBounds(168, 24, 175, 28);
		sel_mod_delP.add(snoTextField);
		snoTextField.setColumns(10);

		JLabel snameLabel = new JLabel("\u59D3\u540D\uFF1A");
		snameLabel.setBounds(511, 29, 55, 18);
		sel_mod_delP.add(snameLabel);

		snameField = new JTextField();
		snameField.setBounds(584, 24, 118, 28);
		sel_mod_delP.add(snameField);
		snameField.setColumns(10);

		JLabel sexLabel = new JLabel("\u6027\u522B\uFF1A");
		sexLabel.setBounds(76, 82, 55, 18);
		sel_mod_delP.add(sexLabel);

		JLabel asgeLabel = new JLabel("\u5E74\u9F84\uFF1A");
		asgeLabel.setBounds(511, 82, 55, 18);
		sel_mod_delP.add(asgeLabel);

		sageField = new JTextField();
		sageField.setBounds(584, 77, 118, 28);
		sel_mod_delP.add(sageField);
		sageField.setColumns(10);

		JLabel sclassLabel = new JLabel("\u73ED\u7EA7\uFF1A");
		sclassLabel.setBounds(76, 140, 55, 18);
		sel_mod_delP.add(sclassLabel);

		sclassField = new JTextField();
		sclassField.setBounds(168, 135, 175, 28);
		sel_mod_delP.add(sclassField);
		sclassField.setColumns(10);

		JLabel stelLabel = new JLabel("\u7535\u8BDD\uFF1A");
		stelLabel.setBounds(511, 140, 55, 18);
		sel_mod_delP.add(stelLabel);

		stelField = new JTextField();
		stelField.setBounds(584, 135, 118, 28);
		sel_mod_delP.add(stelField);
		stelField.setColumns(10);

		JLabel sdeptLabel = new JLabel("\u7CFB\u522B\uFF1A");
		sdeptLabel.setBounds(76, 195, 78, 18);
		sel_mod_delP.add(sdeptLabel);

		sdeptField = new JTextField();
		sdeptField.setBounds(168, 195, 175, 28);
		sel_mod_delP.add(sdeptField);
		sdeptField.setColumns(10);

		JButton queryButton = new JButton("\u67E5\u8BE2");
		queryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTable();
			}
		});
		queryButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\search.png"));
		queryButton.setBounds(497, 224, 83, 35);
		sel_mod_delP.add(queryButton);

		boyRadioButton = new JRadioButton("\u7537");
		boyRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (boyRadioButton.isSelected()) {
					boyRadioButton.setSelected(true);
					girlRadioButton.setSelected(false);
				}
			}
		});
		boyRadioButton.setBounds(170, 79, 59, 24);
		sel_mod_delP.add(boyRadioButton);

		girlRadioButton = new JRadioButton("\u5973");
		girlRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (girlRadioButton.isSelected()) {
					boyRadioButton.setSelected(false);
					girlRadioButton.setSelected(true);
				}
			}
		});
		girlRadioButton.setBounds(240, 79, 55, 24);
		sel_mod_delP.add(girlRadioButton);

		stuUpSelTable = new JTable();
		stuUpSelTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int row = stuUpSelTable.getSelectedRow();
				if (row < count) {
					sclassField.setText((String) stuUpSelTable.getValueAt(row, 0));
					snoTextField.setText((String) stuUpSelTable.getValueAt(row, 1));
					snameField.setText((String) stuUpSelTable.getValueAt(row, 2));
					if ((int) stuUpSelTable.getValueAt(row, 3) > 0) {
						sageField.setText(String.valueOf(stuUpSelTable.getValueAt(row, 3)));
					} else {
						sageField.setText(null);
					}
					if (stuUpSelTable.getValueAt(row, 4).equals("男")) {
						boyRadioButton.setSelected(true);
						girlRadioButton.setSelected(false);
					} else {
						boyRadioButton.setSelected(false);
						girlRadioButton.setSelected(true);
					}
					stelField.setText((String) stuUpSelTable.getValueAt(row, 5));
					sdeptField.setText((String) stuUpSelTable.getValueAt(row, 6));
				}
			}
		});
		sel_mod_del_Pane.setColumnHeaderView(stuUpSelTable);

		stuUpSelTable.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "班级", "学号", "姓名", "年龄", "性别", "电话", "系别" }) {
					boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		sel_mod_del_Pane.setViewportView(stuUpSelTable);

		JButton modifyButton = new JButton("\u4FEE\u6539");
		modifyButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u4FEE\u6539.png"));
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SMDGet();
				StudentDAO sDao = new StudentDAO();
				if (sDao.stuModify(sno, new Student(sclass, sno, sname, sage, ssex, stel, sdept, null)) == 1) {
					JOptionPane.showConfirmDialog(null, "修改成功！", "提示", JOptionPane.CLOSED_OPTION);
					RSet();
					fillTable();
				} else {
					JOptionPane.showConfirmDialog(null, "修改失败！请检查相关输入信息", "提示", JOptionPane.CLOSED_OPTION);
					RSet();
					fillTable();
				}
			}
		});
		modifyButton.setBounds(639, 223, 83, 35);
		sel_mod_delP.add(modifyButton);

		JButton deleteButton = new JButton("\u5220\u9664");
		deleteButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\delete.png"));
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SMDGet();
				if (!(!Constant.isEmpty(sno) && !Constant.isEmpty(sname) && !Constant.isEmpty(sclass)
						&& !Constant.isEmpty(sdept))) {
					StudentDAO sDao = new StudentDAO();
					if (sDao.stuDelete(new Student(sclass, sno, sname, sage, ssex, stel, sdept, null)) != 0) {
						JOptionPane.showConfirmDialog(null, "删除成功！", "提示", JOptionPane.CLOSED_OPTION);
						RSet();
						fillTable();
					}
				} else {
					JOptionPane.showConfirmDialog(null, "删除失败，请检查条件！", "提示", JOptionPane.CLOSED_OPTION);
				}
			}
		});
		deleteButton.setBounds(780, 223, 83, 35);
		sel_mod_delP.add(deleteButton);

		this.fillTable();
	}

	private void addGet() {
		sno = snoNewTextField.getText().toString().trim();
		sname = snameNewTextField.getText().toString().trim();
		String age = sageNewTextField.getText().toString().trim();
		if (!age.equals("") && Constant.isNumeric(age)) {
			sage = Integer.parseInt(age);
		} else {
			sage = -1;
		}
		sdept = sdeptNewTextField.getText().toString().trim();
		sclass = sclassNewTextField.getText().toString().trim();
		stel = stelNewTextField.getText().toString().trim();
		if (boyNewRadioButton.isSelected()) {
			ssex = "男";
		} else {
			ssex = "女";
		}
	}

	private void SMDGet() {
		sno = snoTextField.getText().toString().trim();
		sname = snameField.getText().toString().trim();
		String age = sageField.getText().toString().trim();
		if (!age.equals("") && Constant.isNumeric(age)) {
			sage = Integer.parseInt(age);
		} else {
			sage = -1;
		}
		sdept = sdeptField.getText().toString().trim();
		sclass = sclassField.getText().toString().trim();
		stel = stelField.getText().toString().trim();
		if (boyRadioButton.isSelected()) {
			ssex = "男";
		} else if (girlRadioButton.isSelected()) {
			ssex = "女";
		} else {
			ssex = null;
		}
	}

	private void fillTable() {
		SMDGet();
		DefaultTableModel dModel = (DefaultTableModel) stuUpSelTable.getModel();
		dModel.setRowCount(0);
		count = 0;

		StudentDAO sDao = new StudentDAO();
		ResultSet rSet = sDao.stuSelecte(new Student(sclass, sno, sname, sage, ssex, stel, sdept, null));

		try {
			while (rSet.next()) {
				Vector vector = new Vector();
				vector.add(rSet.getString(1));
				vector.add(rSet.getString(2));
				vector.add(rSet.getString(3));
				if (rSet.getInt(4) > 0) {
					vector.add(rSet.getInt(4));
				} else {
					vector.add(0);
				}
				vector.add(rSet.getString(5));
				vector.add(rSet.getString(6));
				vector.add(rSet.getString(7));
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

	private void RSet() {
		snameNewTextField.setText(null);
		snoNewTextField.setText(null);
		sclassNewTextField.setText(null);
		sdeptNewTextField.setText(null);
		sageNewTextField.setText(null);
		stelNewTextField.setText(null);
		boyNewRadioButton.setSelected(false);
		girlNewRadioButton.setSelected(false);

		snameField.setText(null);
		snoTextField.setText(null);
		sclassField.setText(null);
		sdeptField.setText(null);
		sageField.setText(null);
		stelField.setText(null);
		boyRadioButton.setSelected(false);
		girlRadioButton.setSelected(false);
	}
}
