/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dr
 */
public class HttpRequest {

    private final RequestType type;  // "GET"
    private final String protocol;  // "HTTP/1.1"
    private final String location;  // "/address"
    private final Map<String, String> params;
    public HttpRequest(RequestType type, String protocol, String location, Map<String, String> params) {
        this.type = type;
        this.protocol = protocol;
        this.location = location;
        this.params = params;
    }
    public String getLocation() {
        return location;
    }
    public Map<String, String> getParams() {
        return params;
    }
    public boolean hasParams() {
        return !params.isEmpty();
    }
    public static HttpRequest fromString(String incomingRequestString) {

        // GET /address HTTP/1.1
        // ==>  ["GET", "/address", "HTTP/1.1"]
        String[] components = incomingRequestString.split(" ");

        if (components.length!=3) {
            return null;
        }

        RequestType type = RequestType.valueOf(components[0]);
        if (!type.equals(RequestType.GET)) {
            return null;
        }
        String protocol = components[2];
        String urlString = components[1];
        Map<String, String> params = new HashMap<>();
        String[] urlParts = urlString.split("\\?");
        String location = urlParts[0];
        if (urlParts.length > 1) {            
            for (String pair : urlParts[1].split("&")) {
                String[] keyVal = pair.split("=");
                if (keyVal.length == 2) {
                    params.put(keyVal[0], keyVal[1]);
                }
            }
        }
        return new HttpRequest(type, protocol, location, params);
    }
}

