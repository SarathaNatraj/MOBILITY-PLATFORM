package fileio;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharacterStreamExample {
    public static void main(String[] args) {
    	File f = new File("abc.txt");
        try (FileReader reader = new FileReader(f);
             FileWriter writer = new FileWriter("output_char.txt")) {
            int data;
            while ((data = reader.read()) != -1) { //EOF 
                writer.write(data); // Write character by character
            }
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
