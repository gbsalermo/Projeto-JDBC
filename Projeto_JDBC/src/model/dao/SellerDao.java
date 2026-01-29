package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDao { //Interface de operações para o Seller
	
	void insert (Seller obj); //Essa vai ser uma operação responsavel por enviar ao banco de dados o objt que eu enviar como parametro de entrada
	void update(Seller obj); //Responsavel pela atualização
	void deleteById(Integer id); //Responsavel pela remoção
	Seller findById(Integer id); //Responsavel por pegar o id e consultar no banco de dados um obj com esse id(Se nao existir retorna nulo)
	List<Seller> findAll();
}
