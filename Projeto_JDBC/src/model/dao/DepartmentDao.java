package model.dao; //O MODEL COMPREENDE NAO SO AS ENTIDADES, MAS TAMBÉM AS CLASSES QUE FAZEM TRANFORMAÇÃO NELA POR ISSO USAMOS MODEL.

import java.util.List;
 
import model.entities.Department;

public interface DepartmentDao { //Interface de operações para o Department
	
	void insert (Department obj); //Essa vai ser uma operação responsavel por enviar ao banco de dados o objt que eu enviar como parametro de entrada
	void update(Department obj); //Responsavel pela atualização
	void deleteById(Integer id); //Responsavel pela remoção
	Department findById(Integer id); //Responsavel por pegar o id e consultar no banco de dados um obj com esse id(Se nao existir retorna nulo)
	List<Department> findAll();
}
