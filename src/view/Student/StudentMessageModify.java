package view.Student;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import base.BaseDAO;
import dao.StudentDAO;
import model.Student;
import util.Constant;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class StudentMessageModify extends JFrame {

	private JPanel modifyPane;
	private JTextField snoTextField;
	private JTextField snameTextField;
	private JTextField stelTextField;
	private JTextField sclassTextField;
	private JTextField sageTextField;
	private JTextField sdeptTextField;
	private JRadioButton boyRadioButton;
	private JRadioButton girlRadioButton;
	JFrame jFrame =new JFrame();

	private static String uName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
	}

	/**
	 * Create the frame.
	 */
	public StudentMessageModify(String uName) {
		setResizable(false);
		this.uName = uName;
		setTitle("\u5B66\u751F\u4FE1\u606F\u4FEE\u6539");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 544, 724);
		modifyPane = new JPanel(){
			protected void paintComponent(Graphics g){
				ImageIcon icon = new ImageIcon("F://JavaProjects//MFC_ODBC//images//K.jpg");  
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, icon.getIconWidth(),  
                        icon.getIconHeight(), icon.getImageObserver());  
                jFrame.setSize(icon.getIconWidth(), icon.getIconHeight());  
			}
		};
		modifyPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(modifyPane);
		modifyPane.setLayout(null);

		// 设置窗口居中显示
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
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				
			}
		});

		JLabel snoLabel = new JLabel("\u5B66 \u53F7\uFF1A");
		snoLabel.setBounds(90, 30, 72, 33);
		modifyPane.add(snoLabel);

		snoTextField = new JTextField();
		snoTextField.setEnabled(false);
		snoTextField.setEditable(false);
		snoTextField.setBounds(186, 27, 172, 36);
		modifyPane.add(snoTextField);
		snoTextField.setColumns(10);

		JLabel snameLabel = new JLabel("\u59D3 \u540D\uFF1A");
		snameLabel.setBounds(90, 111, 72, 33);
		modifyPane.add(snameLabel);

		snameTextField = new JTextField();
		snameTextField.setEnabled(false);
		snameTextField.setEditable(false);
		snameTextField.setBounds(186, 108, 172, 36);
		modifyPane.add(snameTextField);
		snameTextField.setColumns(10);

		JLabel ssexLabel = new JLabel("\u6027 \u522B\uFF1A");
		ssexLabel.setBounds(90, 175, 72, 33);
		modifyPane.add(ssexLabel);

		boyRadioButton = new JRadioButton("\u7537");
		boyRadioButton.setEnabled(false);
		boyRadioButton.setBounds(198, 175, 49, 38);
		modifyPane.add(boyRadioButton);

		girlRadioButton = new JRadioButton("\u5973");
		girlRadioButton.setEnabled(false);
		girlRadioButton.setBounds(280, 172, 64, 38);
		modifyPane.add(girlRadioButton);

		JLabel sclassLabel = new JLabel("\u73ED \u7EA7\uFF1A");
		sclassLabel.setBounds(90, 250, 72, 33);
		modifyPane.add(sclassLabel);

		JLabel sageLabel = new JLabel("\u5E74 \u9F84:");
		sageLabel.setBounds(90, 325, 72, 33);
		modifyPane.add(sageLabel);

		JLabel stelLabel = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
		stelLabel.setBounds(90, 392, 86, 33);
		modifyPane.add(stelLabel);

		stelTextField = new JTextField();
		stelTextField.setBounds(186, 392, 172, 36);
		modifyPane.add(stelTextField);
		stelTextField.setColumns(10);

		JLabel sdeptLabel = new JLabel("\u6240\u5728\u7CFB\uFF1A");
		sdeptLabel.setBounds(90, 460, 72, 33);
		modifyPane.add(sdeptLabel);

		JButton modifyButton = new JButton("\u4FEE\u6539");
		modifyButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u4FEE\u6539.png"));
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tel = stelTextField.getText().toString().trim();
				if (tel.length() != 11) {
					JOptionPane.showConfirmDialog(null, "电话号码位数错误！", "提示", JOptionPane.CLOSED_OPTION);
					return;
				}
				if (!Constant.isNumeric(tel)) {
					JOptionPane.showConfirmDialog(null, "电话号码应为11位数字！", "提示", JOptionPane.CLOSED_OPTION);
					return;
				}
				StudentDAO stu = new StudentDAO();
				ResultSet rSet = stu.stuSelecte(new Student(null, null, tel));
				try {
					if (rSet.next()) {
						JOptionPane.showConfirmDialog(null, "您输入的电话已被使用，请重新输入！", "提示", JOptionPane.CLOSED_OPTION);
						return;
					}
					rSet = stu.stuSelecte(new Student(uName));

					if (rSet.next()) {
						if (stu.stuModify(uName, new Student(rSet.getString(1), rSet.getString(2), rSet.getString(3),
								rSet.getInt(4), rSet.getString(5), tel, rSet.getString(7), rSet.getString(8))) == 1) {
							StudentViewFrm.setMessage();
							JOptionPane.showConfirmDialog(null, "修改成功！", "提示", JOptionPane.CLOSED_OPTION);
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					BaseDAO.close();
				}
			}
		});
		modifyButton.setBounds(66, 550, 113, 27);
		modifyPane.add(modifyButton);

		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\reset.png"));
		resetButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setMessage();
			}

		});
		resetButton.setBounds(302, 550, 86, 27);
		modifyPane.add(resetButton);

		sclassTextField = new JTextField();
		sclassTextField.setEnabled(false);
		sclassTextField.setEditable(false);
		sclassTextField.setColumns(10);
		sclassTextField.setBounds(186, 254, 172, 36);
		modifyPane.add(sclassTextField);

		sageTextField = new JTextField();
		sageTextField.setEnabled(false);
		sageTextField.setEditable(false);
		sageTextField.setColumns(10);
		sageTextField.setBounds(186, 329, 172, 36);
		modifyPane.add(sageTextField);

		sdeptTextField = new JTextField();
		sdeptTextField.setEnabled(false);
		sdeptTextField.setEditable(false);
		sdeptTextField.setColumns(10);
		sdeptTextField.setBounds(186, 464, 172, 36);
		modifyPane.add(sdeptTextField);
		setMessage();
	}

	private void setMessage() {
		StudentDAO stuDao = new StudentDAO();
		ResultSet rSet = stuDao.stuSelecte(new Student(uName));

		try {
			if (rSet.next()) {
				sclassTextField.setText(rSet.getString(1));
				snoTextField.setText(rSet.getString(2));
				snameTextField.setText(rSet.getString(3));
				sageTextField.setText(Integer.toString(rSet.getInt(4)));
				if ("男".equals(rSet.getString(5))) {
					boyRadioButton.setSelected(true);
					girlRadioButton.setSelected(false);
				} else {
					boyRadioButton.setSelected(false);
					girlRadioButton.setSelected(true);
				}
				stelTextField.setText(rSet.getString(6));
				sdeptTextField.setText(rSet.getString(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDAO.close();
		}
	}
}
