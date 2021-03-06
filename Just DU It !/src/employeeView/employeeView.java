package employeeView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import MainframeHandler.MainframeHandler;
import MainframeView.MainframeView;
import employeeHandler.EmployeeController;
import employeeModel.employee;

public class employeeView extends JFrame implements ActionListener {
	
	private JTable table;
	private DefaultTableModel dtm;
	private JSpinner spinnerId;
	private JTextField textFieldUsername;
	private JPasswordField PasswordFieldPass;
	private JTextField textFieldName;
	private JTextField textFieldStatus;
	private JSpinner spinnerSalary;
	private JSpinner spinnerRoleId;
	private JButton buttonInsert;
	private JButton buttonUpdate;
	private JButton buttonDelete;
	private JButton Back;
	private void initLayout() {
		// JFrame
		pack();
		setTitle("Manage Employee Form");
		setLocationRelativeTo(null);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// JPanel ContentPane
		JPanel contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new GridLayout(3, 1, 10, 10));
		setContentPane(contentPane);

		// JScrollPane
		JScrollPane scrollPane = new JScrollPane();
		
		// JTable
		table = new JTable();
		
		scrollPane.setViewportView(table);
		add(scrollPane);
		
		// JPanel CUD
		JPanel panelCud = new JPanel();
		panelCud.setLayout(new GridLayout(1, 2, 10, 10));
		
		// JPanel Fields
		JPanel panelFields = new JPanel();
		panelFields.setLayout(new GridLayout(0, 1));
		
		// JLabel Id
		JLabel labelId = new JLabel("Id: ");
		labelId.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFields.add(labelId);
		
		// JSpinner Id
		spinnerId = new JSpinner();
		panelFields.add(spinnerId);
		
		// JLabel Username
		JLabel labelUsername = new JLabel("Username: ");
		labelUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFields.add(labelUsername);
		
		// JTextField Username
		textFieldUsername = new JTextField();
		panelFields.add(textFieldUsername);

		// JLabel Password
		JLabel labelPassword = new JLabel("Password: ");
		labelPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFields.add(labelPassword);
		
		// JPasswordField Password
		PasswordFieldPass = new JPasswordField();
		panelFields.add(PasswordFieldPass);

		
		// JLabel Name
		JLabel labelName = new JLabel("Name: ");
		labelName.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFields.add(labelName);
		
		// JTextField Name
		textFieldName = new JTextField();
		panelFields.add(textFieldName);
		
		// JLabel Status
		JLabel labelStatus = new JLabel("Status: ");
		labelUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFields.add(labelStatus);
		
		// JTextField Status
		textFieldStatus = new JTextField();
		panelFields.add(textFieldStatus);
		
		// JLabel Salary
		JLabel labelSalary = new JLabel("Salary: ");
		labelSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFields.add(labelSalary);
		
		// JSpinner Salary
		spinnerSalary = new JSpinner();
		panelFields.add(spinnerSalary);

		// JLabel RoleId
		JLabel labelRoleId = new JLabel("RoleId: ");
		labelSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFields.add(labelRoleId);
		
		// JSpinner RoleId
		spinnerRoleId = new JSpinner();
		panelFields.add(spinnerRoleId);
		
		panelCud.add(panelFields);
		
		// JPanel Buttons
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
		
		// JButton Insert
		buttonInsert = new JButton("Add Employee");
		buttonInsert.addActionListener(this);
		panelButtons.add(buttonInsert);
		
		// JButton Update
		buttonUpdate = new JButton("Update Employee");
		buttonUpdate.addActionListener(this);
		panelButtons.add(buttonUpdate);
		
		// JButton Delete
		buttonDelete = new JButton("Fire Employee");
		buttonDelete.addActionListener(this);
		panelButtons.add(buttonDelete);
		
		panelCud.add(panelButtons);
		
		add(panelCud);
		
		JPanel backPanel = new JPanel();
		backPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		Back = new JButton("Sign Out");
		Back.addActionListener(this);
		Back.setPreferredSize(new Dimension(250,100));
		Back.setBackground(Color.BLACK);
		Back.setForeground(Color.WHITE);
		backPanel.add(Back);
		add(backPanel);
		setVisible(true);
	}
	
	public employeeView() {
		initLayout();
		setUpDataModel();
		refreshData();
	}
	
	private void setUpDataModel() {
		dtm = new DefaultTableModel(
			new String[] { "ID", "roleID", "name"
					, "username", "salary", "status"
					, "password" },
			0
		);
		table.setModel(dtm);
	}
	
	private void refreshData() {
		dtm.setRowCount(0);
		Vector<employee> employees = EmployeeController.getAllEmployee();
		
		for(employee i : employees) {
			dtm.addRow(new Object[] {
					i.getID(),
					i.getRoleID(),
					i.getName(),
					i.getUsername(),
					i.getSalary(),
					i.getStatus(),
					i.getPassword()
			});
		}
	}
	
	private void insert() {
		String name = textFieldName.getText();
		String username = textFieldUsername.getText();
		String status = textFieldStatus.getText();
		int salary = (int)spinnerSalary.getValue();
		int RoleId = (int)spinnerRoleId.getValue();
	
		employee error = EmployeeController.addEmployee(username, username, name, status, salary, RoleId);
		if(error == null) {
			JOptionPane.showMessageDialog(this, "Inputted Value Invalid!");
		}else {
			refreshData();
		}
	}
	
	private void update() {
		int id = (int)spinnerId.getValue();
		String name = textFieldName.getText();
		String username = textFieldUsername.getText();
		String password = new String(PasswordFieldPass.getPassword());
		String status = textFieldStatus.getText();
		int salary = (int)spinnerSalary.getValue();
		int RoleId = (int)spinnerRoleId.getValue();
		
		employee error = EmployeeController.updateEmployee(username, password, name, status, salary, RoleId,id);
		if(error == null) {
			JOptionPane.showMessageDialog(this, "Inputted Value Invalid!");
		}else {
			refreshData();
		}
	}
	
	private void delete() {
		int id = (int)spinnerId.getValue();
		
		employee error = EmployeeController.fireEmployee(id);
		if(error == null) {
			JOptionPane.showMessageDialog(this, "Inputted Value Invalid!");
		}else {
			refreshData();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonInsert) {
			insert();
		} else if (e.getSource() == buttonUpdate) {
			update();
		} else if (e.getSource() == buttonDelete) {
			delete();
		} else if(e.getSource() == Back) {
			dispose();
			MainframeHandler.showMainframeView();
		}
	}
}
