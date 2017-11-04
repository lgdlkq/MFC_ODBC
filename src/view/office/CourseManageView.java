package view.office;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import base.BaseDAO;
import dao.Course_DAO;
import model.Course;
import util.Constant;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class CourseManageView extends JInternalFrame {
	private JTable courseTable;
	private JTable courseQueryTable;
	private JTextField cnoTextField;
	private JTextField cnameTextField;
	private JTextField cpnoTextField;
	private JTextField creditTextField;
	private JTextField contentTextField;
	private JComboBox queryBox;
	private JButton deleteButton;

	private Course cou;
	private static int select = 0;
	private static int count = 0;
	private static int cno = 0;
	private static String cname;
	private static int cpno;
	private static float cridet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public CourseManageView() {
		setFrameIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u8BFE\u7A0B\u7BA1\u7406.png"));
		setClosable(true);
		setIconifiable(true);
		setTitle("\u8BFE\u7A0B\u4FE1\u606F\u7BA1\u7406");
		setBounds(100, 100, 919, 699);
		getContentPane().setLayout(null);
		
		

		JTabbedPane jp = new JTabbedPane(JTabbedPane.TOP);
		jp.setBounds(14, 13, 875, 637);
		getContentPane().add(jp);
		JPanel modDelP = new JPanel();

		jp.add("更新课程", modDelP);
		modDelP.setLayout(null);

		JScrollPane modDelList = new JScrollPane();
		modDelList.setBounds(14, 13, 856, 278);

		courseTable = new JTable();
		courseTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = courseTable.getSelectedRow();
				if (row < count) {
					cnoTextField.setText(Integer.toString((int) courseTable.getValueAt(row, 0)));
					cnameTextField.setText((String) courseTable.getValueAt(row, 1));
					cpnoTextField.setText(Integer.toString((int) courseTable.getValueAt(row, 2)));
					creditTextField.setText(Float.toString((float) courseTable.getValueAt(row, 3)));

					cno = (int) courseTable.getValueAt(row, 0);
					deleteButton.setEnabled(true);
				}
			}
		});
		modDelList.setColumnHeaderView(courseTable);
		fillTable(courseTable, new Course());

		courseTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u8BFE\u7A0B\u53F7", "\u8BFE\u7A0B\u540D", "\u5148\u884C\u8BFE", "\u5B66\u5206" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		modDelList.setViewportView(courseTable);
		fillTable(courseTable, new Course(cno, cname, cpno, cridet));

		modDelP.add(modDelList);

		JPanel modifyPanel = new JPanel();
		modifyPanel.setBorder(
				new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		modifyPanel.setBounds(14, 317, 856, 288);
		modDelP.add(modifyPanel);
		modifyPanel.setLayout(null);

		JLabel cnoLabel = new JLabel("\u8BFE\u7A0B\u53F7\uFF1A");
		cnoLabel.setBounds(224, 30, 72, 18);
		modifyPanel.add(cnoLabel);

		cnoTextField = new JTextField();
		cnoTextField.setBounds(328, 27, 231, 24);
		modifyPanel.add(cnoTextField);
		cnoTextField.setColumns(10);

		JLabel cnameLabel = new JLabel("\u8BFE\u7A0B\u540D\uFF1A");
		cnameLabel.setBounds(224, 71, 72, 18);
		modifyPanel.add(cnameLabel);

		cnameTextField = new JTextField();
		cnameTextField.setBounds(328, 68, 231, 24);
		modifyPanel.add(cnameTextField);
		cnameTextField.setColumns(10);

		JLabel cpnoLabel = new JLabel("\u5148\u884C\u8BFE\uFF1A");
		cpnoLabel.setBounds(224, 110, 72, 18);
		modifyPanel.add(cpnoLabel);

		cpnoTextField = new JTextField();
		cpnoTextField.setBounds(328, 107, 231, 24);
		modifyPanel.add(cpnoTextField);
		cpnoTextField.setColumns(10);

		JLabel creditLabel = new JLabel("\u5B66 \u5206\uFF1A");
		creditLabel.setBounds(224, 151, 72, 18);
		modifyPanel.add(creditLabel);

		creditTextField = new JTextField();
		creditTextField.setBounds(328, 144, 231, 24);
		modifyPanel.add(creditTextField);
		creditTextField.setColumns(10);

		JButton modifyButton = new JButton("\u4FEE\u6539");
		modifyButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u4FEE\u6539.png"));
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Course_DAO cDao = new Course_DAO();
				System.out.println(cno);
				if (cDao.CModify(
						new Course(Integer.parseInt(cnoTextField.getText()), cnameTextField.getText().toString(),
								Integer.parseInt(cpnoTextField.getText()), Float.parseFloat(creditTextField.getText())),
						cno) != 1) {
					JOptionPane.showConfirmDialog(null, "填入的先行课不存在或已有该课程号！", "提示", JOptionPane.CLOSED_OPTION);
				} else {
					fillTable(courseTable, new Course());
					JOptionPane.showConfirmDialog(null, "修改成功！", "恭喜", JOptionPane.CLOSED_OPTION);
				}
			}
		});
		modifyButton.setBounds(317, 222, 80, 35);
		modifyPanel.add(modifyButton);

		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\add.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				messageGet();
				Course_DAO cDao = new Course_DAO();
				if (cDao.CInsert(new Course(cno, cname, cpno, cridet)) == 1) {
					fillTable(courseTable, new Course());
					fillTable(courseQueryTable, new Course());
					JOptionPane.showConfirmDialog(null, "添加成功！", "恭喜", JOptionPane.CLOSED_OPTION);
				} else {
					JOptionPane.showConfirmDialog(null, "填入的先行课不存在或已有该课程！", "提示", JOptionPane.CLOSED_OPTION);
				}
			}
		});
		btnNewButton.setBounds(173, 222, 80, 35);
		modifyPanel.add(btnNewButton);

		deleteButton = new JButton("\u5220\u9664");
		deleteButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\delete.png"));
		deleteButton.setEnabled(false);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				messageGet();
				Course_DAO cDao = new Course_DAO();
				if (cDao.Cdelete(new Course(cno)) == 1) {
					fillTable(courseTable, new Course());
					fillTable(courseQueryTable, new Course());
					RSet();
					JOptionPane.showConfirmDialog(null, "删除成功！", "提示", JOptionPane.CLOSED_OPTION);
				} else {
					JOptionPane.showConfirmDialog(null, "删除失败，请检查其是否为某课程的先行课！", "提示", JOptionPane.CLOSED_OPTION);
				}

			}
		});
		deleteButton.setBounds(467, 222, 80, 35);
		modifyPanel.add(deleteButton);

		JButton reseButton = new JButton("\u91CD\u7F6E");
		reseButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\reset.png"));
		reseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RSet();
			}
		});
		reseButton.setBounds(612, 222, 80, 35);
		modifyPanel.add(reseButton);

		JPanel queryP = new JPanel();
		jp.add("查询课程", queryP);
		queryP.setLayout(null);

		queryBox = new JComboBox();
		queryBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (queryBox.getSelectedIndex() != select) {
					contentTextField.setText("");
				}
			}
		});
		queryBox.setModel(new DefaultComboBoxModel(
				new String[] { "\u8BFE\u7A0B\u53F7", "\u8BFE\u7A0B\u540D", "\u5148\u884C\u8BFE", "\u5B66\u5206" }));
		queryBox.setBounds(404, 62, 100, 36);
		queryP.add(queryBox);

		JLabel classLabel = new JLabel("\u8BF7\u9009\u62E9\u67E5\u8BE2\u7C7B\u578B\uFF1A");
		classLabel.setBounds(221, 64, 120, 33);
		queryP.add(classLabel);

		JLabel contentLabel = new JLabel("\u8BF7\u8F93\u5165\u67E5\u8BE2\u5185\u5BB9\uFF1A");
		contentLabel.setBounds(221, 125, 120, 33);
		queryP.add(contentLabel);

		contentTextField = new JTextField();
		contentTextField.setColumns(10);
		contentTextField.setBounds(404, 122, 168, 36);
		queryP.add(contentTextField);

		JScrollPane queryList = new JScrollPane();
		queryList.setBounds(14, 269, 856, 307);
		queryP.add(queryList);

		JButton queryButton = new JButton("\u67E5\u8BE2");
		queryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				catogry();
				fillTable(courseQueryTable, cou);
			}
		});
		queryButton.setBounds(262, 212, 113, 27);
		queryP.add(queryButton);

		courseQueryTable = new JTable();
		queryList.setColumnHeaderView(courseQueryTable);

		courseQueryTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u8BFE\u7A0B\u53F7", "\u8BFE\u7A0B\u540D", "\u5148\u884C\u8BFE", "\u5B66\u5206" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		queryList.setViewportView(courseQueryTable);
		fillTable(courseQueryTable, new Course());
	}

	private void fillTable(JTable jt, Course course) {
		
		DefaultTableModel dModel = (DefaultTableModel) jt.getModel();
		dModel.setRowCount(0);
		Course_DAO cDao = new Course_DAO();
		ResultSet rs = cDao.CSelect(course);
		try {
			count = 0;
			while (rs.next()) {
				Vector vector = new Vector();
				vector.add(rs.getInt(1));
				vector.add(rs.getString(2));
				vector.add(rs.getInt(3));
				vector.add(rs.getFloat(4));
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

	private void messageGet() {
		String str = cnoTextField.getText().toString();
		if (!str.equals("") && Constant.isNumeric(str)) {
			cno = Integer.parseInt(str);
		} else {
			JOptionPane.showConfirmDialog(null, "课程号不能为空", "提示", JOptionPane.CLOSED_OPTION);
			return;
		}
		cname = cnameTextField.getText().toString();
		if (cname.equals("")) {
			JOptionPane.showConfirmDialog(null, "课程名不能为空", "提示", JOptionPane.CLOSED_OPTION);
			return;
		}
		str = cpnoTextField.getText().toString();
		if (!str.equals("") && Constant.isNumeric(str)) {
			cpno = Integer.parseInt(str);
		} else {
			cpno = 0;
		}
		str = creditTextField.getText().toString();
		if (!str.equals("")) {
			cridet = Float.parseFloat(str);
		} else {
			JOptionPane.showConfirmDialog(null, "学分不能为空", "提示", JOptionPane.CLOSED_OPTION);
			return;
		}
	}

	private void catogry() {
		select = queryBox.getSelectedIndex();
		cou = null;
		if (!Constant.isEmpty(contentTextField.getText().toString().trim())) {
			cou = new Course();
		} else if (queryBox.getSelectedItem().equals("课程号")) {
			cou = new Course(Integer.parseInt(contentTextField.getText().toString()), 0);
		} else if (queryBox.getSelectedItem().equals("课程名")) {
			cou = new Course(contentTextField.getText().toString(), 0);
		} else if (queryBox.getSelectedItem().equals("先行课")) {
			cou = new Course(0, Integer.parseInt(contentTextField.getText().toString()));
		} else if (queryBox.getSelectedItem().equals("学分")) {
			cou = new Course(null, Float.parseFloat(contentTextField.getText().toString()));
		}
	}

	private void RSet() {
		cnoTextField.setText(null);
		cnameTextField.setText(null);
		cpnoTextField.setText(null);
		creditTextField.setText(null);
		fillTable(courseQueryTable, new Course());
		fillTable(courseTable, new Course());
		deleteButton.setEnabled(false);
	}
}
