package edu.sjsu.cmpe275.lab3.dao;

import edu.sjsu.cmpe275.lab3.model.Player;
import edu.sjsu.cmpe275.lab3.model.Sponsor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class JpaSponsorDAO implements SponsorDAO {

    private EntityManagerFactory entityManagerFactory;

    //set jpa entity manager
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void insert(Sponsor sponsor) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            manager.persist(sponsor);//insert new sponsor
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    @Override
    public Sponsor findBySponsorId(Long sponsorId) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            Sponsor sponsor = manager.find(Sponsor.class, sponsorId);
            tx.commit();
            return sponsor; //return null if not found
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Sponsor> allSponsors() {
        String query = "SELECT s FROM Sponsor s"; //select all row from the table
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            List<Sponsor> listSponsors = manager.createQuery(query).getResultList();
            tx.commit();
            return listSponsors; //return null if empty
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    @Override
    public void update(Sponsor sponsor) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        Sponsor sponsor1 = manager.find(Sponsor.class, sponsor.getId());
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            if(sponsor.getName() != null) {
                sponsor1.setName(sponsor.getName());
            }
            if(sponsor.getDescription() != null){
                sponsor1.setDescription(sponsor.getDescription());
            }
            if(sponsor.getAddress() != null){
                sponsor1.setAddress(sponsor.getAddress());
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
    public boolean delete(long sponsorId) {
        String query = "SELECT p FROM Player p WHERE Sponsor_idSponsor =" + sponsorId; //select all players under sponse with sponsor

        EntityManager manager = entityManagerFactory.createEntityManager();
        Sponsor sponsor1 = manager.find(Sponsor.class, sponsorId);
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            manager.refresh(sponsor1);//for cascading remove address
            manager.remove(sponsor1);

            //check players with sponsor1, set all to null
            List<Player> listPlayers = manager.createQuery(query).getResultList();
            if(listPlayers != null){
                for(Player p :listPlayers){
                    p.setSponsor(null);
                }
            }

            tx.commit();
            return true;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }


}
