package util;

import java.util.List;

public class Listas {

	private List<Category> category;
	private List<Client_Info> client_info;
	private List<Client> client;
	private List<Stock> stock;
	private List<Purchase> purchase;
	
	
	public Listas() {
		
	}
	
	public Listas(List<Category> category, List<Client_Info> client_info, List<Client> client, List<Stock> stock, List<Purchase> purchase) {
		this.category = category;
		this.client_info = client_info;
		this.client = client;
		this.stock = stock;
		this.purchase = purchase;
	}


	
	
	public List<Category> getCategory() {
		return category;
	}
	public List<Client_Info> getClient_info() {
		return client_info;
	}
	public List<Client> getClient() {
		return client;
	}
	public List<Stock> getStock() {
		return stock;
	}
	public List<Purchase> getPurchase() {
		return purchase;
	}
	
	public void addCategory(Category category) {
		this.category.add(category);
	}
	public void addClient_Info(Client_Info client_info) {
		this.client_info.add(client_info);
	}
	public void addClient(Client client) {
		this.client.add(client);
	}
	public void addStock(Stock stock) {
		this.stock.add(stock);
	}
	public void addPurchase(Purchase purchase) {
		this.purchase.add(purchase);
	}
	
	public int getSizeCategory() {
		return category.size();
	}
	public int getSizeClient_Info() {
		return client_info.size();
	}
	public int getSizeClient() {
		return client.size();
	}
	public int getSizeStock() {
		return stock.size();
	}
	public int getSizePurchase() {
		return purchase.size();
	}
	
	public Category getCategory(int index) {
		return category.get(index);
	}
	public Client_Info getClient_Info(int index) {
		return client_info.get(index);
	}
	public Client getClient(int index) {
		return client.get(index);
	}
	public Stock getStock(int index) {
		return stock.get(index);
	}
	public Purchase getPurchase(int index) {
		return purchase.get(index);
	}
}
