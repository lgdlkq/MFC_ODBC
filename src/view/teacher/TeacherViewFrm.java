package view.teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import view.Welcome.LogOnFrm;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class TeacherViewFrm extends JFrame {

	private JPanel contentPane;
	JFrame jframe = new JFrame();
	
	private static String uName;

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
		
		UIManager.put("RootPane.setupButtonVisible", false);
	}

	/**
	 * Create the frame.
	 */
	public TeacherViewFrm(String uName) {
		setResizable(false);
		this.uName = uName;
		setTitle("\u6559\u5E08\u4E3B\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 699);
		
		//设置窗口居中显示
		this.setLocationRelativeTo(null);
		
		
		String src ="/img/教师中心.png";
		Image imgae = null;
		try {
			imgae = ImageIO.read(this.getClass().getResource(src));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setIconImage(imgae);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u9009\u9879");
		mnNewMenu.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\base.png"));
		menuBar.add(mnNewMenu);
		
		JMenuItem courseMenuItem = new JMenuItem("\u8BFE\u7A0B\u8868");
		courseMenuItem.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u8BFE\u7A0B\u8868.png"));
		courseMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherCourseTable teacherCourseTable= new TeacherCourseTable(uName);
				teacherCourseTable.setVisible(true);
			}
		});
		mnNewMenu.add(courseMenuItem);
		
		JMenuItem gradeMenuItem = new JMenuItem("\u6210\u7EE9\u5F55\u5165");
		gradeMenuItem.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u5F55\u5165.png"));
		gradeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentGradeInto studentGradeInto = new StudentGradeInto(uName);
				studentGradeInto.setVisible(true);
			}
		});
		mnNewMenu.add(gradeMenuItem);
		
		JMenuItem messageMenuItem = new JMenuItem("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");
		messageMenuItem.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F.png"));
		messageMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherMessageModify teacherMessageModify = new TeacherMessageModify(uName);
				teacherMessageModify.setVisible(true);
			}
		});
		mnNewMenu.add(messageMenuItem);
		
		JMenuItem passMenuItem = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		passMenuItem.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u4FEE\u6539\u5BC6\u7801.png"));
		passMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherpassWordModify teacherpassWordModify = new TeacherpassWordModify(uName);
				teacherpassWordModify.setVisible(true);
			}
		});
		mnNewMenu.add(passMenuItem);
		
		JMenuItem exitMenuItem = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		exitMenuItem.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\exit.png"));
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LogOnFrm().setVisible(true);
			}
		});
		mnNewMenu.add(exitMenuItem);
		contentPane = new JPanel(){
			protected void paintComponent(Graphics g){
				ImageIcon icon = new ImageIcon("F://JavaProjects//MFC_ODBC//images//c.jpg");  
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, icon.getIconWidth(),  
                        icon.getIconHeight(), icon.getImageObserver());  
                
				jframe.setSize(icon.getIconWidth(), icon.getIconHeight());  
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
