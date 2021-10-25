package TransactionManagementView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import CartHandler.CartHandler;
import TransactionHandler.TransactionHandler;
import TransactionView.viewTodayTransaction;

public class TransactionManagementView extends JFrame implements ActionListener{
	private JButton AddTransaction;
	private JButton ViewTodaytransaction;
	private int employeeID;
	public void Layout() {
		pack();
		setTitle("Transaction Management Just For Cashier Role");
		setLocationRelativeTo(null);
		setSize(400,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.CYAN);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		mainPanel.setLayout(new GridLayout(2,1,0,20));
		setContentPane(mainPanel);
		
		JLabel ChooseTodo = new JLabel("Choose what do you want to do as a Cashier?");
		add(ChooseTodo);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.setBackground(Color.CYAN);
		
		AddTransaction = new JButton("Add New Transaction");
		AddTransaction.setPreferredSize(new Dimension(50, 50));
		buttonPanel.add(AddTransaction,BorderLayout.PAGE_START);
		AddTransaction.addActionListener(this);
		
		ViewTodaytransaction = new JButton("View Today Transaction");
		ViewTodaytransaction.setPreferredSize(new Dimension(50, 50));
		buttonPanel.add(ViewTodaytransaction,BorderLayout.PAGE_END);
		ViewTodaytransaction.addActionListener(this);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		add(buttonPanel);
		
		setVisible(true);
		
	}
	public TransactionManagementView(int employeeID) {
		Layout();
		this.employeeID = employeeID;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == AddTransaction) {
			CartHandler.viewAddToCartForm(employeeID);
			dispose();
		}else if(e.getSource() == ViewTodaytransaction) {
			TransactionHandler.viewTodayTransactionReport();
			dispose();
		}
		
	}

}
