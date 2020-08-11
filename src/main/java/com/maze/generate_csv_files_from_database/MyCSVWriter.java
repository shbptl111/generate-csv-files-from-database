package com.maze.generate_csv_files_from_database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opencsv.CSVWriter;

@Component
public class MyCSVWriter {
	static int fileNumber = 1;
	public static CSVWriter csvWriter = null;

	FileWriter fileWriter;

	public void createCSVWriterObject() {
		try {
			fileWriter = new FileWriter(new File(fileNameCreator()));
			csvWriter = new CSVWriter(fileWriter);
			String[] header = { "Phone Numbers" };
			csvWriter.writeNext(header);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String fileNameCreator() {
		String filePath = "M:\\CSV Data - Unvalidated\\";
		String fileExtension = ".csv";
		String fileName = filePath + fileNumber + fileExtension;
		File file = new File(fileName);

		while (file.exists()) {
			fileNumber++;
			fileName = filePath + fileNumber + fileExtension;
			file = new File(fileName);
		}

		return fileName;
	}

	public void writeToCSV(List<String[]> phoneNumber) {
		if (phoneNumber.size() > 0) {
			createCSVWriterObject();
			System.out.println("Writing to " + fileNumber + ".csv");
			csvWriter.writeAll(phoneNumber);
			phoneNumber.clear();
		}
	}

	public void closeCSVWriterObject() {
		try {
			if (csvWriter != null) {
				csvWriter.close();
				fileWriter.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
