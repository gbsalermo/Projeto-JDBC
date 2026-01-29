package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement(
					"INSERT INTO SELLER "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "Values "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS); //Para retornar o id do novo vendedor inserido
			
			//Pego os valores de cada interrogação
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3,new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId()); //Navego o objeto pra pegar o Id
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs =  st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
		
		
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
	public List<Seller> findAll() { //Vai buscar todos os vendedores pelo nome de departamento(Reutilizo parte do FindByDepartment
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name");
			
			rs = st.executeQuery(); //O resultado do comando vai cair aqui
			
			List<Seller> list = new ArrayList<>(); //Instancio a lista que vai retornar
			Map<Integer, Department> map = new HashMap<>(); //crio uma estrutura map vazia 
			
			//Aqui eu uso um While pra percorrer meu resultset enquanto tiver um prox
			while(rs.next()) {

				Department dep = map.get(rs.getInt("DepartmentId")); //Testo com o map se o departamento ja existe
				
				//Se o departamento não existir retorna nulo
				if(dep == null) {
						dep = instantiateDepartment(rs); //Agora sim, Instancio um departamento
						map.put(rs.getInt("DepartmentId"), dep); //Salvo o departamento
				}
				
				Seller obj = instantiateSeller(rs, dep); //Instancio agora um vendedor
				list.add(obj); //Adciono o objt a lista
		
			}
			return list; //retorno a lista
		}
		catch(SQLException e) {//Pego qualquer erro
			throw new DbException(e.getMessage());
		}
		finally {// Fecho a conexão
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name");
			
			st.setInt(1, department.getId());
			rs = st.executeQuery(); //O resultado do comando vai cair aqui
			
			List<Seller> list = new ArrayList<>(); //Instancio a lista que vai retornar
			Map<Integer, Department> map = new HashMap<>(); //crio uma estrutura map vazia 
			
			//Aqui eu uso um While pra percorrer meu resultset enquanto tiver um prox
			while(rs.next()) {

				Department dep = map.get(rs.getInt("DepartmentId")); //Testo com o map se o departamento ja existe
				
				//Se o departamento não existir retorna nulo
				if(dep == null) {
						dep = instantiateDepartment(rs); //Agora sim, Instancio um departamento
						map.put(rs.getInt("DepartmentId"), dep); //Salvo o departamento
				}
				
				Seller obj = instantiateSeller(rs, dep); //Instancio agora um vendedor
				list.add(obj); //Adciono o objt a lista
		
			}
			return list; //retorno a lista
		}
		catch(SQLException e) {//Pego qualquer erro
			throw new DbException(e.getMessage());
		}
		finally {// Fecho a conexão
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
