package util;

import java.util.List;
import java.util.stream.Collectors;

public class Purchase {

	private Integer id_purchase;
	private Client cliente;
	private List<Stock> produtos;
	private Integer quantity;
	private Double amount;
	
	public Purchase() {
		
	}
	
	public Purchase(Client cliente, List<Stock> produtos) {
		super();
		this.cliente = cliente;
		this.produtos = produtos;
		this.quantity = produtos.stream().collect(Collectors.summingInt(x -> x.getQuantity()));;
		this.amount = produtos.stream().collect(Collectors.summingDouble(x -> x.getAmount()));
	}

	
	public Integer getId_purchase() {
		return id_purchase;
	}

	public Client getCliente() {
		return cliente;
	}

	public List<Stock> getProdutos() {
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
		
		
		montar.append("Client: " + cliente + "\n");
		montar.append("====================================\n");
		montar.append("Number Of Products On Purchase: " + produtos.size() + "\n");
		montar.append("Quantity Total: " + quantity + "\n");
		montar.append("Amount R$: " + String.format("%.2f %n", amount));
		montar.append("====================================\n\n");
		
		
		return montar.toString();
	}
	
}
