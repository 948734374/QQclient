package com.qq.ui;

import java.security.interfaces.RSAKey;

import javax.swing.JOptionPane;

public class DialogUtil {
	public static void showAlarm(String str) {
		JOptionPane.showMessageDialog(null, str, "ϵͳ����", JOptionPane.WARNING_MESSAGE);
	}

	public static void showinfo(String string) {
		JOptionPane.showMessageDialog(null, string, "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean showconfirm(String string) {
		int i = JOptionPane.showConfirmDialog(null, string, "ϵͳ��Ϣ", JOptionPane.YES_NO_OPTION);

		if (i == JOptionPane.OK_OPTION) {
			return true;
		} else {
			return false;
		}
	}

}
