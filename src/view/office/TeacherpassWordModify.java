package view.office;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import base.BaseDAO;
import dao.TeacherDAO;
import model.Teacher;
import util.Constant;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class TeacherpassWordModify extends JInternalFrame {
	private JTextField tnoField;
	private JPasswordField newPassField;
	private JPasswordField okPassField;

	private static String tno;
	private static String newPass;
	private static String okPass;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherpassWordModify frame = new TeacherpassWordModify();
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
	public TeacherpassWordModify() {
		setIconifiable(true);
		setClosable(true);
		setTitle("\u6559\u5E08\u5BC6\u7801\u4FEE\u6539");
		setBounds(100, 100, 404, 487);
		getContentPane().setLayout(null);
		
		JLabel tnoLabel = new JLabel("\u8BF7\u8F93\u5165\u6559\u5E08\u53F7\uFF1A");
		tnoLabel.setBounds(64, 88, 112, 18);
		getContentPane().add(tnoLabel);
		
		tnoField = new JTextField();
		tnoField.setColumns(10);
		tnoField.setBounds(182, 80, 146, 35);
		getContentPane().add(tnoField);
		
		JLabel newPassLabel = new JLabel("\u8BF7\u8F93\u5165\u65B0\u5BC6\u7801\uFF1A");
		newPassLabel.setBounds(64, 155, 112, 18);
		getContentPane().add(newPassLabel);
		
		newPassField = new JPasswordField();
		newPassField.setBounds(182, 147, 146, 35);
		getContentPane().add(newPassField);
		
		JLabel okPassLabel = new JLabel("\u786E\u8BA4\u65B0\u5BC6\u7801\uFF1A");
		okPassLabel.setBounds(64, 225, 112, 18);
		getContentPane().add(okPassLabel);
		
		okPassField = new JPasswordField();
		okPassField.setBounds(182, 217, 146, 35);
		getContentPane().add(okPassField);
		
		JButton confirmButton = new JButton("\u786E\u8BA4");
		confirmButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u786E\u8BA4.png"));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				messageGet();
				if(!Constant.isEmpty(tno)){
					JOptionPane.showConfirmDialog(null, "教师号不能为空！","提示",JOptionPane.CLOSED_OPTION);
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
				ResultSet rSet = tDao.teaSelecte(new Teacher(tno));
				try {
					if(rSet.next()){
						if(tDao.teaModify(new Teacher(tno,rSet.getString(2),rSet.getString(3),rSet.getString(4),newPass)) == 1){
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
		confirmButton.setBounds(75, 324, 101, 35);
		getContentPane().add(confirmButton);
		
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\reset.png"));
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tnoField.setText(null);
				newPassField.setText(null);
				okPassField.setText(null);
			}
		});
		resetButton.setBounds(210, 324, 101, 35);
		getContentPane().add(resetButton);

	}
	
	private void messageGet(){
		tno = tnoField.getText().toString().trim();
		newPass = new String(newPassField.getPassword()).toString().trim();
		okPass = new String(okPassField.getPassword()).toString().trim();
	}

}
