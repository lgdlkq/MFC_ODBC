package view.teacher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import base.BaseDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import model.Student;
import model.Teacher;
import util.Constant;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class TeacherpassWordModify extends JFrame {

	private JPanel contentPane;
	private JPasswordField oldPasswordField;
	private JPasswordField newPasswordField;
	private JPasswordField okPasswordField;
	JFrame jFrame = new JFrame();

	private static String uName;
	private String oldPass;
	private String newPass;
	private String okPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	/**
	 * Create the frame.
	 */
	public TeacherpassWordModify(String uName) {
		this.uName = uName;
		setForeground(Color.WHITE);
		setTitle("\u6559\u5E08\u5BC6\u7801\u4FEE\u6539");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 526, 546);
		contentPane = new JPanel(){
			protected void paintComponent(Graphics g){
				ImageIcon icon = new ImageIcon("F://JavaProjects//MFC_ODBC//images//d.jpg");  
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
		contentPane.add(oldLabel);

		oldPasswordField = new JPasswordField();
		oldPasswordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		oldPasswordField.setBounds(145, 106, 300, 31);
		contentPane.add(oldPasswordField);

		JLabel newLabel = new JLabel("\u8BF7\u8F93\u5165\u65B0\u5BC6\u7801\uFF1A");
		newLabel.setBounds(28, 185, 114, 18);
		contentPane.add(newLabel);

		newPasswordField = new JPasswordField();
		newPasswordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		newPasswordField.setBounds(145, 179, 300, 31);
		contentPane.add(newPasswordField);

		JLabel okLabel = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		okLabel.setBounds(56, 263, 75, 18);
		contentPane.add(okLabel);

		okPasswordField = new JPasswordField();
		okPasswordField.setBounds(145, 257, 300, 31);
		contentPane.add(okPasswordField);

		JButton confirmButton = new JButton("\u786E\u8BA4");
		confirmButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u786E\u8BA4.png"));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPass();
				if(!Constant.isEmpty(oldPass)){
					JOptionPane.showConfirmDialog(null, "原密码不能为空！","提示",JOptionPane.CLOSED_OPTION);
					return;
				}
				if(!Constant.isEmpty(newPass)){
					JOptionPane.showConfirmDialog(null, "新密码不能为空！","提示",JOptionPane.CLOSED_OPTION);
					return;
				}
				if(!Constant.isEmpty(okPass)){
					JOptionPane.showConfirmDialog(null, "请输入确认密码！","提示",JOptionPane.CLOSED_OPTION);
					return;
				}
				if (!okPass.equals(newPass)) {
					JOptionPane.showConfirmDialog(null, "两次输入的密码不一致，请重新输入！","提示",JOptionPane.CLOSED_OPTION);
					return;
				}
				TeacherDAO tDao = new TeacherDAO();
				ResultSet rSet = tDao.teaSelecte(new Teacher(uName));
				try {
					if(rSet.next()){
						if(tDao.teaModify(new Teacher(uName,rSet.getString(2),rSet.getString(3),rSet.getString(4),newPass)) == 1){
							JOptionPane.showConfirmDialog(null, "修改成功！","提示",JOptionPane.CLOSED_OPTION);
							dispose();
						}
					}else {
						JOptionPane.showConfirmDialog(null, "原密码错误，无法修改！","警告",JOptionPane.CLOSED_OPTION);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					BaseDAO.close();
				}
			}
		});
		confirmButton.setBounds(70, 395, 113, 27);
		contentPane.add(confirmButton);

		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\reset.png"));
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				oldPasswordField.setText(null);
				newPasswordField.setText(null);
				okPasswordField.setText(null);
			}
		});
		resetButton.setBounds(285, 395, 113, 27);
		contentPane.add(resetButton);
	}

	private void getPass() {
		oldPass = new String(oldPasswordField.getPassword()).toString().trim();
		newPass = new String(newPasswordField.getPassword()).toString().trim();
		okPass = new String(okPasswordField.getPassword()).toString().trim();
	}
}
