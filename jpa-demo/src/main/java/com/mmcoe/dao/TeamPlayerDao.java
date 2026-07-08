package com.mmcoe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mmcoe.entity.Player;
import com.mmcoe.entity.Team;

public class TeamPlayerDao {
	private static EntityManagerFactory emf;
	static {
		emf = Persistence.createEntityManagerFactory("MyJPA");
	}
	public Team saveTeam(Team t) {
		EntityManager mgr = emf.createEntityManager();
        EntityTransaction txn = mgr.getTransaction();

        try {
            txn.begin();
            mgr.persist(t);
            txn.commit();
            return t;

        } catch (Exception e) {
            e.printStackTrace();
            if (txn.isActive()) {
                txn.rollback();
            }
            return null;
        } finally {
            mgr.close();
        }
	}
	
	public Player savePlayer(Player p, String code) {
		EntityManager mgr = emf.createEntityManager();
        EntityTransaction txn = mgr.getTransaction();

        try {
            txn.begin();
            Team team = mgr.find(Team.class, code);
            if (team == null) {
                System.out.println("Team with code " + code + " not found");
                txn.rollback();
                return null;
            }
            p.setTeam(team);
            mgr.persist(p);
            txn.commit();
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            if (txn.isActive()) {
                txn.rollback();
            }
            return null;
        } finally {
            mgr.close();
        }
	}
	
	public List<Player> findByRole(String role) {
		String hql = "FROM Player WHERE role = :role";
		EntityManager mgr = emf.createEntityManager();
		Query query = mgr.createQuery(hql);
		query.setParameter("role", role);
		List<Player> result = query.getResultList();
		mgr.close();
		return result;
	}
	
	public List<Player> findBidAmountRange(double min, double max){
		String hql = "FROM Player Where bidAmount between :min AND :max";
		EntityManager mgr = emf.createEntityManager();
		Query query = mgr.createQuery(hql);
		query.setParameter("min", min);
		query.setParameter("max", max);
		mgr.close();
		return query.getResultList();
	}
	
}
