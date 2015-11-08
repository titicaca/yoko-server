package com.fifteentec.yoko.server.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;


@Configuration
public class OAuth2Configuration {

    private static final String RESOURCE_ID = "rest_api";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends
            ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(RESOURCE_ID);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
            		.antMatchers("/signup/**","/about", "/home" , "／public/activity/**").permitAll()
            		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                    .antMatchers("/users").hasRole("ADMIN")
//                    .antMatchers("/review").authenticated()
//                    .antMatchers("/logreview").authenticated()
                  //  .antMatchers("/oauth/token").authenticated()
                    .anyRequest().authenticated()
                  //  .and()
                 //   .csrf()
                 //   .csrfTokenRepository(csrfTokenRepository()).and()
                 //   .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)
                    ;
            }
d
            private Filter csrfHeaderFilter() {
                return new OncePerRequestFilter() {

                    @Override
                    protected void doFilterInternal(HttpServletRequest request,
                            HttpServletResponse response, FilterChain filterChain)
                            throws ServletException, IOException {

                        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
                                .getName());
                         if (csrf != null) {
                            Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
                            String token = csrf.getToken();
                            if (cookie == null || token != null
                                    && !token.equals(cookie.getValue())) {
                                cookie = new Cookie("XSRF-TOKEN", token);
                                cookie.setPath("/");
                                response.addCookie(cookie);
                            }
                        }
                        filterChain.doFilter(request, response);
                    }
                };
            }
            private CsrfTokenRepository csrfTokenRepository() {
                HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
                repository.setHeaderName("X-XSRF-TOKEN");
                return repository;
            }
        }


    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends
            AuthorizationServerConfigurerAdapter {
    	
    	String applicationName = "yoko";

    	
    	// This is required for password grants, which we specify below as one of the
    	// {@literal authorizedGrantTypes()}.
    	@Autowired
    	AuthenticationManagerBuilder authenticationManager;
    
    	@Override
    	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
    			throws Exception {
    		// Workaround for https://github.com/spring-projects/spring-boot/issues/1801
    		endpoints.authenticationManager(new AuthenticationManager() {
    			@Override
    			public Authentication authenticate(Authentication authentication)
    					throws AuthenticationException {
    				return authenticationManager.getOrBuild().authenticate(authentication);
    			}
    		});
    	}
    	
    	@Override
    	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    
    		clients.inMemory().withClient("android-" + applicationName)
    				.authorizedGrantTypes("password", "authorization_code", "refresh_token")
    				.authorities("ROLE_USER","ROLE_SPONSOR").scopes("read","write")
    				.secret("123456");
  
    	}

    }
}


