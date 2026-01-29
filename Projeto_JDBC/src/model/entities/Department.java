package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable{ //Instancio a interface Serializable
	//Serializable permite tranformar meus objetos em bytes, podendo entao salva-los em arquivos, banco de dados ou transmitir em rede
	

	private static final long serialVersionUID = 1L;
	//Atributos da classe
	private Integer id;
	private String name;
	
	//Construtor vazio padrão
	public Department() {
		
	}
	//Contrutor com argumentos
	public Department(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	//Getters e setters padrão

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//Gero o hashCode e equals para que as comparações no projeto seja pelo conteudo e não pelos ponteiros de memoria(Referência)
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
}
