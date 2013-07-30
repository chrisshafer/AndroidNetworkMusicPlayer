package com.shafer.mediastreamer.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class ClientListener extends Thread {

		BufferedReader in;
		public ClientListener(BufferedReader in) {
			this.in = in; 
			// store parameter for later user
		}
		
		public void run() {
			while(true){
			String message = "";
			// Read messages from the server and print the messages recieved
			try {
				while ((message=in.readLine()) != null) 
				{
					if(message.contains("<>")){
						String[] parts = message.split("<>");
						ArrayList<String> playlist = new ArrayList<String>();
						for(int i=0; i<parts.length; i++){
							playlist.add(parts[i]);
						}
					
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		}

}
