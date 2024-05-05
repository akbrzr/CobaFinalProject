package view;

import java.util.List;

import controller.ProductController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
		this.scene = new Scene(borderPane, 1000, 550);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
	private void initialize() {
		List<Product> products = ProductController.getAllProduct();
		listProducts = FXCollections.observableArrayList(products);
		borderPane = new BorderPane();
		gridPane = new GridPane();
		welcomeLabel = new Label("Database PT Pudding");
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
		
		double columnWidth = 200;
	    kodeCol.setPrefWidth(columnWidth);
	    namaCol.setPrefWidth(columnWidth);
	    hargaCol.setPrefWidth(columnWidth);
	    stokCol.setPrefWidth(columnWidth);
		
		productTable.setRowFactory(tv -> new TableRow<Product>() {
	        @Override
	        protected void updateItem(Product item, boolean empty) {
	            super.updateItem(item, empty);
	            if (getIndex() % 2 == 0) {
	                setStyle("-fx-background-color: #FFFFFF;");
	            } else {
	                setStyle("-fx-background-color: #E8E8E8;");
	            }
	        }
	    });
		
		insertButton = new Button("Insert Product");
		updateButton = new Button("Update Product");
		deleteButton = new Button("Delete Product");
		
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

	    HBox buttonBox = new HBox(10);
	    buttonBox.setAlignment(Pos.CENTER);

	    buttonBox.getChildren().addAll(insertButton, updateButton, deleteButton);

	    gridPane.add(buttonBox, 0, 2);

	    borderPane.setStyle("-fx-background-color: #A5A7B6;");
	    welcomeLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: #333333;");
	    insertButton.setStyle("-fx-background-color: #76E7A7; -fx-text-fill: black; -fx-font-weight: bold; -fx-padding: 10px 20px;");
	    updateButton.setStyle("-fx-background-color: #ffd966; -fx-text-fill: black; -fx-font-weight: bold; -fx-padding: 10px 20px;");
	    deleteButton.setStyle("-fx-background-color: #FF6F6F; -fx-text-fill: black; -fx-font-weight: bold; -fx-padding: 10px 20px;");

	    borderPane.setCenter(gridPane);

	    gridPane.setAlignment(Pos.TOP_CENTER);
	    
	    gridPane.setVgap(15);
	}

}