package tools;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import util.Category;
import util.Client;
import util.Client_Info;
import util.Listas;
import util.Purchase;
import util.Stock;

public class Banco {

	public static void criar() {
		Connection connect = null;
		PreparedStatement comandos = null;
		Statement query = null;
		ResultSet tabela = null;
		
		try {
			connect = DB.getConnection();
			
			query = connect.createStatement();
			
			//================================= Tabela Category ===============================//
			comandos = connect.prepareStatement(
					"create table if not exists Category("
					+ "" + 
					"ID_Category integer not null primary key,\n" + 
					"Name_Category varchar(25) not null,\n" + 
					"Category_Level integer not null);");
			comandos.execute();
	
			//================================= Tabela Stock ===============================//

			comandos = connect.prepareStatement(
					"create table if not exists Stock (\n" + 
					"\n" + 
					"ID_Product integer primary key not null,\n" + 
					"Name_Product varchar(35) not null,\n" + 
					"Price_Unity double(8,2) not null,\n" + 
					"Quantity integer not null,\n" + 
					"Amount double(8,2) As (Price_Unity * Quantity),\n" + 
					"Category integer not null,\n" + 
					"constraint ID_Category foreign key (Category) references category(ID_Category)\n);"
					);
			comandos.execute();
			
			//================================= Tabela Client Info ===============================//
			
			comandos = connect.prepareStatement(
					"create table if not exists Client_Info(\n" + 
					"\n" + 
					"ID_Info integer primary key unique,\n" + 
					"Email_Client varchar(75) not null unique,\n" + 
					"Telephone1 varchar(10) not null,\n" + 
					"Telephone2 varchar(10),\n" + 
					"RG varchar(12) not null unique,\n" + 
					"CPF varchar(14) not null unique,"
				  + "Registration_Date date not null\n);"
					);
			comandos.execute();
			
			//================================= Tabela Client ===============================//
			
			comandos = connect.prepareStatement(
					"create table if not exists Client(\n" + 
					"\n" + 
					"ID_Client integer primary key,\n" + 
					"Name_Client varchar(35) not null,\n" + 
					"ID_Info integer not null unique,\n" + 
					"constraint ID_Info foreign key (ID_Info) references Client_Info(ID_Info));"
					);
			comandos.execute();
			
			//================================= Tabela Purchase ===============================//
			
			comandos = connect.prepareStatement(
					"create table if not exists Purchase(\r\n" + 
					"\n" + 
					"ID_Purchase integer primary key not null,\r\n" + 
					"ID_Client integer not null,\r\n" + 
					"ID_Product integer not null,\r\n" +
					"Quantity integer not null,\r\n" + 
					"Amount double(8,2) not null,\r\n" + 
					"constraint ID_Client foreign key (ID_Client) references Client(ID_Client),\r\n" + 
					"constraint ID_Product foreign key (ID_Product) references Stock(ID_Product));"
					);
			comandos.execute();
			
			//====================================Tabelas=============================================//
			
			comandos = connect.prepareStatement(
					"INSERT INTO category VALUES"
				  + "(? , ? , ?), (? , ? , ?), (? , ? , ?)");
			
			comandos.setInt(1, 1);
			comandos.setString(2, "Eletrônicos");
			comandos.setInt(3, 1);
			
			comandos.setInt(4, 2);
			comandos.setString(5, "Lazer");
			comandos.setInt(6, 2);
			
			comandos.setInt(7, 3);
			comandos.setString(8, "Cosméticos");
			comandos.setInt(9, 2);
			
			comandos.execute();
			
			//=====================================Linhas Category====================================//
			
			comandos = connect.prepareStatement(
					"INSERT INTO client_info VALUES"
				  + "(?, ?, ?, ?, ?, ?, ?), (? , ?, ?, ?, ?, ?, ?), (? , ?, ?, ?, ?, ?, ?);"
					);
			
			SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
			
			comandos.setInt(1, 1);
			comandos.setString(2, "carlosalvez223@gmail.com");
			comandos.setString(3, "9456-2938");
			comandos.setString(4, null);
			comandos.setString(5, "42.943.412-1");
			comandos.setString(6, "395.901.120-21");
			try{comandos.setDate(7, new Date(form.parse("18/02/2019").getTime()));}catch(Exception ex) {}
			
			comandos.setInt(8, 2);
			comandos.setString(9, "yasmin666santos@hotmail.com");
			comandos.setString(10, "9456-2938");
			comandos.setString(11, "98732-6458");
			comandos.setString(12, "40.328.944-0");
			comandos.setString(13, "997.796.490-42");
			try{comandos.setDate(14, new Date(form.parse("14/01/2019").getTime()));}catch(Exception ex) {}
			
			comandos.setInt(15, 3);
			comandos.setString(16, "brunaorg45@gmail.com");
			comandos.setString(17, "9456-2938");
			comandos.setString(18, "95219-7200");
			comandos.setString(19, "41.875.789-6");
			comandos.setString(20, "255.628.310-66");
			try{comandos.setDate(21, new Date(form.parse("20/02/2019").getTime()));}catch(Exception ex) {}

			
			comandos.execute();
			
			//===========================================Linhas de Clientes==========================//
			
			comandos = connect.prepareStatement(
					"INSERT INTO client VALUES"
				  + "(? , ? , ?), (? , ? , ?), (? , ? , ?);"
					);
			
			comandos.setInt(1, 1);
			comandos.setString(2, "Carlos Alvez");
			comandos.setInt(3, 1);
			
			comandos.setInt(4, 2);
			comandos.setString(5, "Yasmin Costa");
			comandos.setInt(6, 2);
			
			comandos.setInt(7, 3);
			comandos.setString(8, "Bruna Santos");
			comandos.setInt(9, 3);
			
			comandos.execute();
			
			//===========================================Linhas de Stock==========================//
			
			comandos = connect.prepareStatement(
					"INSERT INTO stock (ID_Product, Name_Product, Price_Unity, Quantity, Category) VALUES"
				  + "(?, ?, ? ,? ,?),( ?, ?, ?, ? ,?),( ?, ?, ?, ?, ?);"
					);
			
			comandos.setInt(1, 1);
			comandos.setString(2, "Televisão"); 
			comandos.setDouble(3, 1200.00);
			comandos.setInt(4, 76);
			comandos.setInt(5, 1);
			
			comandos.setInt(6, 2);
			comandos.setString(7, "Notebook HP"); 
			comandos.setDouble(8, 3250.00);
			comandos.setInt(9, 125);
			comandos.setInt(10, 1);
			
			comandos.setInt(11, 3);
			comandos.setString(12, "Shamppo MedCasp"); 
			comandos.setDouble(13, 6.99);
			comandos.setInt(14, 256);
			comandos.setInt(15, 3);
			
			comandos.execute();
			
			//===========================================Compras====================================//
			
query = connect.createStatement();
			
			tabela = query.executeQuery("select * from stock where ID_Product = 1");
			double v1 = 0, v2 = 0, v3 = 0;
			
			while(tabela.next()) {
				v1 = tabela.getDouble(3);
			}
			
			tabela = query.executeQuery("select * from stock where ID_Product = 2");
			while(tabela.next()) {
				v2 = tabela.getDouble(3);
			}
			
			tabela = query.executeQuery("select * from stock where ID_Product = 3");
			while(tabela.next()) {
				v3 = tabela.getDouble(3);
			}
			
			comandos = connect.prepareStatement(
					"INSERT INTO purchase VALUES"
				  + "( ?, ?, ?, ?, ?) , ( ?, ?, ?, ?, ?) , ( ?, ?, ?, ? ,?)"
					);
			comandos.setInt(1, 1);
			comandos.setInt(2, 1);
			comandos.setInt(3, 1);
			comandos.setInt(4, 20);
			comandos.setDouble(5, v1 * 20);
			
			comandos.setInt(6, 2);
			comandos.setInt(7, 2);
			comandos.setInt(8, 2);
			comandos.setInt(9, 13);
			comandos.setDouble(10, v2 * 13);
		
			comandos.setInt(11, 3);
			comandos.setInt(12, 3);
			comandos.setInt(13, 3);
			comandos.setInt(14, 26);
			comandos.setDouble(15, v3 * 26);
			
			comandos.execute();
						
		}
		catch(SQLException ex){
		}
		finally {
			DB.closeStatement(query);
			DB.closeConnection();
		}
	}
	
