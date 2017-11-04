package view.teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import base.BaseDAO;
import dao.TeacherDAO;
import model.Teacher;
import util.Constant;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class TeacherMessageModify extends JFrame {

	private JPanel modifyteaPane;
	private JTextField tnoTextField;
	private JTextField tnameTextField;
	private JTextField ttelTextField;
	private JRadioButton boyRadioButton;
	private JRadioButton girlRadioButton;

	JFrame jFrame =new JFrame();
	private static String uName;
	private String tno;
	private String tname;
	private String tsex;
	private String tel;
	private String tpass;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public TeacherMessageModify(String uName) {
		this.uName = uName;
		setTitle("\u6559\u5E08\u4FE1\u606F\u4FEE\u6539");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 381, 564);
		modifyteaPane = new JPanel(){
			protected void paintComponent(Graphics g){
				ImageIcon icon = new ImageIcon("F://JavaProjects//MFC_ODBC//images//f.jpg");  
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, icon.getIconWidth(),  
                        icon.getIconHeight(), icon.getImageObserver());  
                jFrame.setSize(icon.getIconWidth(), icon.getIconHeight());  
			}
		};
		modifyteaPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(modifyteaPane);
		modifyteaPane.setLayout(null);
		
		//设置窗口居中显示
		this.setLocationRelativeTo(null);
		
		

		String src ="/img/修改个人信息.png";
		Image imgae = null;
		try {
			imgae = ImageIO.read(this.getClass().getResource(src));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setIconImage(imgae);
		
		JLabel tnoLabel = new JLabel("\u6559\u5E08\u53F7\uFF1A");
		tnoLabel.setBounds(56, 71, 72, 18);
		modifyteaPane.add(tnoLabel);
		
		JLabel tnameLabel = new JLabel("\u6559\u5E08\u540D\uFF1A");
		tnameLabel.setBounds(56, 149, 72, 18);
		modifyteaPane.add(tnameLabel);
		
		JLabel tsexLabel = new JLabel("\u6027\u522B\uFF1A");
		tsexLabel.setBounds(56, 228, 72, 18);
		modifyteaPane.add(tsexLabel);
		
		JLabel ttelLabel = new JLabel("\u7535 \u8BDD\uFF1A");
		ttelLabel.setBounds(56, 302, 72, 18);
		modifyteaPane.add(ttelLabel);
		
		tnoTextField = new JTextField();
		tnoTextField.setEnabled(false);
		tnoTextField.setBounds(129, 68, 144, 24);
		modifyteaPane.add(tnoTextField);
		tnoTextField.setColumns(10);
		
		tnameTextField = new JTextField();
		tnameTextField.setEnabled(false);
		tnameTextField.setBounds(130, 146, 144, 24);
		modifyteaPane.add(tnameTextField);
		tnameTextField.setColumns(10);
		
		boyRadioButton = new JRadioButton("\u7537");
		boyRadioButton.setEnabled(false);
		boyRadioButton.setBounds(139, 225, 55, 25);
		modifyteaPane.add(boyRadioButton);
		
		girlRadioButton = new JRadioButton("\u5973");
		girlRadioButton.setEnabled(false);
		girlRadioButton.setBounds(218, 225, 55, 25);
		modifyteaPane.add(girlRadioButton);
		
		ttelTextField = new JTextField();
		ttelTextField.setBounds(129, 299, 144, 24);
		modifyteaPane.add(ttelTextField);
		ttelTextField.setColumns(10);
		
		JButton modifyButton = new JButton("\u4FEE\u6539");
		modifyButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u4FEE\u6539.png"));
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tel = ttelTextField.getText().toString().trim();
				if (tel.length() != 11) {
					JOptionPane.showConfirmDialog(null, "电话号码位数错误！", "提示", JOptionPane.CLOSED_OPTION);
					return;
				}
				if (!Constant.isNumeric(tel)) {
					JOptionPane.showConfirmDialog(null, "电话号码应为11位数字！", "提示", JOptionPane.CLOSED_OPTION);
					return;
				}
				
				TeacherDAO tDao = new TeacherDAO();
				if(tDao.teaModify(new Teacher(uName,tname,tsex,tel,tpass)) == 1){
					JOptionPane.showConfirmDialog(null, "修改成功！","提示",JOptionPane.CLOSED_OPTION);
				}else{
					JOptionPane.showConfirmDialog(null,  "您输入的电话已被使用，请重新输入！", "提示", JOptionPane.CLOSED_OPTION);
					ttelTextField.setText("");
				}
			}
		});
		modifyButton.setBounds(118, 393, 113, 27);
		modifyteaPane.add(modifyButton);
		
		messageSet();
	}
	
	private void messageSet(){
		TeacherDAO tDao = new TeacherDAO();
		ResultSet rSet = tDao.teaSelecte(new Teacher(uName));
		try {
			while(rSet.next()){
				tnoTextField.setText(rSet.getString(1));
				tnameTextField.setText(rSet.getString(2));
				if(rSet.getString(3).equals("男")){
					boyRadioButton.setSelected(true);
					girlRadioButton.setSelected(false);
				}else {
					boyRadioButton.setSelected(false);
					girlRadioButton.setSelected(true);
				}
				ttelTextField.setText(rSet.getString(4));
				
				tname = rSet.getString(2);
				tsex = rSet.getString(3);
				tpass = rSet.getString(5);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			BaseDAO.close();
		}
	}
}
