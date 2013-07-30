package com.shafer.mediastreamer.server;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import com.shafer.mediastreamer.Song;

import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

public class MediaRunner {
	
	private static ArrayList<Song> playlist;
	private static MediaPlayer mediaPlayer;
	private Song nowPlaying;
	private static Handler handler = new Handler();
	final String MEDIA_PATH = new String("/sdcard/Music");
	final String EXTERNAL_MEDIA_PATH = new String("/sdcard/external_sd/music");
	MediaRunner(){
		playlist = new ArrayList<Song>();
		mediaPlayer = new MediaPlayer();
		getPlayList();
	}
	public void playSong(Song song){
		if(songExistsOnDevice(song)){
			nowPlaying = song;
			if(!playlist.contains(song)){
				playlist.add(song);
			}
			try {
				mediaPlayer.reset();
				mediaPlayer.setDataSource(song.getDirectory());
				mediaPlayer.prepare();
				mediaPlayer.start();
				NetworkRunner.displayMessage("Started Playback of "+nowPlaying.getSongName());
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void playSongByName(String name) throws IllegalArgumentException, SecurityException, IllegalStateException, IOException{
		for(Song song: this.playlist){
			if(song.getSongName().equals(name)){
				playSong(song);
				return;
			}
		}
		playOneSong();
		
	}
	public void getPlayList(){
        File home = new File(MEDIA_PATH);
        scanForMp3(home);
        File externalhome = new File(EXTERNAL_MEDIA_PATH);
        scanForMp3(externalhome);
        // return songs list array
    }
	public void scanForMp3(File root){
        NetworkRunner.displayMessage("retrieving files from : "+root);
        if (root.listFiles().length > 0) {
            for (File file : root.listFiles()) {
            	if(file.getName().endsWith(".mp3")){
	                Song song = Song.songFactory(file.getName().substring(0, (file.getName().length() - 4)), file.getPath()); 
	                playlist.add(song);
	                NetworkRunner.displayMessage(song.getSongName());
            	}
            	if(file.isDirectory()){
            		scanForMp3(file);
            	}
            }
        }
	}
	public void sendPlaylist(){
		String stringPlaylist = "";
		for(Song song : this.playlist){
			stringPlaylist += song.getSongName() + "<>";
		}
		if(!this.playlist.isEmpty()){
		NetworkRunner.messageOut.add(stringPlaylist);
	
		}
	}
	class FileExtensionFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3") || name.endsWith(".MP3"));
        }
    }
	public void playOneSong() throws IllegalArgumentException, SecurityException, IllegalStateException, IOException{
		
		playSong(playlist.get(0));
		
		NetworkRunner.displayMessage("playing"+playlist.get(0).getSongName());
	}
	public boolean songExistsOnDevice(Song song){
		return true;
	}
	public void pause(){
		mediaPlayer.pause();
	}	
	public void resume(){
		mediaPlayer.start();
	}
	public void seekTo(){
		
	}
	public void next(){
		int currentIndex = playlist.indexOf(this.nowPlaying);
		if(currentIndex == playlist.size()-1){
			currentIndex = 0;
		} else {
			currentIndex ++;
		}
		playSong(playlist.get(currentIndex));
	}
	public void previous(){
		int currentIndex = playlist.indexOf(this.nowPlaying);
		if(currentIndex == 0){
			currentIndex = playlist.size()-1;
		} else {
			currentIndex --;
		}
		playSong(playlist.get(currentIndex));
	}
	public void createPlaylist(ArrayList<Song> songList){
		clearPlaylist();
		for(Song song: songList){
			addToPlaylist(song);
		}
	}
	public void clearPlaylist(){
		playlist = new ArrayList<Song>();
	}
	public void removeFromPlaylist(Song song){
		playlist.remove(song);
	}
	public void addToPlaylist(Song song){
		if(!playlist.contains(song)){
			playlist.add(song);
		}
	}

	
}
