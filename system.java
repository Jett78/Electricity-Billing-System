

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.sql.*;

public class system 
{

	JFrame frame;
	JTextField t1,t2,t3,t4,t5;
	JLabel custID,custname,address,unit,cell;
	JTextArea textArea;
	JButton btnPrint,btnResult,btnReset;
	
	public system()
	{
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(175, 238, 238));
		frame.setBounds(100, 100, 919, 636);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel header = new JLabel("ELECTRICITY BILLING SYSTEM");
		header.setFont(new Font("Verdana", Font.BOLD, 30));
		header.setBounds(216, 10, 538, 76);
		frame.getContentPane().add(header);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(238, 232, 170));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(30, 109, 840, 438);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
	    custID = new JLabel("Customer ID");
		custID.setFont(new Font("Times New Roman", Font.BOLD, 22));
		custID.setBounds(38, 69, 125, 26);
		panel.add(custID);
		
	    custname = new JLabel("Customer Name");
		custname.setFont(new Font("Times New Roman", Font.BOLD, 22));
		custname.setBounds(38, 133, 166, 33);
		panel.add(custname);
		
	    address = new JLabel("Address");
		address.setFont(new Font("Times New Roman", Font.BOLD, 22));
		address.setBounds(38, 197, 100, 26);
		panel.add(address);
		
		cell = new JLabel("Cell Number");
		cell.setFont(new Font("Times New Roman", Font.BOLD, 22));
		cell.setBounds(38, 258, 122, 26);
		panel.add(cell);
		
		unit = new JLabel("Unit");
		unit.setFont(new Font("Times New Roman", Font.BOLD, 22));
		unit.setBounds(38, 317, 47, 27);
		panel.add(unit);
		
		t1 = new JTextField(20);
		t1.setBounds(199, 57, 144, 38);
		panel.add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField(20);
		t2.setColumns(10);
		t2.setBounds(199, 122, 197, 38);
		panel.add(t2);
		
		t3 = new JTextField(20);
		t3.setColumns(10);
		t3.setBounds(199, 185, 197, 38);
		panel.add(t3);
		
		t4 = new JTextField(20);
		t4.setColumns(10);
		t4.setBounds(199, 246, 197, 38);
		panel.add(t4);
		
		t5 = new JTextField(20);
		t5.setColumns(10);
		t5.setBounds(202, 306, 125, 38);
		panel.add(t5);
		
	    textArea = new JTextArea();
	    textArea.setBackground(new Color(255, 255, 255));
		textArea.setBounds(461, 44, 348, 320);
		panel.add(textArea);
		
		JButton btnresult = new JButton("Result");
		btnresult.setForeground(new Color(0, 0, 0));
		btnresult.setBackground(new Color(255, 51, 51));
		btnresult.addActionListener(new ActionListener() {
		

			public void actionPerformed(ActionEvent e) 
			{
				int charge = 0;
				int price = 30;
				int unit = Integer.parseInt(t5.getText());
				String name = t2.getText();
				String address = t3.getText();
				int cell =Integer.parseInt(t4.getText());
				//String cell = t4.getText();
				int id = Integer.parseInt(t1.getText());
				
				
				//conditions for the price of unit consumed.
			    if(unit<100)
			    {
			    	charge =unit*price;
			    }
			    else if(unit>100 && unit<300)
			    {
			    	charge = unit*price;
			    }
			    else if(unit>300 && unit<500)
			    {
			    	charge = unit*price;
			    }
			    else if(unit>500 && unit<800)
			    {
			    	charge =unit*price;
			    }
			   
			   //the user input data are shown in textArea using append().
			   textArea.append( "\n");
			   textArea.append( "x*******************************NEPAL*******************************x" );
			   textArea.append( "\n");
			   textArea.append("                                      Electricity Authority                        ");
			   textArea.append( "\n");
			   textArea.append( "\n");
			   textArea.append("x**********************************************************************x");
			   textArea.append( "\n");
			   textArea.append( "\n");
			   textArea.append( "CUSTOMER ID            :    "+id);
			   textArea.append( "\n");
			   textArea.append( "CUSTOMER NAME      :    "+name);
			   textArea.append( "\n");
			   textArea.append( "CUSTOMER Address  :    "+address);
			   textArea.append( "\n");
			   textArea.append( "CELL NUMBER            :    "+cell);
			   textArea.append( "\n");
			   textArea.append( "UNIT CONSUMED        :    "+unit);
			   textArea.append( "\n");
			   textArea.append( "\n");
			   textArea.append("x**********************************************************************x");
			   textArea.append( "\n");
			   textArea.append( "\n");
			   textArea.append( "TOTAL AMOUNT          :    "+charge);
			   textArea.append( "\n");
			   textArea.append( "\n");
			   textArea.append( "x****************************THANK YOU****************************x" );
			   
			   try {
	            	
	                //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/electricitybills", "root", " ");
	            	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/electricitybills?user= root&passwrd= ");
	                Statement stmt = conn.createStatement();
	                String sql = "INSERT INTO custdata (ID,Name,Address,Cell,Unit) VALUES ('"+id+"','"+name+"','"+address+"','"+cell+"','"+unit+"')";
	                stmt.executeUpdate(sql);
	                JOptionPane.showMessageDialog(null, "Name and address saved to database.");
	                
	                
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(null,"Error saving name and address to database.");
	            }
			}
		});
		
		
		btnresult.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnresult.setBounds(76, 374, 112, 53);
		panel.add(btnresult);
		
		//print()is a predefined method that is used to connect to a printer to print the following data in textArea
		btnPrint = new JButton("Print");
		btnPrint.setBackground(new Color(0, 204, 255));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textArea.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPrint.setBounds(592, 374, 112, 53);
		panel.add(btnPrint);
		
		//clears the textfield
		btnReset = new JButton("Reset");
		btnReset.setBackground(new Color(102, 204, 0));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setText(null);
				t2.setText(null);
				t3.setText(null);
				t4.setText(null);
				t5.setText(null);
				textArea.setText(null);
				
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReset.setBounds(215, 374, 112, 53);
		panel.add(btnReset);
	}


public static void main(String[] args)
{
	system window = new system();
	window.frame.setVisible(true);
  }
}
