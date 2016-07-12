package com.qq.biz;

import java.io.IOException;
import java.net.Socket;

import com.qq.AddfriendsMsg;
import com.qq.SendMsg;
import com.qq.ui.MainUi;
import com.qq.util.ObjectUtil;



public class Userbiz {
private Socket socket;	
	public Userbiz(Socket socket){
		this.socket=socket;
	}
public void addFriends(AddfriendsMsg msg)throws IOException{
	ObjectUtil.writeObject(socket, msg);
}
/*
 * ������Ϣ��������
 * 
 */
public void sengMsg(SendMsg sendMsg) throws IOException{
	ObjectUtil.writeObject(socket, sendMsg);
}
/*
 * ��ʾ������Ϣ
 */
public void showMsg(SendMsg msg,MainUi mainUi ){
	mainUi.tip(msg);   //��ʾ��Ϣ
}
}
