package com.maze.generate_csv_files_from_database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SQLSelectQuery {

	@Autowired
	Connection databaseConnection;

	@Autowired
	MyCSVWriter myCSVWriter;

	Statement statement;

	public Statement createStatement() {
		try {
			statement = databaseConnection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}

	public void getResultSet() {

		String getResultSQL = "SELECT MobileNo FROM YourTable WHERE ContactType IS NULL ORDER BY SrNo";
		try {
			ResultSet resultSet = statement.executeQuery(getResultSQL);
			List<String[]> list = new ArrayList<String[]>();
			
			while (resultSet.next()) {
				String[] mobileNumbers = { resultSet.getString("MobileNo") };
				list.add(mobileNumbers);
				if (list.size() == 10000) {
					myCSVWriter.writeToCSV(list);
					myCSVWriter.closeCSVWriterObject();
					list.clear();
				}
			}
			
			if (list.size() != 0) {
				myCSVWriter.writeToCSV(list);
				myCSVWriter.closeCSVWriterObject();
				list.clear();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeStatement() {

		try {
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
