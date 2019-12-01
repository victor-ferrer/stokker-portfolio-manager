package org.vferrer.portfolio.manager.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Driver;

import javax.sql.DataSource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 * When running in Heroku we will connect to Postgres (as opposed to the H2
 * database used in development)
 * 
 * @author Victor
 *
 */
@Configuration
@Profile("github_heroku")
public class HerokuPostgresConfig {

	@Bean
	public DataSource dataSource() throws URISyntaxException {
		
		// Environment variable pointing to the Postgres add-on
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath()
				+ "?sslmode=require";

		SimpleDriverDataSource basicDataSource = new SimpleDriverDataSource();
		basicDataSource.setUrl(dbUrl);
		
		Driver postgresDriver = BeanUtils.instantiateClass(org.postgresql.Driver.class);
		System.out.println("Driver to use: " + postgresDriver.toString());
		basicDataSource.setDriver(postgresDriver);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);

		return basicDataSource;
	}
}
