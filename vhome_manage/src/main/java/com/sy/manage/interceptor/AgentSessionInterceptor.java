package com.sy.manage.interceptor;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sy.manage.commons.Constants;


/**
 * Servlet Filter implementation class AgentSessionInterceptor
 */
@WebFilter(filterName="/AgentSessionInterceptor",urlPatterns="/*" )
public class AgentSessionInterceptor implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest r = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = r.getSession();
		String url = r.getRequestURI();
		int su = url.lastIndexOf('/');
		String action = url.substring(su+1);
		String[] jtwj = new String[]{".png",".jpg",".js",".css"};//是否包含.字符，以便过滤静态文件
		String[] guoluaction = new String[]{"login.jsp","queryUserIdByKey","agtLogin","findKeyWordNyName","kyFindKeyWordNyName","kyFindIndentByKeyWordId","findIndentByKeyWordId"};//需要放行的url
		int bh = action.indexOf('.');
		if(session.getAttribute(Constants.AGT_USER_LOGIN_SESSION_KEY)!=null){
			chain.doFilter(request, response);
		}else{
			for(int i =0;i<guoluaction.length;i++){
				if(action.equals(guoluaction[i])){
					chain.doFilter(request, response);
					return;
				}
			}
			if(bh!=-1){
				String tjfile=action.substring(bh);
				for(String j:jtwj){
					if(j.equalsIgnoreCase(tjfile)){
						chain.doFilter(request, response);
						return;
					}
				}
			}
			//r.getRequestDispatcher("/login.jsp").forward(request,response);
			resp.sendRedirect("/login.jsp");
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void destroy() {
		
	}

}
