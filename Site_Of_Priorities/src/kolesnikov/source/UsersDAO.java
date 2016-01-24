package kolesnikov.source;

import java.util.List;

public interface UsersDAO {

	public List<Users> getAllUsers();
	public Users getUser(int userId);
	public void updateUser(Users user);
	public void deleteUser(Users user);
	public Users setUser(Users user);
	
}
