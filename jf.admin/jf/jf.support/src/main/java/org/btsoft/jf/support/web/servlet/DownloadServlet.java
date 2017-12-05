/**
 * @FileName DownloadServlet.java
 * @Package com.jf.web.support.download.servlet
 * @Description 下载支持类
 * @author bchen   
 * @version 1.0  
 * @created 2014-11-13 下午10:52:55 
 */
package org.btsoft.jf.support.web.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.btsoft.jf.core.content.JF;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.core.exception.JFSecurityException;
import org.btsoft.jf.core.file.FileInfo;
import org.btsoft.jf.core.file.IDownloadSupport;
import org.btsoft.jf.core.utils.StreamUtil;
import org.btsoft.jf.core.utils.StringUtils;
import org.btsoft.jf.support.web.util.RequestUtil;
import org.btsoft.jf.support.web.util.ResponseUtil;
import org.btsoft.jf.support.web.util.WebSupport;

/**
 * @ClassName DownloadServlet
 * @Description 下载支持类
 * @author bchen
 * @created 2014-11-13 下午10:52:55
 */
@Named("/servlet/download")
public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = -4391033795032408125L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置编码
		req.setCharacterEncoding(WebSupport.UTF_8);
		resp.setCharacterEncoding(WebSupport.UTF_8);

		// 下载处理类为空
		String dlType = req.getParameter("dlType");
		if (StringUtils.isEmpty(dlType)) {
			ResponseUtil.printException(resp, "attachment.exception.dlTypeIsNull");
			return;
		}

		// 附件id为空
		String attachmentId = req.getParameter("attachmentId");
		if (StringUtils.isEmpty(attachmentId)) {
			ResponseUtil.printException(resp, "attachment.exception.attachmentIdIsNull");
			return;
		}

		// dlType处理类未定义
		IDownloadSupport download = null;
		String downloadBean = "IDownloadSupport." + dlType;
		try {
			download = JF.getContext().getBean(downloadBean, IDownloadSupport.class);
		} catch (Exception e) {
			ApplicationException ex = new ApplicationException("attachment.exception.dlTypeNotFound",
					new Object[] { downloadBean });
			ResponseUtil.printException(resp, ex);
			return;
		}

		// 执行下载
		processDownload(download, req, resp);

	}

	private void processDownload(IDownloadSupport download, HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			Map<String, String> params = RequestUtil.getRequestParams(req);

			// 无权限下载
			if (!download.checkSecurity(params)) {
				JFSecurityException e = new JFSecurityException();
				ResponseUtil.printException(resp, e);
				return;
			}

			if (download.checkSecurity(params)) {
				FileInfo fileInfo = download.processFile(params);

				// 文件找不到
				if (fileInfo == null) {
					ResponseUtil.printException(resp, "attachment.exception.fileNotFound", 404);
					return;
				}

				// 设置文件类型
				String contentType = getServletContext().getMimeType(fileInfo.getFilePath());
				if (contentType == null) {
					contentType = "application/octet-stream";
				}
				resp.setContentType(contentType);

				// 设置文件名称，这样处理可解决中文乱码问题
				String fileName = new String(fileInfo.getFileName().getBytes(), "ISO-8859-1");
				resp.setHeader("Content-disposition", "attachment;filename=\"" + fileName + "\"");

				InputStream is = null;
				OutputStream os = null;
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(fileInfo.getFilePath());
					is = new BufferedInputStream(fis);
					os = new BufferedOutputStream(resp.getOutputStream());
					byte[] buffer = new byte[4 * 1024 * 1024];
					int read = 0;
					while ((read = is.read(buffer)) != -1) {
						os.write(buffer, 0, read);
					}
					os.flush();
				} catch (Exception e) {
					ResponseUtil.printException(resp, "attachment.exception.downloadException");
					return;
				} finally {
					StreamUtil.close(fis);
					StreamUtil.close(is, os);
				}
			}
		} catch (Exception e) {
			if (e instanceof ApplicationException) {
				ResponseUtil.printException(resp, (ApplicationException) e);
			} else {
				ResponseUtil.printException(resp, new ApplicationException());
			}
		}
	}
}
