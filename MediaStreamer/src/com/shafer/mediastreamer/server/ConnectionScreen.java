package com.shafer.mediastreamer.server;


import com.shafer.mediastreamer.R;
import com.shafer.mediastreamer.R.id;
import com.shafer.mediastreamer.R.layout;
import com.shafer.mediastreamer.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConnectionScreen extends Activity {

	private EditText ipBox;
	private EditText portBox;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connection_screen);
		Button loginButton = (Button) findViewById(R.id.button1);
		portBox = (EditText) findViewById(R.id.editText2);
		loginButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent streamingScreen = new Intent(getApplicationContext(),Streamer.class );
				streamingScreen.putExtra("PORT", portBox.getText().toString() );
				startActivity(streamingScreen);
						
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.connection_screen, menu);
		return true;
	}

}
