/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googlemapstestv1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author Joep
 */
public class GoogleMaps
{
    final Logger log = Logger.getLogger(GoogleMaps.class.getName());
    
    private boolean mapAvailable = false;
    private String mapDestinationFile;
    
    public GoogleMaps()
    {
        
    }
    
    public void GenerateMap(String location)
    {
        mapAvailable = false;
        
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try 
                {
                    /*
                    
                    */
                    Scanner sc = new Scanner(location);
                    Scanner sc2 = new Scanner(location);

                    String marker = "";

                    //String path = JOptionPane.showInputDialog("what is your destination?");
                    //String zoom = JOptionPane.showInputDialog("how far in do you want to zoom?\n" + "12(zoomed out) - 20 (zoomed in)");

                    String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?";
                    while (sc.hasNext())
                    {
                        // add location to imageUrl
                        imageUrl = imageUrl + sc.next();
                    }

                    marker = "&markers=color:red|";

                    while (sc2.hasNext())
                    {
                        // add marker location to marker
                        marker = marker + sc2.next() + ",";
                    }

                    marker = marker.substring(0, marker.length() - 1);
                    imageUrl = imageUrl + "&size=620x620&scale=2&maptype=hybrid" + marker;

                    log.info("Generated url");

                    mapDestinationFile = "image.jpg";

                    // read the map image from Google
                    // then save it to a local file: image.jpg
                    URL url = new URL(imageUrl);
                    InputStream is = url.openStream();
                    OutputStream os = new FileOutputStream(mapDestinationFile);

                    byte[] b = new byte[2048];
                    int length;

                    while ((length = is.read(b)) != -1)
                    {
                        os.write(b, 0, length);
                    }

                    log.info("Created image.jpg");

                    is.close();
                    os.close();
                    sc.close();
                    sc2.close();
                    log.info("Closed util's");
                    /*
                    
                    */
                    mapAvailable = true;
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    log.severe("Could not create image.jpg");
                }// fin getting and storing image
            }
        }).start();
    }
    
    public String GetMap()
    {
        return mapDestinationFile;
    }
    
    public boolean MapAvailable()
    {
        return mapAvailable;
    }
}
