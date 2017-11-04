package view.teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import base.BaseDAO;
import dao.Course_DAO;
import dao.SC_DAO;
import dao.TC_DAO;
import model.Course;
import model.SCGrade;
import model.Teacher;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.ImageIcon;

public class StudentGradeInto extends JFrame {

	private JPanel contentPane;
	private JTable StudentGradeTable;
	private JTextField snoTextField;
	private JTextField snameTextField;
	private JTextField gradeTextField;
	private JComboBox queryBox;
	private JButton confirmButton;
	JFrame jFrame =new JFrame();

	private static String uName;
	private static int count = 0;
	private static int select = 0;
	private String sno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	/**
	 * Create the frame.
	 */
	public StudentGradeInto(String uName) {
		setResizable(false);
		this.uName = uName;
		setTitle("\u5B66\u751F\u6210\u7EE9\u5F55\u5165");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 948, 731);
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

		// 设置窗口居中显示
		this.setLocationRelativeTo(null);
		
		String src ="/img/录入.png";
		Image imgae = null;
		try {
			imgae = ImageIO.read(this.getClass().getResource(src));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setIconImage(imgae);
		

		JLabel queryLabel = new JLabel("\u8BF7\u9009\u62E9\u8BFE\u7A0B\uFF1A");
		queryLabel.setBounds(49, 24, 107, 31);
		contentPane.add(queryLabel);

		queryBox = new JComboBox();
		queryBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (queryBox.getSelectedIndex() != select) {
					fillTable();
				}
			}
		});
		queryBox.setBounds(168, 26, 184, 29);
		contentPane.add(queryBox);
		catogry();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u9009\u62E9\u8BE5\u8BFE\u7A0B\u7684\u5B66\u751F", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(14, 57, 902, 396);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane selectPane = new JScrollPane();
		selectPane.setBounds(0, 23, 888, 373);
		panel.add(selectPane);

		StudentGradeTable = new JTable();
		StudentGradeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int row = StudentGradeTable.getSelectedRow();
				if (row < count) {
					snoTextField.setText((String) StudentGradeTable.getValueAt(row, 0));
					snameTextField.setText((String) StudentGradeTable.getValueAt(row, 1));
					gradeTextField.setText(Float.toString((Float) StudentGradeTable.getValueAt(row, 4)));

					sno = (String) StudentGradeTable.getValueAt(row, 0);

					if ((Float) StudentGradeTable.getValueAt(row, 4) > 0) {
						confirmButton.setEnabled(false);
					} else {
						confirmButton.setEnabled(true);
					}
				}
			}
		});
		selectPane.setColumnHeaderView(StudentGradeTable);

		StudentGradeTable.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "学号", "姓名", "性别", "联系电话", "成绩" }));
		selectPane.setViewportView(StudentGradeTable);
		fillTable();

		JLabel snoLabel = new JLabel("\u5B66\u53F7\uFF1A");
		snoLabel.setBounds(229, 495, 72, 18);
		contentPane.add(snoLabel);

		snoTextField = new JTextField();
		snoTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		snoTextField.setEnabled(false);
		snoTextField.setBounds(330, 492, 127, 24);
		contentPane.add(snoTextField);
		snoTextField.setColumns(10);

		JLabel snameLabel = new JLabel("\u59D3\u540D\uFF1A");
		snameLabel.setBounds(229, 554, 72, 18);
		contentPane.add(snameLabel);

		snameTextField = new JTextField();
		snameTextField.setEnabled(false);
		snameTextField.setBounds(330, 551, 127, 24);
		contentPane.add(snameTextField);
		snameTextField.setColumns(10);

		JLabel gradeLabel = new JLabel("\u6210\u7EE9\uFF1A");
		gradeLabel.setBounds(229, 618, 72, 18);
		contentPane.add(gradeLabel);

		gradeTextField = new JTextField();
		gradeTextField.setBounds(330, 615, 127, 24);
		contentPane.add(gradeTextField);
		gradeTextField.setColumns(10);

		confirmButton = new JButton("\u786E\u5B9A");
		confirmButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u786E\u8BA4.png"));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				float grade = Float.parseFloat(gradeTextField.getText());
				Course_DAO cDao = new Course_DAO();
				SC_DAO sc_DAO = new SC_DAO();
				ResultSet rSet = cDao.CSelect(new Course((String) queryBox.getSelectedItem(), 0));
				try {
					int cno = 0;
					while(rSet.next()) cno = rSet.getInt("Cno");
					if (sc_DAO.SCModiy(new SCGrade(sno, cno, null, uName, grade)) == 1) {
						fillTable();
						JOptionPane.showConfirmDialog(null, "成绩录入成功！", "提示", JOptionPane.CLOSED_OPTION);
						confirmButton.setEnabled(false);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		confirmButton.setBounds(631, 598, 113, 27);
		contentPane.add(confirmButton);
	}

	private void catogry() {
		TC_DAO tc_DAO = new TC_DAO();
		ResultSet rSet = tc_DAO.TCSelecte(new Teacher(uName));
		try {
			while (rSet.next()) {
				this.queryBox.addItem(rSet.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDAO.close();
		}
	}

	private void fillTable() {
		select = queryBox.getSelectedIndex();
		String Cname = (String) queryBox.getSelectedItem();
		DefaultTableModel dModel = (DefaultTableModel) StudentGradeTable.getModel();
		dModel.setRowCount(0);
		TC_DAO tDao = new TC_DAO();
		ResultSet rSet = tDao.TCGrade(uName, Cname);
		try {
			count = 0;
			while (rSet.next()) {
				Vector vector = new Vector();
				vector.add(rSet.getString(1));
				vector.add(rSet.getString(2));
				vector.add(rSet.getString(3));
				vector.add(rSet.getString(4));
				if (rSet.getFloat(5) < 0) {
					vector.add(0.0f);
				} else {
					vector.add(rSet.getFloat(5));
				}
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
