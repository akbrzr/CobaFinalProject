package view;

import java.util.List;

import controller.ProductController;
import javafx.collections.FXCollections;
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
	private Label namaLabel, hargaLabel, stokLabel;
	private TextField harga, stok;
	private Button updateButton;
	private ComboBox<String> namaComboBox;
	
	public UpdatePage(Stage stage) {
		this.stage = stage;
		init();
		setLayout();
		scene = new Scene(borderPane, 400, 400);
	}
	

	private void init() {
		borderPane = new BorderPane();
		gridPane = new GridPane();
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
		gridPane.add(namaLabel, 0, 0);
		gridPane.add(namaComboBox, 1, 0);
		gridPane.add(hargaLabel, 0, 1);
		gridPane.add(harga, 1, 1);
		gridPane.add(stokLabel, 0, 2);
		gridPane.add(stok, 1, 2);
		gridPane.add(updateButton, 0, 4);
		borderPane.setCenter(gridPane);
	}
	
	public Scene getScene() {
		return this.scene;
	}
}