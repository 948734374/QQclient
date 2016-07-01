package com.qq.biz;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import com.qq.AddRSMsg;
import com.qq.AddfriendsMsg;
import com.qq.Find;
import com.qq.RegeditRS;
import com.qq.User;
import com.qq.ui.DialogUtil;
import com.qq.ui.FindRsUi;
import com.qq.ui.MainUi;
import com.qq.util.ObjectUtil;

public class Sysbiz {
	private Socket socket;

	public Sysbiz(Socket socket) {
		this.socket = socket;
	}

	/*
	 * 验证用户名是否正确
	 * 
	 * 返回null代表失败,如果返回user(所有信息)
	 */
	public User login(User user) throws IOException, ClassNotFoundException {
		// 把user写进去——》服务端
		ObjectUtil.writeObject(socket, user);

		// 读取服务端验证界面
		return (User) ObjectUtil.readObject(socket);

	}

	// 成功返回账号，错误提示
	public RegeditRS regedit(User user) throws IOException, ClassNotFoundException {
		// 把user写进去——》服务端
		ObjectUtil.writeObject(socket, user);

		// 读取服务端验证界面
		return (RegeditRS) ObjectUtil.readObject(socket);

	}

	public void find(Find find) throws IOException, ClassNotFoundException {
		ObjectUtil.writeObject(socket, find);
	}

	public void showFindRS(List<User> list, User user) {
		if (list.size() == 0) {
			// 没有查询出来结果
			DialogUtil.showAlarm("没有查询到任何结果！");
		} else {
			FindRsUi rsUi = new FindRsUi(list, user, socket);
			rsUi.setLocationRelativeTo(null);
			rsUi.setVisible(true);
		}

	}

	public void showAddMsg(AddfriendsMsg msg, MainUi mainUi) throws IOException {

		// 弹出确认对话框
		boolean b = DialogUtil.showconfirm(msg.getFrom().getNickname() + "请求添加你为好友，是否同意？");

		if (b) {
			// 同意---->添加头像到主窗口
			System.out.println("同意");
			mainUi.AddFriends(msg.getFrom());
			System.out.println("传参");
		} else {
			System.out.println("不同意");
		}
		AddRSMsg rsMsg = new AddRSMsg();
		rsMsg.setForm(msg.getTo());
		rsMsg.setTo(msg.getFrom());
		rsMsg.setAgree(b);

		ObjectUtil.writeObject(socket, rsMsg);

	}

	public void showAddRS(AddRSMsg msg,MainUi mUi){
		if (msg.isAgree()){
			//同意
			DialogUtil.showAlarm(msg.getForm().getNickname()+"同意了你的好友请求！");
			mUi.AddFriends(msg.getForm());
		}else{
			//没同意
			DialogUtil.showAlarm(msg.getForm().getNickname()+"拒绝了你的好友请求！");
		}
	}
}
