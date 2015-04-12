package edu.sjsu.cmpe275.lab3.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.sjsu.cmpe275.lab3.Http.PlayerAdapter;
import edu.sjsu.cmpe275.lab3.Http.SponsorAdapter;
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
@RequestMapping("/sponsors")
public class SponsorController {

    //get all dao beans
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    PlayerDAO playerDAO = (PlayerDAO) context.getBean("playerDAO");
    SponsorDAO sponsorDAO = (SponsorDAO) context.getBean("sponsorDAO");
    OpponentDAO opponentDAO = (OpponentDAO) context.getBean("opponentDAO");

    //1. POST Create a sponsor
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createSponsor(
            @RequestParam(value="name", required = true) String name,
            @RequestParam(value="description", required = false) String description,
            @RequestParam(value="street", required = false) String street,
            @RequestParam(value="city", required = false) String city,
            @RequestParam(value="state", required = false) String state,
            @RequestParam(value="zip", required = false) String zip) {

        Sponsor sponsor = new Sponsor(name);
        sponsor.setDescription(description);

        //add address
        Address add = new Address(street, city, state, zip);
        sponsor.setAddress(add);

        //gson to build and map sponsor class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Sponsor.class, new SponsorAdapter()).create();

        try {
            sponsorDAO.insert(sponsor);
            System.out.println("Created a new sponsor::" + sponsor.getId());
            String result = gson.toJson(sponsor);
            return new ResponseEntity<String>(result, HttpStatus.OK);
        } catch (RuntimeException e) {
            System.out.println("fail to create sponsor");
            String result = new Gson().toJson("Fail to create sponsor");
            return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
        }

    }

    //2. GET get a sponsor
    @RequestMapping(value = "/{sponsorId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> showSponsor(@PathVariable String sponsorId) {
        System.out.println(sponsorId);
        Sponsor sponsor = sponsorDAO.findBySponsorId(Long.parseLong(sponsorId));

        //gson to build and map player class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Sponsor.class, new SponsorAdapter()).create();

        if(sponsor != null){
            System.out.println("Show sponsor details::" + sponsor.getId());
            String result = gson.toJson(sponsor);
            return new ResponseEntity<String>(result, HttpStatus.OK);
        }else{
            String result = new Gson().toJson("Sponsor-" + sponsorId + " not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }
    }

    //3. POST Update a sponsor
    @RequestMapping(value = "/{sponsorId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> updateSponsor(
            @PathVariable String sponsorId,
            @RequestParam(value="name", required = true) String name,
            @RequestParam(value="description", required = false) String description,
            @RequestParam(value="street", required = false) String street,
            @RequestParam(value="city", required = false) String city,
            @RequestParam(value="state", required = false) String state,
            @RequestParam(value="zip", required = false) String zip) {

        Sponsor sponsor = sponsorDAO.findBySponsorId(Long.valueOf(sponsorId));
        //gson to build and map player class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Sponsor.class, new SponsorAdapter()).create();

        if(sponsor != null){
            sponsor.setName(name);
            sponsor.setDescription(description);

            //add address
            Address add = new Address(street, city, state, zip);
            sponsor.setAddress(add);

            try {
                sponsorDAO.update(sponsor);
                System.out.println("Sponsor-"+ sponsorId +" Updated!!");
                String result = gson.toJson(sponsor);
                return new ResponseEntity<String>(result, HttpStatus.OK);
            } catch (Exception e){
                String result = new Gson().toJson("Fail to update Sponsor-" + sponsorId);
                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
            }

        } else {
            String result = new Gson().toJson("Sponsor-" + sponsorId + " not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }

    }

    //4. DELETE delete a sponsor
    @RequestMapping(value = "/{sponsorId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteSponsor(@PathVariable String sponsorId) {
        System.out.println(sponsorId);
        Sponsor sponsor = sponsorDAO.findBySponsorId(Long.parseLong(sponsorId));

        //gson to build and map player class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Sponsor.class, new SponsorAdapter()).create();

        if(sponsor != null){
            try {
                sponsorDAO.delete(Long.valueOf(sponsorId));
                System.out.println("Sponsor-"+ sponsorId +" deleted !!");
                String result = gson.toJson(sponsor);
                return new ResponseEntity<String>(result, HttpStatus.OK);
            } catch (RuntimeException e){
                String result = new Gson().toJson("Fail to delete Sponsor-"+ sponsorId +"!!");
                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
            }

        } else {
            String result = new Gson().toJson("Sponsor-"+ sponsorId +" not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }
    }


    //5. GET get sponsors
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> showSponsors() {
        List<Sponsor> sponsors = sponsorDAO.allSponsors();

        //gson to build and map player class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Player.class, new SponsorAdapter()).create();

        if(sponsors != null){

            for(Sponsor s: sponsors) {
                System.out.println("Show sponsor details::" + s.getId());

            }

            String result = gson.toJson(sponsors);

            return new ResponseEntity<String>(result, HttpStatus.OK);
        }else{
            //no player
            String result = new Gson().toJson("No sponsor found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }
    }
}
