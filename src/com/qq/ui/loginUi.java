package com.qq.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.qq.biz.Sysbiz;
import com.qq.User;
import com.qq.util.PropertiesUtil;

@SuppressWarnings("serial")
public class loginUi extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/*
	 * Launch the application.
	 */
	private Socket socket; // 连接服务器
	private Sysbiz sysbiz;
	private JPasswordField passwordField;



	/**
	 * Create the frame.
	 */
	public loginUi() {

		this.setTitle("QQ");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 407, 300);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLUE);
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("\u8D26\u53F7\uFF1A");

		JLabel label = new JLabel("\u5BC6\u7801\uFF1A");

		JButton btnNewButton = new JButton("\u767B\u9646");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login(e);
			}
		});

		JButton btnNewButton_1 = new JButton("\u6CE8\u518C");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zhuce();
			}
		});

		JButton btnNewButton_2 = new JButton("\u6CE8\u518C\u65B0\u8D26\u53F7");
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				zhuce();
			}

		});

		JButton button = new JButton("\u53D6\u56DE\u5BC6\u7801");

		JCheckBox chckbxNewCheckBox = new JCheckBox("\u8BB0\u4F4F\u5BC6\u7801");

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("\u81EA\u52A8\u767B\u9646");

		JComboBox comboBox = new JComboBox();

		JLabel lblNewLabel_1 = new JLabel("\u72B6\u6001\uFF1A");

		JPanel panel = new JPanel();

		passwordField = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_1)
							.addGap(8)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(chckbxNewCheckBox)
							.addGap(27)
							.addComponent(chckbxNewCheckBox_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(46)
							.addComponent(btnNewButton)
							.addGap(87)
							.addComponent(btnNewButton_1))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(label)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnNewButton_2))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(button)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(8)
							.addComponent(lblNewLabel_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(chckbxNewCheckBox)
								.addComponent(chckbxNewCheckBox_1))))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton)))
		);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(loginUi.class.getResource("/img/QQ\u622A\u56FE20160627002807.png")));
		panel.add(lblNewLabel_2);
		contentPane.setLayout(gl_contentPane);

		try {
			socket = new Socket(PropertiesUtil.readPro("ip"), Integer.parseInt(PropertiesUtil.readPro("port")));
			sysbiz = new Sysbiz(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DialogUtil.showAlarm("服务器打开失败");
		}
	}

	public void login(ActionEvent event) {
		String name = textField.getText().trim();
		String password = new String(passwordField.getPassword()).trim();
		// 使用对象序列化
		// 1.类必须一致（包名+类名）
		// 2.实现序列化接口
		User user = new User();
		user.setAccount(name);
		user.setPassword(password);
		try {

			User user1 = sysbiz.login(user);

			if (user1 == null) {
				DialogUtil.showAlarm("用户名或密码错误");
			} else {
				System.out.println("好友数量" + user1.getFriends().size());
				this.dispose();
				//DialogUtil.showAlarm("登陆成功！");
	            MainUi mainUi=new MainUi(user1,socket);
	            mainUi.setLocationRelativeTo(null);
	            mainUi.setVisible(true);
	            
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			DialogUtil.showAlarm("服务器异常");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			DialogUtil.showAlarm("服务器异常");
			e.printStackTrace();
		}

	}

	private void zhuce() {
		this.setVisible(false);
		RegeditUI regeditUI = new RegeditUI(this,socket);
		regeditUI.setLocationRelativeTo(null);
		regeditUI.setVisible(true);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginUi frame = new loginUi();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
