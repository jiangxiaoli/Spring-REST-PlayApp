package edu.sjsu.cmpe275.lab3.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.sjsu.cmpe275.lab3.Http.SponsorAdapter;
import edu.sjsu.cmpe275.lab3.dao.OpponentDAO;
import edu.sjsu.cmpe275.lab3.dao.PlayerDAO;
import edu.sjsu.cmpe275.lab3.dao.SponsorDAO;
import edu.sjsu.cmpe275.lab3.model.Player;
import edu.sjsu.cmpe275.lab3.model.Sponsor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/opponent")
public class OpponentController {

    //get all dao beans
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    PlayerDAO playerDAO = (PlayerDAO) context.getBean("playerDAO");
    SponsorDAO sponsorDAO = (SponsorDAO) context.getBean("sponsorDAO");
    OpponentDAO opponentDAO = (OpponentDAO) context.getBean("opponentDAO");

    //1. add an opponent
    @RequestMapping(value = "/{playerId1}/{playerId2}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> addOpponent(@PathVariable String playerId1, @PathVariable String playerId2) {

        Player player1 = playerDAO.findByPlayerId(Long.valueOf(playerId1));
        Player player2 = playerDAO.findByPlayerId(Long.valueOf(playerId2));

        if(player1 == null || player2 == null){
            String result = new Gson().toJson("Player not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        } else {
            try {
                opponentDAO.insert(player1, player2);
                String result = new Gson().toJson("Create Opponent: player-" + playerId1 + " with player-" + playerId2 + " succeed!!");
                return new ResponseEntity<String>(result, HttpStatus.OK);

            } catch (RuntimeException e) {
                String result = new Gson().toJson("Fail to insert opponent");
                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
            }
        }
    }

    //2. remove an opponent
    @RequestMapping(value = "/{playerId1}/{playerId2}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> removeOpponent(@PathVariable String playerId1, @PathVariable String playerId2) {

        Player player1 = playerDAO.findByPlayerId(Long.valueOf(playerId1));
        Player player2 = playerDAO.findByPlayerId(Long.valueOf(playerId2));

        if(player1 == null || player2 == null){
            String result = new Gson().toJson("Player not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        } else {
            try {
                opponentDAO.delete(player1,player2);
                String result = new Gson().toJson("Delete Opponent: player-"+playerId1 +" with player-"+playerId2 +" succeed!!");
                return new ResponseEntity<String>(result, HttpStatus.OK);

            } catch (RuntimeException e){
                String result = new Gson().toJson("Fail to delete opponent");
                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
            }
        }

    }
}
