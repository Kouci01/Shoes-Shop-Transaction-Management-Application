package TransactionView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import CartHandler.CartHandler;
import MainframeHandler.MainframeHandler;
import MainframeView.MainframeView;
import Transaction.Transaction;
import TransactionHandler.TransactionHandler;
import TransactionItem.TransactionItem;

public class viewTransactionReports extends JFrame implements ActionListener{
	private JTable transactionTable;
	private DefaultTableModel dtm;
	private JButton Back;
	private JButton ViewByMonthYear;
	private JSpinner month;
	private JSpinner year;
	public void Layout() {
		// Frame
		pack();
		setTitle("Transaction Report");
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		// Set space keatas-bawah dan samping
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainPanel.setLayout(new GridLayout(3,1,20,10));
		setContentPane(mainPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		transactionTable = new JTable();
		transactionTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table = (JTable) e.getSource();
				int getIndex = table.getSelectedRow();
				int data = Integer.parseInt(table.getValueAt(getIndex, 0).toString());				 
				TableItemLayout(data);
				
 			}
		});
		transactionTable.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(transactionTable);
		add(scrollPane);
		
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(5,1,10,10));
		
		JLabel montH = new JLabel("Input Month: ");
		inputPanel.add(montH);
		
		SpinnerNumberModel model = new SpinnerNumberModel(1,1,12,1);
		month = new JSpinner(model);
		month.setPreferredSize(new Dimension(50,20));
		inputPanel.add(month);
		
		// Label plus spinner buat quantity
		JLabel yeaR = new JLabel("Input Year: ");
		inputPanel.add(yeaR);
		
		SpinnerNumberModel model1 = new SpinnerNumberModel();
		year = new JSpinner(model1);
		year.setPreferredSize(new Dimension(50,20));
		inputPanel.add(year);
		
		ViewByMonthYear = new JButton("View By Choosen Month & Year");
		ViewByMonthYear.addActionListener(this);
		ViewByMonthYear.setBackground(Color.GRAY);
		ViewByMonthYear.setForeground(Color.WHITE);
		inputPanel.add(ViewByMonthYear);
		
		add(inputPanel);
		
		JPanel buttonlast = new JPanel();
		buttonlast.setLayout(new FlowLayout(FlowLayout.CENTER));
		Back = new JButton("Sign Out");
		Back.setPreferredSize(new Dimension(250,100));
		Back.setBackground(Color.BLACK);
		Back.setForeground(Color.WHITE);
		Back.addActionListener(this);
		buttonlast.add(Back);
		add(buttonlast);
		
		setVisible(true);
	}
	private void TableItemLayout(int data) {
		JFrame frame = new JFrame();
		frame.setSize(500,500);
		frame.setTitle("Transaction Item in TransactionID "+data);
		
		JPanel panelTransItem = new JPanel();
		panelTransItem.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		frame.setContentPane(panelTransItem);
		
		JScrollPane scrollPane1 = new JScrollPane();
		JTable ItemTable = new JTable();
		ItemTable.setDefaultEditor(Object.class, null);
		scrollPane1.setViewportView(ItemTable);
		frame.add(scrollPane1);
		
		frame.setVisible(true);

		DefaultTableModel dtm1 = new DefaultTableModel(new String[] {"transactionID","productID","quantity"},0);
		ItemTable.setModel(dtm1);
		
		dtm1.setRowCount(0);
		Vector<TransactionItem> items = TransactionHandler.getAllTransactionItem(data);
		
		for(TransactionItem i:items) {
			dtm1.addRow(new Object[] {
					i.getTransactionID(),
					i.getProductID(),
					i.getQuantity()
			});
		}
		
	}
	private void setDTM() {
		// 0 nya itu buat Row count kenapa nol karena bukan masuk data
		dtm = new DefaultTableModel(new String[] {"ID","purchaseDate","employeeID","paymentType"},0);
		transactionTable.setModel(dtm);
	}
	
	private void refreshDataTransaction() {
		dtm.setRowCount(0);
		Vector<Transaction> items = TransactionHandler.getAllTransaction();
		
		for(Transaction i:items) {
			dtm.addRow(new Object[] {
					i.getID(),
					i.getPurchaseDate(),
					i.getEmployeeID(),
					i.getPaymentType()
			});
		}
	}
	private void refreshDataTransactionbyMonthYear(int month,int year) {
		dtm.setRowCount(0);
		Vector<Transaction> items = TransactionHandler.getAllTransactionbyMonthYear(month, year);
		
		for(Transaction i:items) {
			dtm.addRow(new Object[] {
					i.getID(),
					i.getPurchaseDate(),
					i.getEmployeeID(),
					i.getPaymentType()
			});
		}
	}
	public viewTransactionReports() {
		Layout();
		setDTM();
		refreshDataTransaction();
	}
	private void viewMonthYear() {
		int Month = (int)month.getValue();
		int Year = (int)year.getValue();
		
		if(Year <= 0) {
			return;
		}
		refreshDataTransactionbyMonthYear(Month, Year);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Back) {
			// Ini untuk sementara karena belom ada Main Frame
			dispose();
			MainframeHandler.showMainframeView();
		}else if(e.getSource() == ViewByMonthYear) {
			viewMonthYear();
		}
	}

}
