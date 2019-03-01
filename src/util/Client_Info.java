package util;

import java.util.ArrayList;
import java.util.List;

public class Client_Info {

	private Integer id_info;
	private String Email;
	private String Telephone1;
	private String Telephone2;
	private String RG;
	private String CPF;
	
	public Client_Info() {		
	}
	
	public Client_Info(Integer id_info,String Email_Info, String Telephone1, String Telephone2, String RG, String CPF) {
		this.id_info = id_info;
		this.Email = Email_Info;
		this.Telephone1 = Telephone1;
		this.Telephone2 = Telephone2;
		this.RG = RG;
		this.CPF = CPF;
	}

	public String getEmail() {
		return Email;
	}

	public String getTelephone1() {
		return Telephone1;
	}

	public String getTelephone2() {
		return Telephone2;
	}

	public String getRG() {
		return RG;
	}

	public String getCPF() {
		return CPF;
	}

	public Integer getId() {
		return id_info;
	}
	
	public void setEmail(String email) {
		Email = email;
	}

	public void setTelephone1(String telephone1) {
		Telephone1 = telephone1;
	}

	public void setTelephone2(String telephone2) {
		Telephone2 = telephone2;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public List<Object> list(){
		List<Object> lista = new ArrayList<>();
		
		lista.add(id_info);
		lista.add(Email);
		lista.add(Telephone1);
		lista.add(Telephone2);
		lista.add(RG);
		lista.add(CPF);
		
		return lista;
	}
}
