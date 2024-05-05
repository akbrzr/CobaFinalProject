package view;

import java.util.List;

import controller.ProductController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Product;

public class HomePage {
	protected Stage stage;
	protected Scene scene;
	protected BorderPane borderPane;
	protected GridPane gridPane;
	protected Label welcomeLabel;
	protected TableView<Product> productTable;
	protected Button insertButton;
	protected Button updateButton;
	protected Button deleteButton;
	protected ObservableList<Product> listProducts;
	
	public HomePage(Stage stage) {
		this.stage = stage;
		initialize();
		setLayout();
		this.scene = new Scene(borderPane, 600, 600);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
	private void initialize() {
		List<Product> products = ProductController.getAllProduct();
		listProducts = FXCollections.observableArrayList(products);
		borderPane = new BorderPane();
		gridPane = new GridPane();
		welcomeLabel = new Label("Hello");
		TableColumn<Product, String> kodeCol = new TableColumn<>("Kode");
		kodeCol.setCellValueFactory(cell -> cell.getValue().kodeProperty());
		TableColumn<Product, String> namaCol = new TableColumn<>("Nama");
		namaCol.setCellValueFactory(cell -> cell.getValue().namaProperty());
		TableColumn<Product, String> hargaCol = new TableColumn<>("Harga");
		hargaCol.setCellValueFactory(cell -> cell.getValue().hargaProperty());
		TableColumn<Product, String> stokCol = new TableColumn<>("Stok");
		stokCol.setCellValueFactory(cell -> cell.getValue().stokProperty());
		
		productTable = new TableView<>();
		productTable.setItems(listProducts);
		productTable.getColumns().addAll(kodeCol, namaCol, hargaCol, stokCol);
		insertButton = new Button("Insert new Product");
		updateButton = new Button("Update new Product");
		deleteButton = new Button("Delete new Product");
		
		insertButton.setOnAction(event -> {
			InsertPage insertPage = new InsertPage(stage);
			stage.setScene(insertPage.getScene());
			stage.show();
		});
		
		updateButton.setOnAction(event -> {
			Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
			if(selectedProduct != null) {
				UpdatePage updatepage = new UpdatePage(stage);
				stage.setScene(updatepage.getScene());
				stage.show()
;			}
			UpdatePage updatePage = new UpdatePage(stage);
			stage.setScene(updatePage.getScene());
			stage.show();
		});
		
		deleteButton.setOnAction(event -> {
			DeletePage deletePage = new DeletePage(stage);
			stage.setScene(deletePage.getScene());
			stage.show();
		});
	}
	
	private void setLayout() {
		gridPane.add(welcomeLabel, 0, 0);
		gridPane.add(productTable, 0, 1);
		gridPane.add(deleteButton, 0, 2);
		gridPane.add(insertButton, 1, 2);
		gridPane.add(updateButton, 2, 2);
		borderPane.setCenter(gridPane);
	}
}