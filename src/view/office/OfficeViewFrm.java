package view.office;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import view.Welcome.LogOnFrm;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Toolkit;

public class OfficeViewFrm extends JFrame {

	private JDesktopPane table;
	private static String uName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public OfficeViewFrm(String uName) {
		this.uName = uName;

		// 设置 窗口最大化
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// 设置窗口居中显示
		this.setLocationRelativeTo(null);
		
		
		String src ="/img/教务服务.png";
		Image imgae = null;
		try {
			imgae = ImageIO.read(this.getClass().getResource(src));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\JavaProjects\\MFC_ODBC\\images\\\u6559\u52A1\u670D\u52A1.png"));
		

		setTitle("\u6559\u52A1\u5904\u7BA1\u7406\u7CFB\u7EDF\u4E3B\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 468);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu operationMenu = new JMenu("\u57FA\u672C\u6570\u636E\u7EF4\u62A4");
		operationMenu.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u6570\u636E\u7EF4\u62A4.png"));
		menuBar.add(operationMenu);

		JMenu mnNewMenu_5 = new JMenu("\u8BFE\u7A0B\u7BA1\u7406");

		JMenuItem courseMenuItem = new JMenuItem("\u8BFE\u7A0B\u7BA1\u7406");
		courseMenuItem.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u8BFE\u7A0B\u7BA1\u7406.png"));
		courseMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseManageView courseNewsAddView = new CourseManageView();
				courseNewsAddView.setVisible(true);
				table.add(courseNewsAddView);
			}
		});
		operationMenu.add(courseMenuItem);

		JMenuItem teacherMenuItem = new JMenuItem("\u6559\u5E08\u7BA1\u7406");
		teacherMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherManageView teacherManageView = new TeacherManageView();
				teacherManageView.setVisible(true);
				table.add(teacherManageView);
			}
		});
		teacherMenuItem.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\Teacher_male_16px_1185976_easyicon.net.png"));
		operationMenu.add(teacherMenuItem);

		JMenuItem studentMenuItem = new JMenuItem("\u5B66\u751F\u7BA1\u7406");
		studentMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentManageView studentManageView = new StudentManageView();
				studentManageView.setVisible(true);
				table.add(studentManageView);
			}
		});
		studentMenuItem.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\src\\img\\\u5B66\u751F.png"));
		operationMenu.add(studentMenuItem);
		
		JMenuItem gradeMenuItem = new JMenuItem("\u6210\u7EE9\u7BA1\u7406");
		gradeMenuItem.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\edit.png"));
		gradeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GradeManageView gradeManageView = new GradeManageView();
				gradeManageView.setVisible(true);
				table.add(gradeManageView);
			}
		});
		operationMenu.add(gradeMenuItem);
		
		JMenuItem tcMenuItem = new JMenuItem("\u6559\u5E08\u6388\u8BFE\u7BA1\u7406");
		tcMenuItem.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\src\\img\\\u6559\u5E08\u6388\u8BFE.png"));
		tcMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TcMessageManage tcMessageManage = new TcMessageManage();
				tcMessageManage.setVisible(true);
				table.add(tcMessageManage);
			}
		});
		operationMenu.add(tcMenuItem);

		JMenu adMenuItem = new JMenu("\u6559\u52A1\u5904\u7BA1\u7406");
		adMenuItem.setIcon(
				new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u6559\u52A1\u7BA1\u7406.png"));
		operationMenu.add(adMenuItem);

		JMenuItem adPassMenuItem = new JMenuItem("\u6559\u52A1\u5904\u5BC6\u7801\u4FEE\u6539");
		adPassMenuItem.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\src\\img\\\u4FEE\u6539\u5BC6\u7801.png"));
		adPassMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OfficepassWordModify officepassWordModify = new OfficepassWordModify(uName);
				officepassWordModify.setVisible(true);
				table.add(officepassWordModify);
			}
		});
		adMenuItem.add(adPassMenuItem);

		JMenuItem teaPassMenuItem = new JMenuItem("\u6559\u5E08\u5BC6\u7801\u4FEE\u6539");
		teaPassMenuItem.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\password.png"));
		teaPassMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TeacherpassWordModify teacherpassWordModify = new TeacherpassWordModify();
				teacherpassWordModify.setVisible(true);
				table.add(teacherpassWordModify);
			}
		});
		adMenuItem.add(teaPassMenuItem);

		JMenuItem stuPassMenuItem = new JMenuItem("\u5B66\u751F\u5BC6\u7801\u4FEE\u6539");
		stuPassMenuItem.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u4FEE\u6539\u5BC6\u7801.png"));
		stuPassMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentpassWordModify studentpassWordModify = new StudentpassWordModify();
				studentpassWordModify.setVisible(true);
				table.add(studentpassWordModify);
			}
		});
		adMenuItem.add(stuPassMenuItem);

		JMenuItem exitMenuItem = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		exitMenuItem.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\exit.png"));
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(null, "是否退出系统？");
				if (result == 0) {
					dispose();
					new LogOnFrm().setVisible(true);
				}
			}
		});
		operationMenu.add(exitMenuItem);

		JMenu aboutMeMenu = new JMenu("\u5173\u4E8E\u6211\u4EEC");
		aboutMeMenu.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u5173\u4E8E\u6211\u4EEC.png"));
		menuBar.add(aboutMeMenu);

		JMenuItem memberMenuItem = new JMenuItem("\u9879\u76EE\u6210\u5458");
		memberMenuItem.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u6210\u5458.png"));
		memberMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DemoMember demoMember = new DemoMember();
				demoMember.setVisible(true);
				table.add(demoMember);
			}
		});
		aboutMeMenu.add(memberMenuItem);
		getContentPane().setLayout(null);

		table = new JDesktopPane();
		table.setBounds(0, 0, 1914, 975);
		getContentPane().add(table);
	}
}
