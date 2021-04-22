package org.iskon.kirtan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("org.iskon")
public class AppConfig {

	@Bean(name="dataSource")
	public DriverManagerDataSource getDriverManagerSource()
	{
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName("com.mysql.jdbc.Driver");
		dmds.setUrl("jdbc:mysql://localhost:3306/kirtan");
		dmds.setUsername("root");
		dmds.setPassword("my$qlr00t");
		return dmds;
	}
	
	@Bean(name="jdbcTemplate")
	public JdbcTemplate getJdbcTemplate()
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDriverManagerSource());
		return jdbcTemplate;
	}
}
