package com.maze.generate_csv_files_from_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {
	
	private Connection connection;

	@Bean(name = "databaseConnection")
	public Connection getConnection() {
		try {
			connection = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Maze;integratedSecurity=true;");
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	@PreDestroy
	public void closeConnection() {
		try {
			if(connection != null && !connection.isClosed()) {
				connection.close();
				System.out.println("Connection with database closed");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
