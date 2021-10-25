package CartView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import Product.Product;
import ProductHandler.ProductHandler;


public class viewAddToCartForm extends JFrame implements ActionListener{
	private JTable table;
	private JTable productTable;
	private DefaultTableModel defaultTableModel;
	private DefaultTableModel defaultTableModelproduct;
	private JSpinner productID;
	private JSpinner quantityProduct;
	private JButton addProduct;
	private JButton deleteProduct;
	private JButton confirmTransaction;
	private int TotalHarga = 0;
	private int employeeID;
	private void Layout() {
		//Frame
		pack();
		setLocationRelativeTo(null);
		setTitle("Add to Cart Form");
		setSize(800,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Setposition mainpanel
		JPanel mainPanel = new JPanel();
		// Set space keatas-bawah dan samping
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		mainPanel.setLayout(new GridLayout(4,1,20,10));
		setContentPane(mainPanel);
		
		JScrollPane scrollPaneproduct = new JScrollPane();
		productTable = new JTable();
		scrollPaneproduct.setViewportView(productTable);
		// Add table that have scrollPane
		add(scrollPaneproduct);
		
		//For table
		JScrollPane scrollPane = new JScrollPane();
		table = new JTable();
		scrollPane.setViewportView(table);
		// Add table that have scrollPane
		add(scrollPane);
		
		//For input data insert,remove
		JPanel input = new JPanel();
		input.setLayout(new GridLayout(1,2,30,20));
		
		//For inputfield
		JPanel inputfield = new JPanel();
		inputfield.setLayout(new GridLayout(2,2,10,10));
		
		//Label plus spinner buat productID
		JLabel product = new JLabel("Product ID: ");
		product.setHorizontalAlignment(SwingConstants.RIGHT);
		inputfield.add(product);
		
		SpinnerNumberModel model = new SpinnerNumberModel();
		productID = new JSpinner(model);
		productID.setPreferredSize(new Dimension(50,20));
		inputfield.add(productID);
		
		// Label plus spinner buat quantity
		JLabel quantity = new JLabel("Quantity: ");
		quantity.setHorizontalAlignment(SwingConstants.RIGHT);
		inputfield.add(quantity);
		
		SpinnerNumberModel model1 = new SpinnerNumberModel();
		quantityProduct = new JSpinner(model1);
		quantityProduct.setPreferredSize(new Dimension(50,20));
		inputfield.add(quantityProduct);
		
		
		input.add(inputfield);
		
		//Panel buat button
		
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.Y_AXIS));
		
		panelButton.add(Box.createRigidArea(new Dimension(0, 10)));
		addProduct= new JButton("Add to Cart");
//		addProduct.setOpaque(true);
		addProduct.setBackground(Color.GREEN);
		addProduct.setPreferredSize(new Dimension(80, 80));
		addProduct.addActionListener(this);
		panelButton.add(addProduct);
		panelButton.add(Box.createRigidArea(new Dimension(0, 10)));
		
		deleteProduct= new JButton("Remove from Cart");
		deleteProduct.setPreferredSize(new Dimension(80, 80));
		deleteProduct.setBackground(Color.RED);
		deleteProduct.setForeground(Color.WHITE);
		deleteProduct.addActionListener(this);
		panelButton.add(deleteProduct);
		panelButton.add(Box.createRigidArea(new Dimension(0, 10)));
		
		confirmTransaction= new JButton("Confirmation");
		confirmTransaction.setPreferredSize(new Dimension(80, 80));
		confirmTransaction.setBackground(Color.YELLOW);
		confirmTransaction.addActionListener(this);
		panelButton.add(confirmTransaction);
		panelButton.add(Box.createRigidArea(new Dimension(0, 10)));
		
		input.add(panelButton);
		
		add(input);
		
		TotalHarga = CartHandler.CalculateTotal();
		JLabel Total = new JLabel("Total Harga: Rp "+TotalHarga);
		add(Total);
		
		setVisible(true);
	}
	
	public viewAddToCartForm(int employeeID) {
		Layout();
		setDTM();
		refreshDataCart();
		refreshDataProduct();
		this.employeeID =employeeID;
	}
	
	private void setDTM() {
		// 0 nya itu buat Row count kenapa nol karena bukan masuk data
		defaultTableModel = new DefaultTableModel(new String[] {"ProductID","Quantity"},0);
		table.setModel(defaultTableModel);
		defaultTableModelproduct = new DefaultTableModel(new String[] {"ProductID","name","description","price","Stock"},0);
		productTable.setModel(defaultTableModelproduct);
	}
	
	private void refreshDataProduct() {
		defaultTableModelproduct.setRowCount(0);
		Vector<Product> items = ProductHandler.getAllProduct();
		
		for(Product i:items) {
			defaultTableModelproduct.addRow(new Object[] {
					i.getID(),
					i.getName(),
					i.getDescription(),
					i.getPrice(),
					i.getStock()
			});
		}
	}
	
	private void refreshDataCart() {
		defaultTableModel.setRowCount(0);
		Vector<CartItem> items = CartHandler.getListCartItem();
		
		for(CartItem i:items) {
			defaultTableModel.addRow(new Object[] {
					i.getProductID(),
					i.getQuantity()
			});
		}
	}
	
	private void addToCart() {
		int id = (int)productID.getValue();
		int quantity = (int)quantityProduct.getValue();
		
		CartItem error = CartHandler.addToCart(id,quantity);
		if(error == null) {
			JOptionPane.showMessageDialog(this, "ProductID and Quantity Invalid input");
		}else {
			JOptionPane.showMessageDialog(this, "Product "+id+" with quantity "+quantity+" Success added to Cart");
			refreshDataCart();
		}
		
		
	}
	private void removeFromCart() {
		int id = (int)productID.getValue();
		
		Boolean error = CartHandler.deleteItem(id);
		if(error==true) {
			JOptionPane.showMessageDialog(this, "ProductID "+id+" Not in Cart");
		}else {
			JOptionPane.showMessageDialog(this, "Product "+id+" removed from Cart");
			refreshDataCart();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addProduct) {
			addToCart();
			Layout();
			setDTM();
			refreshDataCart();
			refreshDataProduct();
		}else if(e.getSource() == deleteProduct) {
			removeFromCart();
			Layout();
			setDTM();
			refreshDataCart();
			refreshDataProduct();
		}else if(e.getSource() == confirmTransaction) {
			// Confirm ke Transaction
			if(CartHandler.getListCartItem().size()!=0) {
				dispose();
				CartHandler.viewManageCartForm(employeeID);
			}
			
		}
		
	}
}
