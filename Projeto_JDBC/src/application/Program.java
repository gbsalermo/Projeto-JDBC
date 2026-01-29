package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		
		//Testando classe Department
		//Department obj = new Department(1, "Books");
		//Testando classe Seller
		//Seller seller = new Seller(21, "Boc", "bob@gmail.com", new Date(), 3000.0, obj);
		
		
		SellerDao sellerDao = DaoFactory.createSellerDao(); //Não uso new SellerDao, assim meu programa conhece apenas a interface e não a implementação
		
		System.out.println("=== TESTE 1: SELLER FINDBYID ====");
		Seller seller = sellerDao.findById(3);
		
		//System.out.println(obj);
		System.out.println(seller);
		
		System.out.println("\n=== TESTE 2: SELLER FINDBYIDDepartment ====");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		
		for(Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TESTE 3: SELLER findAll ====");
	
		list = sellerDao.findAll();
		
		for(Seller obj : list) {
			System.out.println(obj);
		}
		System.out.println("\n=== TESTE 4: SELLER insert ====");
	/*	
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());
		*/
		System.out.println("\n=== TESTE 5: SELLER Updade ====");
		seller = sellerDao.findById(1);
		seller.setName("Bruce Wayne");
		sellerDao.update(seller);
		
		System.out.println("Update completed");
		
		
		
	}

}
