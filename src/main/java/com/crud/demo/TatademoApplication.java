package com.crud.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.crud.demo.controller.CustomerController;
import com.crud.demo.service.CustomerService;

@SpringBootApplication
@EnableCaching
//@ComponentScan(basePackages ="com.crud.demo")// CustomerController.class
public class TatademoApplication {
 
	
	
	public static void main(String[] args) {
		SpringApplication.run(TatademoApplication.class, args);
		
	

	}
	@Component
	public class CacheCaffine implements CommandLineRunner {
		@Autowired
		CustomerService customerservice;

		@Override
		public void run(String... args) throws Exception {
			// TODO Auto-generated method stub
			customerservice.getCustomerService(1);
			customerservice.getCustomerService(1);
			customerservice.getCustomerService(1);
			customerservice.getCustomerService(1);
			customerservice.getCustomerService(1);
			customerservice.getCustomerService(1);
			
		}
	}

	@Bean
	public DataSource dataSource() {
	        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
	        dataSourceBuilder.url("jdbc:sqlite:testassessment.db");
	     
	        return dataSourceBuilder.build();   
	}
	
}