	public static Listas carregar() {
		
		List<Category> category = new ArrayList<>();
		List<Client_Info> client_info = new ArrayList<>();
		List<Client> client = new ArrayList<>();
		List<Stock> stock = new ArrayList<>();
		List<Purchase> purchase = new ArrayList<>();
		
		try {
			
			Connection connect = DB.getConnection();
			Statement comands = connect.createStatement();
			ResultSet query = comands.executeQuery("select * from category");
			
			while(query.next()) {
				category.add(new Category(query.getInt(1), query.getString(2), query.getInt(3)));
			}
			
			query = comands.executeQuery("select * from Client_Info");
			
			while(query.next()) client_info.add(new Client_Info(query.getInt(1), query.getString(2), query.getString(3) , query.getString(4), query.getString(5), query.getString(6)));
			
			query = comands.executeQuery("select * from Client");
			
			while(query.next()) {
				client.add(new Client(query.getInt(1), query.getString(2), query.getInt(3), client_info.get(query.getInt(1) - 1)));
			}
			
			query = comands.executeQuery("select * from Stock");
			
			while(query.next()) {
				int i = 0;
				
				for(int p = 0; p < category.size(); p++){
					if(query.getInt(6) == category.get(p).getCategoryID()) {
						i = p;
					}
				}
				
				stock.add(new Stock(query.getInt(1), query.getString(2), query.getDouble(3), query.getInt(4), query.getDouble(5), category.get(i)));
			}
			
			query = comands.executeQuery("select * from purchase");
			
			while(query.next()) {
				for(Client i : client) {
					List<Stock> compras = new ArrayList<>();
					if(i.getId_client() == query.getInt(2)) {
						for(Stock i2 : stock) {
							if(i2.getId_product() == query.getInt(3)) {
								compras.add(i2);
							}
						}
						purchase.add(new Purchase(i, compras));
					}
				}
			}
			
		}
		catch(Exception ex){
			System.out.println("Error: " + ex.getMessage());
		}
		finally {
			DB.closeConnection();
		}
				
		return new Listas(category, client_info, client, stock, purchase);
	}

	public static Integer Inserir(String query, List<Object> parameters) {
		int lines = 0;
		try {
			
			Connection connect = DB.getConnection();
			PreparedStatement comands = connect.prepareStatement(query); 
			int num = 1;
			
			for(Object i : parameters) {	
				comands.setObject(num, i);
				num++;
			}
			
			lines = comands.executeUpdate();
			
		}
		catch(Exception ex){
			System.out.println("Error: " + ex.getMessage());
		}
		finally {
			DB.closeConnection();
		}
		return lines;
	}
}
