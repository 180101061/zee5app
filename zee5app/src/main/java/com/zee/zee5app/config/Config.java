package com.zee.zee5app.config;

import java.util.Properties;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("com.zee.zee5app")
@PropertySource(value = "application.properties")
public class Config {

	@Autowired
	Environment environment;
//	@Bean(name = "properties")
//	//@Scope("prototype") -->use for multiple object creation 
//	public Properties getProperties(){
//		Properties properties = new Properties();
//		properties.setProperty("userName", environment.getProperty("db.username"));
//		properties.setProperty("password", environment.getProperty("db.password"));
//		properties.setProperty("url", environment.getProperty("db.url"));
//		return properties;
//	}
	//datasource  --> get the connection object
	@Bean("dataSource")
	public DataSource getDataSource() {
		
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUsername(environment.getProperty("db.username"));
		basicDataSource.setUrl(environment.getProperty("db.url"));
		basicDataSource.setPassword(environment.getProperty("db.password"));
		return basicDataSource;
	}
}