package com.shafer.mediastreamer.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.shafer.mediastreamer.Command;

import android.os.Handler;
import android.util.Log;

public class NetworkRunner extends Thread{
	 	
		String serverAddress;
		int serverPort;
		private static Handler handler = new Handler();
		public static List<String> messageOut;
		public static List<Command> commands;
		ServerSocket serverSocket;
		DatagramSocket discovery;
		public static MediaRunner mediaRunner;
		
		public NetworkRunner(String serverAddress, int serverPort) {	
			this.serverAddress = serverAddress;
			this.serverPort = serverPort;
			handler = new Handler();
			messageOut = Collections.synchronizedList(new ArrayList<String>());
			displayMessage("networkRunner created");
			mediaRunner = new MediaRunner();	
		}
		public void run() {
			NetworkDiscovery networkDiscovery = new NetworkDiscovery(serverAddress, serverPort);
			networkDiscovery.start();
			try {
				serverSocket = new ServerSocket(serverPort);
				
			} catch (IOException se) {
				se.printStackTrace();
				System.exit(-1);
			}
			displayMessage("Server started at port: "+serverSocket.getLocalSocketAddress());
			BufferedReader in = null;
			PrintWriter out = null;
			Listener listener = null;
			Sender sender = null;
			while(true){
				try {
					Socket socket = serverSocket.accept();
					displayMessage("new client connected from: "+socket.getInetAddress()+ ":" + socket.getPort());
					in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
					out = new PrintWriter( new OutputStreamWriter(socket.getOutputStream()));
					mediaRunner.sendPlaylist();
				    
				} catch (IOException ioe) {
					displayMessage("client failed connection at port ");
					ioe.printStackTrace();
					Log.i("Exception Thrown",ioe.toString());
				}
				
				if(in != null && out != null){
					displayMessage("Successfull connection to: " + serverAddress + ":" + serverPort);
					listener = new Listener(in);
					sender = new Sender(out);
					listener.start();
					sender.start();
				}
				else{
					displayMessage("network error: cannot start listener and sender");
				}
				try {
					Thread.sleep(1000);
					displayMessage("sleeping");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	    public static void displayMessage(String msg)
		{ 
			final String message=msg;
			Log.i("message",message);
		    handler.post(new Runnable() {		
				public void run() {
					Streamer.printToConsole(message);
				}
			});	
		}
}
