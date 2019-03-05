package principal;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tools.Banco;
import util.Category;
import util.Client;
import util.Client_Info;
import util.Listas;
import util.Purchase;
import util.Stock;

public class Main {

	public static Listas dados;
	public static Scanner ler = new Scanner(System.in);

	
	public static void main(String[] args) {
		
		Banco.criar();
		@SuppressWarnings("unused")
						
		String opc = "";
		
		dados = Banco.carregar();

		
		while(opc.equals("0") == false) {
			opc = "";
			
			System.out.println("     Mercado 2.0  ");
			System.out.println("========================");
			System.out.println("[1]. Cadastrar Cliente");
			System.out.println("[2]. Cadastrar Produto");
			System.out.println("[3]. Cadastrar Categoria");
			System.out.println("[4]. Realizar Compra");
			System.out.println("[5]. Visualizar");
			System.out.println("========================");
			System.out.print("Escolha uma opção ([0] para sair): ");
			opc = ler.nextLine();
			
			switch(opc) {
			
			case "1":
				client();				
				break;
				
			case "2":
				category();
				break;
				
			case "3":
				product();
				break;
				
			case "4":
				purchase();
				break;
				
			case "5":
				view();
				break;
				
			
			}
			
		}

		ler.close();
	}
	
	private static Integer num() {
		@SuppressWarnings("resource")
		Scanner ler = new Scanner(System.in);
		int num = 0;
		while(num == 0 && num <= 0) {
			try {
				num = Integer.parseInt(ler.nextLine());
			}
			catch(Exception ex) {
				num = 0;
			}
		}
		return num;
	}

	private static void client() {
				
		int num = 0;
		System.out.print("\n\n\nCadastrar Cliente: \n");
		System.out.print("============================================= \n");
		System.out.print("Número De Clientes A Serem Cadastrados: ");
		num = num();
		System.out.println("=============================================\n\n\n");
		
		for(int i = 1; i <= num; i++) {
			System.out.println("Client " + i + "#: ");
			System.out.println("=======================================");
			System.out.print("Name Client: ");
			String name = ler.nextLine();
			System.out.print("Email Client: ");
			String email = ler.nextLine();
			int tel = 0;
			while(tel != 1 && tel != 2) {
				System.out.print("Numbers of Telephone (1 / 2): ");
				try {
					tel = Integer.parseInt(ler.nextLine());
				}
				catch(Exception ex) {
					tel = 0;
				}
			}
			System.out.print("Digite um número de telefone (00000-0000): ");
			String telephone1 = ler.nextLine();
			String telephone2 = null;
			if(tel == 2) {
				System.out.print("Digite o segundo número de telefone (00000-0000): ");
				telephone2 = ler.nextLine();
			}
			System.out.print("RG Do Cliente: ");
			String rg = ler.nextLine();
			System.out.print("CPF Do Cliente: ");
			String cpf = ler.nextLine();
			
			dados.addClient_Info(new Client_Info(dados.getSizeClient_Info() + 1, email, telephone1, telephone2, rg, cpf));
			dados.addClient(new Client(dados.getSizeClient() + 1, name, dados.getSizeClient_Info(), dados.getClient_Info(dados.getSizeClient_Info() - 1)));
								
			Banco.Inserir("insert into client_info values (?, ?, ?, ?, ?, ?, NOW())", dados.getClient_Info(dados.getSizeClient_Info() - 1).list());
			Banco.Inserir("insert into client values (? ,? ,?)", dados.getClient(dados.getSizeClient() - 1).list());
			System.out.println("=======================================");
			System.out.println("Cliente Cadastrado Com Sucesso!!!!\n\n");
			
		}
		
		System.out.println("Tecle Enter Para Continuar......\n\n\n");
		ler.nextLine();
	}

