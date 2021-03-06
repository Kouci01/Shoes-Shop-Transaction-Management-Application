package ProductView;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import MainframeHandler.MainframeHandler;
import MainframeView.MainframeView;
import Product.Product;
import ProductHandler.ProductHandler;

public class viewManageProductForm extends JFrame implements ActionListener{
	
	private JTable table;
	private DefaultTableModel defaultTableModel;
	private JButton insertProduct;
	private JButton updateProduct;
	private JButton deleteProduct;
	private JTextField insertName;
	private JTextField insertDesc;
	private JTextField insertPrice;
	private JTextField insertStock;
	private JButton Back;
	private void Layout() {
		//Frame
		pack();
		setTitle("Manage Product Form");
		setSize(800,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Setposition mainpanel
		JPanel mainPanel = new JPanel();
		// Set space keatas-bawah dan samping
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		mainPanel.setLayout(new GridLayout(3,1,20,10));
		setContentPane(mainPanel);

		//For table
		JScrollPane scrollPane = new JScrollPane();
		table = new JTable();
		scrollPane.setViewportView(table);
		// Add table that have scrollPane
		add(scrollPane);
		
		JPanel input = new JPanel();
		input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
		
		//Panel buat button
		
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.Y_AXIS));
		
		panelButton.add(Box.createRigidArea(new Dimension(0, 10)));
		insertProduct= new JButton("Insert Product");
		insertProduct.setAlignmentX(CENTER_ALIGNMENT);
		insertProduct.setBackground(Color.GREEN);
		insertProduct.setPreferredSize(new Dimension(80, 80));
		insertProduct.addActionListener(this);
		panelButton.add(insertProduct);
		panelButton.add(Box.createRigidArea(new Dimension(0, 10)));
		
		updateProduct= new JButton("Update Product");
		updateProduct.setAlignmentX(CENTER_ALIGNMENT);
		updateProduct.setPreferredSize(new Dimension(80, 80));
		updateProduct.setBackground(Color.YELLOW);
		updateProduct.addActionListener(this);
		panelButton.add(updateProduct);
		panelButton.add(Box.createRigidArea(new Dimension(0, 10)));
		
		deleteProduct= new JButton("Delete Product");
		deleteProduct.setAlignmentX(CENTER_ALIGNMENT);
		deleteProduct.setPreferredSize(new Dimension(80, 80));
		deleteProduct.setBackground(Color.RED);
		deleteProduct.setForeground(Color.WHITE);
		deleteProduct.addActionListener(this);
		panelButton.add(deleteProduct);
		panelButton.add(Box.createRigidArea(new Dimension(0, 10)));
		
		input.add(panelButton);
		
		add(input);
		
		JPanel buttonBack = new JPanel();
		buttonBack.setLayout(new FlowLayout(FlowLayout.CENTER));
		Back = new JButton("Sign Out");
		Back.addActionListener(this);
		Back.setPreferredSize(new Dimension(250,100));
		Back.setBackground(Color.BLACK);
		Back.setForeground(Color.WHITE);
		buttonBack.add(Back);
		
		add(buttonBack);
		
		setVisible(true);
	}
	
	
	
	public viewManageProductForm() {
		Layout();
		setDTM();
		refreshDataProduct();
	}
	
	private void setDTM() {
		// 0 nya itu buat Row count kenapa nol karena bukan masuk data
		defaultTableModel = new DefaultTableModel(new String[] {"ID","Name", "Description", "Price", "Stock"},0);
		table.setModel(defaultTableModel);
	}

	private void refreshDataProduct() {
		defaultTableModel.setRowCount(0);
		Vector<Product> items = ProductHandler.getAllProduct();
		
		for(Product i:items) {
			defaultTableModel.addRow(new Object[] {
					i.getID(),
					i.getName(),
					i.getDescription(),
					i.getPrice(),
					i.getStock()
			});
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == insertProduct) {
			dispose();
			ProductHandler.viewInsertProductForm();
		}else if(e.getSource() == updateProduct) {
			dispose();
			ProductHandler.viewUpdateProductForm();
		}else if(e.getSource() == deleteProduct) {
			dispose();
			ProductHandler.viewDeleteProductForm();
		}else if(e.getSource() == Back) {
			dispose();
			MainframeHandler.showMainframeView();
		}
	}

}
