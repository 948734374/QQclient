package com.qq.ui;

import java.security.interfaces.RSAKey;

import javax.swing.JOptionPane;

public class DialogUtil {
	public static void showAlarm(String str) {
		JOptionPane.showMessageDialog(null, str, "系统警告", JOptionPane.WARNING_MESSAGE);
	}

	public static void showinfo(String string) {
		JOptionPane.showMessageDialog(null, string, "系统消息", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean showconfirm(String string) {
		int i = JOptionPane.showConfirmDialog(null, string, "系统消息", JOptionPane.YES_NO_OPTION);

		if (i == JOptionPane.OK_OPTION) {
			return true;
		} else {
			return false;
		}
	}

}
