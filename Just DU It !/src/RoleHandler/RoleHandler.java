package RoleHandler;

import java.util.Vector;

import RoleModel.Role;

public class RoleHandler {

	public static Vector<Role> getAllRole(){
		return Role.getAllRole();
	}
	public static Role getRole(int ID) {
		return Role.getRole(ID);
	}
}
