package util;

public class Stock {

	private Integer id_product;
	private String name_product;
	private Double price_unity;
	private Integer quantity;
	private Double amount;
	private Category category;
	
	public Stock() {
		
	}
	
	public Stock(Integer id_product, String name_product, Double price_unity, Integer quantity, Double amount, Category category) {
		this.id_product = id_product;
		this.name_product = name_product;
		this.price_unity = price_unity;
		this.quantity = quantity;
		this.amount = amount;
		this.category = category;
	}

	
	public Integer getId_product() {
		return id_product;
	}

	public String getName_product() {
		return name_product;
	}
	public Double getPrice_unity() {
		return price_unity;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public Double getAmount() {
		return amount;
	}
	public String getCategoryName() {
		return category.getCategoryName();
	}
	public Integer getCategoryLevel() {
		return category.getCategoryLevel();
	}
	
	public void setId_product(Integer id_product) {
		this.id_product = id_product;
	}

	public void setName_product(String name_product) {
		this.name_product = name_product;
	}

	public void setPrice_unity(Double price_unity) {
		this.price_unity = price_unity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		StringBuilder montar = new StringBuilder();
	
		montar.append("ID " + id_product + "#: \n");
		montar.append("=====================================\n");
		montar.append("Name: " + name_product + "\n");
		montar.append("=====================================\n");
		montar.append("Quantity: " + quantity + "\n");
		montar.append("Price Unity R$: " + String.format("%.2f %n", price_unity));
		montar.append("Amount R$: " + String.format("%.2f %n", amount));
		montar.append("Category: " + getCategoryName() + "\n");
		montar.append("=====================================\n\n");
		
		return montar.toString();
	}
	
	
}
