package com.qq.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.qq.Find;
import com.qq.User;
import com.qq.biz.Sysbiz;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Event;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.awt.event.ActionEvent;

public class FindUi extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	JRadioButton radioButton_1;
	JRadioButton radioButton;

	private Socket socket;
	private User user;

	ButtonGroup bGroup;

	private Sysbiz sysbiz;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FindUi(Socket socket, User user) {
		this.socket = socket;
		this.user = user;
		sysbiz = new Sysbiz(socket);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		radioButton = new JRadioButton("\u7CBE\u786E\u67E5\u627E");
		radioButton.setBackground(new Color(230, 230, 250));
		radioButton.setBounds(31, 22, 121, 23);
		contentPane.add(radioButton);

		textField = new JTextField();
		textField.setBounds(31, 75, 208, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		radioButton_1 = new JRadioButton("\u67E5\u627E\u6240\u6709");
		radioButton_1.setBackground(new Color(230, 230, 250));
		radioButton_1.setBounds(31, 133, 121, 23);
		radioButton_1.setSelected(true);
		contentPane.add(radioButton_1);

		JButton button = new JButton("\u67E5\u627E");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				find(e);

			}
		});
		button.setBackground(new Color(211, 211, 211));
		button.setBounds(31, 208, 93, 23);
		contentPane.add(button);

		JButton button_1 = new JButton("\u5173\u95ED");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close(e);
			}
		});
		button_1.setBackground(new Color(169, 169, 169));
		button_1.setBounds(263, 208, 93, 23);
		contentPane.add(button_1);
		{
			bGroup = new ButtonGroup();
			bGroup.add(radioButton);
			bGroup.add(radioButton_1);
		}
	}

	public void close(ActionEvent event) {
		this.dispose();
	}

	public void find(ActionEvent event) {
		// 精确查找 查找所有
		Find find = new Find();
		if (radioButton.isSelected()) {
			find.setType(Find.ONE);
			find.setFaccount(textField.getText().trim());
		} else {// 所有
			find.setType(Find.All);
		}
		// 把查找信息发送给服务器

		try {
			 sysbiz.find(find);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
