package com.sy.web.interceptor;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sy.modules.entity.sys.SysUser;
import com.sy.web.commons.Constants;

public class SessionInterceptor extends HandlerInterceptorAdapter {

			@Override
			public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
				String req_url=request.getRequestURI();
				if(req_url.contains("wap")){
					return true;
				}
				if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))||request.getParameter("ajax") != null) {
					SysUser user = (SysUser) request.getSession().getAttribute(Constants.USER_LOGIN_SESSION_KEY);
					String jsonStr = "{\"statusCode\":301,\"message\":\"登录已超时，请重新登录！\"}";
					
					if (null == user) {
						// 未登录				
						ObjectMapper mapper = new ObjectMapper();
						mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
						JsonNode df = mapper.readValue(jsonStr, JsonNode.class);				
						PrintWriter out = response.getWriter();
						response.setContentType("text/plain");
						System.out.println(df);					
						out.println(df);
						out.flush();
						out.close();
						return false;
					}
				}
				return true;  
			}
}
