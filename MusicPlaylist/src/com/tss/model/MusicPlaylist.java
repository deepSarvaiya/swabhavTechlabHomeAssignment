package com.tss.model;

import java.io.Serializable;

public class MusicPlaylist implements Serializable{
    private static final long serialVersionUID = 1L;
	private String title;
	private String singer;
	private double duration;
	
	public MusicPlaylist(String title, String singer, double duration) {
		this.title = title;
		this.singer = singer;
		this.duration = duration;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "MusicPlaylist [title=" + title + ", singer=" + singer + ", duration=" + duration + "]";
	}
}