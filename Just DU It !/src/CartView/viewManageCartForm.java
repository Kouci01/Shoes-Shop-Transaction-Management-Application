package CartView;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
import CartItem.CartItem;

public class viewManageCartForm extends JFrame implements ActionListener {
	private JTable CartTable;
	private DefaultTableModel CartTableModel;
	private JSpinner ProductID;
	private JSpinner ProductQuantity;
	private JButton CheckOut;
	private JButton DeleteItem;
	private JButton UpdateItem;
	private int employeeID;
	private void Layout() {
		//Frame setting
		pack();
		setLocationRelativeTo(null);
		setTitle("Manage Cart Form");
		setSize(800,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Main Frame setting
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		mainPanel.setLayout(new GridLayout(2,1,40,40));
		setContentPane(mainPanel);
		
		// Buat table view dan bisa discroll
		JScrollPane scrollproducttable = new JScrollPane();
		CartTable = new JTable();
		scrollproducttable.setViewportView(CartTable);
		add(scrollproducttable);
		
		// Buat bagian inputan keseluruhan
		JPanel input = new JPanel();
		input.setLayout(new GridLayout(1,2,20,20));
		
		// Buat bagian inputfield aja
		JPanel inputField = new JPanel();
		inputField.setLayout(new GridLayout(2,2,30,30));
		
		//Buat masukin ke inputfield
		JLabel product = new JLabel("Product ID: ");
		product.setHorizontalAlignment(SwingConstants.RIGHT);
		inputField.add(product);
		
		SpinnerNumberModel model = new SpinnerNumberModel();
		ProductID = new JSpinner(model);
		ProductID.setPreferredSize(new Dimension(50,20));
		inputField.add(ProductID);
		
		// Label plus spinner buat quantity
		JLabel quantity = new JLabel("Quantity: ");
		quantity.setHorizontalAlignment(SwingConstants.RIGHT);
		inputField.add(quantity);
		
		SpinnerNumberModel model1 = new SpinnerNumberModel();
		ProductQuantity = new JSpinner(model1);
		ProductQuantity.setPreferredSize(new Dimension(50,20));
		inputField.add(ProductQuantity);
		
		input.add(inputField);
		
		// Panel for Button
		JPanel Panelbtn = new JPanel();
		Panelbtn.setLayout(new BoxLayout(Panelbtn, BoxLayout.Y_AXIS));
		
		Panelbtn.add(Box.createRigidArea(new Dimension(0, 10)));
		CheckOut= new JButton("Check Out");
		CheckOut.setBackground(Color.BLUE);
		CheckOut.setForeground(Color.WHITE);
		CheckOut.setPreferredSize(new Dimension(100,100));
		CheckOut.addActionListener(this);
		Panelbtn.add(CheckOut);
		
		Panelbtn.add(Box.createRigidArea(new Dimension(0, 10)));
		DeleteItem= new JButton("Remove from Cart");
		DeleteItem.setPreferredSize(new Dimension(100, 100));
		DeleteItem.setBackground(Color.RED);
		DeleteItem.setForeground(Color.WHITE);
		DeleteItem.addActionListener(this);
		Panelbtn.add(DeleteItem);
		
		Panelbtn.add(Box.createRigidArea(new Dimension(0, 10)));
		UpdateItem= new JButton("Update Item");
		UpdateItem.setPreferredSize(new Dimension(100, 100));
		UpdateItem.setBackground(Color.YELLOW);
		UpdateItem.addActionListener(this);
		Panelbtn.add(UpdateItem);
		Panelbtn.add(Box.createRigidArea(new Dimension(0, 10)));
		
		input.add(Panelbtn);
		
		add(input);
		setVisible(true);
	}
	private void setDTM() {
		// 0 nya itu buat Row count kenapa nol karena bukan masuk data
		CartTableModel = new DefaultTableModel(new String[] {"ProductID","Quantity"},0);
		CartTable.setModel(CartTableModel);
	}
	private void refreshDataCart() {
		CartTableModel.setRowCount(0);
		Vector<CartItem> items = CartHandler.getListCartItem();
		
		for(CartItem i:items) {
			CartTableModel.addRow(new Object[] {
					i.getProductID(),
					i.getQuantity()
			});
		}
	}
	public viewManageCartForm(int employeeID) {
		Layout();
		setDTM();
		refreshDataCart();
		this.employeeID = employeeID;
	}
	private void DeleteFromCart() {
		int id = (int)ProductID.getValue();
		
		Boolean error = CartHandler.deleteItem(id);
		if(error==true) {
			JOptionPane.showMessageDialog(this, "ProductID "+id+" Not in Cart");
		}else {
			JOptionPane.showMessageDialog(this, "Product "+id+" removed from Cart");
			refreshDataCart();
		}
	}
	
	private void UpdateCart() {
		int id = (int)ProductID.getValue();
		int quantity = (int)ProductQuantity.getValue();
		
		CartItem error = CartHandler.updateStock(id, quantity);
		if(error==null) {
			JOptionPane.showMessageDialog(this, "ProductID "+id+" Not in Cart or Invalid Input!");
		}else {
			JOptionPane.showMessageDialog(this, "Product "+id+" Updated!");
			refreshDataCart();
		}
	}
	private void CheckOut(int employeeID) {
		CartHandler.viewPaymentForm(employeeID);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == CheckOut) {
			int res = JOptionPane.showConfirmDialog(this,"Are you sure want to Check Out?","Confirmation Box",JOptionPane.YES_NO_OPTION);
			if(res == 0) {
				// Ini Kalo Yes
				dispose();
				CheckOut(employeeID);
			}else {
				refreshDataCart();
			}
		}else if(e.getSource() == DeleteItem) {
			int res = JOptionPane.showConfirmDialog(this,"Are you sure want to Delete ProductID "+(int)ProductID.getValue(),"Confirmation Box",JOptionPane.YES_NO_OPTION);
			if(res == 0) {
				// Ini Kalo Yes
				DeleteFromCart();
				refreshDataCart();
			}else {
				refreshDataCart();
			}
		}else if(e.getSource() == UpdateItem) {
			UpdateCart();
			refreshDataCart();
		}
		
	}

}
