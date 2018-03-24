package controller;

import dao.UserDAO;
import entidade.Product;
import entidade.User;

public class ManageProduct {
	
	public void insertNew(String name, String description, String category, float price, byte[] image) {
		Product item = new Product();
		
		item.setName(name);
		item.setDescription(description);
		item.setCategory(category);
		item.setPrice(price);
		item.setImage(image);
		
		new UserDAO().inserir(people);
		
	}
	
}
