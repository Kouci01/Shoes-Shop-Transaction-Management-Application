package MainframeView;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import MainframeHandler.MainframeHandler;
import ProductHandler.ProductHandler;
import TransactionHandler.TransactionHandler;
import TransactionManagementView.TransactionManagementView;
import employeeHandler.EmployeeController;
import viewManager.viewManager;

public class MainframeView extends JFrame implements ActionListener{
	
	private String username, password;
	private JTextField usernamefield;
	private JPasswordField passwordfield;
	private JButton transactionManagebtn;
	private JButton productManagebtn;
	private JButton humanResourceManagebtn;
	private JButton managerbtn;
	private int employeeID;
	private void mainLayout() {
		pack();
		setTitle("Just DU It!");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new FlowLayout());
		setContentPane(contentPane);
		
		JPanel panelCud = new JPanel();
		panelCud.setLayout(new BoxLayout(panelCud, BoxLayout.Y_AXIS));
		
		JPanel panelFields = new JPanel();
		panelFields.setLayout(new GridLayout(2, 2));
		
		JLabel labelusername = new JLabel("Username :");
		labelusername.setPreferredSize(new Dimension(150, 50));
		labelusername.setHorizontalAlignment(SwingConstants.LEFT);
		panelFields.add(labelusername);
		
		usernamefield = new JTextField();
		panelFields.add(usernamefield);
		
		JLabel labelpassword = new JLabel("Password :");
		labelpassword.setHorizontalAlignment(SwingConstants.LEFT);
		panelFields.add(labelpassword);
		
		passwordfield = new JPasswordField();
		panelFields.add(passwordfield);
		
		panelCud.add(panelFields);
		
		JPanel panelbuttons = new JPanel();
		panelbuttons.setLayout(new BoxLayout(panelbuttons, BoxLayout.PAGE_AXIS));
		
		panelbuttons.add(Box.createRigidArea(new Dimension(0, 5)));
		transactionManagebtn = new JButton("Transaction Management");
		transactionManagebtn.setAlignmentX(CENTER_ALIGNMENT);
		transactionManagebtn.addActionListener(this);
		panelbuttons.add(transactionManagebtn);
		
		panelbuttons.add(Box.createRigidArea(new Dimension(0, 5)));
		productManagebtn = new JButton("Product Management");
		productManagebtn.setAlignmentX(CENTER_ALIGNMENT);
		productManagebtn.addActionListener(this);
		panelbuttons.add(productManagebtn);
		
		panelbuttons.add(Box.createRigidArea(new Dimension(0, 5)));
		humanResourceManagebtn = new JButton("Human Resource Management");
		humanResourceManagebtn.setAlignmentX(CENTER_ALIGNMENT);
		humanResourceManagebtn.addActionListener(this);
		panelbuttons.add(humanResourceManagebtn);
		
		panelbuttons.add(Box.createRigidArea(new Dimension(0, 5)));
		managerbtn = new JButton("Manager");
		managerbtn.setAlignmentX(CENTER_ALIGNMENT);
		managerbtn.addActionListener(this);
		panelbuttons.add(managerbtn);
		
		panelCud.add(panelbuttons);
		
		add(panelCud);
		
		setVisible(true);
		
	}
	
	public MainframeView() {
		mainLayout();
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == transactionManagebtn) {
			username = usernamefield.getText();
			password = new String(passwordfield.getPassword());
			
			if(username.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Username must not be empty!");
				return;
			}else if(password.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Password must not be empty!");
			}else if(MainframeHandler.employeeLogin(username, password) == false){
				JOptionPane.showMessageDialog(this, "User Not Found.");
			}else if(MainframeHandler.employeeLogin(username, password) == true){
				if(MainframeHandler.employeeRole(username, password)== 1) {
					employeeID = EmployeeController.getEmployee(username).getID();
					dispose();
					new TransactionManagementView(employeeID);
				}else {
					JOptionPane.showMessageDialog(this, "You are Not a Cashier");
				}
			}
		} else if(e.getSource() == productManagebtn) {
			username = usernamefield.getText();
			password = new String(passwordfield.getPassword());
			
			if(username.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Username must not be empty!");
				return;
			}else if(password.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Password must not be empty!");
			}else if(MainframeHandler.employeeLogin(username, password) == false){
				JOptionPane.showMessageDialog(this, "User Not Found.");
			}else if(MainframeHandler.employeeLogin(username, password) == true){
				if(MainframeHandler.employeeRole(username, password)== 2) {
					dispose();
					ProductHandler.viewManageProductForm();
				}else {
					JOptionPane.showMessageDialog(this, "You are Not a Product Management");
				}
			}
		}else if(e.getSource() == humanResourceManagebtn) {
			username = usernamefield.getText();
			password = new String(passwordfield.getPassword());
			
			if(username.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Username must not be empty!");
				return;
			}else if(password.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Password must not be empty!");
			}else if(MainframeHandler.employeeLogin(username, password) == false){
				JOptionPane.showMessageDialog(this, "User Not Found.");
			}else if(MainframeHandler.employeeLogin(username, password) == true){
				if(MainframeHandler.employeeRole(username, password)== 3) {
					dispose();
					EmployeeController.viewManageEmployeeForm();
				}else {
					JOptionPane.showMessageDialog(this, "You are Not a Human Department");
				}
			}
		}else if(e.getSource() == managerbtn) {
			username = usernamefield.getText();
			password = new String(passwordfield.getPassword());
			
			if(username.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Username Must Not Be Empty!");
				return;
			}else if(password.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Password Must Not Be Empty!");
			}else if(MainframeHandler.employeeLogin(username, password) == false){
				JOptionPane.showMessageDialog(this, "User Not Found.");
			}else if(MainframeHandler.employeeLogin(username, password) == true){
				if(MainframeHandler.employeeRole(username, password)== 4) {
					dispose();
					new viewManager();
				}else {
					JOptionPane.showMessageDialog(this, "You are Not a Manager");
				}
			}
		}
	}


}
