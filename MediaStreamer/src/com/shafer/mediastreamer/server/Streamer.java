package com.shafer.mediastreamer.server;

import com.shafer.mediastreamer.R;
import com.shafer.mediastreamer.R.id;
import com.shafer.mediastreamer.R.layout;
import com.shafer.mediastreamer.R.menu;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.widget.TextView;

public class Streamer extends Activity {

	static TextView console;
	static MediaPlayer mediaPlayer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_streamer);
		console = (TextView) findViewById(R.id.console);
		console.setMovementMethod(new ScrollingMovementMethod());
		Intent i = getIntent();
		String ipAddress = i.getStringExtra("IP");
		int port = Integer.parseInt(i.getStringExtra("PORT"));
		printToConsole("Ip address: "+ipAddress);
		printToConsole("Port: "+port);
		NetworkRunner networkRunner = new NetworkRunner(ipAddress, port);
		networkRunner.start();
		printToConsole("Network Command Connection Attempted");
		FileClient fileClient = new FileClient(ipAddress, port+1);
		fileClient.start();
		printToConsole("File Transfer Connection Attempted");
		
		
	}
	public static void printToConsole(String inputString){
		if(inputString != null){
			console.setText(console.getText() + "\n" + inputString);
		} else {
			printToConsole("Input is null");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.streamer, menu);
		return true;
	}

   

}
