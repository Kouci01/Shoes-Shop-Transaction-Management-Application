package ProductView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ProductHandler.ProductHandler;

public class viewUpdateForm extends JFrame implements ActionListener{

	private JButton updateProduct1;
	private JTextField insertName;
	private JTextField insertDesc;
	private JTextField insertPrice;
	private JTextField insertStock;
	private int ID;
	
	private void Layout(int ID) {
		pack();
		setTitle("Update Product Form");
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Setposition mainpanel
		JPanel mainPanel = new JPanel();
		// Set space keatas-bawah dan samping
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		mainPanel.setLayout(new GridLayout(1,1,20,10));
		setContentPane(mainPanel);

		
		JPanel input = new JPanel();
		input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
		
		JPanel panelText = new JPanel();
		panelText.setLayout(new GridLayout(5, 2));
		
		JLabel labelid = new JLabel("ID : ");
		labelid.setPreferredSize(new Dimension(150, 50));
		labelid.setHorizontalAlignment(SwingConstants.LEFT);
		panelText.add(labelid);
		
		JLabel labelid1 = new JLabel(String.valueOf(ID));
		labelid1.setPreferredSize(new Dimension(150, 50));
		labelid1.setHorizontalAlignment(SwingConstants.LEFT);
		panelText.add(labelid1);
		
		JLabel labelname = new JLabel("Insert Name :");
		labelname.setPreferredSize(new Dimension(150, 50));
		labelname.setHorizontalAlignment(SwingConstants.LEFT);
		panelText.add(labelname);
		
		insertName = new JTextField();
		panelText.add(insertName);
		
		JLabel labeldesc = new JLabel("Insert Description :");
		labeldesc.setPreferredSize(new Dimension(150, 50));
		labeldesc.setHorizontalAlignment(SwingConstants.LEFT);
		panelText.add(labeldesc);
		
		insertDesc = new JTextField();
		panelText.add(insertDesc);
		
		JLabel labelprice = new JLabel("Insert Price :");
		labelprice.setPreferredSize(new Dimension(150, 50));
		labelprice.setHorizontalAlignment(SwingConstants.LEFT);
		panelText.add(labelprice);
		
		insertPrice = new JTextField();
		panelText.add(insertPrice);
		
		insertPrice.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent key) {
	            String value = insertPrice.getText();
	            int l = value.length();
	            if (key.getKeyChar() >= '0' && key.getKeyChar() <= '9' || key.getKeyChar() == key.VK_BACK_SPACE || key.getKeyChar() == key.VK_DELETE) {
	               insertPrice.setEditable(true);
	            } else {
	               insertPrice.setEditable(false);
	            }
	         }
	      });
		
		JLabel labelstock = new JLabel("Insert Stock :");
		labelstock.setPreferredSize(new Dimension(150, 50));
		labelstock.setHorizontalAlignment(SwingConstants.LEFT);
		panelText.add(labelstock);
		
		insertStock = new JTextField();
		panelText.add(insertStock);
		
		insertStock.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent key) {
	            if (key.getKeyChar() >= '0' && key.getKeyChar() <= '9' || key.getKeyChar() == key.VK_BACK_SPACE || key.getKeyChar() == key.VK_DELETE) {
	               insertStock.setEditable(true);
	            } else {
	               insertStock.setEditable(false);
	            }
	         }
	      });
		
		input.add(panelText);
		
		//Panel buat button
		
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.Y_AXIS));
		
		panelButton.add(Box.createRigidArea(new Dimension(0, 10)));
		updateProduct1= new JButton("Update Product");
		updateProduct1.setAlignmentX(CENTER_ALIGNMENT);
		updateProduct1.setBackground(Color.YELLOW);
		updateProduct1.setPreferredSize(new Dimension(80, 80));
		updateProduct1.addActionListener(this);
		panelButton.add(updateProduct1);
//		panelButton.add(Box.createRigidArea(new Dimension(0, 10)));
		
		input.add(panelButton);
		
		add(input);
		
		setVisible(true);
	}
	
	public viewUpdateForm(int ID) {
		Layout(ID);
		this.ID = ID;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == updateProduct1) {
			if(insertName.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Name Must Not Be Empty!");
			}else if(insertDesc.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Description Must Not Be Empty!");
			}else if(insertPrice.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Price Must Be Numeric Value!");
			}else if(insertStock.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Stock Must Be Numeric Value!");
			}else if(Integer.parseInt(insertPrice.getText()) <= 0) {
				JOptionPane.showMessageDialog(null, "Price Must Above Zero!");
			}else if(Integer.parseInt(insertStock.getText()) <= 0) {
				JOptionPane.showMessageDialog(null, "Stock Must Above Zero!");
			}else {
				ProductHandler.updateProduct(this.ID, insertName.getText(), insertDesc.getText(), Integer.parseInt(insertPrice.getText()), Integer.parseInt(insertStock.getText()));
				JOptionPane.showMessageDialog(null, "Product Updated Successfully!");
				dispose();
				ProductHandler.viewUpdateProductForm();							
			}
		}
	}

}
