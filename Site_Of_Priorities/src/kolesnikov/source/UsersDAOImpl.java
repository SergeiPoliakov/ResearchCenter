package kolesnikov.source;

import java.util.ArrayList;
import java.util.List;

public class UsersDAOImpl implements UsersDAO {

	private List<Users> users = null;
	
	public UsersDAOImpl() {
		users = new ArrayList<Users>();
		
	}
	
	@Override
	public List<Users> getAllUsers() {
		return users;
	}

	@Override
	public Users getUser(int userIndex) {
		return users.get(userIndex);
	}

	@Override
	public void updateUser(Users user) {
		
	}

	@Override
	public void deleteUser(Users user) {
		
	}

	@Override
	public Users setUser(Users user) {
		return null;
	}

}
