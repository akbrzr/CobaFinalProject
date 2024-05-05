package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import database.DatabaseConnection;
import database.DatabaseSingleton;
import model.Product;

public class ProductController {
	static DatabaseConnection db = DatabaseSingleton.getInstance();
	
	public static Boolean insertProduct(Product product) {
	    String query = "INSERT INTO Products(Kode, Nama, Harga, Stok) VALUES(?, ?, ?, ?)";
	    try {
	        PreparedStatement stmt = db.connection.prepareStatement(query);
	        stmt.setString(1, product.getKode());
	        stmt.setString(2, product.getNama());
	        stmt.setInt(3, product.getHarga());
	        stmt.setInt(4, product.getStok());
	        int rowAffected = stmt.executeUpdate();
	        return rowAffected > 0;
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	
	public static List<Product> getAllProduct(){
		String query = "SELECT * FROM Products";
		List<Product> products = new ArrayList<Product>();
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String kode = rs.getString("Kode");
				String nama = rs.getString("Nama");
				String harga = rs.getString("Harga");
				String stok = rs.getString("Stok");
				Product product = new Product(kode, nama, Integer.parseInt(harga), Integer.parseInt(stok));
				products.add(product);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public static List<String> getAllProductNama(){
		String query = "SELECT Nama FROM Products";
	    List<String> productsNama = new ArrayList<String>();
	    try {
	        PreparedStatement stmt = db.connection.prepareStatement(query);
	        ResultSet rs = stmt.executeQuery();
	        while(rs.next()) {
	            String nama = rs.getString("Nama");
	            productsNama.add(nama);
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return productsNama;
	}

	
	public static Boolean updateProductHarga(String nama, int harga, int stok) {
	    String query = "UPDATE Products SET Harga = ?, Stok = ? WHERE Nama = ?";
	    try {
	        PreparedStatement stmt = db.connection.prepareStatement(query);
	        stmt.setInt(1, harga);
	        stmt.setInt(2, stok);
	        stmt.setString(3, nama);
	        int rowAffected = stmt.executeUpdate();
	        return rowAffected > 0;
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	
	public static Boolean DeleteProduct(String nama) {
		String query = "DELETE FROM Products WHERE Nama = ?";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1, nama);
			int rowAffected = stmt.executeUpdate();
			return rowAffected > 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}