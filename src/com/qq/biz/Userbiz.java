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
 * 发送消息给服务器
 * 
 */
public void sengMsg(SendMsg sendMsg) throws IOException{
	ObjectUtil.writeObject(socket, sendMsg);
}
/*
 * 显示好友信息
 */
public void showMsg(SendMsg msg,MainUi mainUi ){
	mainUi.tip(msg);   //显示消息
}
}
