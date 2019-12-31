/*
 * ==========================
 * 
 * CS 342 | HW 5 | GUI #2
 * 
 * AUTHOR: Osama Ahmad
 * UIN:    663191463
 * NETID:  oahmad7
 * 
 * ==========================
 */

import java.awt.*;
import static java.lang.Thread.sleep;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class GUI2 extends JFrame implements UserInterface{
	
	private String buffer;
	private boolean bufferReady;
	private JTextArea textArea;
	private JTextArea inventory;
	private JTextField textField;
	
	public GUI2(String name){

		JPanel mainPanel = new JPanel();
		this.getContentPane().add(mainPanel);
		
		// basic setup of buffer and boolean
		buffer = null;
		bufferReady = false;
        ActionListener listener = makeListener();

		// Buttons to switch UI
		JLabel label = new JLabel("Choose Another UI: ");
		label.setFont(new Font("Verdana", Font.BOLD, 12));
		mainPanel.add(label);
		
		JButton button1 = new JButton("Text");
		button1.setFont(new Font("Verdana", Font.BOLD, 12));
		button1.addActionListener(listener);
		
		JButton button2 = new JButton("GUI 1");
		button2.setFont(new Font("Verdana", Font.BOLD, 12));
		button2.addActionListener(listener);
		
		JButton button3 = new JButton("GUI 3");
		button3.setFont(new Font("Verdana", Font.BOLD, 12));
		button3.addActionListener(listener);
		
		mainPanel.add(button1);
		mainPanel.add(button2);
		mainPanel.add(button3);
		// =========================================
		
		// text Area for console information
		textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(400,450));
		textArea.setFont(new Font("Verdana", Font.BOLD, 14));
		//textArea.setBa
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
				
		JScrollPane scrollPane = new JScrollPane(textArea);
		mainPanel.add(scrollPane);
		// =========================================
		
		// text area for inventory
		inventory = new JTextArea();
		inventory.setPreferredSize(new Dimension(200,450));
		inventory.setFont(new Font("Verdana", Font.BOLD, 12));
		inventory.setEditable(false);
		inventory.setLineWrap(true);
		inventory.setWrapStyleWord(true);
		
		JScrollPane inventoryScrollPane = new JScrollPane(inventory);
		mainPanel.add(inventoryScrollPane, "East");
		// =========================================
		
		// Label and Command Line
		label = new JLabel("Command Line: ");
		mainPanel.add(label);
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(500,25));
		label.setFont(new Font("Verdana", Font.BOLD, 12));
		mainPanel.add(textField);
		
		//set this size
		this.setSize(new Dimension(700,575));
		//set start position
		this.setLocationRelativeTo(null);
		//set default close action
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set a title
		this.setTitle(name);
		//disable resize
		this.setResizable(false);
		//frame.pack();
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);	
		
		// Read from pseudo command line and print into actual command line
		textField.addActionListener(e->{
			buffer = textField.getText();
			bufferReady= true;
			textField.setText("");
			textField.requestFocus();
		});
		mainPanel.setBackground(Color.lightGray);
	}

	@Override
	public void display(String str) {
		// Display information to textArea (pseudo-console)
		textArea.setCaretPosition(textArea.getDocument().getLength());
		textArea.replaceSelection(str + "\n");
		textArea.revalidate();
	}
	
	@Override
    public String getLine() {
		// taken straight from professor's code in lecture
        while(!bufferReady) {
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }        
        String s;
        synchronized(buffer) {
        	s = new String(buffer);
            bufferReady = false;

        }
        return s;
    }

	@Override
	public void setInventory(ArrayList<String> inve, int carry, int limit) {
		// Print inventory to inventory box
		inventory.setText("Inventory: (" + carry + "/" + limit + ")\n");
        if (inve.size() < 1) {
        	inventory.setCaretPosition(inventory.getDocument().getLength());
        	inventory.replaceSelection("-- Empty --");
        	inventory.revalidate();
            return;
        }
        for (String str : inve) {
        	inventory.setCaretPosition(inventory.getDocument().getLength());
        	inventory.replaceSelection(" - " + str + "\n");
        	inventory.revalidate();
        }
	}
	
	private ActionListener makeListener() {
	        // Effective use of three buttons at the top
	        ActionListener listener = new ActionListener() {
	            
	            public void actionPerformed(ActionEvent e) {
	                JButton button1 = (JButton) e.getSource();
	                JButton button2 = (JButton) e.getSource();
	                JButton button3 = (JButton) e.getSource();
	                
	                String buttonText = button1.getText();
	                
	                if (buttonText.equalsIgnoreCase("text")) {
	                    buffer = buttonText;
	                }
	                
	                buttonText = button2.getText();
	                if (buttonText.equalsIgnoreCase("gui 1")) {
	                    buffer = "gui1";
	                }
	                
	                buttonText = button3.getText();
	                if (buttonText.equalsIgnoreCase("gui 3")) {
	                    buffer = "gui3";
	                }
	                bufferReady = true;
	            }	            
	        };	        
	        return listener;
	 }	
}