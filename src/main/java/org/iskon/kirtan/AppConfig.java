package org.iskon.kirtan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("org.iskon")
@PropertySource("classpath:application.properties")
public class AppConfig {

	@Value("${spring.datasource.url}")
    private String url;
	
	@Value("${spring.datasource.username}")
    private String username;
	
	@Value("${spring.datasource.password}")
    private String password; 
	
	@Value("${spring.datasource.driver-class-name}")
    private String driverclassname; 
	
	@Bean(name="dataSource")
	public DriverManagerDataSource getDriverManagerSource()
	{
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName(driverclassname);
		dmds.setUrl(url);
		dmds.setUsername(username);
		dmds.setPassword(password);
		return dmds;
	}
	
	@Bean(name="jdbcTemplate")
	public JdbcTemplate getJdbcTemplate()
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDriverManagerSource());
		return jdbcTemplate;
	}
}
