package MainframeHandler;

import java.util.Vector;

import MainframeView.MainframeView;
import employeeHandler.EmployeeController;
import employeeModel.employee;

public class MainframeHandler {

	public static void showMainframeView() {
		new MainframeView();
	}
	
	public static boolean employeeLogin(String username, String password) {
		Vector<employee> gae = EmployeeController.getAllEmployee();
		for(int i = 0; i < gae.size(); i++) {
			if(gae.get(i).getUsername().equals(username) && gae.get(i).getPassword().equals(password)) {
				
				return true;
			}
		}
		return false;
	}
	
	public static int employeeRole(String username, String password) {
		int role = 0;
		Vector<employee> gae = EmployeeController.getAllEmployee();
		for(int i = 0; i < gae.size(); i++) {
			if(gae.get(i).getUsername().equals(username) && gae.get(i).getPassword().equals(password)) {
				role = gae.get(i).getRoleID();
			}
		}
		return role;
	}

}
