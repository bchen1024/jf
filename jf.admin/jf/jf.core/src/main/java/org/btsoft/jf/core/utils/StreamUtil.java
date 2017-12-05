package org.btsoft.jf.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @ClassName StreamUtil
 * @Description 流工具类
 * @author bchen
 * @created 2017年11月12日 下午7:32:07
 */
public class StreamUtil {

	public static void close(InputStream is) {
		try {
			if (is != null) {
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void close(OutputStream out) {
		try {
			if (out != null) {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void close(InputStream is, OutputStream out) {
		try {
			if (out != null) {
				out.close();
			}
			if (is != null) {
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
