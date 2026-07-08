package com.mmcoe.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mmcoe.entity.Movie;
import com.mmcoe.entity.Cinema;

public class CinemaMovieDao {
	private static EntityManagerFactory emf;
	
	public CinemaMovieDao() {
		emf = Persistence.createEntityManagerFactory("MyJPA");
	}
	public Object save(Object obj) {
		EntityManager mgr = emf.createEntityManager();
		EntityTransaction txn = mgr.getTransaction();
		txn.begin();
		mgr.persist(obj);
		txn.commit();
		return obj;
	}
	
	public boolean addMovieTOCinema(int mid,int cid) {
		EntityManager mgr = emf.createEntityManager();
		EntityTransaction txn = mgr.getTransaction();
		txn.begin();
		Movie m = mgr.find(Movie.class, mid);
		Cinema c = mgr.find(Cinema.class, cid);
		c.getMovies().add(m);
		txn.commit();
		return true;
	}
	
	public List<Movie> findByGenre(String genre) {
		String hql = "FROM Movie WHERE genre = :genre";
		EntityManager mgr = emf.createEntityManager();
		Query query = mgr.createQuery(hql);
		query.setParameter("genre", genre);
		List<Movie> result = query.getResultList();
		mgr.close();
		return result;
	}
	
	public List<Movie> findMovieByTitle(String title){
		String hql = "FROM Movie WHERE title = :title";
		EntityManager mgr = emf.createEntityManager();
		Query query = mgr.createQuery(hql);
		query.setParameter("title", title);
		List<Movie> result = query.getResultList();
		mgr.close();
		return result;
	}
	public List<Cinema> findMovieByLocation (String location) {
		String hql = "FROM Cinema WHERE location = :location";
		EntityManager mgr = emf.createEntityManager();
		Query query = mgr.createQuery(hql);
		query.setParameter("location", location);
		List<Cinema> result = query.getResultList();
		mgr.close();
		return result;
	}
	public void addSongtoMvie() {}
}
