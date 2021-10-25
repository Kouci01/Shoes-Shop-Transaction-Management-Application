package RoleModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


public class Role {
	private int ID;
	private String name;
	
	public static Vector<Role> getAllRole(){
		Vector<Role> results = new Vector<Role>();
		try {
			Statement st = Connect.Connector.connect().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM role");
			
			while(rs.next()) {
				results.add(new Role(rs.getInt("ID"),rs.getString("name")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	public static Role getRole(int ID){
		try {
			Statement st = Connect.Connector.connect().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM role WHERE ID = "+ ID);
			
			if(rs.next()) {
				return new Role(rs.getInt("ID"),rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	public Role(int iD, String name) {
		ID = iD;
		this.name = name;
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
	
	

}
