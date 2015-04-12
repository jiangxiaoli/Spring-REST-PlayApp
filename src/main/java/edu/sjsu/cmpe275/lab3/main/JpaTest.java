package edu.sjsu.cmpe275.lab3.main;

import edu.sjsu.cmpe275.lab3.dao.OpponentDAO;
import edu.sjsu.cmpe275.lab3.dao.PlayerDAO;
import edu.sjsu.cmpe275.lab3.dao.SponsorDAO;
import edu.sjsu.cmpe275.lab3.model.Address;
import edu.sjsu.cmpe275.lab3.model.Player;
import edu.sjsu.cmpe275.lab3.model.Sponsor;
import org.hibernate.Hibernate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class JpaTest {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");

        PlayerDAO playerDAO = (PlayerDAO) context.getBean("playerDAO");
        SponsorDAO sponsorDAO = (SponsorDAO) context.getBean("sponsorDAO");
        OpponentDAO opponentDAO = (OpponentDAO) context.getBean("opponentDAO");

        /******************************  PlayerDAO test ******************************************/
        /***** 1. insert test *****/
//        Player player = new Player("alice1", "lee1", "alice11@me.com");
//        if(playerDAO.insert(player)){
//            Address add = new Address("6 1st St", "San Jose", "CA", "95112");
//            player.setAddress(add);
//            playerDAO.update(player);
//        } else {
//            System.out.println("fail to create player");
//        }

//        /***** 2. retrieve one test ****/
//        if(playerDAO.findByPlayerId(Long.valueOf(2)) != null) {
//            System.out.println("player::" + playerDAO.findByPlayerId(Long.valueOf(2)).getFirstname());
//        } else {
//            System.out.println("player not found");
//        }

//        /***** 3. retrieve all test *****/
//        List<Player> plist = playerDAO.allPlayers();
//        if(plist.size() != 0){
//            for(Player p : plist){
//                System.out.println("Player List::" + p.getFirstname());
//            }
//        } else {
//            System.out.println("no players in list");
//        }


//        /***** 4. update test *****/
//        Player player1 = playerDAO.findByPlayerId(Long.valueOf(1));
//        if(player1 != null){
//            player1.setFirstname("Olivia");//update address
//            Address add1 = player1.getAddress(); //check if null
//            if(add1 != null){
//                System.out.println("in update address");
//                add1.setState("AZ");
//            } else {
//                System.out.println("in new address");
//                Address newadd = new Address();
//                newadd.setState("AZ");
//                player1.setAddress(newadd);
//            }
//            playerDAO.update(player1);
//        } else {
//            System.out.println("player not found");
//        }


//        /***** 5. delete player, should also delete opponent relationship(manual) *****/
        long playerId = Long.valueOf(2); Player player = playerDAO.findByPlayerId(playerId);
        if(playerDAO.findByPlayerId(playerId) !=null){
            //delete all opponents first
            opponentDAO.deleteP(player);
            playerDAO.update(player);
//            System.out.println("first: delete opponent::" + opponentDAO.deleteP(playerId));
            System.out.println("second: delete player::" + playerDAO.delete(playerId));
        } else {
            System.out.println("player "+ playerId+" not found!");
        }


        /******************************  SponsorDAO test ******************************************/

//        /***** 1. insert test *****/
//        Sponsor sponsor = new Sponsor("tom");
//        sponsorDAO.insert(sponsor);


//        /***** 2. retrieve one test ****/
//        long sponsorId = Long.valueOf(2);
//        Sponsor sponsor = sponsorDAO.findBySponsorId(sponsorId);
//        if(sponsor != null){
//            System.out.println("sponsor::" + sponsor.getName());
//            Player player11 = playerDAO.findByPlayerId(Long.valueOf(5));
//            player11.setSponsor(sponsor);
//            playerDAO.update(player11);
//        } else {
//            System.out.println("sponsor "+sponsorId+" NOT found!! ");
//        }
////
//        /***** 3. retrieve all test *****/
//        List<Sponsor> slist = sponsorDAO.allSponsors();
//        for(Sponsor s : slist){
//            System.out.println("Sponsor List::"+ s.getName());
//        }
//
//        /***** 4. update test - no 1 *****/
//        long sponsorId1 = Long.valueOf(2);
//        Sponsor sponsor1 = sponsorDAO.findBySponsorId(sponsorId1);
//        if(sponsor1 != null) {
//            sponsor1.setName("Asheley");
//            sponsor1.setDescription("2222");
//            sponsorDAO.update(sponsor1);
//            Sponsor sponsor2 = sponsorDAO.findBySponsorId(sponsorId1);
//            System.out.println("update::"+ sponsor2.getName());
//        }

//
//        /***** 5. delete sponsor, should also delete sponsor id in player *****/
//        long sponsorId2 = Long.valueOf(3);
//        System.out.println("delete::" + sponsorDAO.delete(sponsorId2));


        /******************************  OpponentDAO test ******************************************/

//        /***** 1. insert test *****/
        long id1 = Long.valueOf(1); Player player1 = playerDAO.findByPlayerId(id1);
        long id2 = Long.valueOf(2); Player player2 = playerDAO.findByPlayerId(id2);
        long id3 = Long.valueOf(3); Player player3 = playerDAO.findByPlayerId(id3);
        long id4 = Long.valueOf(4); Player player4 = playerDAO.findByPlayerId(id4);
        long idX = Long.valueOf(19);Player playerX = playerDAO.findByPlayerId(idX);


//        System.out.println(player1.getOpponents().size());
//        for (Player p: player1.getOpponents()){
//            System.out.println(p.getId());
//        }

//        opponentDAO.insert(player1, player4); //insert
//        opponentDAO.insert(player1, player3); //insert

//        /***** 2. retrieve one test ****/
//        List<Player> players = opponentDAO.findOpponentsByPlayerId(idX);
//        if(players.size() != 0){
//            for(Player p : players){
//                System.out.println("Player List::" + p.getId() +", "+ p.getFirstname());
//            }
//        } else {
//            System.out.println("player "+idX+"'don't have opponents");
//        }

//        /***** 3. retrieve all test *****/

//
//        /***** 4. update test *****/

//
//        /***** 5. delete opponent, should delete pairs *****/
//        opponentDAO.delete(player1,player3);
//        opponentDAO.deleteP(player3);


        //close resources
        context.close();
    }
}
