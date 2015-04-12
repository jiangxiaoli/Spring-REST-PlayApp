package edu.sjsu.cmpe275.lab3.dao;

import edu.sjsu.cmpe275.lab3.model.Player;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class JpaPlayerDAO implements PlayerDAO {

    private EntityManagerFactory entityManagerFactory;

    //set jpa entity manager
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public boolean insert(Player player) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            manager.persist(player); //insert new player
            tx.commit();
            return true;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    @Override
    public Player findByPlayerId(long playerId) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            Player player = manager.find(Player.class, playerId);
            tx.commit();
            return player;
        } catch (RuntimeException e) {
            tx.rollback();
            return null;
        } finally {
            manager.close();
        }
    }

    @Override
    public void update(Player player) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        Player player1 = manager.find(Player.class, player.getId());
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            if(player.getFirstname() != null) {
                player1.setFirstname(player.getFirstname()); //update player automatically in jpa
            }
            if(player.getLastname() != null){
                player1.setLastname(player.getLastname());
            }
            if(player.getEmail() != null){
                player1.setEmail(player.getEmail());
            }
            if(player.getDescription() != null){
                player1.setDescription(player.getDescription());
            }
            if(player.getSponsor() != null){
                player1.setSponsor(player.getSponsor());
            }
            if(player.getAddress() != null){
                player1.setAddress(player.getAddress());
            }
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    @Override
    public boolean delete(long playerId) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        Player player = manager.find(Player.class, playerId);
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            manager.remove(player);
            //not delete opponents, need to use with opponentDAO.deleteP first
            tx.commit();
            return true;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Player> allPlayers() {
        String query = "SELECT p FROM Player p"; //select all row from the table
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            List<Player> listPlayers = manager.createQuery(query).getResultList();
            tx.commit();
            return listPlayers; //return empty list, list.size() == 0
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }

    }
}
