package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

//Classe de implementação
public class SellerDaoJDBC implements SellerDao{

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	@Override
	public void insert(Seller obj) {
		
		
	}

	@Override
	public void update(Seller obj) {
		
		
	}

	@Override
	public void deleteById(Integer id) {
		
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "Where seller.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery(); //O resultado do comando vai cair aqui
			if(rs.next()) {
				//Para gerar os objetos corretamente
				Department dep = instantiateDepartment(rs); //Instancio um departamento
				Seller obj = instantiateSeller(rs, dep); //Instancio agora um vendedor
				return obj;
				
				//obs: Todos os getAlgo tem que ter o nome correpondente ao do MySQL para evitar erros
				
			}
			return null; //não existe nenhum vendedor com esse id
		}
		catch(SQLException e) {//Pego qualquer erro
			throw new DbException(e.getMessage());
		}
		finally {// Fecho a conexão
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	//Abaixo não tratei as exceções pois ja trato no Find, entao só propaguei
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {

		//Aqui realizo a instanciação completa do Seller para meu FindByd
		Seller obj = new Seller();
		//Seto os valores dele
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep); //Faço a associação entre objetos
		return obj;
	}
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		//Aqui realizo a instanciação completa do Departamento para meu FindByd
		Department dep = new Department();
		//Seto os valores dele
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}
	@Override
	public List<Seller> findAll() {
		
		return null;
	}

}
