package view.Welcome;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import base.BaseDAO;
import util.Constant;
import view.Student.StudentViewFrm;
import view.office.OfficeViewFrm;
import view.teacher.TeacherViewFrm;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;

public class LogOnFrm extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passWordTxt;
	private JComboBox identityBox;
	
	
	JFrame jframe = new JFrame();
	
	private String uName;
	private String uPass;
	private String uIdentity;
	
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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogOnFrm frame = new LogOnFrm();
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
	public LogOnFrm() {
		setResizable(false);
		setTitle("\u5B66\u751F\u6210\u7EE9\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 471);
		contentPane = new JPanel(){};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//设置窗口居中显示
				this.setLocationRelativeTo(null);
		
				String src ="/img/学生成绩登记.png";
				Image imgae = null;
				try {
					imgae = ImageIO.read(this.getClass().getResource(src));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setIconImage(imgae);
				
		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u6210\u7EE9\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setIcon(new ImageIcon("F:\\Work\\photo\\\u5C0F\u56FE\u6807\\images\\contract_32px_1192381_easyicon.net.png"));
		lblNewLabel.setFont(new Font("黑体", Font.BOLD, 31));
		lblNewLabel.setBounds(170, 29, 267, 45);
		contentPane.add(lblNewLabel);
		
		JLabel userLable = new JLabel("\u8D26\u53F7\uFF1A");
		userLable.setFont(new Font("宋体", Font.PLAIN, 17));
		userLable.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\userName.png"));
		userLable.setBounds(150, 109, 71, 35);
		contentPane.add(userLable);
		
		JLabel passLable = new JLabel("\u5BC6\u7801\uFF1A");
		passLable.setFont(new Font("宋体", Font.PLAIN, 17));
		passLable.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\password.png"));
		passLable.setBounds(150, 184, 71, 35);
		contentPane.add(passLable);
		
		userNameTxt = new JTextField();
		userNameTxt.setBounds(242, 106, 232, 38);
		contentPane.add(userNameTxt);
		userNameTxt.setColumns(10);
		
		passWordTxt = new JPasswordField();
		passWordTxt.setBounds(242, 181, 232, 38);
		contentPane.add(passWordTxt);
		
		
		identityBox = new JComboBox();
		identityBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		identityBox.setModel(new DefaultComboBoxModel(new String[] {"\u5B66\u751F", "\u6559\u5E08", "\u6559\u52A1\u5904"}));
		identityBox.setBounds(238, 239, 113, 38);
		contentPane.add(identityBox);
		
		JLabel userIdentity = new JLabel("\u7528\u6237\u8EAB\u4EFD:");
		userIdentity.setFont(new Font("宋体", Font.PLAIN, 17));
		userIdentity.setIcon(new ImageIcon("F:\\Work\\photo\\\u5C0F\u56FE\u6807\\images\\identity_16px_26393_easyicon.net.png"));
		userIdentity.setBounds(150, 242, 80, 35);
		contentPane.add(userIdentity);
		
		JRadioButton showPass = new JRadioButton("\u663E\u793A\u5BC6\u7801");
		showPass.setFont(new Font("宋体", Font.PLAIN, 17));
		showPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (showPass.isSelected()) {
					passWordTxt.setEchoChar('\0');
				}else{
					passWordTxt.setEchoChar('*');
				}
			}
		});
		showPass.setBounds(380, 236, 103, 41);
		contentPane.add(showPass);
		
		JButton loginButton = new JButton("\u767B\u5F55");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setText();
				if (!Constant.isEmpty(uName)) {
					JOptionPane.showConfirmDialog(null, "用户名不能为空！","警告",JOptionPane.CLOSED_OPTION);
					return ;
				}
				if (!Constant.isEmpty(uPass)) {
					JOptionPane.showConfirmDialog(null, "密码不能为空！","警告",JOptionPane.CLOSED_OPTION);
					return ;
				}
				if("学生".equals(uIdentity) && BaseDAO.login(uName, uPass, "Student", "Sno")){
					new StudentViewFrm(uName).setVisible(true);
					dispose();
				}else if ("教师".equals(uIdentity) && BaseDAO.login(uName, uPass, "Teacher", "Tno")) {
					new TeacherViewFrm(uName).setVisible(true);
					dispose();
				}else if("教务处".equals(uIdentity) && BaseDAO.login(uName, uPass, "AU", "Uno")){
					new OfficeViewFrm(uName).setVisible(true);
					dispose();
				}else {
					JOptionPane.showConfirmDialog(null, "用户名、密码或用户身份错误！","提示",JOptionPane.CLOSED_OPTION);
				}
			}
		});
		loginButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\login.png"));
		loginButton.setBounds(113, 310, 113, 35);
		contentPane.add(loginButton);
		
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  userNameTxt.setText("");
				  passWordTxt.setText("");
			}
		});
		resetButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\reset.png"));
		resetButton.setBounds(362, 310, 113, 35);
		contentPane.add(resetButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u5B66\u751F\u6210\u7EE9 (1).png"));
		lblNewLabel_1.setBounds(28, 109, 122, 118);
		contentPane.add(lblNewLabel_1);
		
	}
	
	private void setText(){
		uName = userNameTxt.getText().trim();
		uPass = new String(passWordTxt.getPassword());
		uPass = uPass.trim();
		uIdentity = (String) identityBox.getSelectedItem();
	}
}
