package com.shafer.mediastreamer;

public class Command {
	public int command;
	public String commandData;
	String text;
	// {"command":101,"commandData":"none","text":"hello"}
	Command(int command, String commandData, String text) {
		this.command = command;
		this.commandData = commandData;
		this.text = text;
	}
}
