package edu.sjsu.cmpe275.lab3.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.sjsu.cmpe275.lab3.Http.PlayerAdapter;
import edu.sjsu.cmpe275.lab3.dao.OpponentDAO;
import edu.sjsu.cmpe275.lab3.dao.PlayerDAO;
import edu.sjsu.cmpe275.lab3.dao.SponsorDAO;
import edu.sjsu.cmpe275.lab3.model.Address;
import edu.sjsu.cmpe275.lab3.model.Player;
import edu.sjsu.cmpe275.lab3.model.Sponsor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/players")
public class PlayerController {

    //get all dao beans
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    PlayerDAO playerDAO = (PlayerDAO) context.getBean("playerDAO");
    SponsorDAO sponsorDAO = (SponsorDAO) context.getBean("sponsorDAO");
    OpponentDAO opponentDAO = (OpponentDAO) context.getBean("opponentDAO");

    //1. POST Create a player
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createPlayer(
            @RequestParam(value="firstname", required = true) String firstname,
            @RequestParam(value="lastname", required = true) String lastname,
            @RequestParam(value="email", required = true) String email,
            @RequestParam(value="description", required = false) String description,
            @RequestParam(value="street", required = false) String street,
            @RequestParam(value="city", required = false) String city,
            @RequestParam(value="state", required = false) String state,
            @RequestParam(value="zip", required = false) String zip,
            @RequestParam(value="Sponsor_idSponsor", required = false) String Sponsor_idSponsor) {

        System.out.println();

        Player player = new Player(firstname, lastname, email);
        player.setDescription(description);

        //add address
        Address add = new Address(street, city, state, zip);
        player.setAddress(add);

        //add sponsor
        if(Sponsor_idSponsor != null){
            Sponsor sponsor = sponsorDAO.findBySponsorId(Long.parseLong(Sponsor_idSponsor));
            if(sponsor != null){
                player.setSponsor(sponsor);
            } else {
                System.out.println("Sponsor-"+Sponsor_idSponsor+" not found!");
                String result = new Gson().toJson("Fail to create Player: Sponsor-"+Sponsor_idSponsor+" not found!");
                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
            }
        }

        //gson to build and map player class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Player.class, new PlayerAdapter()).create();

        try {
            playerDAO.insert(player);
            System.out.println("Created a new player::" + player.getId());
            String result = gson.toJson(player);
            return new ResponseEntity<String>(result, HttpStatus.OK);
        } catch (RuntimeException e){
            System.out.println("fail to create player");
            String result = new Gson().toJson("Fail to create Player");
            return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
        }
    }

    //2. GET get a player
    @RequestMapping(value = "/{playerId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> showPlayer(@PathVariable String playerId) {
        Player player = playerDAO.findByPlayerId(Long.parseLong(playerId));

        //gson to build and map player class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Player.class, new PlayerAdapter()).create();

        if(player != null){
            System.out.println("Show player details::" + player.getId());
            String result = gson.toJson(player);
            return new ResponseEntity<String>(result, HttpStatus.OK);
        }else{
            //player not found
            String result = new Gson().toJson("Player-" + playerId + " not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }
    }

    //3. POST Update a player
    @RequestMapping(value = "/{playerId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> updatePlayer(
            @PathVariable String playerId,
            @RequestParam(value="firstname", required = true) String firstname,
            @RequestParam(value="lastname", required = true) String lastname,
            @RequestParam(value="email", required = true) String email,
            @RequestParam(value="description", required = false) String description,
            @RequestParam(value="street", required = false) String street,
            @RequestParam(value="city", required = false) String city,
            @RequestParam(value="state", required = false) String state,
            @RequestParam(value="zip", required = false) String zip,
            @RequestParam(value="Sponsor_idSponsor", required = false) String Sponsor_idSponsor) {



        Player player = playerDAO.findByPlayerId(Long.valueOf(playerId));

        if(player != null){
            player.setFirstname(firstname);
            player.setLastname(lastname);
            player.setEmail(email);
            player.setDescription(description);

            //add address
            Address add = new Address(street, city, state, zip);
            player.setAddress(add);

            //add sponsor
            if(Sponsor_idSponsor != null){
                Sponsor sponsor = sponsorDAO.findBySponsorId(Long.parseLong(Sponsor_idSponsor));
                if(sponsor != null){
                    player.setSponsor(sponsor);
                } else {
                    System.out.println("Sponsor-"+Sponsor_idSponsor+" not found!");
                    String result = new Gson().toJson("Fail to update Player-" + playerId+": Sponsor-"+Sponsor_idSponsor+" not found!");
                    return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
                }
            }

            //gson to build and map player class
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.registerTypeAdapter(Player.class, new PlayerAdapter()).create();

            try {
                playerDAO.update(player);
                System.out.println("player-"+playerId+" Updated!!");
                String result = gson.toJson(player);
                return new ResponseEntity<String>(result, HttpStatus.OK);
            } catch (Exception e){
                String result = new Gson().toJson("Fail to update Player-" + playerId);
                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
            }

        } else {
            String result = new Gson().toJson("Player-" + playerId + " not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }

    }

    //4. DELETE delete a player
    @RequestMapping(value = "/{playerId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deletePlayer(@PathVariable String playerId) {
        Player player = playerDAO.findByPlayerId(Long.parseLong(playerId));

        //gson to build and map player class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Player.class, new PlayerAdapter()).create();

        if(player != null){
            try {
                opponentDAO.deleteP(player); //delete opponent first
                playerDAO.delete(Long.valueOf(playerId)); //delete player
                System.out.println("player-"+playerId+" deleted !!");
                String result = gson.toJson(player);
                return new ResponseEntity<String>(result, HttpStatus.OK);
            } catch (RuntimeException e){
                String result = new Gson().toJson("Fail to delete player- "+playerId+"!");
                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
            }

        } else {
            String result = new Gson().toJson("Player-"+playerId+" not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }
    }


    //5. GET get players
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> showPlayers() {
        List<Player> players = playerDAO.allPlayers();

        //gson to build and map player class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Player.class, new PlayerAdapter()).create();

        if(players != null){

            for(Player player: players) {
                System.out.println("Show players details::" + player.getId());

            }

            String result = gson.toJson(players);

            return new ResponseEntity<String>(result, HttpStatus.OK);
        }else{
            //no player
            String result = new Gson().toJson("No player found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }
    }

}
