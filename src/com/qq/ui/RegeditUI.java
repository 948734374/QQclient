package com.qq.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.qq.RegeditRS;
import com.qq.User;
import com.qq.biz.Sysbiz;

import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.Socket;
import java.security.interfaces.RSAKey;
import java.awt.event.ItemEvent;
import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegeditUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JComboBox comboBox;
    
	private Sysbiz sysbiz;
	private RegeditRS rs;
	
	private loginUi mainUI;
	

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { RegeditUI frame = new RegeditUI();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 * }); }
	 */

	/**
	 * Create the frame.
	 */
	public RegeditUI(loginUi mainUI,Socket socket) {
		this.mainUI = mainUI;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		textField = new JTextField();
		textField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		sysbiz=new Sysbiz(socket);

		JLabel lblNewLabel = new JLabel("\u6635\u79F0\uFF1A");

		JLabel label = new JLabel("\u5BC6\u7801\uFF1A");

		JLabel lblNewLabel_1 = new JLabel("\u5E74\u9F84\uFF1A");

		JLabel label_1 = new JLabel("\u90AE\u7BB1\uFF1A");

		JButton button = new JButton("\u6CE8\u518C");

		comboBox = new JComboBox();

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				regedit(event);
			}
		});

		JLabel label_2 = new JLabel("\u5934\u50CF\uFF1A");

		passwordField = new JPasswordField();

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(RegeditUI.class.getResource("/img/headImage/1.jpg")));

		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int index = comboBox.getSelectedIndex() + 1;

				lblNewLabel_2.setIcon(
						new ImageIcon(getClass().getClassLoader().getResource("img/headImage/" + index + ".jpg")));
			}
		});

		comboBox.addItem("Æó¶ì");
		comboBox.addItem("ÄÐº¢1");
		comboBox.addItem("Å®º¢1");
		comboBox.addItem("ÄÐº¢2");
		comboBox.addItem("Å®º¢2");

		JButton btnNewButton = new JButton("\u5173\u95ED");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(e);
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
						.addComponent(label).addComponent(lblNewLabel_1).addComponent(label_1))
				.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false).addComponent(passwordField)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
						.addComponent(textField_2).addComponent(textField_3))
				.addGap(18).addComponent(label_2)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED, 12,
								Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_2)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnNewButton, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
										.addComponent(button, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGap(60))
						.addGroup(gl_contentPane.createSequentialGroup().addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap()))));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane
								.createSequentialGroup().addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane
												.createSequentialGroup().addGap(37).addGroup(gl_contentPane
														.createParallelGroup(Alignment.BASELINE)
														.addComponent(textField, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblNewLabel).addComponent(label_2)
														.addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(18)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(label).addComponent(passwordField,
																GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
												.addGap(30)
												.addGroup(gl_contentPane
														.createParallelGroup(Alignment.BASELINE)
														.addComponent(textField_2, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblNewLabel_1))
												.addGap(18)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
																.addComponent(textField_3, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(button))
														.addComponent(label_1)))
										.addGroup(gl_contentPane.createSequentialGroup().addGap(77)
												.addComponent(lblNewLabel_2)))
								.addGap(18)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(21, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);

	}

	private void close(ActionEvent event) {
		this.dispose(); // Ïú»Ù
		mainUI.setVisible(true); // µÇÂ¼½çÃæÏÔÊ¾
	}

	private void regedit(ActionEvent event) {
		String nickname = textField.getText().trim();
		String password = new String(passwordField.getPassword()).trim();
		String age = textField_2.getText().trim();
		String email = textField_3.getText().trim();
		String img = String.valueOf(comboBox.getSelectedIndex()+1);
		User user = new User();
		user.setNickname(nickname);
		user.setAge(Integer.parseInt(age));
		user.setEmail(email);
		user.setPassword(password);
		user.setImg(img);
		try {
			 rs=sysbiz.regedit(user);
			if (rs.isRs()){
			//×¢²á³É¹¦	
				this.dispose();
				DialogUtil.showinfo(rs.getMsString());
			    mainUI.setVisible(true);
			}else {
		    //×¢²áÊ§°Ü
				DialogUtil.showAlarm(rs.getMsString());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
