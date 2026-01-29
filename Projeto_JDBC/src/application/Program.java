package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		
		//Testando classe Department
		Department obj = new Department(1, "Books");
		//Testando classe Seller
		
		Seller seller = new Seller(21, "Boc", "bob@gmail.com", new Date(), 3000.0, obj);
		
		
		//System.out.println(obj);
		System.out.println(seller);
		

		
	}

}
