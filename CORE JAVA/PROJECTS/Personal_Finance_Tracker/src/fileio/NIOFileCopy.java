package fileio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class NIOFileCopy {
    public static void main(String[] args) {
        Path source = Path.of("input.txt");
        Path destination = Path.of("output_channel.txt");

        try (FileChannel sourceChannel = FileChannel.open(source, StandardOpenOption.READ);
             FileChannel destChannel = FileChannel.open(destination, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {

            ByteBuffer buffer = ByteBuffer.allocate(1024); // Allocate buffer
            while (sourceChannel.read(buffer) > 0) {
                buffer.flip(); // Switch to read mode
                destChannel.write(buffer); // Write to destination
                buffer.clear(); // Clear buffer for next read
            }
            System.out.println("File copied successfully using NIO.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
