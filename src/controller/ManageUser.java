package controller;

import dao.UserDAO;
import entidade.User;

public class ManageUser {
	
	public void insertNew(String firstname, String lastname, String email, String passwd) {
		User people = new User();

		people.setNome(firstname);
		people.setSobrenome(lastname);
		people.setEmail(email);
		people.setPassword(passwd);
		
		new UserDAO().inserir(people);
		
	}
	
	public boolean authUser(String email, String passwd) {
		User people = new User();

		people.setEmail(email);
		people.setPassword(passwd);
		
		return new UserDAO().authUser(people);
	}

}
