package com.shafer.mediastreamer.server;

import java.io.PrintWriter;

public class Sender extends Thread {

	PrintWriter out;
	public Sender(PrintWriter out) {
	   this.out = out;
	}
	
    public void run() {
	 //Send messages to server and print the messages sent
    	while(true){
			while(NetworkRunner.messageOut.size() > 0)
			{
				out.println(NetworkRunner.messageOut.get(0));
				out.flush();
				NetworkRunner.displayMessage(NetworkRunner.messageOut.get(0));
				NetworkRunner.messageOut.remove(0);
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
