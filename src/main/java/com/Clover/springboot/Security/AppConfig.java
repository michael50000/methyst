package com.Clover.springboot.Security;

import javax.sql.DataSource;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

	
	  @Bean
	  @Primary
	  public DataSource dataSource() {
	      return DataSourceBuilder
	          .create()
	          .username("root")
	          .password("root")
	          .url("jdbc:mysql://localhost:3306/sys?useSSL=false")
	          .driverClassName("com.mysql.cj.jdbc.Driver")
	          .build();
	  }
	
}
