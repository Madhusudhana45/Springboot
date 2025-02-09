package com.example.emp.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter  {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private CustomUserServiceDetails customerUserDetails;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		// get the token form header
		// check the token either valid or not
		// load the user and setAuthentication
		
		String token=getToken(request);
		
		if(StringUtils.isNotEmpty(token) &&  jwtTokenProvider.validateToken(token)) {
			 String email=jwtTokenProvider.getEmailFromToken(token);
			UserDetails userDetails = customerUserDetails.loadUserByUsername(email);
		UsernamePasswordAuthenticationToken authentication =
				new  UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}
		private String getToken(HttpServletRequest request)
		{
			String token = request.getHeader("Authorization");
			
			if(StringUtils.isNotBlank(token) && token.startsWith("Bearer")) {
					return token.substring(7, token.length());
			}
			
			return null;
		}
		
	
	

}
