package com.mmcoe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name = "songs")
public class Song {
	@Id @GeneratedValue
	private int sId;
	@Column(length = 30, unique = true)
	private String title;
	@Column(length = 25, nullable = false)
	private String singer;
	
	@ManyToOne
	@JoinColumn(name = "mid")
	private Movie movie;
	
	public Song() {
	}

	public Song(String title, String singer) {
		this.title = title;
		this.singer = singer;
	}

	public String getTitle() {
		return title;
	}

	public String getSinger() {
		return singer;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		return "Song [sId=" + sId + ", title=" + title + ", singer=" + singer + ", movie=" + movie + "]";
	}
	
	
	
}
