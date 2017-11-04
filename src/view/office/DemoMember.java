package view.office;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class DemoMember extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemoMember frame = new DemoMember();
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
	public DemoMember() {
		setFrameIcon(new ImageIcon("F:\\JavaProjects\\MFC_ODBC\\images\\about.png"));
		setIconifiable(true);
		setClosable(true);
		setTitle("\u6210\u5458\u540D\u5355");
		setBounds(100, 100, 382, 466);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7EC4\u957F\uFF1A");
		lblNewLabel.setBounds(113, 70, 72, 18);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7EC4\u5458\uFF1A");
		lblNewLabel_1.setBounds(113, 141, 72, 18);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u7EC4\u5458\uFF1A");
		lblNewLabel_2.setBounds(113, 216, 72, 18);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u7EC4\u5458\uFF1A");
		lblNewLabel_3.setBounds(113, 289, 72, 18);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u6307\u5BFC\u8001\u5E08\uFF1A");
		lblNewLabel_4.setBounds(98, 350, 87, 18);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\u6797\u9053\u96C4");
		lblNewLabel_5.setBounds(199, 70, 72, 18);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\u96F7\u56FD\u680B");
		lblNewLabel_6.setBounds(199, 141, 72, 18);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("\u9EC4\u5411\u9633");
		lblNewLabel_7.setBounds(199, 216, 72, 18);
		getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("\u4E07\u5955\u8FDC");
		lblNewLabel_8.setBounds(199, 289, 72, 18);
		getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("\u738B\u4E3D\u82B3");
		lblNewLabel_9.setBounds(199, 350, 72, 18);
		getContentPane().add(lblNewLabel_9);

	}

}
