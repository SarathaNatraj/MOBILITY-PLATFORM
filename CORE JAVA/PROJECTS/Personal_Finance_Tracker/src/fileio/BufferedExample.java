package fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (FileReader reader = new FileReader("input.txt");
				BufferedReader bReader = new BufferedReader(reader);
	             FileWriter writer = new FileWriter("output_buffer.txt");
				BufferedWriter bWriter = new BufferedWriter(writer)) {
	            int data;
	            while ((data = bReader.read()) != -1) { //EOF 
	                bWriter.write(data); // Write character by character
	            }
	            System.out.println("File copied successfully.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	}

}
