package org.btsoft.jf.core.utils;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @ClassName DESEncrypt
 * @Description 字符串加密解密工具类
 * @author bchen
 * @created 2014-6-10 下午11:17:07
 */
public class DESEncrypt {

	/**
	 * @Description 加密字符串
	 * @param s
	 * @return
	 * @author bchen
	 * @created 2014-6-10 下午11:18:12
	 */
	public static String encrypt(String s) {
		StringBuffer sb = new StringBuffer(1024);
		byte[] bt = encrypt(s.getBytes(), Cipher.ENCRYPT_MODE);
		Random rd = new Random(s.length());
		char ch;
		for (int i = 0; i < bt.length; i++) {
			ch = (char) (rd.nextInt(10) + 'G');
			sb.append(ch);
			if (bt[i] < 0) {
				ch = (char) (rd.nextInt(10) + 'Q');
				bt[i] = (byte) (-bt[i]);
				sb.append(ch);
			}
			sb.append(Integer.toString(bt[i], 16).toUpperCase());
		}
		sb.deleteCharAt(0);
		return sb.toString();
	}

	/**
	 * @Description 解密字符串
	 * @param s
	 * @return
	 * @author bchen
	 * @created 2014-6-10 下午11:18:32
	 */
	public static String dncrypt(String s) {
		if (s == null || s.length() < 1)
			return null;
		char ch;
		String[] strs = s.split("[G-Pg-p]");
		byte[] bt = new byte[strs.length];
		for (int i = 0; i < bt.length; i++) {
			ch = strs[i].charAt(0);
			if ((ch >= 'Q' && ch <= 'Z') || (ch >= 'q' && ch <= 'z'))
				bt[i] = (byte) -Byte.parseByte(strs[i].substring(1), 16);
			else
				bt[i] = Byte.parseByte(strs[i], 16);
		}
		byte[] bytes = encrypt(bt, Cipher.DECRYPT_MODE);
		if (bytes == null || bytes.length < 1)
			return null;
		return new String(bytes);
	}

	/**
	 * @Description 字节加密解密
	 * @param s
	 * @param mode
	 * @return
	 * @author bchen
	 * @created 2014-6-10 下午11:18:58
	 */
	private static byte[] encrypt(byte[] s, int mode) {
		DESKeySpec des;
		SecretKeyFactory keyFactory;
		byte[] ch = null;
		byte[] salt = { (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c, (byte) 0x7e, (byte) 0xc8, (byte) 0xee,
				(byte) 0x99 };
		try {
			keyFactory = SecretKeyFactory.getInstance("DES");
			des = new DESKeySpec(salt);
			SecretKey key = keyFactory.generateSecret(des);
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(mode, key);
			ch = cipher.doFinal(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ch;
	}
}
