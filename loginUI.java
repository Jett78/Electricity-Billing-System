import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Window.Type;



public class loginUI
{

	 JFrame frame;
	 JTextField txtuname;
	 JButton btnlogin;
	 JButton btncancel;
	 JPasswordField passwordField;

	
	
	public loginUI() 
	{
		
		frame = new JFrame();
		frame.setType(Type.POPUP);
		frame.setBounds(100, 100, 693, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel header = new JLabel("Login");
		header.setFont(new Font("Tahoma", Font.BOLD, 60));
		header.setBounds(274, 20, 166, 79);
		frame.getContentPane().add(header);
		
		JLabel username = new JLabel("Username");
		username.setFont(new Font("Tahoma", Font.BOLD, 30));
		username.setBounds(32, 161, 166, 64);
		frame.getContentPane().add(username);
		
		JLabel password = new JLabel("Password");
		password.setFont(new Font("Tahoma", Font.BOLD, 30));
		password.setBounds(32, 257, 166, 64);
		frame.getContentPane().add(password);
		
		txtuname = new JTextField();
		txtuname.setBounds(235, 161, 294, 51);
		frame.getContentPane().add(txtuname);
		txtuname.setColumns(10);
		
		btnlogin = new JButton("Login");
		btnlogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				String username = txtuname.getText();
				String password = passwordField.getText();
				
				if(username.equals("John") && password.equals("123"))
				{
					system m = new system();
					m.frame.setVisible(true);
					frame.dispose();
				}
				else{
					JOptionPane.showMessageDialog(null,"Username and password doesnt match");
					txtuname.setText("");
					passwordField.setText("");
				}
			}
		});
		btnlogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnlogin.setBounds(367, 368, 146, 51);
		frame.getContentPane().add(btnlogin);
		
		btncancel = new JButton("Cancel");
		btncancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtuname.setText(null);
				passwordField.setText(null);
			}
		});
		btncancel.setFont(new Font("Tahoma", Font.BOLD, 20));
		btncancel.setBounds(523, 368, 146, 51);
		frame.getContentPane().add(btncancel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(235, 257, 294, 51);
		frame.getContentPane().add(passwordField);
	}
	
	public static void main(String[] args) {
		loginUI window = new loginUI();
		window.frame.setVisible(true);
	}
}
