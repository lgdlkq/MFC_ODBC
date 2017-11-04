package view.Student;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import base.BaseDAO;
import dao.StudentDAO;
import model.Student;
import view.Welcome.LogOnFrm;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class StudentViewFrm extends JFrame {

	private JPanel contentPane;
	private static JTextField snoTextFiled;
	private static JTextField snameTextFiled;
	private static JTextField sageTextFiled;
	private static JTextField sclassTextFiled;
	private static JTextField stelTextFiled;
	private static JTextField sdeptTextFiled;
	private static JRadioButton boyButton;
	private static JRadioButton girlButton;
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
	public StudentViewFrm(String uName) {
		setResizable(false);
		this.uName = uName;
		setTitle("\u5B66\u751F\u7BA1\u7406\u7CFB\u7EDF\u4E3B\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 588);

		// 设置窗口居中显示
		this.setLocationRelativeTo(null);
		
		String src ="/img/学生.png";
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

		JMenu mangerNewMenu = new JMenu("\u7BA1\u7406");
		mangerNewMenu.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\base.png"));
		menuBar.add(mangerNewMenu);

		JMenuItem selectCourseMenu = new JMenuItem("\u9009\u8BFE");
		selectCourseMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StudentChooseCourse(uName).setVisible(true);

			}
		});
		selectCourseMenu.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u9009\u8BFE.png"));
		mangerNewMenu.add(selectCourseMenu);

		JMenuItem modifyPassMenu = new JMenuItem("\u5BC6\u7801\u4FEE\u6539");
		modifyPassMenu.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u4FEE\u6539\u5BC6\u7801.png"));
		modifyPassMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StudentpassWordModify(uName).setVisible(true);

			}
		});
		
		mangerNewMenu.add(modifyPassMenu);
		JMenuItem modifyMessageMenu = new JMenuItem("\u4E2A\u4EBA\u4FE1\u606F\u4FEE\u6539");
		modifyMessageMenu.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F.png"));
		modifyMessageMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StudentMessageModify(uName).setVisible(true);

			}
		});
		mangerNewMenu.add(modifyMessageMenu);

		JMenuItem exitMenu = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		exitMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LogOnFrm().setVisible(true);
			}
		});
		
		JMenuItem queryCourseMenuItem = new JMenuItem("\u6210\u7EE9\u67E5\u8BE2");
		queryCourseMenuItem.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u6210\u7EE9\u67E5\u8BE2.png"));
		queryCourseMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new StudentMyGradeView(uName).setVisible(true);
			}
		});
		mangerNewMenu.add(queryCourseMenuItem);
		exitMenu.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\exit.png"));
		mangerNewMenu.add(exitMenu);
		contentPane = new JPanel(){
			protected void paintComponent(Graphics g){
				ImageIcon icon = new ImageIcon("F://JavaProjects//MFC_ODBC//images//e.jpg");  
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, icon.getIconWidth(),  
                        icon.getIconHeight(), icon.getImageObserver());  
                jframe.setSize(icon.getIconWidth(), icon.getIconHeight());  
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel snoLabel = new JLabel("\u5B66\u53F7\uFF1A");
		snoLabel.setBounds(158, 63, 72, 18);
		contentPane.add(snoLabel);

		snoTextFiled = new JTextField();
		snoTextFiled.setEnabled(false);
		snoTextFiled.setBounds(258, 60, 128, 24);
		contentPane.add(snoTextFiled);
		snoTextFiled.setColumns(10);

		JLabel snameLable = new JLabel("\u59D3\u540D\uFF1A");
		snameLable.setBounds(158, 120, 72, 18);
		contentPane.add(snameLable);

		snameTextFiled = new JTextField();
		snameTextFiled.setEnabled(false);
		snameTextFiled.setBounds(258, 120, 128, 24);
		contentPane.add(snameTextFiled);
		snameTextFiled.setColumns(10);

		JLabel ssexLable = new JLabel("\u6027\u522B\uFF1A");
		ssexLable.setBounds(158, 174, 72, 18);
		contentPane.add(ssexLable);

		JLabel sageLable = new JLabel("\u5E74\u9F84\uFF1A");
		sageLable.setBounds(158, 232, 72, 18);
		contentPane.add(sageLable);

		sageTextFiled = new JTextField();
		sageTextFiled.setEnabled(false);
		sageTextFiled.setBounds(258, 229, 128, 24);
		contentPane.add(sageTextFiled);
		sageTextFiled.setColumns(10);

		boyButton = new JRadioButton("\u7537");
		boyButton.setEnabled(false);
		boyButton.setBounds(258, 170, 61, 27);
		contentPane.add(boyButton);

		girlButton = new JRadioButton("\u5973");
		girlButton.setEnabled(false);
		girlButton.setBounds(325, 170, 61, 27);
		contentPane.add(girlButton);

		JLabel sclassLable = new JLabel("\u73ED\u7EA7\uFF1A");
		sclassLable.setBounds(158, 289, 72, 18);
		contentPane.add(sclassLable);

		sclassTextFiled = new JTextField();
		sclassTextFiled.setEnabled(false);
		sclassTextFiled.setBounds(258, 286, 128, 24);
		contentPane.add(sclassTextFiled);
		sclassTextFiled.setColumns(10);

		JLabel stelLable = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
		stelLable.setBounds(158, 346, 86, 18);
		contentPane.add(stelLable);

		stelTextFiled = new JTextField();
		stelTextFiled.setEnabled(false);
		stelTextFiled.setBounds(258, 343, 128, 24);
		contentPane.add(stelTextFiled);
		stelTextFiled.setColumns(10);

		JLabel sdeptLable = new JLabel("\u6240\u5728\u7CFB\uFF1A");
		sdeptLable.setBounds(158, 403, 72, 18);
		contentPane.add(sdeptLable);

		sdeptTextFiled = new JTextField();
		sdeptTextFiled.setEnabled(false);
		sdeptTextFiled.setBounds(258, 400, 128, 24);
		contentPane.add(sdeptTextFiled);
		sdeptTextFiled.setColumns(10);
		
		setMessage();
	}

	protected static void setMessage() {
		StudentDAO stuDao = new StudentDAO();
		ResultSet rSet = stuDao.stuSelecte(new Student(uName));

		try {
			if (rSet.next()) {
				sclassTextFiled.setText(rSet.getString(1));
				snoTextFiled.setText(rSet.getString(2));
				snameTextFiled.setText(rSet.getString(3));
				sageTextFiled.setText(Integer.toString(rSet.getInt(4)));
				if ("男".equals(rSet.getString(5))) {
					boyButton.setSelected(true);
					girlButton.setSelected(false);
				} else {
					boyButton.setSelected(false);
					girlButton.setSelected(true);
				}
				stelTextFiled.setText(rSet.getString(6));
				sdeptTextFiled.setText(rSet.getString(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDAO.close();
		}
	}
}
