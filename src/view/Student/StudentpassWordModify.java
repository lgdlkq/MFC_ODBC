package view.Student;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import base.BaseDAO;
import dao.SC_DAO;
import dao.StudentDAO;
import model.Student;
import util.Constant;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class StudentpassWordModify extends JFrame {

	private JPanel passPane;
	private JPasswordField oldPasswordField;
	private JPasswordField newPasswordField;
	private JPasswordField okPasswordField;
	
	JFrame jFrame =new JFrame();

	private static String uName;
	private static String oldPsaa;
	private static String newPass;
	private static String confirmPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	/**
	 * Create the frame.
	 */
	public StudentpassWordModify(String uName) {
		setResizable(false);
		this.uName = uName;
		setForeground(Color.WHITE);
		setTitle("\u5B66\u751F\u5BC6\u7801\u4FEE\u6539");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 526, 546);
		passPane = new JPanel(){
			protected void paintComponent(Graphics g){
				ImageIcon icon = new ImageIcon("F://JavaProjects//MFC_ODBC//images//i.jpg");  
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, icon.getIconWidth(),  
                        icon.getIconHeight(), icon.getImageObserver());  
                jFrame.setSize(icon.getIconWidth(), icon.getIconHeight());  
			}
		};
		passPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(passPane);
		passPane.setLayout(null);

		// 设置窗口居中显示
		this.setLocationRelativeTo(null);
		
		
		String src ="/img/修改密码.png";
		Image imgae = null;
		try {
			imgae = ImageIO.read(this.getClass().getResource(src));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setIconImage(imgae);

		JLabel oldLabel = new JLabel("\u8BF7\u8F93\u5165\u539F\u5BC6\u7801\uFF1A");
		oldLabel.setBounds(28, 102, 114, 38);
		passPane.add(oldLabel);

		oldPasswordField = new JPasswordField();
		oldPasswordField.setBounds(145, 106, 300, 31);
		passPane.add(oldPasswordField);

		JLabel newLabel = new JLabel("\u8BF7\u8F93\u5165\u65B0\u5BC6\u7801\uFF1A");
		newLabel.setBounds(28, 185, 114, 18);
		passPane.add(newLabel);

		newPasswordField = new JPasswordField();
		newPasswordField.setBounds(145, 179, 300, 31);
		passPane.add(newPasswordField);

		JLabel okLabel = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		okLabel.setBounds(56, 263, 75, 18);
		passPane.add(okLabel);

		okPasswordField = new JPasswordField();
		okPasswordField.setBounds(145, 257, 300, 31);
		passPane.add(okPasswordField);

		JButton confirmButton = new JButton("\u786E\u8BA4");
		confirmButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u786E\u8BA4.png"));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setPass();
				if(!Constant.isEmpty(oldPsaa)){
					JOptionPane.showConfirmDialog(null, "原密码不能为空！","提示",JOptionPane.CLOSED_OPTION);
					return;
				}
				if(!Constant.isEmpty(newPass)){
					JOptionPane.showConfirmDialog(null, "新密码不能为空！","提示",JOptionPane.CLOSED_OPTION);
					return;
				}
				if(!Constant.isEmpty(confirmPass)){
					JOptionPane.showConfirmDialog(null, "请输入确认密码！","提示",JOptionPane.CLOSED_OPTION);
					return;
				}
				if (!confirmPass.equals(newPass)) {
					JOptionPane.showConfirmDialog(null, "两次输入的密码不一致，请重新输入！","提示",JOptionPane.CLOSED_OPTION);
					return;
				}
				StudentDAO stu = new StudentDAO();
				ResultSet rSet = stu.stuSelecte(new Student(uName, oldPsaa));
				try {
					if(rSet.next()){
						if(stu.stuModify(uName, new Student(rSet.getString(1),rSet.getString(2),rSet.getString(3),
								rSet.getInt(4),rSet.getString(5),rSet.getString(6),rSet.getString(7), newPass)) == 1){
							JOptionPane.showConfirmDialog(null, "修改成功！","提示",JOptionPane.CLOSED_OPTION);
						}
					}else {
						JOptionPane.showConfirmDialog(null, "原密码错误，无法修改！","警告",JOptionPane.CLOSED_OPTION);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}finally{
					BaseDAO.close();
				}
			}
		});
		confirmButton.setBounds(70, 395, 113, 27);
		passPane.add(confirmButton);

		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\reset.png"));
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (resetButton.isEnabled()) {
					oldPasswordField.setText(null);
					newPasswordField.setText(null);
					okPasswordField.setText(null);
				}
			}
		});
		resetButton.setBounds(285, 395, 113, 27);
		passPane.add(resetButton);
	}

	private void setPass() {
		oldPsaa = new String(oldPasswordField.getPassword()).trim();
		newPass = new String(newPasswordField.getPassword()).trim();
		confirmPass = new String(okPasswordField.getPassword()).trim();
	}
}
