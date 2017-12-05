package org.btsoft.jf.support.web.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.btsoft.jf.core.application.IUserPrincipal;
import org.btsoft.jf.core.cache.JFCache;
import org.btsoft.jf.core.common.ILoginService;
import org.btsoft.jf.core.utils.DESEncrypt;
import org.btsoft.jf.core.utils.StringUtils;
import org.btsoft.jf.support.web.util.RequestUtil;
import org.btsoft.jf.support.web.util.ResponseUtil;

/**
 * @ClassName LoginServlet
 * @Description 登录Servlet
 * @author bchen
 * @created 2017年11月14日 上午11:59:47
 */
@Named("/auth/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1816996129745709843L;

	@Inject
	private ILoginService loginService;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> parameterMap=RequestUtil.getParameterMap(req);
		
		String userAccount = parameterMap.get("userAccount");
		String password = parameterMap.get("password");
		
		if(StringUtils.isEmpty(userAccount)) {
			ResponseUtil.printException(resp, "security.exception.accountNotNull");
			return;
		}
		
		if(StringUtils.isEmpty(password)) {
			ResponseUtil.printException(resp, "security.exception.passwordNotNull");
			return;
		}

		// 根据账号获取用户
		IUserPrincipal user = loginService.findUserByAccount(userAccount);

		//用户不存在
		if (user == null) {
			ResponseUtil.printException(resp, "security.exception.accountNotExists");
			return;
		}
		
		//判断是否在锁定中
		Date lockTime=user.getLockTime();
		//用户锁定时长
		long lockDuration=Long.parseLong(JFCache.getCahce("property","lockDuration","30"));
		if(lockTime!=null) {
			long lockOverplus=System.currentTimeMillis()-lockTime.getTime();
			if(lockOverplus<=lockDuration * 60 * 1000){
				ResponseUtil.printException(resp, "security.exception.accountLock",new Object[] {lockDuration-lockOverplus/1000/60});
				return;
			}
		}
		
		Map<String,Object> userMap=new HashMap<String,Object>();
		userMap.put("userAccount", user.getUserAccount());
		// 匹配密码是否正确
		if (!user.getUserPassword().equals(DESEncrypt.encrypt(password))) {
			
			//密码错误最大次数,错误密码次数大于等于最大错误次数，设置锁定时间
			int pwdErrorMaxNum=Integer.parseInt(JFCache.getCahce("property","pwdErrorMaxNum","5"));
			userMap.put("passwordError", user.getPasswordError()+1);
			if(user.getPasswordError()+1>=pwdErrorMaxNum) {
				userMap.put("lockTime", Calendar.getInstance().getTime());
				ResponseUtil.printException(resp, "security.exception.accountLock",new Object[] {lockDuration});
			}else {
				ResponseUtil.printException(resp, "security.exception.accountPasswordError",new Object[] {pwdErrorMaxNum-user.getPasswordError()-1});
			}
			loginService.updatePasswordErrorNumber(userMap);
			return;
		}else {
			userMap.put("passwordError", 0);
			loginService.updatePasswordErrorNumber(userMap);
		}
		long tokenActivetime=Long.parseLong(JFCache.getCahce("property", "tokenActivetime",(30 * 60 * 1000)+""));
		String signingKey=JFCache.getCahce("property", "signingKey", "weomnlcdsjuowjhzl");
		String token = DESEncrypt.createJWT(user,tokenActivetime,signingKey);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("token", token);
		map.put("user", user);
		ResponseUtil.printValue(resp, map);
	}

}
