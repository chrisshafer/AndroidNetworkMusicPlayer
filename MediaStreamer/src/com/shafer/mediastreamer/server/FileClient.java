package com.shafer.mediastreamer.server;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileClient extends Thread {
	
	String serverAddress;
	int serverPort;
	
	public FileClient(String serverAddress, int serverPort) {	
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;
		NetworkRunner.displayMessage("FileClient created");
			
	}
	public void run(){
		try{
			int bytesRead;  
		    int current = 0;  
		  
		    ServerSocket serverSocket = null; 
		    NetworkRunner.displayMessage("Attempting file transfer server setup");
		    serverSocket = new ServerSocket(serverPort);
		    NetworkRunner.displayMessage("File Client transfer server started at port:"+serverSocket.getLocalSocketAddress());
		        
		    while(true) {  
		        Socket clientSocket = null;  
		        clientSocket = serverSocket.accept();  
		          
		        InputStream in = clientSocket.getInputStream();  
		          
		        DataInputStream clientData = new DataInputStream(in);   
		          
		        String fileName = clientData.readUTF();     
		        OutputStream output = new FileOutputStream(fileName);     
		        long size = clientData.readLong();     
		        byte[] buffer = new byte[1024];     
		        while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1)     
		        {     
		            output.write(buffer, 0, bytesRead);     
		            size -= bytesRead;     
		        }  
		          
		        // Closing the FileOutputStream handle  
		        output.close();  
		    }  		   	
		    
		} catch (IOException e){
			NetworkRunner.displayMessage("File transfer failed");
			
		}
		
	}
	public static void recieveFile(){
		
	}
}
