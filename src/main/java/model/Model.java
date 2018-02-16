/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author drdea
 */
public abstract class Model  {
    public abstract boolean isValid();
    public JsonNode serializeToJson(ObjectMapper om) {
        return om.valueToTree(this);  
    }
    public String serializeToJsonString() {
        return serializeToJson(new ObjectMapper()).toString();
        
    }
    public void saveJsonToFile(File f, ObjectMapper om) throws IOException {
        om.writeValue(f, this);
        
    }
}
