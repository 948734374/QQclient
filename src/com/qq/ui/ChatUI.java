package com.qq.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.qq.SendMsg;
import com.qq.User;
import com.qq.biz.Userbiz;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ChatUI extends JFrame {

	private JPanel contentPane;

	JTextArea textArea_1;
	JTextArea textArea;

	Userbiz userbiz;

	private User f;

	private User u;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * 
	 */
	public void appendMsg(SendMsg msg){
		textArea.append(msg.toString());
	}

	
	public ChatUI(User f, User u, Socket socket) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				setVisible(false);
			}
		});

		this.f = f;
		this.u = u;
		userbiz = new Userbiz(socket);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 494, 689);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(ChatUI.class.getResource("/img/qqshow/qqshow_girl_02_180.jpg")));
		lblNewLabel.setBounds(319, 10, 159, 281);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(ChatUI.class.getResource("/img/qqshow/qqshow_boy_01.jpg")));
		lblNewLabel_1.setBounds(319, 317, 159, 270);
		contentPane.add(lblNewLabel_1);

		JButton button = new JButton("\u53D1\u9001");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// ·¢ËÍÏûÏ¢
				sendMsg();
			}
		});
		button.setBounds(78, 617, 93, 23);
		contentPane.add(button);

		JButton button_1 = new JButton("\u5173\u95ED");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
			}
		});
		button_1.setBounds(303, 617, 93, 23);
		contentPane.add(button_1);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 317, 300, 270);
				contentPane.add(scrollPane);
				
						textArea_1 = new JTextArea();
						scrollPane.setViewportView(textArea_1);
						
						JScrollPane scrollPane_1 = new JScrollPane();
						scrollPane_1.setBounds(10, 10, 299, 290);
						contentPane.add(scrollPane_1);
						
						 textArea = new JTextArea();
						scrollPane_1.setViewportView(textArea);
	}

	private void sendMsg() {
		SendMsg msg = new SendMsg();
		msg.setFrom(u);
		msg.setTo(f);
		msg.setMsg(textArea_1.getText());
		msg.setTime(new Date());
		try {

			userbiz.sengMsg(msg);
			textArea.append(msg.toString());
			textArea_1.setText("");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}
}
