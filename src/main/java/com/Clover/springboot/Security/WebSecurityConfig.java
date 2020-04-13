package com.Clover.springboot.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	@Value("${ldap.urls}")
	private String ldapUrls;
	@Value("${ldap.base.dn}")
	private String ldapBaseDn;
	@Value("${ldap.username}")
	private String ldapSecurityPrincipal;
	@Value("${ldap.password}")
	private String ldapPrincipalPassword;
	@Value("${ldap.user.dn.pattern}")
	private String ldapUserDnPattern;
	@Value("${ldap.enabled}")
	private String ldapEnabled;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http
	.authorizeRequests()
	.anyRequest().fullyAuthenticated()
	.and()
	.formLogin();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.warn("Ldap Enab:="+ldapEnabled);
		/*  auth
		 .ldapAuthentication()
		 .contextSource()
		 .url(ldapUrls + ldapBaseDn)
		 .managerDn(ldapSecurityPrincipal)
		 .managerPassword(ldapPrincipalPassword)
		 .and()
		 .userDnPatterns(ldapUserDnPattern);*/
		
		if(Boolean.parseBoolean(ldapEnabled)){
			  auth
			 .ldapAuthentication()
			 .contextSource()
			 .url(ldapUrls + ldapBaseDn)
			 .managerDn(ldapSecurityPrincipal)
			 .managerPassword(ldapPrincipalPassword)
			 .and()
			 .userDnPatterns(ldapUserDnPattern);
			
		}else{
			auth
			.inMemoryAuthentication()
			.withUser("user")
			.password("{noop}user")
			.roles("USER")
			.and()
			.withUser("admin")
			.password("{noop}admin")
			.roles("ADMIN");
			
		}
	}
	
	

}