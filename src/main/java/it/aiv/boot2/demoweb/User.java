package it.aiv.boot2.demoweb;

public class User {

	int _id;
	String _name;
	String _surname;
	public User(int id, String name, String surname) {
		_id = id;
		_name = name;
		_surname = surname;
	}

	public String GetName() {
		return _name;
	}
	public String GetSurname() {
		return _surname;
	}
	public int GetId() {
		return _id;
	}
	
	public void SetName(String newname) {
		_name = newname;
	}
	public void SetSurname(String newsurname) {
		_surname = newsurname;
	}
}
