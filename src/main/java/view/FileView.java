/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dr
 */
public class FileView extends View {
    
    Path path;
    int status = 0;
    
    public FileView (String location)
    {
        if (location.equals("/") || location.equals(""))
        {
            location = "/index.html";
        }
        path = Paths.get("public" + location);
        if (Files.exists(path))
        {
        } else {
            status = -1;
        }
    }

    public boolean isOKStatus () {
        return (status==0);
    }
    
    public String getType () {
        
        if (path.toString().endsWith(".jpeg"))
        {
            return "image/jpeg";
        }
        else if (path.toString().endsWith(".png")) {
            return "image/png";
        }
        return "text/html";
    }
    
    public byte[] makeBytes () {
        
        try {
            return Files.readAllBytes(path);
        } catch (IOException ex) {
            Logger.getLogger(FileView.class.getName()).log(Level.SEVERE, null, ex);
            return new byte[0];
        }
    }
    
    @Override
    public String makeHTML() {
    
        try {
            return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            return "File could not be read.";
    //            Logger.getLogger(FileView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
