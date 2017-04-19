/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googlemapstestv1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Joep
 */
public class GoogleMapsTestv1 extends JFrame
{

    final Logger log = Logger.getLogger(GoogleMapsTestv1.class.getName());
    private JPanel contentPane;
    private GoogleMaps googleMap;

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        GoogleMapsTestv1 frame = new GoogleMapsTestv1();
        frame.setVisible(true);
    }

    /**
     * Create the frame.
     */
    public GoogleMapsTestv1()
    {
        setTitle("Map");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 592, 352);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        //JFrame test = new JFrame("Google Maps");

        googleMap = new GoogleMaps();
        
        googleMap.GenerateMap("Nieuwlandstraat 47b");
        
        JButton button = new JButton("Show Map");
        button.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(googleMap.MapAvailable())
                {
                    String imgDestinion = googleMap.GetMap();

                    ImageIcon imageIcon = new ImageIcon((new ImageIcon(imgDestinion)).getImage().getScaledInstance(630, 600, java.awt.Image.SCALE_SMOOTH));

                    contentPane.setLayout(null);
                    JLabel imgMap = new JLabel(imageIcon);
                    imgMap.setBounds(5, 5, 571, 308);
                    contentPane.add(imgMap);
                    contentPane.updateUI();
                    System.out.println("Do Something Clicked");
                }
            }
        });
        contentPane.add(button);
    }
}