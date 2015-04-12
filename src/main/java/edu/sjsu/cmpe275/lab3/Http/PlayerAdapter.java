package edu.sjsu.cmpe275.lab3.Http;

import com.google.gson.*;
import edu.sjsu.cmpe275.lab3.model.Address;
import edu.sjsu.cmpe275.lab3.model.Player;
import edu.sjsu.cmpe275.lab3.model.Sponsor;

import java.lang.reflect.Type;
import java.util.List;

//used for gson serializer on player class
public class PlayerAdapter implements JsonSerializer<Player> {

    @Override
    public JsonElement serialize(Player player, Type type, JsonSerializationContext jsc) {
        //parse player info
        JsonObject playerObj = new JsonObject();
        playerObj.addProperty("player_id", player.getId());
        playerObj.addProperty("firstname", player.getFirstname());
        playerObj.addProperty("lastname", player.getLastname());
        playerObj.addProperty("email", player.getEmail());
        playerObj.addProperty("description", player.getDescription());

        //parse player address
        Address address_p = player.getAddress();
        if(address_p != null){
            JsonObject playerAddressObj = new JsonObject();
            playerAddressObj.addProperty("street", player.getAddress().getStreet());
            playerAddressObj.addProperty("city", player.getAddress().getCity());
            playerAddressObj.addProperty("state", player.getAddress().getState());
            playerAddressObj.addProperty("zip", player.getAddress().getZip());
            playerObj.add("address", playerAddressObj);
        }

        //mapping sponsor
        Sponsor sponsor = player.getSponsor();
        if(sponsor != null){
            JsonObject sponsorObj = new JsonObject();
            sponsorObj.addProperty("sponsor_id", sponsor.getId());
            sponsorObj.addProperty("name", sponsor.getName());
            sponsorObj.addProperty("description", sponsor.getDescription());
            //parse sponsor address
            Address address_s = sponsor.getAddress();
            if(address_s != null){
                JsonObject sponsorAddressObj = new JsonObject();
                sponsorAddressObj.addProperty("street", address_s.getStreet());
                sponsorAddressObj.addProperty("city", address_s.getCity());
                sponsorAddressObj.addProperty("state", address_s.getState());
                sponsorAddressObj.addProperty("zip", address_s.getZip());
                sponsorObj.add("address", sponsorAddressObj);
            }
            playerObj.add("sponsor", sponsorObj);
        }

        //mapping opponents - as array
        List<Player> opponents = player.getOpponents();
        if(opponents != null) {
            JsonArray playerListArr = new JsonArray();
            for(Player p : opponents){
                JsonObject playerItemObj = new JsonObject();
                playerItemObj.addProperty("player_id", String.valueOf(p.getId()));
                playerItemObj.addProperty("firstname", p.getFirstname());
                playerItemObj.addProperty("lastname", p.getLastname());
                playerListArr.add(playerItemObj);
            }
            playerObj.add("opponents", playerListArr);
        }

        return playerObj;
    }
}

