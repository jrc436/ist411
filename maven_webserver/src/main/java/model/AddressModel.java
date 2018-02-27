/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dr
 */
public class AddressModel extends Model {
    
    private final String name;
    private final String street;
    private final int zip;
    private final String state;
    
    public AddressModel(Map<String, String> params) {
        this.name = params.getOrDefault("name", "");
        this.street = params.getOrDefault("street", "");
        int zipCode = -1;
        try {
             zipCode = Integer.parseInt(params.getOrDefault("zip", "0"));
        } catch (java.lang.NumberFormatException e) {}
        this.zip = zipCode;
        this.state = params.getOrDefault("state", "");
    }
    public AddressModel(String name, String street, int zip, String state) {
        this.name = name;
        this.street = street;
        this.zip = zip;
        this.state = state;
    }
    public AddressModel() {
        this("", "", 0, "");
    }
    public String getName() {
        return name;
    }
    public String getStreet() {
        return street;
    }
    public int getZip() {
        return zip;
    }
    public String getState() {
        return state;
    }
    @Override
    public boolean isValid () {
        
        if (name.length()>1 && zip>0 && zip<99999)
        {
            if (street.length()>1 && state.length()==2)
            {
                return true;
            }
        }
        return false;
    }
    
    public String serializeToString () {
        String s = "";
        s+= "name="+name;
        s+= "\tstreet="+street;
        s+= "\tzipCode="+zip;
        s+= "\tstate="+state;
        return s;
    }
     public static AddressModel deserializeFromJson(ObjectMapper om, JsonNode node) throws JsonProcessingException {
        return om.treeToValue(node, AddressModel.class);
    }
     public static AddressModel readJsonFromFile(ObjectMapper om, File f) throws IOException {
        return om.readValue(f, AddressModel.class);
    }
    
    public static AddressModel deserializeFromString (String s) {
        // e.g., s = "name=Linda\tzipCode=10100"
        Map<String, String> params = new HashMap<>();
        for (String kv : s.split("\t")) {            
            // e.g., kv = "name=Linda"
            String[] nv = kv.split("=");
            params.put(nv[0], nv[1]);
                  
        }
        return new AddressModel(params);
    }
    
    
}
