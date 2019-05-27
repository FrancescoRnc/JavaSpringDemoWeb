package it.aiv.boot2.demoweb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController    //per restare in ascolto di chiamate HTTP
public class UserController {
	
	public static int IdCounter;
	
	@Autowired // cerca e prende i componenti da usare
	private UserRepository users;
	
	
	@GetMapping("/users/franci")
	public UserJson getFranci() {
		UserJson user = new UserJson();
		user.Name = "franci";
		user.Surname = "Ronconi";
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserJson> CreateNewUser(@RequestBody UserJson aUser) //RequestBody fa sì che i parametri
	{
		String name = aUser.Name;
		String surname = aUser.Surname;
		int id = IdCounter; 
		
		ResponseEntity<UserJson> error = new ResponseEntity<UserJson>(new UserJson(), HttpStatus.BAD_REQUEST);
		
		if (name == null || name.isEmpty() || 
			surname == null || surname.isEmpty()) return error;
		
		User newUser = new User(id, name, surname);
		
		users.add(newUser);
		
		UserJson json = new UserJson();
		json.Id = id;
		json.Name = name;
		json.Surname = surname;
		
		IdCounter++;
		
		ResponseEntity<UserJson> response = new ResponseEntity<UserJson>(json, HttpStatus.OK);
		return response;
	}

	@GetMapping("/users/all")
	public List<UserJson> AllUsers()
	{
		List<UserJson> result = new ArrayList<UserJson>();
		for (User each : users.all()) {
			UserJson json = new UserJson();
			json.Name = each.GetName();
			json.Surname = each.GetSurname();
			json.Id = each.GetId();
			result.add(json);
		}
		return result;
	}
	
	// Exercises
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserJson> GetUserFromId(@PathVariable int id)
	{
		User user = users.get(id);
		if (user == null) 
			return new ResponseEntity<UserJson>(new UserJson(), 
												HttpStatus.NOT_FOUND);
		
		UserJson json = new UserJson();
		json.Id = user.GetId();
		json.Name = user.GetName();
		json.Surname = user.GetSurname();
		
		return new ResponseEntity<UserJson>(json, HttpStatus.OK);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<UserJson> UpdateUserFromId(@PathVariable int id, @RequestBody UserJson newjson)
	{
		User user = users.get(id);
		if (user == null) 
			return new ResponseEntity<UserJson>(new UserJson(), 
												HttpStatus.NOT_FOUND);
		
		User updatedUser = users.set(user, newjson.Name, newjson.Surname);
		
		UserJson json = new UserJson();
		json.Id = updatedUser.GetId();
		json.Name = updatedUser.GetName();
		json.Surname = updatedUser.GetSurname();
		
		return new ResponseEntity<UserJson>(json, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<UserJson> DeleteUserFromId(@PathVariable int id)
	{
		User user = users.delete(id);
		if (user == null) 
			return new ResponseEntity<UserJson>(new UserJson(), 
												HttpStatus.NOT_FOUND);
		
		UserJson json = new UserJson();
		json.Id = user.GetId();
		json.Name = user.GetName();
		json.Surname = user.GetSurname();
		
		return new ResponseEntity<UserJson>(json, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/all")
	public ResponseEntity<String> DeleteAllUsers()
	{
		int listCount = users.all().size();
		
		users.all().clear();
		IdCounter = 0;
		
		return new ResponseEntity<String>("Number of users deleted: " + 
										  listCount,  HttpStatus.OK);
	}
}
