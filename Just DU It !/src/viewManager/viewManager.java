package viewManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import TransactionHandler.TransactionHandler;
import employeeHandler.EmployeeController;

public class viewManager extends JFrame implements ActionListener{
	
	private JButton viewTransaction;
	private JButton viewEmployee;
	
	private void Layout() {
		//Frame
		pack();
		setTitle("Manager Form");
		setSize(600, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Setposition mainpanel
		JPanel mainPanel = new JPanel();
		// Set space keatas-bawah dan samping
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JLabel choosemanager = new JLabel("Choose What Do You Want To Do as Manager");
		choosemanager.setAlignmentX(CENTER_ALIGNMENT);
		add(choosemanager);
		
		JPanel input = new JPanel();
		input.setLayout(new BoxLayout(input, BoxLayout.X_AXIS));
		
		//Panel buat button
		
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER,50,20));
				
		viewTransaction = new JButton("View Transaction Report");
		viewTransaction.setBackground(Color.CYAN);
		viewTransaction.setAlignmentX(LEFT_ALIGNMENT);
		viewTransaction.setPreferredSize(new Dimension(180, 50));
		viewTransaction.addActionListener(this);
		panelButton.add(viewTransaction);
		
		viewEmployee = new JButton("Manage Employee");
		viewEmployee.setBackground(Color.GREEN);
		viewEmployee.setAlignmentX(RIGHT_ALIGNMENT);
		viewEmployee.setPreferredSize(new Dimension(180,50));
		viewEmployee.addActionListener(this);
		panelButton.add(viewEmployee);
		
		input.add(panelButton);
		
		add(input);
		
		setVisible(true);
	}
	
	public viewManager() {
		Layout();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == viewTransaction) {
			dispose();
			TransactionHandler.viewTransactionReport();
		}else if(e.getSource() == viewEmployee) {
			dispose();
			EmployeeController.viewManageEmployeeForm();
		}
	}
}
