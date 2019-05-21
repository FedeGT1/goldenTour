package com.goldentour.jee.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration("configBean")
@EnableTransactionManagement
@PropertySources({ @PropertySource("classpath:/spring/database.properties")})
public class ConfigurationBean {
	
	/* Parametri connessione database. */
	@Value("${database.url}")
	private String databaseUrl;
	
	@Value("${database.username}")
	private String databaseUsername;
	
	@Value("${database.password}")
	private String databasePassword;
	
	@Value("${database.driverClassName}")
	private String databaseDriverClassName;
	
	@Value("${jpa.persistence.unit}")
	private String persistenceUnitName;
	
	@Value("${hibernate.show_sql}")
	private String showSql;

	@Value("${hibernate.dialect}")
	private String databaseDialect;
	 

	@Bean
	public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		return new PropertyPlaceholderConfigurer();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		
		// Specifico qual'è la persistence unit
		em.setPersistenceUnitName("goldenTourPU");

		// Setto tutte le info per la connessione
		em.setDataSource(dataSource());
		
		em.setPackagesToScan(new String[] { "com.goldentour.jee.entities" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}
	
	/**
	 * Metodo per la costruzione del DataSource per l'inzializzazione delle
	 * connessioni JPA.
	 */ 
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/goldentour");
		dataSource.setUsername("goldentour");
		dataSource.setPassword("goldentour");
		return dataSource;
	}
	
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
		properties.setProperty("hibernate.show_sql", "true");
		return properties;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager trxManager = new JpaTransactionManager();
		trxManager.setEntityManagerFactory(emf);
		return trxManager;
	}

}
