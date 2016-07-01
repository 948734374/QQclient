package com.qq.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.qq.User;

public class InfoUi extends JFrame {

	private JPanel contentPane;
	JLabel lblNewLabel;
	JLabel lblNewLabel_1;
	JLabel lblNewLabel_2;
	JLabel lblNewLabel_3;
	JLabel lblNewLabel_4;
	JLabel lblNewLabel_5;
	JLabel label;
	JLabel label_1;
	JLabel label_2;
	JLabel label_3;

	private User user;

	/**
	 * Launch the application.
	 * 
	 * 
	 * /** Create the frame.
	 */
	public InfoUi(User user) {
		super();
		this.user=user;
		initGUiI();	}
	private void initGUiI(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("\u8D26\u53F7\uFF1A");
		lblNewLabel.setBounds(10, 25, 69, 21);
		contentPane.add(lblNewLabel);

		label = new JLabel("\u6635\u79F0\uFF1A");
		label.setBounds(10, 82, 54, 15);
		contentPane.add(label);

		label_1 = new JLabel("\u5E74\u9F84\uFF1A");
		label_1.setBounds(10, 136, 54, 15);
		contentPane.add(label_1);

		label_2 = new JLabel("\u90AE\u7BB1\uFF1A");
		label_2.setBounds(10, 191, 54, 15);
		contentPane.add(label_2);

		label_3 = new JLabel("\u5934\u50CF\uFF1A");
		label_3.setBounds(274, 28, 54, 15);
		contentPane.add(label_3);

		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(54, 28, 54, 15);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(54, 82, 54, 15);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(54, 136, 54, 15);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(54, 191, 54, 15);
		contentPane.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon(InfoUi.class.getResource("/img/headImage/1.jpg")));
		lblNewLabel_5.setBounds(338, 28, 62, 69);
		contentPane.add(lblNewLabel_5);

		JButton button = new JButton("\u5173\u95ED");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				close(event);
			}
		});
		button.setBounds(303, 197, 93, 23);
		contentPane.add(button);
		initInfo();

	}
	/*
	 * 初始化所有用户信息
	 * 
	 */

	private void initInfo() {

		this.user = user;
		lblNewLabel_1.setText(user.getAccount());

		lblNewLabel_2.setText(user.getNickname());

		lblNewLabel_3.setText(String.valueOf(user.getAge()));

		lblNewLabel_4.setText(user.getEmail());

		lblNewLabel_5.setIcon(new ImageIcon(InfoUi.class.getResource("/img/headImage/" + user.getImg() + ".jpg")));

	}

	private void close(ActionEvent event) {
		this.dispose();
	}

}
