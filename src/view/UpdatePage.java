package view;

import java.util.List;

import controller.ProductController;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Product;

public class UpdatePage {
	private Stage stage;
	private Scene scene;
	private BorderPane borderPane;
	private GridPane gridPane;
	private Label footerLabel, namaLabel, hargaLabel, stokLabel;
	private TextField harga, stok;
	private Button updateButton;
	private ComboBox<String> namaComboBox;
	
	public UpdatePage(Stage stage) {
		this.stage = stage;
		init();
		setLayout();
		scene = new Scene(borderPane, 500, 250);
	}
	

	private void init() {
		borderPane = new BorderPane();
		gridPane = new GridPane();
		footerLabel = new Label("Update Page");
		namaLabel = new Label("Nama");
		hargaLabel = new Label("Harga");    
		stokLabel = new Label("Stok");
		namaComboBox = new ComboBox<>();
		harga = new TextField();
		stok = new TextField();
		updateButton = new Button("Update Product");
		List<String> products = ProductController.getAllProductNama();
		namaComboBox.setItems(FXCollections.observableArrayList(products));
		updateButton.setOnAction(event -> {
			String selectedNama = namaComboBox.getValue();
			String productHarga = harga.getText();
			String productStok = stok.getText();
			boolean updated = ProductController.updateProductHarga(selectedNama, Integer.parseInt(productHarga), Integer.parseInt(productStok));
			
			if(updated) {
				HomePage homePage = new HomePage(stage);
				stage.setScene(homePage.getScene());
				stage.show();
			}
		});
	}
	
	private void setLayout() {
		gridPane.add(footerLabel, 0, 0);
		gridPane.add(namaLabel, 0, 1);
		gridPane.add(namaComboBox, 1, 1);
		gridPane.add(hargaLabel, 0, 2);
		gridPane.add(harga, 1, 2);
		gridPane.add(stokLabel, 0, 3);
		gridPane.add(stok, 1, 3);
		gridPane.add(updateButton, 0, 6);

		harga.setStyle("-fx-background-color: #E5E5E5;");
		stok.setStyle("-fx-background-color: #E5E5E5;");
		
		namaLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
		hargaLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
		stokLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

		harga.setPromptText("Masukkan harga produk");
		stok.setPromptText("Masukkan stok produk");
		
		borderPane.setStyle("-fx-background-color: #A5A7B6;");
		footerLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #333333;");
		updateButton.setStyle("-fx-background-color: #ffd966; -fx-text-fill: black; -fx-font-weight: bold; -fx-padding: 10px 20px;");
		
		borderPane.setCenter(gridPane);
		
		gridPane.setAlignment(Pos.TOP_CENTER);
		
		gridPane.setHgap(20);
		gridPane.setVgap(10);
	}
	
	public Scene getScene() {
		return this.scene;
	}
}