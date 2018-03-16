package com.fb.vuebasicproxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class CustomAuthenticationProvider
  implements AuthenticationProvider {
 
	@Autowired
	OAuthClient oAuthClient;
	
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
    	
		HashMap<String, Object> info = new HashMap<String, Object>();
    	String accessToken;		
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        accessToken = oAuthClient.getToken(name, password).toString();        
        
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
         
		if (!(accessToken == null)
		    && !accessToken.equals("ERROR")) {
			//Create and populate authentication token for proxy access
			UsernamePasswordAuthenticationToken authenticationOutcome 
			    = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
			//Set authserver token on local(proxy) authentication object

			info.put("accessToken", accessToken);		
			authenticationOutcome.setDetails(info);
			
            return authenticationOutcome;
        } else {     	
            return null;
        }
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
          UsernamePasswordAuthenticationToken.class);
    }
        
}