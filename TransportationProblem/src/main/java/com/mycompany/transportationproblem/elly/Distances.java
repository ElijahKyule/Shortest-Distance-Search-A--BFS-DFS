/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.transportationproblem.elly;

import static com.mycompany.transportationproblem.elly.Graph.log3;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.util.*;

/**
 *
 * @author elijah
 */


class Distances 
    extends JFrame
       implements ActionListener 
{ 
    //variables and jcomponents declaration
    private final Container container; 
    private final JLabel title; 
    private final JLabel sourceTown;
    private final JLabel destinationTown;
    private final JLabel method; 
    private final JLabel map_image;
    private final JLabel cities;
    private final JLabel output; 
    private final JComboBox itsSourceTown;
    private final JComboBox itsDestinationTown;
    private final JRadioButton itsA_search;
    private final JRadioButton itsBreadthFirst;
    private final JRadioButton itsDepthFirst;
    private final ButtonGroup chooser; 
    private final JButton search;
    private final JButton reset; 
    private final JTextArea itsOutput; 
    private final JPanel itsMap2;
    private final ImageIcon background;
    private static final String[] towns = {"Nairobi", "Mombasa", "Kisumu", "Meru",
   "Garissa"};
    private static String source, destination, logs = "", optimization_method = "";
    private int sourcetwn, dest;
  
    public Distances() 
    { 
        setTitle("Elly Routes"); 
        setBounds(200, 90, 1040, 600); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setResizable(false); 
  
        container = getContentPane(); 
        container.setLayout(null); 
        
        title = new JLabel("Route Optimizer"); 
        title.setFont(new Font("Arial", Font.PLAIN, 30)); 
        title.setSize(300, 30); 
        title.setLocation(400, 30); 
        container.add(title); 
  
        sourceTown = new JLabel("Select Source Town: "); 
        sourceTown.setFont(new Font("Arial", Font.PLAIN, 15)); 
        sourceTown.setSize(200, 20); 
        sourceTown.setLocation(100, 100); 
        container.add(sourceTown); 
        
        itsSourceTown = new JComboBox(towns); 
        itsSourceTown.setFont(new Font("Arial", Font.PLAIN, 15)); 
        itsSourceTown.setSize(150, 20); 
        itsSourceTown.setLocation(240, 100); 
        container.add(itsSourceTown); 
  
        destinationTown = new JLabel("Select Destination Town: "); 
        destinationTown.setFont(new Font("Arial", Font.PLAIN, 15)); 
        destinationTown.setSize(200, 20); 
        destinationTown.setLocation(410, 100); 
        container.add(destinationTown); 
        
        itsDestinationTown = new JComboBox(towns); 
        itsDestinationTown.setFont(new Font("Arial", Font.PLAIN, 15)); 
        itsDestinationTown.setSize(150, 20); 
        itsDestinationTown.setLocation(580, 100); 
        container.add(itsDestinationTown); 
        
  
        method = new JLabel("Optimization Method: "); 
        method.setFont(new Font("Arial", Font.PLAIN, 15)); 
        method.setSize(200, 20); 
        method.setLocation(100, 140); 
        container.add(method);
        
        itsA_search = new JRadioButton ("A* Algorithm", true); 
        itsA_search.setFont(new Font("Arial", Font.PLAIN, 15)); 
        itsA_search.setSize(120, 20); 
        itsA_search.setLocation(240, 140); 
        container.add(itsA_search);
        
        
        itsBreadthFirst = new JRadioButton ("BreadthFirst Search", true); 
        itsBreadthFirst.setFont(new Font("Arial", Font.PLAIN, 15)); 
        itsBreadthFirst.setSize(160, 20); 
        itsBreadthFirst.setLocation(357, 140); 
        container.add(itsBreadthFirst);
        
        itsDepthFirst = new JRadioButton ("DepthFirst Search", true); 
        itsDepthFirst.setFont(new Font("Arial", Font.PLAIN, 15)); 
        itsDepthFirst.setSize(200, 20); 
        itsDepthFirst.setLocation(520, 140); 
        container.add(itsDepthFirst); 
  
        
        //button group for radio buttons
        chooser = new ButtonGroup();  
        chooser.add(itsA_search);
        chooser.add(itsBreadthFirst);
        chooser.add(itsDepthFirst);
  
        search = new JButton("Search"); 
        search.setFont(new Font("Arial", Font.PLAIN, 15)); 
        search.setSize(100, 25); 
        search.setLocation(100, 180); 
        search.addActionListener(this); 
        container.add(search); 
  
        reset = new JButton("Reset"); 
        reset.setFont(new Font("Arial", Font.PLAIN, 15)); 
        reset.setSize(100, 25); 
        reset.setLocation(220, 180); 
        reset.addActionListener(this); 
        container.add(reset); 
        
        map_image = new JLabel("Kenyan Cities: "); 
        map_image.setFont(new Font("Arial", Font.PLAIN, 15)); 
        map_image.setSize(200, 20); 
        map_image.setLocation(100, 240); 
        container.add(map_image);
        
        background=new ImageIcon("cities.png");
        cities = new JLabel(background);
        cities.setSize(background.getIconWidth(),background.getIconHeight());
        cities.setLocation(100, 270);
        itsMap2 = (JPanel)this.getContentPane();
        itsMap2.setOpaque(false);
        this.getLayeredPane().add(cities,new Integer(Integer.MIN_VALUE));
        
        output = new JLabel("Output Panel: "); 
        output.setFont(new Font("Arial", Font.PLAIN, 15)); 
        output.setSize(200, 20); 
        output.setLocation(720, 240); 
        container.add(output);
        
        itsOutput = new JTextArea(); 
        itsOutput.setFont(new Font("Arial", Font.PLAIN, 15)); 
        itsOutput.setSize(330, 290); 
        itsOutput.setLocation(720, 270); 
        itsOutput.setLineWrap(true); 
        itsOutput.setEditable(false); 
        container.add(itsOutput); 
        
        
        setVisible(true); 
    } 
    

    
    @Override
    public void actionPerformed(ActionEvent e) 
    { 
        //submit button clicked
        if (e.getSource() == search) 
        {
            source = (String)itsSourceTown.getSelectedItem();
            destination = (String)itsDestinationTown.getSelectedItem();
            if (source.equals(destination))
            {
                logs = " ERROR: Source and Destination towns can't be    the same";
                itsOutput.setText(logs);
                itsOutput.setEditable(false);  
            }
            else
            {
                int V = 5;  
                int sourcetwn = 0;
                int dest = 0;
                if(("Nairobi".equals(source))){sourcetwn = 0;}
                else if(("Kisumu".equals(source))){sourcetwn = 1;}
                else if(("Meru".equals(source))){sourcetwn = 2;}
                else if(("Garissa".equals(source))){sourcetwn = 3;}
                else if(("Mombasa".equals(source))){sourcetwn = 4;}
                
                if(("Nairobi".equals(destination))){dest = 0;}
                else if(("Kisumu".equals(destination))){dest = 1;}
                else if(("Meru".equals(destination))){dest = 2;}
                else if(("Garissa".equals(destination))){dest = 3;}
                else if(("Mombasa".equals(destination))){dest = 4;}
                
                Graph kenyaCities = new Graph(V); 
                kenyaCities.addEdge(0, 1, 357);
                kenyaCities.addEdge(0, 2, 225);
                kenyaCities.addEdge(0, 3, 373);
                kenyaCities.addEdge(0, 4, 484);
                kenyaCities.addEdge(1, 0, 357);
                kenyaCities.addEdge(2, 0, 225);
                kenyaCities.addEdge(3, 0, 373);
                kenyaCities.addEdge(4, 0, 484);
                kenyaCities.addEdge(1, 2, 443);
                kenyaCities.addEdge(2, 1, 443);
                kenyaCities.addEdge(2, 3, 408);
                kenyaCities.addEdge(3, 2, 408);
                kenyaCities.addEdge(3, 4, 462);
                kenyaCities.addEdge(4, 3, 462);

                if(itsA_search.isSelected())
                {
                    optimization_method = "A* Search";

                    List<List<Node> > adj = new ArrayList<List<Node> >();

                    for (int i = 0; i < V; i++) {
                        List<Node> item = new ArrayList<Node>();
                        adj.add(item);
                    }
                    adj.get(0).add(new Node(1, 357));
                    adj.get(0).add(new Node(2, 225));
                    adj.get(0).add(new Node(3, 373));
                    adj.get(0).add(new Node(4, 484));
                    adj.get(1).add(new Node(0, 357));
                    adj.get(1).add(new Node(2, 443));
                    adj.get(2).add(new Node(0, 225));
                    adj.get(2).add(new Node(1, 443));
                    adj.get(2).add(new Node(3, 408));
                    adj.get(3).add(new Node(0, 373));
                    adj.get(3).add(new Node(2, 408));
                    adj.get(3).add(new Node(4, 462));
                    adj.get(4).add(new Node(0, 484));
                    adj.get(4).add(new Node(3, 462));

                    // Calculate the single source shortest path
                    DPQ dpq = new DPQ(V);
                    dpq.a_search(adj, sourcetwn);
                    
                    String Route = "";
                    int length = dpq.dist[dest];
                    if((("Kisumu".equals(source))&&("Meru".equals(destination)))||(("Meru".equals(source))&&("Kisumu".equals(destination)))){Route = "A";}
                    else if((("Meru".equals(source))&&("Garissa".equals(destination)))||(("Garissa".equals(source))&&("Meru".equals(destination)))){Route = "B";}
                    else if((("Garissa".equals(source))&&("Mombasa".equals(destination)))||(("Mombasa".equals(source))&&("Garissa".equals(destination)))){Route = "C";}
                    else if(("Kisumu".equals(source))&&("Mombasa".equals(destination))){Route = "G -> D";}
                    else if(("Mombasa".equals(source))&&("Kisumu".equals(destination))){Route = "D -> G";}
                    else if((("Nairobi".equals(source))&&("Mombasa".equals(destination)))||(("Mombasa".equals(source))&&("Nairobi".equals(destination)))){Route = "D";}
                    else if(("Meru".equals(source))&&("Mombasa".equals(destination))){Route = "F -> D";}
                    else if(("Mombasa".equals(source))&&("Meru".equals(destination))){Route = "D -> F";} 
                    else if((("Nairobi".equals(source))&&("Garissa".equals(destination)))||(("Garissa".equals(source))&&("Nairobi".equals(destination)))){Route = "E";}
                    else if((("Nairobi".equals(source))&&("Meru".equals(destination)))||(("Meru".equals(source))&&("Nairobi".equals(destination)))){Route = "F";}
                    else if((("Nairobi".equals(source))&&("Kisumu".equals(destination)))||(("Kisumu".equals(source))&&("Nairobi".equals(destination)))){Route = "G";}
                    else if(("Kisumu".equals(source))&&("Garissa".equals(destination))){Route = "G -> E";}
                    else if(("Garissa".equals(source))&&("Kisumu".equals(destination))){Route = "E -> G";}
                   
                    logs = " SOURCE TOWN: "+source+"\n DESTINATION TOWN: "+destination+
                            "\n OPTIMAL ROUTE: "+Route+" \n OPTIMAL DISTANCE: "+length+" KM \n OPTIMIZATION METHOD: "+optimization_method;
                    itsOutput.setText(logs);
                    itsOutput.setEditable(false);
                    
                }
                else if(itsBreadthFirst.isSelected())
                {
                    optimization_method = "BreadthFirst Search";
                    kenyaCities.BFS(sourcetwn,dest); 
                    logs = " SOURCE TOWN: "+source+"\n DESTINATION TOWN: "+destination+ "\n"+log3+" SEARCH METHOD: "+optimization_method;
                    itsOutput.setText(logs);
                    itsOutput.setEditable(false);
                }
                else if(itsDepthFirst.isSelected())
                {
                    optimization_method = "DepthFirst Search";
                    kenyaCities.DFS(sourcetwn,dest);
                    logs = " SOURCE TOWN: "+source+"\n DESTINATION TOWN: "+destination+ "\n"+log3+" SEARCH METHOD: "+optimization_method;
                    itsOutput.setText(logs);
                    itsOutput.setEditable(false);
                }
            }
            
        } 
        
        else if (e.getSource() == reset) 
        { 
            source = "";
            destination = "";
            optimization_method = "";
            logs = " Reset Complete!";
            itsOutput.setText(logs);
            itsOutput.setEditable(false);
        } 
    }
    
    public static void main(String[] args) throws Exception 
    { 
        Distances distance = new Distances();
        distance.setSize(1100,650);
        
    } 
} 