/*
 Author: Christian Rivera
 NetID:  criver32
 UIN:    672524194
 
 CS 342 Homework 5
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;

public class GUI1 extends JFrame implements UserInterface {
    private JTextArea printBox;
    private JScrollBar scrollBar;
    private JScrollPane scrollPane;
    private JTextArea inveBox;
    private JTextArea textInput;
    private String output;
    private boolean ready;
    
    //--------------------------------------------------------------------------
    // Constructor
    public GUI1(String name) {
        this.setVisible(false);
        this.setTitle(name);
        this.setSize(900, 300);
        this.setResizable(true);
        this.setLocation(100,100);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        
        output = null;
        ready = false;
        
        ActionListener listener = createListener();
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(1,1,1,1);
        
        // Switch GUI buttons
        JPanel guiSwitch = new JPanel();
        guiSwitch.setLayout(new FlowLayout());
        
        JLabel label = new JLabel("This is GUI 1.  Switch to: ");
        guiSwitch.add(label);
        
        JButton b = new JButton("Text");
        b.addActionListener(listener);
        guiSwitch.add(b);
        
        b = new JButton("GUI 2");
        b.addActionListener(listener);
        guiSwitch.add(b);
        
        b = new JButton("GUI 3");
        b.addActionListener(listener);
        guiSwitch.add(b);
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = 0;
        c.weighty = 0;
        
        this.add(guiSwitch);
        
        // Display Box
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.gridheight = 10;
        c.weightx = 3;
        c.weighty = 3;
        
        printBox = new JTextArea();
        printBox.setEditable(false);
        
        scrollPane = new JScrollPane(printBox);
        scrollBar = scrollPane.getVerticalScrollBar();
        
        this.add(scrollPane, c);
        
        // Inventory Box
        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 10;
        c.weightx = 1;
        c.weighty = 3;
        
        inveBox = new JTextArea();
        JScrollPane inveScroll = new JScrollPane(inveBox);
        this.add(inveScroll, c);
        
        // Compass Buttons
        c.gridx = 0;
        c.gridy = 11;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = 0;
        c.weighty = 0;
        
        JPanel compass = addCompass(listener);
        this.add(compass, c);
        
        // Action Buttons
        c.gridx = 3;
        c.gridy = 11;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.weighty = 0;
        
        JPanel actions = addActions(listener);
        this.add(actions, c);
        
    }
    
    //--------------------------------------------------------------------------
    // Print to display textbox
    public void display(String str) {
        printBox.setCaretPosition(printBox.getDocument().getLength());
        printBox.replaceSelection(str + "\n");
        printBox.setCaretPosition(printBox.getDocument().getLength());
        printBox.validate();
    }
    
    //--------------------------------------------------------------------------
    // Return user input
    public String getLine() {
        if (ready) {
            ready = false;
            return output;
        }
        else {
            return "";
        }
    }
    
    //--------------------------------------------------------------------------
    // Update inventory display
    public void setInventory(ArrayList<String> inve, int carry, int limit) {
        inveBox.setText("Inventory: (" + carry + "/" + limit + ")\n");
        if (inve.size() < 1) {
            inveBox.setCaretPosition(inveBox.getDocument().getLength());
            inveBox.replaceSelection("-- Empty --");
            inveBox.revalidate();
            return;
        }
        for (String str : inve) {
            inveBox.setCaretPosition(inveBox.getDocument().getLength());
            inveBox.replaceSelection(" - " + str + "\n");
            inveBox.revalidate();
        }
    }
    
    //--------------------------------------------------------------------------
    // Add the action buttons to the frame
    private JPanel addActions(ActionListener l) {
        JPanel actions = new JPanel();
        actions.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
        JButton b;
        
        // Label
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.gridheight = 1;
        JLabel label = new JLabel("Enter artifact and select action:");
        actions.add(label, c);
        
        // Text input field
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 4;
        c.gridheight = 1;
        textInput = new JTextArea();
        actions.add(textInput, c);
        
        // Get button
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        b = new JButton("get");
        b.addActionListener(l);
        actions.add(b, c);
        
        // Use button
        c.gridx = 1;
        c.gridy = 2;
        b = new JButton("use");
        b.addActionListener(l);
        actions.add(b, c);
        
        // Drop button
        c.gridx = 2;
        c.gridy = 2;
        b = new JButton("drop");
        b.addActionListener(l);
        actions.add(b, c);
        
        // Look button
        c.gridx = 3;
        c.gridy = 2;
        b = new JButton("look");
        b.addActionListener(l);
        actions.add(b, c);
        
        return actions;
    }
    
    //--------------------------------------------------------------------------
    // Add the compass buttons to the frame
    private JPanel addCompass(ActionListener l) {
        // Create a GridBag JFrame for the compass
        JPanel compass = new JPanel();
        compass.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JButton wnw, nw, nnw, n, nne, ne, ene,
                w,      u,          d,      e,
                wsw, sw, ssw, s, sse, se, ese;
        
        // First row
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        wnw = new JButton("WNW");
        wnw.addActionListener(l);
        compass.add(wnw, c);
        
        c.gridx = 1;
        c.gridy = 0;
        nw = new JButton("NW");
        nw.addActionListener(l);
        compass.add(nw, c);
        
        c.gridx = 2;
        c.gridy = 0;
        nnw = new JButton("NNW");
        nnw.addActionListener(l);
        compass.add(nnw, c);
        
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        n = new JButton("N");
        n.addActionListener(l);
        compass.add(n, c);
        
        c.gridx = 5;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        nne = new JButton("NNE");
        nne.addActionListener(l);
        compass.add(nne, c);
        
        c.gridx = 6;
        c.gridy = 0;
        ne = new JButton("NE");
        ne.addActionListener(l);
        compass.add(ne, c);
        
        c.gridx = 7;
        c.gridy = 0;
        ene = new JButton("ENE");
        ene.addActionListener(l);
        compass.add(ene, c);
        
        // Second row
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        w = new JButton("W");
        w.addActionListener(l);
        compass.add(w, c);
        
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        u = new JButton("U");
        u.addActionListener(l);
        compass.add(u, c);
        
        c.gridx = 3;
        c.gridy = 1;
        compass.add(new JLabel("<<< COMPASS >>>"), c);
        
        c.gridx = 5;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        d = new JButton("D");
        d.addActionListener(l);
        compass.add(d, c);
        
        c.gridx = 6;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        e = new JButton("E");
        e.addActionListener(l);
        compass.add(e, c);
        
        // Third row
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        wsw = new JButton("WSW");
        wsw.addActionListener(l);
        compass.add(wsw, c);
        
        c.gridx = 1;
        c.gridy = 2;
        sw = new JButton("SW");
        sw.addActionListener(l);
        compass.add(sw, c);
        
        c.gridx = 2;
        c.gridy = 2;
        ssw = new JButton("SSW");
        ssw.addActionListener(l);
        compass.add(ssw, c);
        
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 1;
        s = new JButton("S");
        s.addActionListener(l);
        compass.add(s, c);
        
        c.gridx = 5;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        sse = new JButton("SSE");
        sse.addActionListener(l);
        compass.add(sse, c);
        
        c.gridx = 6;
        c.gridy = 2;
        se = new JButton("SE");
        se.addActionListener(l);
        compass.add(se, c);
        
        c.gridx = 7;
        c.gridy = 2;
        ese = new JButton("ESE");
        ese.addActionListener(l);
        compass.add(ese, c);
        return compass;
    }
    
    //--------------------------------------------------------------------------
    // Returns an action listener to handle all button press events
    private ActionListener createListener() {
        
        ActionListener listener = new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton) e.getSource();
                
                String bText = b.getText();
                String input = textInput.getText();
                
                if (bText.equalsIgnoreCase("get")
                    || bText.equalsIgnoreCase("use")
                    || bText.equalsIgnoreCase("drop"))
                {
                    textInput.setText("");
                    output = bText + " " + input;
                }
                else if (bText.equalsIgnoreCase("text")
                         || bText.equalsIgnoreCase("look"))
                {
                    output = bText;
                }
                else if (bText.equalsIgnoreCase("gui 2")) {
                    output = "gui2";
                }
                else if (bText.equalsIgnoreCase("gui 3")) {
                    output = "gui3";
                }
                else {
                    output = "go " + bText;
                }
                ready = true;
            }
            
        };
        
        return listener;
    }
}

