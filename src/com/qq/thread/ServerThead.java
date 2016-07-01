package com.qq.thread;

import java.awt.Dialog;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import com.qq.AddRSMsg;
import com.qq.AddfriendsMsg;
import com.qq.SendMsg;
import com.qq.User;
import com.qq.biz.Sysbiz;
import com.qq.biz.Userbiz;
import com.qq.ui.DialogUtil;
import com.qq.ui.FindRsUi;
import com.qq.ui.MainUi;
import com.qq.util.ObjectUtil;

public class ServerThead extends Thread {
	private Socket socket;
	private User user;
	private Sysbiz sysbiz;
	private MainUi mainUi;
	private Userbiz userbiz;

	public ServerThead(Socket socket, User user, MainUi mainUi) {
		this.socket = socket;
		this.user = user;
		this.mainUi = mainUi;
		sysbiz = new Sysbiz(socket);
		userbiz = new Userbiz(socket);
	}

	public void run() {
		while (true) {
			Object object;
			try {

				object = ObjectUtil.readObject(socket);
				System.out.println("服务器发送的消息" + object);
				if (object instanceof List) {
					// 查询结果------》List<User> 代码重构
					List<User> list = (List<User>) object;
					sysbiz.showFindRS(list, user);

				} else if (object instanceof AddfriendsMsg) {
					AddfriendsMsg msg = (AddfriendsMsg) object;
					System.out.println("dianyong");
					sysbiz.showAddMsg(msg, mainUi);
					System.out.println("diao");
				} else if (object instanceof AddRSMsg) {
					AddRSMsg rsMsg = (AddRSMsg) object;
					sysbiz.showAddRS(rsMsg, mainUi);
				} else if (object instanceof SendMsg) {
					SendMsg msg = (SendMsg) object;
					userbiz.showMsg(msg, mainUi);
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
}
