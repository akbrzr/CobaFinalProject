package view;

import java.util.List;

import controller.ProductController;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DeletePage {
	private Stage stage;
	private Scene scene;
	private BorderPane borderPane;
	private GridPane gridPane;
	private Label footerLabel, namaLabel;
	private Button deleteButton;
	private ComboBox<String> namaComboBox;
	
	public DeletePage(Stage stage) {
		this.stage = stage;
		init();
		setLayout();
		scene = new Scene(borderPane, 400, 180);
	}
	
	private void init() {
		borderPane = new BorderPane();
		gridPane = new GridPane();
		footerLabel = new Label("Delete Page");
		namaLabel = new Label("Nama");
		namaComboBox = new ComboBox<>();
		deleteButton = new Button("Delete Product");
		List<String> products = ProductController.getAllProductNama();
		namaComboBox.setItems(FXCollections.observableArrayList(products));
		deleteButton.setOnAction(event -> {
			String selectedNama = namaComboBox.getValue();
			boolean updated = ProductController.DeleteProduct(selectedNama);
			
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
		gridPane.add(deleteButton, 0, 4);
		
		namaLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
		
		borderPane.setStyle("-fx-background-color: #A5A7B6;");
		footerLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #333333;");
		deleteButton.setStyle("-fx-background-color: #FF6F6F; -fx-text-fill: black; -fx-font-weight: bold; -fx-padding: 10px 20px;");
		
		borderPane.setCenter(gridPane);
		
		gridPane.setAlignment(Pos.TOP_CENTER);
		
		gridPane.setHgap(20);
		gridPane.setVgap(10);
	}
	
	public Scene getScene() {
		return this.scene;
	}
}