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

	// string->account����
	private Map<String, ChatUI> chats = new HashMap<String, ChatUI>();

	private Socket socket; // ���ӵ���ǰsocket
	// private JLabel fimg[]; //����ͷ��

	/**
	 * Launch the application.
	 * 
	 * 
	 * /** Create the frame.
	 * 
	 * 
	 */
	/*
	 * ����º���
	 */

	private void initFriends() {
		List<User> list = user.getFriends();
		for (User fuser : list) {

			JLabel fimg = new JLabel();
			fimg.setIcon(new ImageIcon(MainUi.class.getResource("/img/headImage/" + user.getImg() + ".JPG")));
			fimg.setToolTipText(fuser.getAccount());
			fimg.setText(fuser.getNickname());

			System.out.println("ͷ��" + fuser.getImg());
			System.out.println("�ǳ�" + fuser.getNickname());
			// �ӵ���¼�
			fimg.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent event) {
					super.mouseClicked(event);
					if (event.getClickCount() == 2) {
						// ���˫��

						ChatUI chatUI = chats.get(fuser.getAccount());
						if (chatUI == null) {
							// ��һ����ʾ
							chatUI = new ChatUI(fuser, user, socket);
							chatUI.setLocationRelativeTo(null);
							chatUI.setVisible(true);
							chatUI.setTitle(fuser.getNickname());
							// ���½��Ĵ��ڷ��õ�map
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
		new ServerThead(socket, user, this).start(); // �����̼߳�������
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
	 * ����º���ͷ��
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
						// ��һ����ʾ
						chatUI = new ChatUI(user, user, socket);
						chatUI.setLocationRelativeTo(null);
						chatUI.setVisible(true);

						// ���½��Ĵ��ڷ��õ�map
						chats.put(user.getAccount(), chatUI);
					} else {
						chatUI.setVisible(true); // �ô�����ʾ
					}
				}
			}
		});
		panel.add(fimg);
		this.getContentPane().validate();// ���¸��½���
	}

	/*
	 * �ú���ͷ������
	 */
	public void tip(SendMsg msg) {
		// ��Ϣ��ʾ

		// ��ʾ��Ϣ�����촰��
		ChatUI cj = chats.get(msg.getFrom().getAccount());
		if (cj == null) {
			cj = new ChatUI(msg.getFrom(), user, socket);
			cj.setLocationRelativeTo(null);
			chats.put(msg.getFrom().getAccount(), cj);
		}
		cj.appendMsg(msg);
		if (!cj.isVisible()) {
			//������ڲ��ɼ�������ͷ��
			Component[] imgs = panel.getComponents();
			System.out.println("����" + imgs.length);
			for (Component c : imgs) {
				System.out.println("c is" + c);
				JLabel label = (JLabel) c;
				if (label.getToolTipText().equals(msg.getFrom().getAccount())) {
					// �ҵ���Ӧͷ��
					label.setIcon(new ImageIcon(
							getClass().getClassLoader().getResource("img/headImg/" + 1 + ".jpg")));
					break;
				}

			}
		}

	}
}