	private static void category() {
		System.out.println("\n\n\nCadastrar Produto: ");
		System.out.println("==============================================");
		System.out.print("Número De Produtos A Serem Cadastrados: ");
		int num = num();
		System.out.println("==============================================\n\n\n");
		
		for(int i = 1; i <= num; i++) {
			System.out.println("Product " + i + "#: ");
			System.out.println("=======================================");
			System.out.print("Name Product: ");
			String name = ler.nextLine();
			System.out.print("Price Unity R$: ");
			Double price_unity = Double.parseDouble(ler.nextLine());					
			System.out.print("Quantity: ");
			Integer quantity = Integer.parseInt(ler.nextLine());
			
			//Seleção de Categoria ==============================================//
			int esc = -1;
			int cont = -1;
			while(esc == cont) {
				while(esc < 0 || esc > dados.getSizeCategory() + 1) {
					System.out.println("\nChoose One Category: ");
					System.out.println("===============================");
					cont = 1;
					for(Category ls : dados.getCategory()) {
						System.out.println("[" + ls.getCategoryID() + "]. " + ls.getCategoryName() + " [" + ls.getCategoryLevel() + "]");
						cont++;
					}
					System.out.println("[" + cont + "]. Registration Category");
					System.out.println("===============================");
					System.out.print("Escolha uma opção ([0]. para sair): ");
					try {
						esc = Integer.parseInt(ler.nextLine());
					}
					catch(Exception ex) {
						esc = -1;
					}
				}
				
				if(esc != 0) {
				
					if(esc == cont) {
						System.out.println("\n\n\nRegistration Category: ");
						System.out.println("=======================\n");
						System.out.print("Name Category: ");
						String name2 = ler.nextLine();
						System.out.print("Category Level: ");
						Integer level = Integer.parseInt(ler.nextLine());	
						
						List<Object> ls = new ArrayList<Object>();
						ls.add(name2);
						ls.add(level);
						if(Banco.pesquisar("select * from category where Name_Category = ? and Category_Level = ?", ls) == false) {
							
							dados.addCategory(new Category(dados.getSizeCategory() + 1, name2, level));
							Banco.Inserir("insert into Category values (?, ?, ?)", dados.getCategory(dados.getSizeCategory() - 1).list());
						
							System.out.println("=======================================");
							System.out.println("Category Created Of Sucess!!!\n\n\n");
							esc = -1;
							cont = esc;
						}
						else {
							System.out.println("=======================================");
							System.out.println("Existing Of Category!!!\n\n\n");
						}								
					}
					else {
						for(int p = 0; p < dados.getSizeCategory(); p++) {
							if(dados.getCategory(p).getCategoryID() == esc) {
								dados.addStock(new Stock(dados.getSizeStock() + 1, name, price_unity, quantity, dados.getCategory(p)));
								Banco.Inserir("insert into stock (ID_Product, Name_Product, Price_Unity, Quantity, Category) values (?, ?, ?, ?, ?)", dados.getStock(dados.getSizeStock() - 1).list());
								break;
							}
						}
						System.out.println("=======================================");
						System.out.println("Product Of Registration Sucess!!!\n");
					}
				}
			}
		}			
	}

	private static void product() {
		System.out.println("\n\n\nRegistration Category: ");
		System.out.println("==============================================");
		System.out.print("Number On Category Of Registration: ");
		int num = num();
		System.out.println("==============================================\n\n\n");
		
		for(int i = 1; i <= num; i++) {
			System.out.println("Category " + i + "#: ");
			System.out.println("=======================================");
			System.out.print("Name Category: ");
			String name = ler.nextLine();
			System.out.print("Category Level: ");
			Integer level = Integer.parseInt(ler.nextLine());			
			
			List<Object> ls = new ArrayList<Object>();
			ls.add(name);
			ls.add(level);
			if(Banco.pesquisar("select * from category where Name_Category = ? and Category_Level = ?", ls) == false) {
				
				dados.addCategory(new Category(dados.getSizeCategory() + 1, name, level));
				Banco.Inserir("insert into Category values (?, ?, ?)", dados.getCategory(dados.getSizeCategory() - 1).list());
			
			System.out.println("=======================================");
			System.out.println("Category Created Of Sucess!!!\n\n\n");
			}
			else {
				System.out.println("=======================================");
				System.out.println("Existing Of Category!!!\n\n\n");
			}			
		}					
	}

