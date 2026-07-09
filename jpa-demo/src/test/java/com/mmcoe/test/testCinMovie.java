package com.mmcoe.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mmcoe.dao.CinemaMovieDao;
import com.mmcoe.entity.Cinema;
import com.mmcoe.entity.Movie;

public class testCinMovie {
    private static CinemaMovieDao dao;

    @BeforeAll
    public static void setup() {
        dao = new CinemaMovieDao();
    }

    @Test
    public void findMovieByCity() {
        List<Cinema> list = dao.findMovieByLocation("pune");
        assertNotNull(list);
        for(Cinema m : list) {
            System.out.println("Title    : " + m.getName());
        }
    }

    @Test
    public void testSaveMovie() {
        Movie m = new Movie(120, "Pushpa", "Action");
        assertNotNull(dao.save(m));
    }

    @Test
    public void testFindMovieByTitle() {
        List<Movie> list = dao.findMovieByTitle("Pushpa");
        assertNotNull(list);
        for(Movie m : list) {
        	System.out.println(m.getTitle());
        }
    }

    @Test
    public void testSaveCinemaWithMovies() {
        Cinema c = new Cinema();
        c.setCinId(1);
        c.setName("PVR");
        c.setLocation("Pune");
        Movie m1 = new Movie(104, "ABC", "Sci-Fi");
        Movie m2 = new Movie(105, "DEF", "Action");
        c.getMovies().add(m1);
        c.getMovies().add(m2);
        m1.getCinemas().add(c);
        m2.getCinemas().add(c);
        assertNotNull(dao.save(c));
        System.out.println("Cinema with Movies Saved Successfully");
    }

    @Test
    public void testAddMovieToCinema() {
        boolean status = dao.addMovieTOCinema(101, 1);
        assertTrue(status);
        System.out.println("Movie Added to Cinema Successfully");
    }
}