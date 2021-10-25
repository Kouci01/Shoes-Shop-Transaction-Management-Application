package employeeHandler;

import java.util.Vector;

import RoleHandler.RoleHandler;
import employeeModel.employee;

public class EmployeeController {
	
	public static Vector<employee> getAllEmployee(){
		
		return employee.getAllEmployee();
	}
	
	public static employee getEmployee(String username) {
		return employee.getEmployee(username);
	}
	public static employee addEmployee(String username
										, String password
										, String name
										, String status,
										int salary,
										int roleid) {
		
		if(username.length()<0) return null;
		if(getEmployee(username)!=null) return null;
		if(name.length()<0) return null;
		if(salary < 0) return null;
		if(RoleHandler.getRole(roleid)==null) return null;
		if(!status.equals("Active") && !status.equals("Not Active")) return null;
		
		employee e = new employee(0,roleid,name,username,salary,status,password);
		e.insertEmployee();
		
		return e;
	}
	
	public static employee updateEmployee(String username
			, String password
			, String name
			, String status,
			int salary,
			int roleid, int id) {
		
		if(id<0)return null;
		if(username.length()<0) return null;
		if(getEmployee(username)!=null) return null;
		if(password.length()<0) return null;
		if(name.length()<0) return null;
		if(salary < 0) return null;
		if(RoleHandler.getRole(roleid)==null) return null;
		if(!status.equals("Active") && !status.equals("Not Active")) return null;
		
		
		employee e = new employee(id,roleid,name,username,salary,status,password);
		e.updateEmployee();
		
		return e;
	}
	
	public static employee fireEmployee(int ID) {
		employee e = new employee(ID, 0,null,null,0,null,null);
		if(e.fireEmployee()) {
			return e;
		}
		return null;
		
	}
	public static void viewManageEmployeeForm() {
		new employeeView.employeeView();
	}
}
