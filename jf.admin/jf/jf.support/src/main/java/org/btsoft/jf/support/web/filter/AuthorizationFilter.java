package org.btsoft.jf.support.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.btsoft.jf.core.application.IUserPrincipal;
import org.btsoft.jf.core.cache.JFCache;
import org.btsoft.jf.core.common.ILoginService;
import org.btsoft.jf.core.content.JF;
import org.btsoft.jf.core.content.RequestContext;
import org.btsoft.jf.core.utils.DESEncrypt;
import org.btsoft.jf.core.utils.StringUtils;
import org.btsoft.jf.support.web.util.RequestUtil;
import org.btsoft.jf.support.web.util.ResponseUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

/**
 * @ClassName AuthorizationFilter
 * @Description 鉴权过滤器
 * @author bchen
 * @created 2017年11月18日 下午8:52:37
 */
public class AuthorizationFilter implements Filter {
	
	private final static Logger logger=Logger.getLogger(AuthorizationFilter.class);
	
	private ILoginService loginService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		loginService=JF.getContext().getBean("loginService", ILoginService.class);
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		//允许跨域
		RequestUtil.allowCrossDomain(resp);
		if(!"OPTIONS".equals(req.getMethod())) {
			logger.debug("[request url:"+uri+"]");
		}else {
			return;
		}
		
		try {
			//判断氢气是否需要鉴权
			boolean isAuth=RequestUtil.isAuthRequest(req);
			String token=null;
			if(!isAuth) {
				
				//获取鉴权token
				token=req.getHeader("Authorization");
				if(StringUtils.isEmpty(token)) {
					token=req.getParameter("token");
				}
				
				//如果token为空，无权限
				if(StringUtils.isEmpty(token)) {
					ResponseUtil.printException(resp,"security.exception.tokenIsNull",401);
					return;
				}
				
				//判断token是否过期失效
				String signingKey=JFCache.getCahce("property", "signingKey", "weomnlcdsjuowjhzl");
				Claims claims= DESEncrypt.parseJWT(token,signingKey);
				
				String userAccount=claims.get("userAccount").toString();
				IUserPrincipal user=loginService.findUserByAccount(userAccount);
				if(user==null) {
					ResponseUtil.printException(resp,"security.exception.tokenIsValid",401);
					return;
				}
				RequestContext rc=new RequestContext();
				rc.setUser(user);
				//构建上下文
				RequestUtil.buildRequestContext(req,rc);
				RequestContext.setCurrent(rc);
			}
			chain.doFilter(req, resp);
		}catch(ExpiredJwtException ex) {
			ResponseUtil.printException(resp,"security.exception.tokenIsOverdue",401);
			return;
		} catch (Exception e) {
			ResponseUtil.printException(resp,"security.exception.tokenIsValid",401);
			return;
		}
	}

	@Override
	public void destroy() {

	}

}
