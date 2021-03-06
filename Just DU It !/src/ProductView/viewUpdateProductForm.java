package ProductView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Product.Product;
import ProductHandler.ProductHandler;


public class viewUpdateProductForm extends JFrame implements ActionListener{

	private JTable table;
	private DefaultTableModel defaultTableModel;
	private JButton updateProduct;
	
	private void Layout() {
		//Frame
		pack();
		setTitle("Update Product Form");
		setSize(800,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Setposition mainpanel
		JPanel mainPanel = new JPanel();
		// Set space keatas-bawah dan samping
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		mainPanel.setLayout(new GridLayout(2,1,20,10));
		setContentPane(mainPanel);

		//For table
		JScrollPane scrollPane = new JScrollPane();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTable table = (JTable) e.getSource();
				int getIndex = table.getSelectedRow();
				int ID = Integer.parseInt(table.getValueAt(getIndex, 0).toString());
				dispose();
				new viewUpdateForm(ID);
			}
		});
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		add(scrollPane);
		
		JPanel input = new JPanel();
		input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
		
		//Panel buat button
		
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.Y_AXIS));
				
		updateProduct= new JButton("Back To Manage Product Form");
		updateProduct.setAlignmentX(CENTER_ALIGNMENT);
		updateProduct.setPreferredSize(new Dimension(80, 80));
		updateProduct.setBackground(Color.YELLOW);
		updateProduct.addActionListener(this);
		panelButton.add(updateProduct);
		panelButton.add(Box.createRigidArea(new Dimension(0, 10)));
		
		
		input.add(panelButton);
		
		add(input);
		
		setVisible(true);
	}
	
	public viewUpdateProductForm() {
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
		if(e.getSource() == updateProduct) {
			dispose();
			ProductHandler.viewManageProductForm();	
		}
	}

}
