package org.btsoft.jf.core.utils;

import java.io.File;
import java.text.MessageFormat;

import org.btsoft.jf.core.application.IApplication;
import org.btsoft.jf.core.content.JF;

/**
 * @ClassName PathUtil
 * @Description 路径工具类
 * @author bchen
 * @created 2013-12-4 19:09:48
 */
public class PathUtils {

	private static final String ROOT_PATH="app";
	private static final String DEFAULT_APP_PATH=File.separator+ROOT_PATH+File.separator+"{0}";//系统目录
	private static final String DEFAULT_TMP_PATH=File.separator+ROOT_PATH+File.separator+"{0}"+File.separator+"temp"+File.separator+"{1}";//系统临时目录
	private static final String DEFAULT_APP_TYPE_PATH=File.separator+ROOT_PATH+File.separator+"{0}"+File.separator+"{1}";//系统类别目录
	
	public static String getAppName(){
		IApplication app=JF.getContext().getBean("application",IApplication.class);
		return app.getAppName();
	}
	
	public static String getAppPath(){
		String path=MessageFormat.format(DEFAULT_APP_PATH,new Object[]{getAppName()});
		return makeDirs(path);
	}
	
	public static String getAppTypePath(String type){
		String path=MessageFormat.format(DEFAULT_APP_TYPE_PATH,new Object[]{getAppName(),type});
		return makeDirs(path);
	}
	
	public static String getTempPath(String type){
		String path=MessageFormat.format(DEFAULT_TMP_PATH,new Object[]{getAppName(),type});
		return makeDirs(path);
	}
	
	public static String makeDirs(String path){
		File file=new File(path);
		makeDirs(file);
		return path;
	}
	
	public static void makeDirs(File file){
		if(!file.exists()){
			file.mkdirs();
		}
	}
}
