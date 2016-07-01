package com.qq.ui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.MultiDocPrintJob;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import com.qq.SendMsg;
import com.qq.User;
import com.qq.thread.ServerThead;

public class MainUi extends JFrame {

	private JPanel contentPane;

	private User user;
	private JScrollPane scrollPane;
	private JPanel panel;
	private ChatUI chatUI;

	// string->account窗口
	private Map<String, ChatUI> chats = new HashMap<String, ChatUI>();

	private Socket socket; // 连接到当前socket
	// private JLabel fimg[]; //好友头像

	/**
	 * Launch the application.
	 * 
	 * 
	 * /** Create the frame.
	 * 
	 * 
	 */
	/*
	 * 添加新好友
	 */

	private void initFriends() {
		List<User> list = user.getFriends();
		for (User fuser : list) {

			JLabel fimg = new JLabel();
			fimg.setIcon(new ImageIcon(MainUi.class.getResource("/img/headImage/" + user.getImg() + ".JPG")));
			fimg.setToolTipText(fuser.getAccount());
			fimg.setText(fuser.getNickname());

			System.out.println("头像" + fuser.getImg());
			System.out.println("昵称" + fuser.getNickname());
			// 加点击事件
			fimg.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent event) {
					super.mouseClicked(event);
					if (event.getClickCount() == 2) {
						// 鼠标双击

						ChatUI chatUI = chats.get(fuser.getAccount());
						if (chatUI == null) {
							// 第一次显示
							chatUI = new ChatUI(fuser, user, socket);
							chatUI.setLocationRelativeTo(null);
							chatUI.setVisible(true);
							chatUI.setTitle(fuser.getNickname());
							// 把新建的窗口放置到map
							chats.put(fuser.getAccount(), chatUI);
						}
					}
				}
			});
			panel.add(fimg);
		}
	}

	public MainUi(User user, Socket socket) {
		this.socket = socket;
		this.user = user;
		new ServerThead(socket, user, this).start(); // 启动线程监听服务
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 286, 654);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 278, 75);
		lblNewLabel.setIcon(new ImageIcon(MainUi.class.getResource("/img/QQ\u622A\u56FE20160629200621.png")));
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				find(event);
			}
		});
		lblNewLabel_1.setBounds(0, 560, 278, 55);
		lblNewLabel_1.setIcon(new ImageIcon(MainUi.class.getResource("/img/QQ\u622A\u56FE20160629200847.png")));
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblNewLabel);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 73, 278, 486);
		contentPane.add(scrollPane);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(50, 1, 5, 5));
		setTitle(user.getAccount());
		initFriends();
		// System.out.println(user.getImg());

	}

	private void find(MouseEvent event) {
		FindUi findfriends = new FindUi(socket, user);
		findfriends.setLocationRelativeTo(null);
		findfriends.setVisible(true);
		;
	}

	/*
	 * 添加新好友头像
	 */
	public void AddFriends(User fUser) {
		// TODO Auto-generated method stub
		JLabel fimg = new JLabel();
		fimg.setIcon(
				new ImageIcon(getClass().getClassLoader().getResource("/img/headImage/" + fUser.getImg() + ".JPG")));
		fimg.setToolTipText(fUser.getAccount());
		fimg.setText(fUser.getNickname());
		fimg.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				if (event.getClickCount() == 2) {
					//
					fimg.setIcon(new ImageIcon(
							getClass().getClassLoader().getResource("/img/headImage/" + fUser.getImg() + ".JPG")));

					ChatUI chatUI = chats.get(fUser.getAccount());
					if (chatUI == null) {
						// 第一次显示
						chatUI = new ChatUI(user, user, socket);
						chatUI.setLocationRelativeTo(null);
						chatUI.setVisible(true);

						// 把新建的窗口放置到map
						chats.put(user.getAccount(), chatUI);
					} else {
						chatUI.setVisible(true); // 让窗口显示
					}
				}
			}
		});
		panel.add(fimg);
		this.getContentPane().validate();// 重新更新界面
	}

	/*
	 * 让好友头像跳动
	 */
	public void tip(SendMsg msg) {
		// 消息显示

		// 显示消息到聊天窗口
		ChatUI cj = chats.get(msg.getFrom().getAccount());
		if (cj == null) {
			cj = new ChatUI(msg.getFrom(), user, socket);
			cj.setLocationRelativeTo(null);
			chats.put(msg.getFrom().getAccount(), cj);
		}
		cj.appendMsg(msg);
		if (!cj.isVisible()) {
			//如果窗口不可见，跳动头像
			Component[] imgs = panel.getComponents();
			System.out.println("数量" + imgs.length);
			for (Component c : imgs) {
				System.out.println("c is" + c);
				JLabel label = (JLabel) c;
				if (label.getToolTipText().equals(msg.getFrom().getAccount())) {
					// 找到对应头像
					label.setIcon(new ImageIcon(
							getClass().getClassLoader().getResource("img/headImg/" + 1 + ".jpg")));
					break;
				}

			}
		}

	}
}
