package com.myapp.util;

import com.myapp.database.entity.User;
import com.myapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.ArrayList;
import java.util.List;

public class WebAuthProvider implements AuthenticationProvider
{
	@Autowired
	UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		String ip = "";
		if(authentication.getDetails()!=null && authentication.getDetails() instanceof WebAuthenticationDetails){
			ip = ((WebAuthenticationDetails) authentication.getDetails()).getRemoteAddress();
		}

		User user = userService.login(name, password, ip);
		if(user != null)
		{
			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().getRole());
			grantedAuths.add(grantedAuthority);
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name, password,
				grantedAuths);
			token.setDetails(user);
			return token;
		} else {
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
