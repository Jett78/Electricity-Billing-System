

import javax.swing.*;
import javax.swing.border.BevelBorder;
import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.sql.*;
import java.awt.Window.Type;

public class system 
{

	JFrame frame;
	JTextField idfield,namefield,addressfield,numberfield,unitfield;
	JTextField searchfield, sidfield,snamefield,saddressfield,scellfield,sunitfield;
	JLabel header,custID,custname,address,unit,cell,search;
	JTextArea textArea;
	JPanel panel,panel_1,panel_2;
	JButton btnPrint,btnResult,btnReset,btnupdate,btndelete;
	JTable table;
	
	public system()    
	{
		initialize();
		connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void connect()         //This method is used to connect to the database
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/electricitybills","root","");
		}
		catch(ClassNotFoundException ex)
		{
			JOptionPane.showMessageDialog(null,"Error connecting to database");
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,"Error connecting to database");
		}
	}
	
	public void table_load()      //this method is used to display contents from database to the table.
	{
		try 
		{
			pst = con.prepareStatement("select * from custdata");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));    //should use rs2xml.jar file in build path to use this statement else it shows error.
			
			
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void initialize()
	{
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(175, 238, 238));
		frame.setBounds(100, 100, 1445, 789);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Electricity Billing System");
		
		header = new JLabel("ELECTRICITY BILLING SYSTEM");
		header.setFont(new Font("Times New Roman", Font.BOLD, 35));
		header.setBounds(472, 11, 568, 59);
		frame.getContentPane().add(header);
		
		panel = new JPanel();
		panel.setBackground(new Color(238, 232, 170));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 80, 1120, 315);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
	    custID = new JLabel("Customer ID");
		custID.setFont(new Font("Times New Roman", Font.BOLD, 20));
		custID.setBounds(71, 34, 125, 26);
		panel.add(custID);
		
	    custname = new JLabel("Customer Name");
		custname.setFont(new Font("Times New Roman", Font.BOLD, 20));
		custname.setBounds(71, 92, 138, 33);
		panel.add(custname);
		
	    address = new JLabel("Address");
		address.setFont(new Font("Times New Roman", Font.BOLD, 20));
		address.setBounds(71, 152, 100, 26);
		panel.add(address);
		
		cell = new JLabel("Cell Number");
		cell.setFont(new Font("Times New Roman", Font.BOLD, 20));
		cell.setBounds(71, 203, 122, 36);
		panel.add(cell);
		
		unit = new JLabel("Unit");
		unit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		unit.setBounds(71, 259, 47, 36);
		panel.add(unit);
		
		idfield = new JTextField(20);
		idfield.setBounds(220, 20, 166, 40);
		panel.add(idfield);
		idfield.setColumns(10);
		
		namefield = new JTextField(20);
		namefield.setColumns(10);
		namefield.setBounds(220, 80, 255, 40);
		panel.add(namefield);
		
		addressfield = new JTextField(20);
		addressfield.setColumns(10);
		addressfield.setBounds(220, 138, 255, 40);
		panel.add(addressfield);
		
		numberfield = new JTextField(20);
		numberfield.setColumns(10);
		numberfield.setBounds(220, 198, 255, 41);
		panel.add(numberfield);
		
		unitfield = new JTextField(20);
		unitfield.setColumns(10);
		unitfield.setBounds(220, 255, 125, 40);
		panel.add(unitfield);
		
		
		btnReset = new JButton("Reset");
		btnReset.setBackground(new Color(255, 127, 80));
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReset.setBounds(918, 134, 138, 40);
		panel.add(btnReset);
		btnReset.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)        //clears the textfield.
			{
				idfield.setText(null);
				namefield.setText(null);
				addressfield.setText(null);
				numberfield.setText(null);
				unitfield.setText(null);
				textArea.setText(null);
				idfield.requestFocus();         //cursor automatically sets its position on the idfield.
				
			}
		});
		
	    textArea = new JTextArea();
	    textArea.setBounds(516, 10, 368, 285);
	    panel.add(textArea);
	    textArea.setBackground(new Color(255, 255, 255));
	    
	    
	    btnPrint = new JButton("Print");
	    btnPrint.setBounds(918, 204, 138, 33);
	    panel.add(btnPrint);
	    btnPrint.setBackground(new Color(0, 204, 255));
	    btnPrint.setFont(new Font("Tahoma", Font.BOLD, 18));
	    btnPrint.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		try {
	    			textArea.print();          //print()is a predefined method that is used to connect to a printer to print the following data in textArea
	    		} 
	    		catch (PrinterException e1)
	    		{
	    			e1.printStackTrace();
	    		}
	    	}
	    });
	    
	    JButton btnresult = new JButton("Submit");
	    btnresult.setBounds(918, 80, 138, 33);
	    btnresult.setForeground(new Color(0, 0, 0));
	    btnresult.setBackground(new Color(124, 252, 0));
	    btnresult.setFont(new Font("Tahoma", Font.BOLD, 18));
	    panel.add(btnresult);
	    btnresult.addActionListener(new ActionListener()
	    {
	    	
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		int charge = 0;
	    		int price = 30;
	    		int unit = Integer.parseInt(unitfield.getText());
	    		String name = namefield.getText();
	    		String address = addressfield.getText();
	    		int cell =Integer.parseInt(numberfield.getText());
	    		int id = Integer.parseInt(idfield.getText());
	    		
	    		
	    		//These are the conditions for the price of unit consumed.
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
	    	   
	    	   try 
	    	   {
	    		    pst = con.prepareStatement("insert into custdata(ID,Name,Address,Cell,Unit)values(?,?,?,?,?)");
	    		    pst.setLong(1, id);
	    		    pst.setString(2, name );
	    			pst.setString(3, address);
	    			pst.setLong(4, cell);
	    			pst.setLong(5, unit);
	    			
	    			pst.executeUpdate();
	    			JOptionPane.showMessageDialog(null,"Entry saved to Database");
	                
	    			table_load();       //we also called this method here as soon as the data are inserted in the database it is also shown in the table.
	    			
	    			
	            } 
	    	   catch (SQLException ex)
	    	   {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(null,"Error saving Entry to the Database");
	            }
	    	}
	    });
	    
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 394, 1120, 336);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		panel_1 = new JPanel();
		panel_1.setBounds(1130, 80, 273, 650);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		search = new JLabel("Search ID");
		search.setFont(new Font("Times New Roman", Font.BOLD, 20));
		search.setBounds(22, 58, 125, 26);
		panel_1.add(search);
		
		searchfield = new JTextField(10);
		searchfield.setBackground(new Color(240, 255, 255));
		searchfield.setBounds(21, 94, 149, 43);
		panel_1.add(searchfield);
		searchfield.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) 
			{
				try 
				{
					String id = searchfield.getText();
					
					pst = con.prepareStatement("select ID,Name,Address,Cell,Unit from custdata where id = ?");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next()==true)

					{
						String ids = rs.getString(1);        //temporarily stores data into these variables.
						String name = rs.getString(2);
						String address = rs.getString(3);
						String cell= rs.getString(4);
						String unit= rs.getString(5);
						
						sidfield.setText(ids);
						snamefield.setText(name);
						saddressfield.setText(address);
						scellfield.setText(cell);
						sunitfield.setText(unit);
						 
					}
					else {
						sidfield.setText(null);
						snamefield.setText(null);
						saddressfield.setText(null);
						scellfield.setText(null);
						sunitfield.setText(null);	
					}
				}		
					catch(SQLException ex) 
				{
						ex.printStackTrace();
					}
				}
			
		});
		
		
		btnupdate = new JButton("Update");
		btnupdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				
	    		int unit = Integer.parseInt(unitfield.getText());
	    		String name = namefield.getText();
	    		String address = addressfield.getText();
	    		int cell =Integer.parseInt(numberfield.getText());
	    		int id = Integer.parseInt(searchfield.getText());
	    		
				try
				{
					pst = con.prepareStatement("update custdata set ID= ?,Name= ?,Address= ?,Cell= ?,Unit= ? where id = ?");
					pst.setLong(1, id);
	    		    pst.setString(2, name );
	    			pst.setString(3, address);
	    			pst.setLong(4, cell);
	    			pst.setLong(5, unit);
					
					JOptionPane.showMessageDialog(null,"Record Updated");
					
					sidfield.setText(null);
					snamefield.setText(null);
					saddressfield.setText(null);
					scellfield.setText(null);
					sunitfield.setText(null);
					
					table_load();
					
					
				}
				catch(SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		
		btnupdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnupdate.setBounds(162, 531, 101, 43);
		panel_1.add(btnupdate);
		
		btndelete = new JButton("Delete");
		btndelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btndelete.setBounds(162, 584, 101, 43);
		panel_1.add(btndelete);
		btndelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
                   String id = searchfield.getText();
				
				try 
				{
					pst = con.prepareStatement("delete from custdata where id = ?");
					pst.setString(1, id );
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record deleted");
					sidfield.setText(null);
					snamefield.setText(null);
					saddressfield.setText(null);
					scellfield.setText(null);
					sunitfield.setText(null);
					table_load();
						
				}
				catch(SQLException e1) 
				{
					e1.printStackTrace();
				}
				
			}
		});
		
		
		panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(22, 154, 230, 337);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		sidfield = new JTextField();
		sidfield.setBounds(10, 31, 210, 38);
		panel_2.add(sidfield);
		sidfield.setColumns(10);
		
		snamefield = new JTextField();
		snamefield.setColumns(10);
		snamefield.setBounds(10, 90, 210, 38);
		panel_2.add(snamefield);
		
		saddressfield = new JTextField();
		saddressfield.setColumns(10);
		saddressfield.setBounds(10, 152, 210, 38);
		panel_2.add(saddressfield);
		
		scellfield = new JTextField();
		scellfield.setColumns(10);
		scellfield.setBounds(10, 210, 210, 38);
		panel_2.add(scellfield);
		
		sunitfield = new JTextField();
		sunitfield.setColumns(10);
		sunitfield.setBounds(10, 272, 210, 38);
		panel_2.add(sunitfield);
	}

public static void main(String[] args)
{
	system window = new system();
	window.frame.setVisible(true);
  }
}
