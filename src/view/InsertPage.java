package view;

import controller.ProductController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Product;

public class InsertPage {
	Stage stage;
	Scene scene;
	BorderPane borderPane;
	GridPane gridPane;
	Label footerLabel, kodeLabel, namaLabel, hargaLabel, stokLabel;
	TextField kode, nama, harga, stok;
	Button insertButton;
	
	public InsertPage(Stage stage) {
		this.stage = stage;
		init();
		setLayout();
		setEventHandler();
		scene = new Scene(borderPane, 500, 300);
	}
	
	private void init() {
		borderPane = new BorderPane();
		gridPane = new GridPane();
		footerLabel = new Label("Insert Page");
		kodeLabel = new Label("Kode :");
		namaLabel = new Label("Nama :");
		hargaLabel = new Label("Harga :");
		stokLabel = new Label("Stok :");
		kode = new TextField();
		nama = new TextField();
		harga = new TextField();
		stok = new TextField();
		insertButton = new Button("Insert Product");
	}
	
	private void setLayout() {
		gridPane.add(footerLabel, 0, 0);
		gridPane.add(kodeLabel, 0, 1);
		gridPane.add(kode, 1, 1);
		gridPane.add(namaLabel, 0, 2);
		gridPane.add(nama, 1, 2);
		gridPane.add(hargaLabel, 0, 3);
		gridPane.add(harga, 1, 3);
		gridPane.add(stokLabel, 0, 4);
		gridPane.add(stok, 1, 4);
		gridPane.add(insertButton, 0, 8);
		
		kode.setStyle("-fx-background-color: #E5E5E5;");
		nama.setStyle("-fx-background-color: #E5E5E5;");
		harga.setStyle("-fx-background-color: #E5E5E5;");
		stok.setStyle("-fx-background-color: #E5E5E5;");
		
		kodeLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
		namaLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
		hargaLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
		stokLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

		kode.setPromptText("Masukkan kode produk");
		nama.setPromptText("Masukkan nama produk");
		harga.setPromptText("Masukkan harga produk");
		stok.setPromptText("Masukkan stok produk");
		
		borderPane.setStyle("-fx-background-color: #A5A7B6;");
		footerLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #333333;");
		insertButton.setStyle("-fx-background-color: #76E7A7; -fx-text-fill: black; -fx-font-weight: bold; -fx-padding: 10px 20px;");
		
		borderPane.setCenter(gridPane);
		
		gridPane.setAlignment(Pos.TOP_CENTER);
		
		gridPane.setHgap(20);
		gridPane.setVgap(10);
	}
	
	private void setEventHandler() {
		insertButton.setOnAction(event -> {
			String productKode = kode.getText();
			String productNama = nama.getText();
			int productHarga;
			int productStok;
			try {
				productHarga = Integer.parseInt(harga.getText());
			} catch (NumberFormatException e) {
				System.out.println("Invalid price format.");
				return;
			}
			
			try {
				productStok = Integer.parseInt(stok.getText());
			} catch (NumberFormatException e) {
				System.out.println("Invalid price format.");
				return;
			}
			
			if(productKode.isEmpty() || productNama.isEmpty()) {
				System.out.println("Please fill all fields!!!");
			}
			
			Product product = new Product(productKode, productNama, productHarga, productStok);
			boolean inserted = ProductController.insertProduct(product);
			if(inserted) {
				HomePage homePage = new HomePage(stage);
				stage.setScene(homePage.getScene());
				stage.show();
 			}
		});
	}
	
	public Scene getScene() {
		return this.scene;
	}
}