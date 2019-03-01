package principal;


import java.util.Scanner;

import tools.Banco;
import util.Client;
import util.Client_Info;
import util.Listas;

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
				System.out.print("Cadastrar Cliente: \n");
				System.out.print("============================================= \n");
				System.out.print("Número De Clientes A Serem Cadastrados: ");
				while(num == 0) {
					try {
						num = Integer.parseInt(ler.nextLine());
					}
					catch(Exception ex) {
						num = 0;
					}
				}
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
				break;
				
			case "3":
				break;
					
			case "4":
				break;
				
			case "5":
				break;
			
			}
			
		}

		ler.close();
	}

}
