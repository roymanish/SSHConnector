/**
 * 
 */
package com.maroy.filters;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * @author MaRoy
 *
 */
public class AjaxAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		response.getWriter().write("SUCCESS");
		//super.onAuthenticationSuccess(request, response, authentication);
	}
}
