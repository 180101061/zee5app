package com.zee.zee5app;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.config.Config2;

public class Main4 {

	public static void main(String[] args) {
//		ApplicationContext applicationContext = new 
//				AnnotationConfigApplicationContext(Config.class);
//		DataSource dataSource2 = applicationContext.getBean("dataSource2",DataSource.class);
//		System.out.println(dataSource2);
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        DataSource dataSource2 = applicationContext.getBean("dataSource2", DataSource.class);

        System.out.println(dataSource2);
	}
}
