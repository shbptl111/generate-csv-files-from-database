package com.maze.generate_csv_files_from_database;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

	private static AnnotationConfigApplicationContext context;

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		context = new AnnotationConfigApplicationContext();
		context.scan("com.maze.generate_csv_files_from_database");
		context.refresh();

		SQLSelectQuery sql = (SQLSelectQuery) context.getBean(SQLSelectQuery.class);

		sql.createStatement();
		sql.getResultSet();
		sql.closeStatement();
		context.close();
		long end = System.currentTimeMillis();

		System.out.println("Total time required: " + (TimeUnit.MILLISECONDS.toSeconds(end - start) / 60) + " minutes, "
				+ (TimeUnit.MILLISECONDS.toSeconds(end - start) % 60) + " seconds");
	}

}
