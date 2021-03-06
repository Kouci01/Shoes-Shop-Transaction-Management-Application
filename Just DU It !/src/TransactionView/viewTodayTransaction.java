package TransactionView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CartHandler.CartHandler;
import CartItem.CartItem;
import MainframeHandler.MainframeHandler;
import MainframeView.MainframeView;
import Transaction.Transaction;
import TransactionHandler.TransactionHandler;

public class viewTodayTransaction extends JFrame implements ActionListener {
	private JTable transactionTable;
	private DefaultTableModel dtm;
	private JButton Back;
	
	public void Layout() {
		// Frame
		pack();
		setTitle("Today Transaction");
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		// Set space keatas-bawah dan samping
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainPanel.setLayout(new GridLayout(2,1,20,10));
		setContentPane(mainPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		transactionTable = new JTable();
		scrollPane.setViewportView(transactionTable);
		add(scrollPane);
		
		JPanel button = new JPanel();
		button.setLayout(new FlowLayout(FlowLayout.CENTER));
		Back = new JButton("Sign Out");
		Back.setPreferredSize(new Dimension(250,100));
		Back.setBackground(Color.BLACK);
		Back.setForeground(Color.WHITE);
		Back.addActionListener(this);
		button.add(Back);
		add(button);
		
		setVisible(true);
	}
	private void setDTM() {
		// 0 nya itu buat Row count kenapa nol karena bukan masuk data
		dtm = new DefaultTableModel(new String[] {"ID","purchaseDate","employeeID","paymentType"},0);
		transactionTable.setModel(dtm);
	}
	
	private void refreshDataTransaction() {
		dtm.setRowCount(0);
		Vector<Transaction> items = TransactionHandler.getTodayTransaction();
		
		for(Transaction i:items) {
			dtm.addRow(new Object[] {
					i.getID(),
					i.getPurchaseDate(),
					i.getEmployeeID(),
					i.getPaymentType()
			});
		}
	}
	public viewTodayTransaction() {
		Layout();
		setDTM();
		refreshDataTransaction();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Back) {
			// Ini untuk sementara karena belom ada Main Frame
			dispose();
			MainframeHandler.showMainframeView();
		}
		
	}

}
