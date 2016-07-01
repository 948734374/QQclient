package com.qq.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.qq.AddfriendsMsg;
import com.qq.User;
import com.qq.biz.Userbiz;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.handler.MessageContext;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;

public class FindRsUi extends JFrame {

	private JPanel contentPane;
	private JTable table_1;

	private List<User> list;
	private User user; // 当前登录用户
	private Userbiz userbiz;
  private Socket socket;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FindRsUi(List<User> list, User user,Socket socket) {
		this.list = list;
		this.user = user;
		this.socket=socket;
		this.userbiz=new Userbiz(socket);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 10, 419, 186);
		contentPane.add(scrollPane);

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(getForTable(), new String[] { "账号", "昵称" }));
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		;
		scrollPane.setViewportView(table_1);

		JButton button = new JButton("\u67E5\u770B\u4FE1\u606F");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				showInfo(event);
			}
		});

		button.setBounds(48, 215, 93, 23);
		contentPane.add(button);

		JButton btnNewButton = new JButton("\u6DFB\u52A0\u597D\u53CB");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
             addFriends(event);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(287, 215, 93, 23);
		contentPane.add(btnNewButton);
	}

	/*
	 * 把list中的USer信息转换成string【】【】格式
	 */
	private String[][] getForTable() {
		String strs[][] = new String[list.size()][2];
		for (int x = 0; x < list.size(); x++) {
			strs[x][0] = list.get(x).getAccount();
			strs[x][1] = list.get(x).getNickname();
		}
		return strs;
	}

	
	public void showInfo(MouseEvent event) {
		int index = table_1.getSelectedRow();

		if (index != -1) {
			// 选中
			InfoUi infoUi = new InfoUi(list.get(index));
			infoUi.setLocationRelativeTo(null);
			infoUi.setVisible(true);
		} else {
			// 没有选中
			DialogUtil.showAlarm("请选择一个用户！");
		}
	}

	private void addFriends(MouseEvent event) {
		int index = table_1.getSelectedRow();
		if (index != -1) {
			// 选中
			User fUser = list.get(index);
			AddfriendsMsg msg = new AddfriendsMsg();
			msg.setTo(fUser);
			msg.setFrom(user);
			try {
				userbiz.addFriends(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// 没有选中
			DialogUtil.showAlarm("请选择一个用户！");
		}
	}
}
