package Product;

import java.sql.*;
import java.util.Vector;

import Connect.Connector;

public class Product {

	private int ID;

	private String name;
	private String description;
	private int price;
	private int stock;
	
	public Product(int ID, String name, String description, int price, int stock) {
		this.ID = ID;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}
	
	public static Vector<Product> getAllProduct(){
		Vector<Product> results = new Vector<Product>();
		try {
			Statement st = Connect.Connector.connect().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM product");
			
			while(rs.next()) {
				results.add(new Product(rs.getInt("ID"), rs.getString("name"), rs.getString("description"), rs.getInt("price"), rs.getInt("stock")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return results;
	}
	
	public static Product getProduct(int ID) {
		Product result = new Product(ID, null, null, 0, 0);
		String query = "SELECT * FROM product WHERE ID ="+ID;
		try {
			Statement st = Connector.connect().createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				result = new Product(ID, rs.getString("name"), rs.getString("description"), rs.getInt("price"), rs.getInt("stock"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Product insertProduct() {
		String query = "INSERT INTO product values(null,?,?,?,?)";
		try {
			PreparedStatement ps = Connector.connect().prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setInt(3, price);
			ps.setInt(4, stock);
			ps.executeUpdate();
			return new Product(ID, name, description, price, stock);
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public Product updateProduct() {
		String query = "update product set name=?, description=?, price=?, stock=? where id=?";
		try {
			PreparedStatement ps = Connector.connect().prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setInt(3, price);
			ps.setInt(4, stock);
			ps.setInt(5, ID);
			ps.executeUpdate();
			
			return new Product(ID,name, description, price, stock);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean deleteProduct() {
		String query = "delete from product where id=?";
		try {
			PreparedStatement ps = Connector.connect().prepareStatement(query);
			ps.setInt(1, ID);
			
			return ps.executeUpdate() ==1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
	}
	
	public static void updateProductStock(int ID, int stock) {
		String query = "update product set stock=? where id=?";
		try {
			PreparedStatement ps = Connector.connect().prepareStatement(query);
			ps.setInt(1, stock);
			ps.setInt(2, ID);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
}
