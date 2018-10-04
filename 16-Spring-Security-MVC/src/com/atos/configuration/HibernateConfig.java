package com.atos.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = "com.atos")
@EnableTransactionManagement
public class HibernateConfig {

	@Autowired
	private Environment env;

	/*
	 * DataSource ----> Session Factory ----> Transaction Manager
	 **/

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean session = new LocalSessionFactoryBean();
		session.setDataSource(dataSource());
		session.setPackagesToScan(new String[] { "com.atos" });
		session.setHibernateProperties(hibernateProperties());
		return session;
	}

	private Properties hibernateProperties() {
		Properties props = new Properties();
		props.getProperty("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		props.getProperty("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		return props;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource source = new DriverManagerDataSource();
		source.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
		source.setUrl(env.getRequiredProperty("jdbc.url"));
		source.setUsername(env.getRequiredProperty("jdbc.username"));
		source.setPassword(env.getRequiredProperty("jdbc.password"));
		return source;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager hibernateTransactionManager(SessionFactory s) {
		HibernateTransactionManager manager = new HibernateTransactionManager();
		manager.setSessionFactory(s);
		return manager;
	}

}
