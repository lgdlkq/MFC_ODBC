package view.office;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.AU_DAO;
import model.AU;
import util.Constant;
import view.Welcome.LogOnFrm;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class OfficepassWordModify extends JInternalFrame {
	private JPasswordField oldPassField;
	private JPasswordField newPassField;
	private JPasswordField okPassField;
	private String oldPsaa;
	private String newPass;
	private String confirmPass;

	private static String Uname;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public OfficepassWordModify(String uName) {
		setFrameIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u4FEE\u6539\u5BC6\u7801.png"));
		this.Uname = uName;
		setIconifiable(true);
		setClosable(true);
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		setBounds(100, 100, 477, 566);
		getContentPane().setLayout(null);
		
		JLabel oldLabel = new JLabel("\u8BF7\u8F93\u5165\u539F\u5BC6\u7801\uFF1A");
		oldLabel.setBounds(58, 105, 117, 18);
		getContentPane().add(oldLabel);
		
		JLabel newLabel = new JLabel("\u8BF7\u8F93\u5165\u65B0\u5BC6\u7801\uFF1A");
		newLabel.setBounds(58, 197, 117, 18);
		getContentPane().add(newLabel);
		
		JLabel okLabel = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		okLabel.setBounds(71, 278, 98, 18);
		getContentPane().add(okLabel);
		
		oldPassField = new JPasswordField();
		oldPassField.setBounds(189, 100, 191, 28);
		getContentPane().add(oldPassField);
		
		newPassField = new JPasswordField();
		newPassField.setBounds(189, 192, 191, 28);
		getContentPane().add(newPassField);
		
		okPassField = new JPasswordField();
		okPassField.setBounds(189, 273, 191, 28);
		getContentPane().add(okPassField);
		
		JButton confirmButton = new JButton("\u786E\u8BA4");
		confirmButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u786E\u8BA4.png"));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPass();
				if(!Constant.isEmpty(oldPsaa)){
					JOptionPane.showConfirmDialog(null, "原密码不能为空！","提示",JOptionPane.CLOSED_OPTION);
					return;
				}
				if(!Constant.isEmpty(newPass)){
					JOptionPane.showConfirmDialog(null, "新密码不能为空！","提示",JOptionPane.CLOSED_OPTION);
					return;
				}
				if(!Constant.isEmpty(confirmPass)){
					JOptionPane.showConfirmDialog(null, "请输入新密码密码！","提示",JOptionPane.CLOSED_OPTION);
					return;
				}
				if (!confirmPass.equals(newPass)) {
					JOptionPane.showConfirmDialog(null, "两次输入的密码不一致，请重新输入！","提示",JOptionPane.CLOSED_OPTION);
					return;
				}
				
				AU_DAO aDao = new AU_DAO();
				ResultSet rSet = aDao.AUSelect(new AU(uName, oldPsaa));
				
				try {
					if (rSet.wasNull()) {
						return;
					}
					if(aDao.AUmodify(new AU(uName, newPass)) == 1){
						JOptionPane.showConfirmDialog(null, "修改成功！","提示", JOptionPane.CLOSED_OPTION);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		confirmButton.setBounds(77, 413, 113, 27);
		getContentPane().add(confirmButton);
		
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\reset.png"));
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oldPassField.setText(null);
				newPassField.setText(null);
				okPassField.setText(null);
			}
		});
		resetButton.setBounds(285, 413, 113, 27);
		getContentPane().add(resetButton);

	}

	private void getPass() {
		oldPsaa = new String(oldPassField.getPassword()).trim();
		newPass = new String(newPassField.getPassword()).trim();
		confirmPass = new String(okPassField.getPassword()).trim();
	}
	
}
