package util;

import java.util.ArrayList;
import java.util.List;

public class Client {
	
	private String name;
	private Integer id_info;
	private Client_Info info;
	private Integer id_client;
	
	public Client(Integer id_client, String name, Integer id_info, Client_Info info) {
		this.id_client = id_client;
		this.name = name;
		this.id_info = id_info;
		this.info = info;
	}

	
	public Integer getId_client() {
		return id_client;
	}
	public String getName() {
		return name;
	}	
	public Integer getId_info() {
		return id_info;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setId_info(Integer id_info) {
		this.id_info = id_info;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_info == null) ? 0 : id_info.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id_info == null) {
			if (other.id_info != null)
				return false;
		} else if (!id_info.equals(other.id_info))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder montar = new StringBuilder();
		
		montar.append("Name: " + name + "\n");
		montar.append("===========================================\n");
		montar.append("Email: " + info.getEmail() + "\n");
		montar.append("Telephone 1: " + info.getTelephone1() + "\n");
		montar.append(info.getTelephone2() != null ? "Telephone 2: " + info.getTelephone2() + "\n" : "");
		montar.append("RG: " + info.getRG() + "\n");
		montar.append("CPF: " + info.getCPF() + "\n");
		montar.append("===========================================\n\n");
		
		return montar.toString();
	}
	
	public List<Object> list(){
		List<Object> list = new ArrayList<>();
		
		list.add(id_client);
		list.add(name);
		list.add(id_info);
		
		return list;
	}
}
