package view.office;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import base.BaseDAO;
import dao.StudentDAO;
import model.Student;
import util.Constant;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class StudentpassWordModify extends JInternalFrame {
	private JTextField snoField;
	private JPasswordField newPassField;
	private JPasswordField okPassField;
	
	private static String sno;
	private static String newPass;
	private static String okPass;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	/**
	 * Create the frame.
	 */
	public StudentpassWordModify() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u5B66\u751F\u5BC6\u7801\u4FEE\u6539");
		setBounds(100, 100, 357, 458);
		getContentPane().setLayout(null);
		
		JLabel snoLabel = new JLabel("\u8BF7\u8F93\u5165\u5B66\u53F7\uFF1A");
		snoLabel.setBounds(40, 66, 112, 18);
		getContentPane().add(snoLabel);
		
		snoField = new JTextField();
		snoField.setColumns(10);
		snoField.setBounds(158, 58, 146, 35);
		getContentPane().add(snoField);
		
		JLabel newPassLabel = new JLabel("\u8BF7\u8F93\u5165\u65B0\u5BC6\u7801\uFF1A");
		newPassLabel.setBounds(40, 133, 112, 18);
		getContentPane().add(newPassLabel);
		
		newPassField = new JPasswordField();
		newPassField.setBounds(158, 125, 146, 35);
		getContentPane().add(newPassField);
		
		JLabel okPassLabel = new JLabel("\u786E\u8BA4\u65B0\u5BC6\u7801\uFF1A");
		okPassLabel.setBounds(40, 203, 112, 18);
		getContentPane().add(okPassLabel);
		
		okPassField = new JPasswordField();
		okPassField.setBounds(158, 195, 146, 35);
		getContentPane().add(okPassField);
		
		JButton confirmButton = new JButton("\u786E\u8BA4");
		confirmButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u786E\u8BA4.png"));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				messageGet();
				if(!Constant.isEmpty(sno)){
					JOptionPane.showConfirmDialog(null, "学号不能为空！","提示",JOptionPane.CLOSED_OPTION);
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
				
				StudentDAO stu = new StudentDAO();
				ResultSet rSet = stu.stuSelecte(new Student(sno));
				try {
					if(rSet.next()){
						if(stu.stuModify(sno, new Student(rSet.getString(1),rSet.getString(2),rSet.getString(3),
								rSet.getInt(4),rSet.getString(5),rSet.getString(6),rSet.getString(7), newPass)) == 1){
							JOptionPane.showConfirmDialog(null, "修改成功！","提示",JOptionPane.CLOSED_OPTION);
						}
					}else {
						JOptionPane.showConfirmDialog(null, "修改失败，请检查学号是否正确！","警告",JOptionPane.CLOSED_OPTION);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}finally{
					BaseDAO.close();
				}
			}
		});
		confirmButton.setBounds(51, 302, 101, 35);
		getContentPane().add(confirmButton);
		
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\reset.png"));
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				snoField.setText(null);
				newPassField.setText(null);
				okPassField.setText(null);
			}
		});
		resetButton.setBounds(186, 302, 101, 35);
		getContentPane().add(resetButton);

	}

	private void messageGet(){
		sno = snoField.getText().toString().trim();
		newPass = new String(newPassField.getPassword()).toString().trim();
		okPass = new String(okPassField.getPassword()).toString().trim();
	}
}
