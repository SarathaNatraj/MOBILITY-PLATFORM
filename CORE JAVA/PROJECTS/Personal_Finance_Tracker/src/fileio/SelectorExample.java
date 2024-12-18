package fileio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class SelectorExample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ServerSocketChannel serverSocket = ServerSocketChannel.open();
		serverSocket.configureBlocking(true);
		serverSocket.bind(new InetSocketAddress(8091));
		
		//Selector 
		Selector selector = Selector.open();
		serverSocket.register(selector, SelectionKey.OP_ACCEPT);
		
		System.out.println(" Server started on port 8091 ");
		
		while(true) {
			
		}

	}

}
