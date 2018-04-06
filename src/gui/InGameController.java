package gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InGameController extends Application {

	@FXML
	private ImageView c1;
	private GuiController main;

	private Stage primaryStage;
	private AnchorPane root;

	public InGameController() {
		this.main = new GuiController();
	}

	public void setc1(ImageView c) {
		this.c1 = c;
		Image jcc = new Image(getClass().getResource("/Jclubs.png").toExternalForm());
		c1.setImage(jcc);
		System.out.println(c1);
	}

	public void setMouseEvents() {
		System.out.println("bla");
		c1.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
			System.out.println("entered");
		});
	}

	public void click() {
		System.out.println("bla");
	}

	// public void show(){
	// try {
	//
	// Image jcc = new
	// Image(getClass().getResource("/Jclubs.png").toExternalForm());
	//
	// c1 = new ImageView(jcc);
	// c1.setFitHeight(227);
	// c1.setFitWidth(182);
	// c1.setLayoutX(125);
	// c1.setLayoutY(527);
	// c1.setRotate(-17.4);
	// c1.setId("c1");
	//
	// root.getChildren().add(c1);
	//
	// Scene scene = new Scene(root);
	// primaryStage.setScene(scene);
	// primaryStage.show();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.primaryStage = primaryStage;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
