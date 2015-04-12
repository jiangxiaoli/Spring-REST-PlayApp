package edu.sjsu.cmpe275.lab3.dao;

import edu.sjsu.cmpe275.lab3.model.Player;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class JpaOpponentDAO implements OpponentDAO{

    private EntityManagerFactory entityManagerFactory;

    //set jpa entity manager
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void insert(Player player1, Player player2) {

        //check duplication
        for (Player p: player1.getOpponents()){
            if(p.getId() == player2.getId()) return;
        }

        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();

        Player player11 = manager.find(Player.class, player1.getId());
        Player player12 = manager.find(Player.class, player2.getId());

        //check if null
        if(player11 == null || player12 == null){
            return;
        }

        try {
            tx.begin();
            player11.getOpponents().add(player12);//add player in pairs
            player12.getOpponents().add(player11);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }

    }

    @Override
    public void delete(Player player1, Player player2) {

        //check if exist
        boolean hasPlayer = false;
        for (Player p: player1.getOpponents()){
            if(p.getId() == player2.getId()) hasPlayer = true;
        }
        if(!hasPlayer) return;

        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();

        Player player11 = manager.find(Player.class, player1.getId());
        Player player12 = manager.find(Player.class, player2.getId());

        try {
            tx.begin();
            //delete in pairs
            player11.getOpponents().remove(player12);//remove player in pairs
            player12.getOpponents().remove(player11);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    @Override
    public void deleteP(Player player) {

        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();

        Player player11 = manager.find(Player.class, player.getId());
        List<Player> playerList= findOpponentsByPlayer(player11);
        try {
            tx.begin();

            //delete all relationships contains player
            for(Player p: playerList) {
                Player playerX = manager.find(Player.class, p.getId());
                playerX.getOpponents().remove(player11);
            }

            player11.getOpponents().clear(); //clear all opponents in player
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Player> findOpponentsByPlayer(Player player) {
        return player.getOpponents();
    }

}
