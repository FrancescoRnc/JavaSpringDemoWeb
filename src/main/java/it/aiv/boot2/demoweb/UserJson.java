package it.aiv.boot2.demoweb;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserJson {
	
	@JsonProperty // Per rendere il parametro Jsonabile
	public String Name;
	@JsonProperty
	public String Surname;
	@JsonProperty
	public int Id;
	
}
