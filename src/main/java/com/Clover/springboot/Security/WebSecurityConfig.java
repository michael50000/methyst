package com.Clover.springboot.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;


@Configuration
@Order(1)
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
         .antMatcher("/**")
             .authorizeRequests()
             .antMatchers("/oauth/authorize**", "/login**", "/error**")
             .permitAll()
         .and()
             .authorizeRequests()
             .anyRequest().authenticated()
         .and()
         .formLogin().permitAll();
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("humptydumpty").password(passwordEncoder().encode("123456")).roles("USER");
    }
	@Bean
    public BCryptPasswordEncoder passwordEncoder(){ 
        return new BCryptPasswordEncoder(); 
    }
	
	

}