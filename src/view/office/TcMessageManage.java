package view.office;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import base.BaseDAO;
import dao.TC_DAO;
import model.Teacher;
import util.Constant;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class TcMessageManage extends JInternalFrame {
	private JTable tcTable;
	private JTextField tnoField;
	private JTextField tnameField;
	private JTextField cnoField;
	private JTextField cnameField;
	private JButton deleteButton;
	
	private static int count = 0;
	private static String tno;
	private static String tname;
	private static int cno;
	private static String cname;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TcMessageManage frame = new TcMessageManage();
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
	public TcMessageManage() {
		setClosable(true);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\\u6559\u5E08\u6388\u8BFE.png"));
		setTitle("\u6559\u5E08\u6388\u8BFE\u7BA1\u7406");
		setBounds(100, 100, 848, 627);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 13, 810, 375);
		getContentPane().add(scrollPane);

		
	       tcTable = new JTable();
	       tcTable.addMouseListener(new MouseAdapter() {
	       	@Override
	       	public void mousePressed(MouseEvent arg0) {//获取选中行信息
	       		int row = tcTable.getSelectedRow();
	       		if (row < count) {
	       			tnoField.setText((String) tcTable.getValueAt(row, 0));
	       			tnameField.setText((String) tcTable.getValueAt(row, 1));
					cnoField.setText(String.valueOf((int)tcTable.getValueAt(row, 2)));
					cnameField.setText((String) tcTable.getValueAt(row, 3));
				}
	       		
	       	}
	       });
			scrollPane.setColumnHeaderView(tcTable);
			

	        tcTable.setModel(new DefaultTableModel(
	        	new Object[][] {
	        	},
	        	new String[] {
	        			"教师号","教师姓名","课程号","课程名"
	        	}
	        ));
	        scrollPane.setViewportView(tcTable);
	        
	        JLabel tnoLabel = new JLabel("\u6559\u5E08\u53F7\uFF1A");
	        tnoLabel.setBounds(112, 423, 72, 18);
	        getContentPane().add(tnoLabel);
	        
	        tnoField = new JTextField();
	        tnoField.setBounds(198, 420, 132, 24);
	        getContentPane().add(tnoField);
	        tnoField.setColumns(10);
	        
	        JLabel tnameLabel = new JLabel("\u6559\u5E08\u59D3\u540D\uFF1A");
	        tnameLabel.setBounds(423, 423, 82, 18);
	        getContentPane().add(tnameLabel);
	        
	        tnameField = new JTextField();
	        tnameField.setBounds(535, 420, 141, 24);
	        getContentPane().add(tnameField);
	        tnameField.setColumns(10);
	        
	        JLabel cnoLabel = new JLabel("\u8BFE\u7A0B\u53F7\uFF1A");
	        cnoLabel.setBounds(112, 477, 72, 18);
	        getContentPane().add(cnoLabel);
	        
	        cnoField = new JTextField();
	        cnoField.setBounds(196, 474, 134, 24);
	        getContentPane().add(cnoField);
	        cnoField.setColumns(10);
	        
	        JLabel cnameLabel = new JLabel("\u8BFE\u7A0B\u540D\uFF1A");
	        cnameLabel.setBounds(433, 477, 72, 18);
	        getContentPane().add(cnameLabel);
	        
	        cnameField = new JTextField();
	        cnameField.setBounds(535, 474, 141, 24);
	        getContentPane().add(cnameField);
	        cnameField.setColumns(10);
	        
	        JButton addButton = new JButton("\u6DFB\u52A0");
	        addButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\add.png"));
	        addButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		messageGet();
	        		if (!Constant.isEmpty(tno)) {
						JOptionPane.showConfirmDialog(null, "教师号不能为空！","提示",JOptionPane.CLOSED_OPTION);
						return;
					}
	        		if (cno <= 0) {
	        			JOptionPane.showConfirmDialog(null, "课程号不能为空！","提示",JOptionPane.CLOSED_OPTION);
	        			return;
					}
	        		
	        		TC_DAO tDao = new TC_DAO();
	        		if (tDao.TCInsert(new Teacher(tno,cno,null)) == 1) {
	        			fillTable();
	        			JOptionPane.showConfirmDialog(null, "添加成功！","恭喜",JOptionPane.CLOSED_OPTION);
					}else {
						JOptionPane.showConfirmDialog(null, "添加失败，请检查教师号和课程号是否正确!","提示",JOptionPane.CLOSED_OPTION);
					}
	        	}
	        });
	        addButton.setBounds(146, 539, 82, 27);
	        getContentPane().add(addButton);
	        
	        deleteButton = new JButton("\u5220\u9664");
	        deleteButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\delete.png"));
	        deleteButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		messageGet();
	        		if (!Constant.isEmpty(tno) && cno <= 0) {
						JOptionPane.showConfirmDialog(null, "教师号和课程号不能都为为空！","提示",JOptionPane.CLOSED_OPTION);
						return;
					}
	        		TC_DAO tDao = new TC_DAO();
	        		if (tDao.TCDelete(new Teacher(tno,cno,null)) != 0) {
	        			RSet();
	        			fillTable();
	        			JOptionPane.showConfirmDialog(null, "删除成功！","提示",JOptionPane.CLOSED_OPTION);
					}else {
						JOptionPane.showConfirmDialog(null, "删除失败，请检查教师号和课程号是否正确!","提示",JOptionPane.CLOSED_OPTION);
					}
	        	}
	        });
	        deleteButton.setBounds(287, 539, 82, 27);
	        getContentPane().add(deleteButton);
	        
	        JButton queryButton = new JButton("\u67E5\u8BE2");
	        queryButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\search.png"));
	        queryButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		fillTable();
	        	}
	        });
	        queryButton.setBounds(436, 539, 82, 27);
	        getContentPane().add(queryButton);
	        
	        JButton resetButton = new JButton("\u91CD\u7F6E");
	        resetButton.setIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\reset.png"));
	        resetButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		RSet();
	        	}
	        });
	        resetButton.setBounds(578, 539, 82, 27);
	        getContentPane().add(resetButton);
	        fillTable();
	}
	
	private void RSet(){
		tnoField.setText(null);
		tnameField.setText(null);
		cnoField.setText(null);
		cnameField.setText(null);
		fillTable();
	}
	
	private void messageGet(){
		tno = tnoField.getText().toString().trim();
		tname = tnameField.getText().toString().trim();
		if(cnoField.getText().toString().trim().equals("")){
			cno = 0;
		}else {
			cno = Integer.parseInt(cnoField.getText().toString().trim());
		}
		cname = cnameField.getText().toString().trim();
	}
	
	private void fillTable() {
		messageGet();
		DefaultTableModel dModel = (DefaultTableModel) tcTable.getModel();
		dModel.setRowCount(0);
		TC_DAO tDao = new TC_DAO();
		ResultSet rSet = tDao.TCSelecte(new Teacher(tno,tname,cno,cname));
		try {
			count = 0;
			while (rSet.next()) {
				Vector vector = new Vector();
				vector.add(rSet.getString(3));
				vector.add(rSet.getString(4));
				vector.add(rSet.getInt(1));
				vector.add(rSet.getString(2));
				dModel.addRow(vector);
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDAO.close();
		}
	}
}
