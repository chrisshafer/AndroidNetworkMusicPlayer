package com.shafer.mediastreamer.server;

import java.io.IOException;

import com.google.gson.Gson;
import com.shafer.mediastreamer.Command;

public class CommandProcessor {
	
	public static void parseCommand(String input){
		
	}
	public static void processCommand( String message) throws IOException
    {
    	Gson gson = new Gson();
    	Command input = gson.fromJson(message, Command.class);
    	switch(input.command)
    	{
	    	case 101:
	    		NetworkRunner.displayMessage("Attempting to play song");
				NetworkRunner.mediaRunner.playSongByName(input.commandData);
				break;
	    	case 102:
	    		NetworkRunner.displayMessage("Attempting to play next song");
				NetworkRunner.mediaRunner.next();
				break;
	    	case 103:
	    		NetworkRunner.displayMessage("Attempting to play previous song");
				NetworkRunner.mediaRunner.previous();
				break;
	    	case 104:
	    		NetworkRunner.displayMessage("Pause");
				NetworkRunner.mediaRunner.pause();
				break;
			default:
				NetworkRunner.displayMessage("Input error: command number not recognized");
				break;
    	
    	}
    		
    }
}
