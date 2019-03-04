package principal;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tools.Banco;
import util.Category;
import util.Client;
import util.Client_Info;
import util.Listas;
import util.Stock;

public class Main {

	public static void main(String[] args) {

		Scanner ler = new Scanner(System.in);
		
		Banco.criar();
		@SuppressWarnings("unused")
		Listas dados = Banco.carregar();
		
		String opc = "";
		
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
				
				
				break;
				
			case "2":
				System.out.println("\n\n\nCadastrar Produto: ");
				System.out.println("==============================================");
				System.out.print("Número De Produtos A Serem Cadastrados: ");
				num = num();
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
				
				break;
				
			case "3":
				
				System.out.println("\n\n\nRegistration Category: ");
				System.out.println("==============================================");
				System.out.print("Number On Category Of Registration: ");
				num = num();
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
				
				
				break;
					
			case "4":
				break;
				
			case "5":
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

}
