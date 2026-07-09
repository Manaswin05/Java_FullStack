package com.mmcoe.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity @Table(name = "movie")
public class Movie {
	@Id @Column(name = "mid")
	private int movieId;
	@Column(name = "title")
	private String title;
	@Column(name = "gen")
	private String genre;
	
	@ManyToMany
	@JoinTable(name = "showtime",joinColumns = {@JoinColumn(name = "mid")},
	inverseJoinColumns = {@JoinColumn(name = "cid")})
	private List<Cinema> cinemas = new ArrayList<Cinema>();
	
	@OneToMany(mappedBy = "movie")
	private List<Song> songs = new ArrayList<Song>();
	
	public Movie() {
	}
	
	public Movie(int movieId, String title, String genre) {
		this.movieId = movieId;
		this.title = title;
		this.genre = genre;
	}
	
	public Movie(int movieId, String title, String genre, List<Cinema> cinemas) {
		this.movieId = movieId;
		this.title = title;
		this.genre = genre;
		this.cinemas = cinemas;
	}

	public int getMovieId() {
		return movieId;
	}
	public String getTitle() {
		return title;
	}
	public String getGenre() {
		return genre;
	}
	public List<Cinema> getCinemas() {
		return cinemas;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public void setCinemas(List<Cinema> cinemas) {
		this.cinemas = cinemas;
	}
	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", title=" + title + ", genre=" + genre + ", cinemas=" + cinemas + "]";
	}

	
}
