package edu.sjsu.cmpe275.lab3.Http;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import edu.sjsu.cmpe275.lab3.model.Address;
import edu.sjsu.cmpe275.lab3.model.Sponsor;

import java.lang.reflect.Type;

//used for gson serializer on sponsor class
public class SponsorAdapter implements JsonSerializer<Sponsor> {
    @Override
    public JsonElement serialize(Sponsor sponsor, Type type, JsonSerializationContext jsc) {
        //parse sponsor infp
        JsonObject sponsorObj = new JsonObject();
        sponsorObj.addProperty("sponsor_id", sponsor.getId());
        sponsorObj.addProperty("name", sponsor.getName());
        sponsorObj.addProperty("description", sponsor.getDescription());

        //mapping address
        Address address_s = sponsor.getAddress();
        if(address_s != null){
            JsonObject sponsorAddressObj = new JsonObject();
            sponsorAddressObj.addProperty("street", address_s.getStreet());
            sponsorAddressObj.addProperty("city", address_s.getCity());
            sponsorAddressObj.addProperty("state", address_s.getState());
            sponsorAddressObj.addProperty("zip", address_s.getZip());
            sponsorObj.add("address", sponsorAddressObj);
        }
        return sponsorObj;
    }
}

