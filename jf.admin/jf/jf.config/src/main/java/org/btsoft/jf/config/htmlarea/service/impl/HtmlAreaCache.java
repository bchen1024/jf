package org.btsoft.jf.config.htmlarea.service.impl;

import java.util.Hashtable;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.btsoft.jf.config.htmlarea.dao.IHtmlAreaDao;
import org.btsoft.jf.config.htmlarea.entity.HtmlAreaVO;
import org.btsoft.jf.core.cache.ICache;

@Named("ICache.htmlarea")
public class HtmlAreaCache implements ICache {

	public Logger logger = Logger.getLogger(HtmlAreaCache.class);

	public final static Map<String, HtmlAreaVO> htmlareaCache = new Hashtable<String, HtmlAreaVO>();

	@Inject
	private IHtmlAreaDao dao;

	@Override
	public Object getObject(String param) {
		HtmlAreaVO htmlarea = htmlareaCache.get(param);
		if (htmlarea == null) {
			htmlarea = new HtmlAreaVO();
			htmlarea.setHtmlAreaName(param);
			htmlarea = dao.findHtmlArea(htmlarea);
			if (htmlarea != null) {
				htmlareaCache.put(param, htmlarea);
			}
			return htmlareaCache.get(param);
		}
		return htmlarea;
	}

	@Override
	public String getValue(String param) {
		Object htmlareObj = getObject(param);
		if (htmlareObj == null) {
			return null;
		}
		HtmlAreaVO htmlare = (HtmlAreaVO) htmlareObj;
		return htmlare.getHtmlAreaContent();
	}

	@Override
	public int getSize() {
		return htmlareaCache.size();
	}

	@Override
	public void clear() {
		htmlareaCache.clear();
	}
}
