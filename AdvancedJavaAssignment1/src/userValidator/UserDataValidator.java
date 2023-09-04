package userValidator;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDataValidator {
	public static void main(String[] args) {
		
		// Inside a try block, instantiate 3 separate objects: a 'reader' that reads the
		// data inside user_data.txt, a 'validWriter' for
		// writing to a file with all the valid data,
		// and an errorWriter for writing to a file with all the faulty data.
		// If this try block fails, it would have to be because of an error from
		// instantiating these readers (e.g. file not found, I/O exception, etc.), so
		// just print the error to the console

		String userData = "src\\data\\user_data.txt";
		String dataFile = "src\\data\\user_data_filtered.txt";
		String errorFile  = "src\\data\\error_output_file.txt";;
		BufferedReader reader = null;
		BufferedWriter validWriter = null;
		BufferedWriter errorWriter = null;

		try{
			reader = new BufferedReader(new FileReader(userData));
			validWriter = new BufferedWriter(new FileWriter(dataFile));
			errorWriter = new BufferedWriter(new FileWriter(errorFile));
			String line;

			while ((line = reader.readLine()) != null) {
				String[] lineArray = line.split(",");
			try{
				if (lineArray.length!=3) {
					throw new IllegalArgumentException("Missing Data");
				}

				String name = lineArray[0].trim();
				String email = lineArray[1].trim();
				int age = Integer.parseInt(lineArray[2].trim());

				if (age <= 0) {
					throw new IllegalArgumentException("Invalid Age");
				}

//							System.out.println("Name: " + name);
//							System.out.println("Email: " + email);
//							System.out.println("Age: " + age);

				validWriter.write(name + "," + email + "," + age + "\n");
			}catch (IllegalArgumentException e) {
				errorWriter.write(line + " - " + e.getMessage() + "\n");
				continue;
			}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (validWriter != null) {
					validWriter.close();
				}
				if (errorWriter != null) {
					errorWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
