package it.aiv.boot2.demoweb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component 
public class UserRepository {

	private List<User> _users = new ArrayList<User>();
	
	
	
	public void add(User aUser)
	{
		_users.add(aUser);
	}
	
	public List<User> all()
	{
		return _users;
	}
	
	public User get(int id)
	{
		return _users.stream().filter(user -> user._id == id).findFirst().get();
	}
	
	public User delete(int id)
	{
		User user = get(id);
		_users.remove(user);
		return user;
	}
	
	public User set(int id, String name, String surname)
	{
		User user = get(id);
		if (name != "" && name != null) user.SetName(name);
		if (surname != "" && surname != null) user.SetSurname(surname);
		
		return user;
	}
	
	public User set(User user, String name, String surname)
	{		
		if (name != "" && name != null) user.SetName(name);
		if (surname != "" && surname != null) user.SetSurname(surname);
		
		return user;
	}
}
