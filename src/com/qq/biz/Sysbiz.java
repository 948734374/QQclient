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
	 * ��֤�û����Ƿ���ȷ
	 * 
	 * ����null����ʧ��,�������user(������Ϣ)
	 */
	public User login(User user) throws IOException, ClassNotFoundException {
		// ��userд��ȥ�����������
		ObjectUtil.writeObject(socket, user);

		// ��ȡ�������֤����
		return (User) ObjectUtil.readObject(socket);

	}

	// �ɹ������˺ţ�������ʾ
	public RegeditRS regedit(User user) throws IOException, ClassNotFoundException {
		// ��userд��ȥ�����������
		ObjectUtil.writeObject(socket, user);

		// ��ȡ�������֤����
		return (RegeditRS) ObjectUtil.readObject(socket);

	}

	public void find(Find find) throws IOException, ClassNotFoundException {
		ObjectUtil.writeObject(socket, find);
	}

	public void showFindRS(List<User> list, User user) {
		if (list.size() == 0) {
			// û�в�ѯ�������
			DialogUtil.showAlarm("û�в�ѯ���κν����");
		} else {
			FindRsUi rsUi = new FindRsUi(list, user, socket);
			rsUi.setLocationRelativeTo(null);
			rsUi.setVisible(true);
		}

	}

	public void showAddMsg(AddfriendsMsg msg, MainUi mainUi) throws IOException {

		// ����ȷ�϶Ի���
		boolean b = DialogUtil.showconfirm(msg.getFrom().getNickname() + "���������Ϊ���ѣ��Ƿ�ͬ�⣿");

		if (b) {
			// ͬ��---->���ͷ��������
			System.out.println("ͬ��");
			mainUi.AddFriends(msg.getFrom());
			System.out.println("����");
		} else {
			System.out.println("��ͬ��");
		}
		AddRSMsg rsMsg = new AddRSMsg();
		rsMsg.setForm(msg.getTo());
		rsMsg.setTo(msg.getFrom());
		rsMsg.setAgree(b);

		ObjectUtil.writeObject(socket, rsMsg);

	}

	public void showAddRS(AddRSMsg msg,MainUi mUi){
		if (msg.isAgree()){
			//ͬ��
			DialogUtil.showAlarm(msg.getForm().getNickname()+"ͬ������ĺ�������");
			mUi.AddFriends(msg.getForm());
		}else{
			//ûͬ��
			DialogUtil.showAlarm(msg.getForm().getNickname()+"�ܾ�����ĺ�������");
		}
	}
}
