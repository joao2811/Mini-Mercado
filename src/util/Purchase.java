package util;

import java.util.ArrayList;
import java.util.List;

public class Purchase {

	private Integer id_purchase;
	private Client cliente;
	private Stock produtos;
	private Integer quantity;
	private Double amount;
	
	public Purchase() {
		
	}
	
	public Purchase(Integer id, Client cliente, Stock produtos, Integer Quantity) {
		super();
		this.id_purchase = id;
		this.cliente = cliente;
		this.produtos = produtos;
		this.quantity = Quantity;
		this.amount = produtos.getPrice_unity() * Quantity;
	}

	
	public Integer getId_purchase() {
		return id_purchase;
	}

	public Client getCliente() {
		return cliente;
	}

	public Stock getProdutos() {
		return produtos;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Double getAmount() {
		return amount;
	}


	
	@Override
	public String toString() {
		StringBuilder montar = new StringBuilder();
		
		
		montar.append("Client: " + cliente.getName() + "\n");
		montar.append("====================================\n");
		montar.append("Name Product: " + produtos.getName_product() + "\n");
		montar.append("Quantity Total: " + quantity + "\n");
		montar.append("Price Unity R$: " + String.format("%.2f %n", produtos.getPrice_unity()));
		montar.append("Amount R$: " + String.format("%.2f %n", amount));
		montar.append("====================================\n\n");
		
		
		return montar.toString();
	}
	
	public List<Object> list(){
		List<Object> lista = new ArrayList<>();
		
		lista.add(id_purchase);
		lista.add(cliente.getId_client());
		lista.add(produtos.getId_product());
		lista.add(quantity);
		lista.add(amount);
		
		return lista;
	}
	
}