	private static void purchase() {

		System.out.println("\n\n\nCheckout:");
		System.out.println("=============================================");
		
		System.out.print("Number of purchase to be made: ");
		int num = num();
		System.out.println("=============================================");
		
		for(int i = 0; i < num; i++) {
			System.out.println("\nChoose To Client: ");
			System.out.println("==================");
			int cont = 1;
			for(Client client : dados.getClient()) {
				System.out.println("[" + cont++ + "]. " + client.getName());
			}
			System.out.println("==================");
			
			int id_client = 0;
			while(id_client <= 0 || id_client >= cont) {
				System.out.print("Choose ID: ");
				try {
					id_client = Integer.parseInt(ler.nextLine());
				}
				catch(Exception ex) {
					id_client = 0;
				}
			}
			
			String esc = "";
			
			while(esc.equalsIgnoreCase("s") == false && esc.equalsIgnoreCase("Sim") == false && esc.equalsIgnoreCase("n") == false && esc.equalsIgnoreCase("não") == false) {
				System.out.println("\nChoose To Product: ");
				cont = 1;
				System.out.println("===================");
				for(Stock prod : dados.getStock()) {
					System.out.println("["+ cont++ +"]. " + prod.getName_product());
				}
				System.out.println("===================");
				
				int id_prod = 0;
				while(id_prod <= 0 || id_prod >= cont) {
					System.out.print("Choose ID: ");						
					try {
						id_prod = Integer.parseInt(ler.nextLine());
					}
					catch(Exception ex) {
						id_prod = 0;
					}
				}
				
				--id_prod;
				
				if(dados.getStock(id_prod).getQuantity() > 0) {
					System.out.println("\nProduct Information: ");
					System.out.println("=================================");
					System.out.println("Name Product: " + dados.getStock(id_prod).getName_product());
					System.out.println("Quantity In Stock: " + dados.getStock(id_prod).getQuantity());
					System.out.println("Price Unity R$: " + String.format("%.2f",dados.getStock(id_prod).getPrice_unity()));
					System.out.println("=================================");
					
					System.out.print("\nChoose Quantity Of Product: ");
					int quantity = 0;
					while(quantity <= 0 || quantity > dados.getStock(id_prod).getQuantity()) {
						try {
							quantity = Integer.parseInt(ler.nextLine());
						}
						catch(Exception ex) {
							quantity = 0;
						}
					}
					double value = quantity * dados.getStock(id_prod).getPrice_unity();
					
					dados.getStock(id_prod).setQuantity(quantity);
					
					System.out.println("\nNew Product Information: ");
					System.out.println("=================================");
					System.out.println("Name Product: " + dados.getStock(id_prod).getName_product());
					System.out.println("Quantity In Stock: " + dados.getStock(id_prod).getQuantity());
					System.out.println("Price Unity R$: " + String.format("%.2f",dados.getStock(id_prod).getPrice_unity()));
					System.out.println("=================================\n");
					
					System.out.println("Value Total On Purchase R$: " + String.format("%.2f", value));
					dados.addPurchase(new Purchase(dados.getSizePurchase() + 1,dados.getClient(id_client - 1), new Stock(id_prod + 1, dados.getStock(id_prod).getName_product() , dados.getStock(id_prod).getPrice_unity(), quantity, dados.getStock(id_prod).getCategory()), quantity));
					Banco.Inserir("insert into purchase values (?, ?, ?, ?, ?)", dados.getPurchase(dados.getSizePurchase() - 1).list());							
					System.out.println("\nSuccessful Purchase!!!");
					
					while(esc.equalsIgnoreCase("s") == false && esc.equalsIgnoreCase("Sim") == false && esc.equalsIgnoreCase("n") == false && esc.equalsIgnoreCase("Não") == false) {
						System.out.print("Make Another Purchase?[S/N]: ");
						esc = ler.nextLine();
					}
					
					if(esc.equalsIgnoreCase("s") || esc.equalsIgnoreCase("sim")) {
						esc = "";
					}
					System.out.println("\n\n\n");
				}
				
			}
			
			
		}
	}

	private static void view() {
		int num = 0;

		while(num != 5) {
		
			System.out.println("\n\n\nVisualizar Dados: ");
			System.out.println("================================");
			System.out.println("[1]. View Clients");
			System.out.println("[2]. View Category");
			System.out.println("[3]. View Products");
			System.out.println("[4]. View Purchases");
			System.out.println("[5]. Come Back");
			System.out.println("================================");
			num = 0;
			while(num == 0 || num > 5) {
				try {
					System.out.print("Choose Option: ");
					num = Integer.parseInt(ler.nextLine());
				}
				catch(Exception ex) {
					num = 0;
				}
			}
			
			switch(num) {
			
			case 1:
				System.out.println("\n\nView Clients:");
				System.out.println("===================================");
				dados.getClient().forEach(System.out::println);
				msg();
				break;
				
			case 2:
				System.out.println("\n\nView Category:");
				System.out.println("===================================");
				dados.getCategory().forEach(System.out::println);
				msg();
				break;
				
			case 3:
				System.out.println("\n\nView Products:");
				System.out.println("===================================");
				dados.getStock().forEach(System.out::println);
				msg();
				break;
				
			case 4:
				System.out.println("\n\nView Purchases:");
				System.out.println("===================================");
				dados.getPurchase().forEach(System.out::println);
				msg();
				break;
				
			case 5:
				break;
			
			}
		
		}
		
	}
	
	private static void msg() {
		System.out.println("Dados Carregados Com Sucesso!!!\nTecle Enter Para Continuar......\n\n\n\n");
		ler.nextLine();
	}
}
