package Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;


public class Transaction {
	private int ID;
	private String purchaseDate;
	private int employeeID;
	private String paymentType;
	
	public Transaction(int iD, String purchaseDate2, int employeeID, String paymentType) { 
		ID = iD;
		this.purchaseDate = purchaseDate2;
		this.employeeID = employeeID;
		this.paymentType = paymentType;
	}
	public static int GetLastTransactionID() {
		int LastTransactionID = 0;
		try {
			Statement st = Connect.Connector.connect().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM transaction WHERE ID=(SELECT MAX(ID) FROM transaction)");
			
			if(rs.next()) {
				LastTransactionID = rs.getInt("ID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return LastTransactionID;
	}
	public static Vector<Transaction> getAllTransaction(){
		Vector<Transaction> results = new Vector<Transaction>();
		try {
			Statement st = Connect.Connector.connect().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM transaction");
			
			while(rs.next()) {
				results.add(new Transaction(rs.getInt("ID"),rs.getString("purchaseDate"),rs.getInt("employeeID"),rs.getString("paymentType")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	public static Vector<Transaction> getTransactionReport(int month, int year){
		Vector<Transaction> results = new Vector<Transaction>();
		try {
			Statement st = Connect.Connector.connect().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM transaction WHERE MONTH(purchaseDate) ="+month+" AND YEAR(purchaseDate) ="+year);
			
			while(rs.next()) {
				results.add(new Transaction(rs.getInt("ID"),rs.getString("purchaseDate"),rs.getInt("employeeID"),rs.getString("paymentType")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
		
	}
	public static Vector<Transaction> getTodayTransaction(){
		Vector<Transaction> results = new Vector<Transaction>();
		try {
			Statement st = Connect.Connector.connect().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM transaction WHERE purchaseDate = DATE(NOW())");
			
			while(rs.next()) {
				results.add(new Transaction(rs.getInt("ID"),rs.getString("purchaseDate"),rs.getInt("employeeID"),rs.getString("paymentType")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	public Transaction addTransaction() {
		String query = "INSERT INTO transaction values(?,?,?,?)";
		try {
			PreparedStatement ps = Connect.Connector.connect().prepareStatement(query);
			ps.setInt(1, ID);
			ps.setString(2, purchaseDate);
			ps.setInt(3, employeeID);
			ps.setString(4, paymentType);
			ps.executeUpdate();
			return new Transaction(ID,purchaseDate,employeeID,paymentType);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	


}
