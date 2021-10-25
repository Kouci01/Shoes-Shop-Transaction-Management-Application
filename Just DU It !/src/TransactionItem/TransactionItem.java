package TransactionItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Transaction.Transaction;

public class TransactionItem {
	private int transactionID;
	private int productID;
	private int quantity;
	
	public TransactionItem(int transactionID, int productID, int quantity) {
		this.transactionID = transactionID;
		this.productID = productID;
		this.quantity = quantity;
	}
	public static Vector<TransactionItem> getTransactionItem(int transactionID){
		Vector<TransactionItem> results = new Vector<TransactionItem>();
		try {
			Statement st = Connect.Connector.connect().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM transactionitem WHERE transactionID = "+transactionID);
			
			while(rs.next()) {
				results.add(new TransactionItem(rs.getInt("transactionID"),rs.getInt("productID"),rs.getInt("quantity")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	public static TransactionItem addTransactionItem(int transactionID, int productID, int quantity) {
		String query = "INSERT INTO transactionitem values(?,?,?)";
		try {
			PreparedStatement ps = Connect.Connector.connect().prepareStatement(query);
			ps.setInt(1, transactionID);
			ps.setInt(2, productID);
			ps.setInt(3, quantity);
			ps.executeUpdate();
			return new TransactionItem(transactionID,productID,quantity);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
