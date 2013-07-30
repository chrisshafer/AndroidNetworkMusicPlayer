package com.shafer.mediastreamer.server;

import java.io.BufferedReader;
import java.io.IOException;

public class Listener extends Thread{
		BufferedReader in;
		public Listener(BufferedReader in) {
			this.in = in; 
			// store parameter for later user
		}
		
		public void run() {
			while(true){
			String message = "";
			// Read messages from the server and print the messages recieved
			try {
				while ((message=in.readLine()) != null){ 
					CommandProcessor.processCommand(message);
					NetworkRunner.displayMessage(message);
				}
					
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
				NetworkRunner.displayMessage("sleeping");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		
}
