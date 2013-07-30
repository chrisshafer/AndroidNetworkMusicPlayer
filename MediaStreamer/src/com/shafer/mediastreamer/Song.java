package com.shafer.mediastreamer;

public class Song {
	private String songName;
	private String directory;
	
	private Song(String songName, String directory){
		this.songName = songName;
		this.directory = directory;
	}
	public static Song songFactory(String songName, String directory){
		if(songName != null & directory != null){
			return new Song (songName, directory);
		}
		return null;
	}
	public String getSongName(){
		return this.songName;
	}
	public String getDirectory(){
		return this.directory;
	}
	public void setSongName(String songName){
		if(songName != null)
		this.songName = songName;
	}
	public void setDirectory(String directory){
		if(directory != null)
		this.directory = directory;
	}
	@Override
    public boolean equals(Object obj) {
		
        if(songName != null && directory != null){
        	if(obj instanceof Song){
        		if(((Song) obj).getSongName().equalsIgnoreCase(songName))
        			return true;
        	}
        }
        return false;
    }
}
