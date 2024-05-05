package view;

import java.util.List;

import controller.ProductController;
import javafx.collections.FXCollections;
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
	private Label namaLabel;
	private Button deleteButton;
	private ComboBox<String> namaComboBox;
	
	public DeletePage(Stage stage) {
		this.stage = stage;
		init();
		setLayout();
		scene = new Scene(borderPane, 400, 400);
	}
	
	private void init() {
		borderPane = new BorderPane();
		gridPane = new GridPane();
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
		gridPane.add(namaLabel, 0, 0);
		gridPane.add(namaComboBox, 1, 0);
		gridPane.add(deleteButton, 0, 4);
		borderPane.setCenter(gridPane);
	}
	
	public Scene getScene() {
		return this.scene;
	}
}