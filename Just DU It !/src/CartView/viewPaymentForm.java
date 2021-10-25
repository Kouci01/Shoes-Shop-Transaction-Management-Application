package CartView;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import CartHandler.CartHandler;
import MainframeHandler.MainframeHandler;
import MainframeView.MainframeView;
import TransactionHandler.TransactionHandler;

public class viewPaymentForm extends JFrame implements ActionListener{
	private JSpinner TotalPayment;
	private JButton Cash;
	private JButton Credit;
	private int employeeID;
	private void Layout() {
		pack();
		setTitle("Payment Form");
		setLocationRelativeTo(null);
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		// Set space keatas-bawah dan samping
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		mainPanel.setLayout(new GridLayout(4,1,0,10));
		setContentPane(mainPanel);
		
		JLabel TotalHarga = new JLabel("Total Harga: Rp. "+CartHandler.CalculateTotal());
		TotalHarga.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(TotalHarga);
		
		JPanel inputCol = new JPanel();
		inputCol.setLayout(new GridLayout(2,1,10,10));
		
		
		JLabel totalpay = new JLabel("Input Customer Total Money: ");
		inputCol.add(totalpay);
		SpinnerNumberModel model2 = new SpinnerNumberModel();
		TotalPayment = new JSpinner(model2);
		TotalPayment.setPreferredSize(new Dimension(50,20));
		inputCol.add(TotalPayment);
		
		add(inputCol);
		
		JLabel ChoosePayway = new JLabel("Choose Your Way to Pay: ");
		ChoosePayway.setHorizontalAlignment(SwingConstants.CENTER);
		add(ChoosePayway);
		
		JPanel button = new JPanel();
		button.setLayout(new FlowLayout());
		
		Cash = new JButton("Cash");
		Credit = new JButton("Credit");
		Cash.addActionListener(this);
		Credit.addActionListener(this);
		button.add(Cash);
		button.add(Credit);
		
		add(button);
		setVisible(true);
		
	}
	
	public viewPaymentForm(int employeeID) {
		Layout();
		this.employeeID = employeeID;
	}
	public void InsertTransactionToDatabase(int employeeID, String paymentType) {
		TransactionHandler.insertTransaction(employeeID, paymentType, (int)TotalPayment.getValue());
	}
	@Override
	public void actionPerformed(ActionEvent o) {
		if(o.getSource()==Cash) {
			if((int)TotalPayment.getValue() >= CartHandler.CalculateTotal()) {
				int jumlahkembalian = ((int)TotalPayment.getValue() - CartHandler.CalculateTotal());
				InsertTransactionToDatabase(employeeID,"Cash");
				if(jumlahkembalian!=0) {
					JOptionPane.showMessageDialog(this, "Here is the change amount: Rp. "+String.valueOf(jumlahkembalian));
				}
				dispose();
				new MainframeView();
				// Balik ke Main Frame abis itu
			}else {
				JOptionPane.showMessageDialog(this, "Ammount Inputted not enough please input again!");
			}
		}else if(o.getSource()==Credit) {
			if((int)TotalPayment.getValue() >= CartHandler.CalculateTotal()) {
				int jumlahkembalian = ((int)TotalPayment.getValue() - CartHandler.CalculateTotal());
				InsertTransactionToDatabase(employeeID,"Credit");
				if(jumlahkembalian!=0) {
					JOptionPane.showMessageDialog(this, "Here is the change amount: Rp. "+String.valueOf(jumlahkembalian));
				}
				dispose();
				MainframeHandler.showMainframeView();
				// Balik ke Main Frame abis itu
			}else {
				JOptionPane.showMessageDialog(this, "Ammount Inputted not enough please input again!");
			}
		}
		
	}

	

}
