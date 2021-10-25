package employeeModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Connect.Connector;


public class employee {
	private int ID;
	private int roleID;
	private String name;
	private String username;
	private int salary;
	private String status;
	private String password;
	
	
	
	public employee(int iD, int roleID, String name, String username, int salary, String status, String password) {
		ID = iD;
		this.roleID = roleID;
		this.name = name;
		this.username = username;
		this.salary = salary;
		this.status = status;
		this.password = password;
	}

	public static Vector<employee> getAllEmployee(){
		Vector<employee> results = new Vector<employee>();
		
		try {
			Statement stat = Connector.connect().createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM employee");
			
			while(rs.next()) {
				results.add(new employee(
					rs.getInt("ID"), 
					rs.getInt("roleID"),
					rs.getString("name"),
					rs.getString("username"),
					rs.getInt("salary"),
					rs.getString("status"),
					rs.getString("password")
				));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;
	}
	public static employee getEmployee(String username) {
		employee em;
		try {
			Statement stat = Connector.connect().createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM employee WHERE username = '"+username+"'");
			
			if(rs.next()) {
				em = new employee(
					rs.getInt("ID"), 
					rs.getInt("roleID"),
					rs.getString("name"),
					rs.getString("username"),
					rs.getInt("salary"),
					rs.getString("status"),
					rs.getString("password")
				);
				return em;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	public employee insertEmployee() {
		String query = "insert into employee values (null, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = Connector.connect().prepareStatement(query);
			ps.setInt(1, roleID);
			ps.setString(2, name);
			ps.setString(3, username);
			ps.setInt(4, salary);
			ps.setString(5, status);
			ps.setString(6, password);
			ps.executeUpdate();
			return new employee(0,roleID,name,username,salary,status,password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public employee updateEmployee() {
		String query ="update employee set roleID=?,name=?, username=?,salary=?,status=?,password=? where ID=?";
		try {
			PreparedStatement ps = Connector.connect().prepareStatement(query);
			ps.setInt(1, roleID);
			ps.setString(2, name);
			ps.setString(3, username);
			ps.setInt(4, salary);
			ps.setString(5, status);
			ps.setString(6, password);
			ps.setInt(7, ID);
			ps.executeUpdate();
			return new employee(ID,roleID,name,username,salary,status,password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	public boolean fireEmployee() {
		String query = "update employee set status=? where ID=?";
		
		try {
			PreparedStatement ps = Connector.connect().prepareStatement(query);
			ps.setString(1, "Not Active");
			ps.setInt(2, ID);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
		
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	
	
}
