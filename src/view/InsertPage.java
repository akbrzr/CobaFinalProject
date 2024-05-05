package view;

import controller.ProductController;
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
	Label kodeLabel, namaLabel, hargaLabel, stokLabel;
	TextField kode, nama, harga, stok;
	Button insertButton;
	
	public InsertPage(Stage stage) {
		this.stage = stage;
		init();
		setLayout();
		setEventHandler();
		scene = new Scene(borderPane, 400, 400);
	}
	
	private void init() {
		borderPane = new BorderPane();
		gridPane = new GridPane();
		kodeLabel = new Label("Kode");
		namaLabel = new Label("Nama");
		hargaLabel = new Label("Harga");
		stokLabel = new Label("Stok");
		kode = new TextField();
		nama = new TextField();
		harga = new TextField();
		stok = new TextField();
		insertButton = new Button("Insert Product");
	}
	
	private void setLayout() {
		gridPane.add(kodeLabel, 0, 0);
		gridPane.add(kode, 1, 0);
		gridPane.add(namaLabel, 0, 1);
		gridPane.add(nama, 1, 1);
		gridPane.add(hargaLabel, 0, 2);
		gridPane.add(harga, 1, 2);
		gridPane.add(stokLabel, 0, 3);
		gridPane.add(stok, 1, 3);
		gridPane.add(insertButton, 0, 4);
		borderPane.setCenter(gridPane);
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
				System.out.println("Invalid price fromat.");
				return;
			}
			
			try {
				productStok = Integer.parseInt(stok.getText());
			} catch (NumberFormatException e) {
				System.out.println("Invalid price fromat.");
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